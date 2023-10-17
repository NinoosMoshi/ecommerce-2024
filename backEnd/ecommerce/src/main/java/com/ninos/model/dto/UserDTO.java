package com.ninos.model.dto;

import lombok.Data;

import com.ninos.model.enums.UserRole;

@Data
public class UserDTO {


    private Long id;
    private String email;
    private String name;
    private UserRole userRole;



}
