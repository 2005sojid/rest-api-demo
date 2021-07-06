package com.example.restapidemo.dto;

import com.example.restapidemo.model.Role;
import com.example.restapidemo.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;
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


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FriendDto> friendTo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<FriendDto> myFriends;

    public static UserDto fromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setFriendTo(FriendDto.getFriendsList(user.getFriends()));
        userDto.setMyFriends(FriendDto.getFriendsList(user.getMyFriends()));
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
