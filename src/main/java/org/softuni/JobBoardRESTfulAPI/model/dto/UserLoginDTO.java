package org.softuni.JobBoardRESTfulAPI.model.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class UserLoginDTO {

    @NotEmpty
    @Size(min = 4, max = 20)
    private String username;
    @NotEmpty
    @Size(min = 4)
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
