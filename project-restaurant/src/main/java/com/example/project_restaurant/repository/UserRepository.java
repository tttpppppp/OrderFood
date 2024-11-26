package com.example.project_restaurant.repository;

import com.example.project_restaurant.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserId(int userId);
    Users findByEmail(String email);
    Users findByEmailAndPassword(String email , String passWord);
}
