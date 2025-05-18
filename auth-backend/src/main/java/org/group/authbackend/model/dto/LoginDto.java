package org.group.authbackend.model.dto;

import lombok.Getter;

@Getter
public class LoginDto {

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String username;
    public String password;
}
