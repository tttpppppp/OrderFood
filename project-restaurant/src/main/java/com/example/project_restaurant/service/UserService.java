package com.example.project_restaurant.service;

import com.example.project_restaurant.dto.UserDTO;
import com.example.project_restaurant.entity.Roles;
import com.example.project_restaurant.entity.Users;
import com.example.project_restaurant.payload.request.LoginRequest;
import com.example.project_restaurant.payload.request.SignUpRequest;
import com.example.project_restaurant.repository.UserRepository;
import com.example.project_restaurant.service.Imp.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements UserImp {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAll();
        List<UserDTO> listUserDTO = new ArrayList<>();
        for (Users user : users) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            userDTO.setEmail(user.getEmail());
            userDTO.setFullName(user.getFullName());
            userDTO.setPassword(user.getPassword());
            userDTO.setRolename(user.getRoles().getRoleName());
            listUserDTO.add(userDTO);
        }
        return listUserDTO;
    }

    @Override
    public UserDTO getUserById(int userId) {
        Users user = userRepository.findByUserId(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRolename(user.getRoles().getRoleName());
        return userDTO;
    }

    @Override
    public boolean createUser(SignUpRequest signUpRequest) {
        if(userRepository.findByEmail(signUpRequest.getEmail()) != null){
            return false;
        }
        Users user = new Users();
        Roles role = new Roles();
        role.setRoleId(1);
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setFullName(signUpRequest.getFullname());
        user.setCreateDate(new Date());
        user.setRoles(role);
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public UserDTO loginUser(LoginRequest loginRequest) {
        Users user = userRepository.findByEmailAndPassword(loginRequest.getEmail() , loginRequest.getPassword());
        if(user != null){
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId());
            return userDTO;
        }
        return null;
    }
}
