package org.softuni.JobBoardRESTfulAPI.model.dto;

import lombok.Getter;
import org.softuni.JobBoardRESTfulAPI.model.validation.FieldMatch;
import org.softuni.JobBoardRESTfulAPI.model.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match."
)
public class UserRegisterDTO {

    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;
    @NotEmpty
    @Size(min = 4)
    private String password;
    private String confirmPassword;
    @NotNull
    private String role;
    @UniqueUsername(message = "Username occupied!")
    @NotEmpty
    @Size(min = 4, max = 20)
    private String username;

    private LocalDate dateOfBirth;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public UserRegisterDTO setRole(String role) {
        this.role = role;
        return this;
    }
}
