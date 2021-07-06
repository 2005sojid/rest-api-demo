package com.example.restapidemo.dto;

import com.example.restapidemo.model.Role;
import lombok.Data;

@Data
public class RoleUserDto {
    private String name;

    public static RoleUserDto fromRole(Role role) {
        RoleUserDto roleUserDto = new RoleUserDto();
        roleUserDto.setName(role.getName());
        return roleUserDto;
    }
}
