package com.auction.authorization.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterationRequest {
    
    private String firstName;

    private String lastName;

    private String email;

    private String userName;

    private String password;

    private String phone;

    private String address;
}
