package com.example.restapidemo.rest;

import com.example.restapidemo.dto.AdminDto;
import com.example.restapidemo.model.User;
import com.example.restapidemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestControllerV1 {
    private final UserRepo userRepo;

    public static UserRepo globalUserRepo;
    @Autowired
    public AdminRestControllerV1(UserRepo userRepo, UserRepo userRepo2) {
        this.userRepo = userRepo;
        globalUserRepo = userRepo2;
    }




    @GetMapping("/users")
    public List<AdminDto> getAdminUsers(){
        List<AdminDto> adminDtoList = new ArrayList<>();
        List<User> users = userRepo.findAll();
        for (User user:
             users) {
                adminDtoList.add(AdminDto.fromAdmin(user));
        }
        return adminDtoList;
    }


    @GetMapping("/users/{id}")
    public AdminDto getUser(@PathVariable("id") Long id){
        User userFromDB = userRepo.findById(id).orElse(null);
        if (userFromDB == null){
            throw new UsernameNotFoundException("User with " + id + " not found");
        }
        return AdminDto.fromAdmin(userFromDB);
    }


    @PostMapping("/users")
    public AdminDto createUser(@RequestBody AdminDto adminDto){
        if (adminDto == null){
            throw new UsernameNotFoundException("User shouldn't be null");
        }
        User user = AdminDto.toUser(adminDto);
        Date date = new Date();
        user.setCreatedDate(date);
        user.setUpdatedDate(date);
        userRepo.save(user);
        return AdminDto.fromAdmin(user);
    }

    @PutMapping("/users/{id}")
    public AdminDto updateUser(@PathVariable Long id, @RequestBody AdminDto adminDto){
        User userFromDB = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("User with id: %s not found", id)));
        if (adminDto == null){
            throw new UsernameNotFoundException("User shouldn't be null");
        }
        adminDto.setId(id);
        adminDto.setCreated(userFromDB.getCreatedDate());
        User newUser = AdminDto.toUser(adminDto);
        Date date = new Date();
        newUser.setUpdatedDate(date);
        userRepo.save(newUser);
        return AdminDto.fromAdmin(newUser);
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        userRepo.deleteById(id);
    }
}
