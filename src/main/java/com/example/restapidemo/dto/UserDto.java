package com.example.restapidemo.dto;

import com.example.restapidemo.model.Role;
import com.example.restapidemo.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private List<RoleUserDto> roles;


    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(roleUserDtoList(user.getRoles()));
        return userDto;
    }

    public static List<RoleUserDto> roleUserDtoList(List<Role> roles){
        List<RoleUserDto> roleUserDtoList = new ArrayList<>();
        roles.forEach(role -> roleUserDtoList.add(RoleUserDto.fromRole(role)));
        return roleUserDtoList;
    }

}
