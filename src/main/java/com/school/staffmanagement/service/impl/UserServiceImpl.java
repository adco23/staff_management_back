package com.school.staffmanagement.service.impl;

import com.school.staffmanagement.constants.StaffMessages;
import com.school.staffmanagement.model.dto.response.ResponseMessage;
import com.school.staffmanagement.model.entity.UserEntity;
import com.school.staffmanagement.repository.UserRepository;
import com.school.staffmanagement.service.UserService;
import com.school.staffmanagement.utils.StaffUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseMessage> signUp(Map<String, String> requestMap) {
        log.info("Registro de usuario {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                UserEntity user = userRepository.findByEmail(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userRepository.save(getUserFromMap(requestMap));

                    return StaffUtil.getResponseEntity(StaffMessages.USER_SAVED, HttpStatus.CREATED);
                } else {
                    return StaffUtil.getResponseEntity(StaffMessages.USER_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
                }
            } else {
                return StaffUtil.getResponseEntity(StaffMessages.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return StaffUtil.getResponseEntity(StaffMessages.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignUpMap(Map<String, String> requestMap){
        return requestMap.containsKey("email") && requestMap.containsKey("password");
    }

    private UserEntity getUserFromMap (Map<String, String> requestMap) {
        UserEntity user = new UserEntity();
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
//        user.setStatus("false");
//        user.setRole("user");

        return user;
    }
}
