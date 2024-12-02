package com.example.project_restaurant.service.Imp;

import com.example.project_restaurant.dto.UserDTO;
import com.example.project_restaurant.entity.Users;
import com.example.project_restaurant.payload.request.LoginRequest;
import com.example.project_restaurant.payload.request.SignUpRequest;
import org.apache.catalina.User;

import java.util.List;

public interface UserImp {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(int userId);
    boolean createUser(SignUpRequest signUpRequest);
    UserDTO loginUser(LoginRequest loginRequest);
}
