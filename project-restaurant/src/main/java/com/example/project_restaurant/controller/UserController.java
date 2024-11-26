package com.example.project_restaurant.controller;

import com.example.project_restaurant.payload.ResponeData;
import com.example.project_restaurant.payload.request.LoginRequest;
import com.example.project_restaurant.payload.request.SignUpRequest;
import com.example.project_restaurant.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser() {
       try {
           ResponeData responeData = new ResponeData();
           if(userService.getAllUsers().isEmpty()){
               responeData.setMessage("Không có người dùng nào!");
               return new ResponseEntity<>(responeData , HttpStatus.BAD_REQUEST);
           }
           responeData.setMessage("Get successs!");
           responeData.setData(userService.getAllUsers());
           return new ResponseEntity<>(responeData , HttpStatus.OK);
       }catch (Exception e){
           return new ResponseEntity<>( e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") int id) {
        try {
            ResponeData responeData = new ResponeData();
            if(userService.getUserById(id) == null){
                responeData.setMessage("Không có tìm thấy người dùng id " + id);
                return new ResponseEntity<>(responeData , HttpStatus.BAD_REQUEST);
            }
            responeData.setMessage("Get successs!");
            responeData.setData(userService.getUserById(id));
            return new ResponseEntity<>(responeData , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("Hello");
        try {
            ResponeData responeData = new ResponeData();
            boolean isSuccess = userService.createUser(signUpRequest);
            if(isSuccess){
                responeData.setMessage("Đăng kí thành công!");
                responeData.setData(signUpRequest);
                return new ResponseEntity<>(responeData , HttpStatus.CREATED);
            }
            responeData.setMessage("Email đã tồn tại!");
            return new ResponseEntity<>(responeData , HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>( e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        ResponeData responeData = new ResponeData();
        try {
            boolean isSuccess = userService.loginUser(loginRequest);
            if(isSuccess){
                responeData.setMessage("Login sucesss");
                return new ResponseEntity<>(responeData , HttpStatus.OK);
            }
            responeData.setMessage("Login fail");
            return new ResponseEntity<>(responeData , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(responeData , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
