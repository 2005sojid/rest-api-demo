package com.example.restapidemo.dto;
import com.example.restapidemo.model.Role;
import com.example.restapidemo.model.Status;
import lombok.Data;

import java.util.Date;

@Data
public class RoleAdminDto {
    private Long id;
    private String name;
    private Date created;
    private Date updated;
    private Status status;

    public static RoleAdminDto fromRole(Role role){
        RoleAdminDto roleAdminDto = new RoleAdminDto();
        roleAdminDto.setId(role.getId());
        roleAdminDto.setName(role.getName());
        roleAdminDto.setCreated(role.getCreatedDate());
        roleAdminDto.setUpdated(role.getUpdatedDate());
        roleAdminDto.setStatus(role.getStatus());
        return roleAdminDto;
    }

    public static Role toRole(RoleAdminDto roleAdminDto){
        Role role = new Role();
        role.setId(roleAdminDto.getId());
        role.setName(roleAdminDto.getName());
        role.setCreatedDate(roleAdminDto.getCreated());
        role.setUpdatedDate(roleAdminDto.getUpdated());
        role.setStatus(roleAdminDto.getStatus());
        return role;
    }

}
