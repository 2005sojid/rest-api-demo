package com.example.restapidemo.dto;

import com.example.restapidemo.model.Role;
import com.example.restapidemo.model.Status;
import com.example.restapidemo.model.User;
import com.example.restapidemo.repo.UserRepo;
import com.example.restapidemo.rest.AdminRestControllerV1;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AdminDto {

    static UserRepo userRepo = AdminRestControllerV1.globalUserRepo;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    private static PasswordEncoder passwordEncoder = passwordEncoder();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<RoleAdminDto> roles;
    private String status;
    private Date created;
    private Date updated;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FriendDto> friendTo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FriendDto> myFriends;

    public static AdminDto fromAdmin(User user) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(user.getId());
        adminDto.setUsername(user.getUsername());
        adminDto.setRoles(roleAdminDtos(user.getRoles()));
        adminDto.setFirstName(user.getFirstName());
        adminDto.setLastName(user.getLastName());
        adminDto.setFriendTo(FriendDto.getFriendsList(user.getFriends()));
        adminDto.setMyFriends(FriendDto.getFriendsList(user.getMyFriends()));
        adminDto.setStatus(String.valueOf(user.getStatus()));
        adminDto.setCreated(user.getCreatedDate());
        adminDto.setUpdated(user.getUpdatedDate());
        return adminDto;
    }

    public static User toUser(AdminDto adminDto) {
        User user = new User();
        User userFromDB = null;
        if (adminDto.getId() != null){
            user.setId(adminDto.getId());
            userFromDB = userRepo.findById(adminDto.getId()).orElseThrow();
        }
        if (adminDto.getUsername() == null) {
            user.setUsername(userFromDB.getUsername());
        } else {
            user.setUsername(adminDto.getUsername());
        }

        if (adminDto.getPassword() == null) {
            user.setPassword(userFromDB.getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        }
        if (adminDto.getFirstName() == null) {
            user.setFirstName(userFromDB.getFirstName());
        } else {
            user.setFirstName(adminDto.getFirstName());
        }
        if (adminDto.getStatus() == null){
            user.setStatus(userFromDB.getStatus());
        }
        else {
            user.setStatus(Status.valueOf(adminDto.getStatus()));
        }
        if (adminDto.getLastName() == null) {
            user.setLastName(userFromDB.getLastName());
        } else {
            user.setLastName(adminDto.getLastName());
        }

        if (adminDto.getMyFriends() == null){
            user.setMyFriends(userFromDB.getMyFriends());
        }
        else {
            user.setMyFriends(FriendDto.users(adminDto.getMyFriends()));
        }

        if (adminDto.getFriendTo() == null){
            user.setFriends(userFromDB.getFriends());
        }
        else {
            user.setFriends(FriendDto.users(adminDto.getFriendTo()));
        }

        if (adminDto.getRoles() == null) {
            user.setRoles(userFromDB.getRoles());
        } else {
            user.setRoles(roles(adminDto.getRoles()));
        }
        if (adminDto.getCreated() != null){
            user.setCreatedDate(userFromDB.getCreatedDate());
        }
        return user;
    }

    public static List<RoleAdminDto> roleAdminDtos(List<Role> roles) {
        List<RoleAdminDto> roleAdminDtos = new ArrayList<>();
        for (Role role :
                roles) {
            roleAdminDtos.add(RoleAdminDto.fromRole(role));
        }
        return roleAdminDtos;
    }

    public static List<Role> roles(List<RoleAdminDto> roles) {
        List<Role> roleList = new ArrayList<>();
        roles.forEach(roleAdminDto -> roleList.add(RoleAdminDto.toRole(roleAdminDto)));
        return roleList;
    }


}
