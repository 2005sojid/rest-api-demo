package com.example.restapidemo.dto;

import com.example.restapidemo.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FriendDto {
    private String username;
    private Long id;

    public static FriendDto fromUser(User user){
        FriendDto friendDto = new FriendDto();
        friendDto.setId(user.getId());
        friendDto.setUsername(user.getUsername());
        return friendDto;
    }
    public static User toUser(FriendDto friendDto){
        User user = new User();
        user.setId(friendDto.getId());
        user.setUsername(friendDto.getUsername());
        return user;
    }

    public static List<FriendDto> getFriendsList(List<User> users){
        List<FriendDto> friends = new ArrayList<>();
        users.forEach(user -> friends.add(FriendDto.fromUser(user)));
        return friends;
    }
    public static List<User> users(List<FriendDto> friends){
        List<User> users = new ArrayList<>();
        friends.forEach(friendDto -> users.add(FriendDto.toUser(friendDto)));
        return users;
    }
}
