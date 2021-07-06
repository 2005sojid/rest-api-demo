package com.example.restapidemo.rest;

import com.example.restapidemo.dto.UserDto;
import com.example.restapidemo.model.Status;
import com.example.restapidemo.model.User;
import com.example.restapidemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestControllerV1 {
    private final UserRepo userRepo;

    @Autowired
    public UserRestControllerV1(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<UserDto> getUsers(){
        List<User> users = userRepo.findAll();
        List<UserDto> userDtoList  = new ArrayList<>();
        for (User user:
             users) {
            userDtoList.add(UserDto.fromUser(user));
        }
        return userDtoList;
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDto.fromUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setStatus(Status.DELETED);
        userRepo.save(user);
    }
}
