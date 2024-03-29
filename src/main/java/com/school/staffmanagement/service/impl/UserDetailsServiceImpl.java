package com.school.staffmanagement.service.impl;


import com.school.staffmanagement.config.security.jwt.JwtUtils;
import com.school.staffmanagement.constants.StaffResponseMessages;
import com.school.staffmanagement.model.dto.request.AuthCreateUserRequest;
import com.school.staffmanagement.model.dto.request.AuthLoginRequest;
import com.school.staffmanagement.model.dto.response.AuthResponse;
import com.school.staffmanagement.model.entity.RoleEntity;
import com.school.staffmanagement.model.entity.UserEntity;
import com.school.staffmanagement.repository.RoleRepository;
import com.school.staffmanagement.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("LoadUserByUsername {}", username);

        UserEntity user = userRepository.findByEmail(username);

        log.info("User found: {}", user);

        if (Objects.isNull(user)){
            throw new UsernameNotFoundException(StaffResponseMessages.userNotFoundMsg(username));
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        user.getRoles()
                .forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))));

        user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNoExpired(),
                user.isCredentialNoExpired(),
                user.isAccountNoLocked(),
                authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
        log.info("Login {}", authLoginRequest);

        String username = authLoginRequest.email();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> roles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .map(role -> role.substring(5))
                .collect(Collectors.toList());

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(username, StaffResponseMessages.USER_LOGIN_OK, roles, accessToken);
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(StaffResponseMessages.BAD_CREDENTIALS);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(StaffResponseMessages.BAD_CREDENTIALS);
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
        String username = authCreateUserRequest.email();
        String password = authCreateUserRequest.password();
        List<String> roles = authCreateUserRequest.roles();

        Set<RoleEntity> roleEntities = roleRepository.findRoleEntitiesByNameIn(roles).stream().collect(Collectors.toSet());

        if (roleEntities.isEmpty()) {
            throw new IllegalArgumentException(StaffResponseMessages.ROLE_IS_REQUIRED);
        }

        UserEntity userEntity = UserEntity.builder()
                .email(username)
                .password(passwordEncoder.encode(password))
                .email(authCreateUserRequest.email())
                .roles(roleEntities)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity userCreated = userRepository.save(userEntity);

        ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userCreated.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName().name()))));

        userCreated.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getPassword(), authorityList);

        List<String> userCreatedRoles = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(authority -> authority.startsWith("ROLE_"))
                .map(role -> role.substring(5))
                .collect(Collectors.toList());

        String accessToken = jwtUtils.createToken(authentication);

        return new AuthResponse(userCreated.getEmail(), StaffResponseMessages.USER_SIGNUP_OK, userCreatedRoles, accessToken);
    }
}
