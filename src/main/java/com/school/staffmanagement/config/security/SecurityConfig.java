package com.school.staffmanagement.config.security;

import com.school.staffmanagement.config.security.jwt.JwtUtils;
import com.school.staffmanagement.config.security.jwt.JwtValidator;
import com.school.staffmanagement.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    // CONFIG SIN ANOTACIONES
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                // .cors(withDefaults())
                //.httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // EP PUBLICOS
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // EP PRIVADOS
                    // http.requestMatchers(HttpMethod.GET, "/auth/hello/user").hasRole("user");
                    // http.requestMatchers(HttpMethod.GET, "/test/get").hasRole("admin");
                    http.requestMatchers(HttpMethod.GET, "/test/get").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.GET, "/test/admin").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/test/post").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.DELETE, "/test/delete").hasAuthority("DELETE");
                    http.requestMatchers(HttpMethod.PUT, "/test/put").hasAuthority("UPDATE");

                    // EP NO ESPECIFICADOS
                    // http.anyRequest().authenticated(); // Los usuarios deben estar autenticados
                    http.anyRequest().denyAll(); // Se rechaza cualquier solicitud a cualquier EP sin importar si está autenticado
                })
//                .formLogin(form -> form.loginPage("/auth/login"))
                .addFilterBefore(new JwtValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return NoOpPasswordEncoder.getInstance(); // solo para pruebas
        return new BCryptPasswordEncoder();
    }
}
