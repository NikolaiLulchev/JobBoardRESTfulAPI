package org.softuni.JobBoardRESTfulAPI.model.dto;

import org.softuni.JobBoardRESTfulAPI.model.enums.GenderEnum;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

public class UserUpdateDTO {

    private Long id;
    private String username;
    private String password;
    @Email
    @NotEmpty
    private String email;

    @Min(18)
    @Max(110)
    @NotNull
    private Integer age;

    @NotNull
    private GenderEnum gender;

    private Set<String> role;
    @NotEmpty
    private String level;
    @NotNull
    private List<String> techStack;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(Long id, String username, String password, String email,
                         Integer age, GenderEnum gender, Set<String> role, String level,
                         List<String> techStack, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.role = role;
        this.level = level;
        this.techStack = techStack;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public UserUpdateDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserUpdateDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserUpdateDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserUpdateDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserUpdateDTO setAge(Integer age) {
        this.age = age;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserUpdateDTO setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public Set<String> getRole() {
        return role;
    }

    public UserUpdateDTO setRole(Set<String> role) {
        this.role = role;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public UserUpdateDTO setLevel(String level) {
        this.level = level;
        return this;
    }

    public List<String> getTechStack() {
        return techStack;
    }

    public UserUpdateDTO setTechStack(List<String> techStack) {
        this.techStack = techStack;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserUpdateDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserUpdateDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
