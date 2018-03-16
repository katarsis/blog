package com.kapetingi.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRegistration {

    private String userName;
    private String password;
    private String passwordConfirmation;

}
