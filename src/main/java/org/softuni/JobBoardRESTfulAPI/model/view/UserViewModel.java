package org.softuni.JobBoardRESTfulAPI.model.view;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

public class UserViewModel {

    private Long id;
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    //    @NotNull
//    private Integer age;
    @NotNull
    private String gender;

    private Set<String> role;
    @NotNull
    private String level;
    @NotNull
    private List<String> techStack;

    public UserViewModel() {
    }

    public UserViewModel(Long id, String username, String firstName, String lastName, String email,
                         Integer age, String gender, Set<String> role,
                         String level, List<String> techStack) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        this.age = age;
        this.gender = gender;
        this.role = role;
        this.level = level;
        this.techStack = techStack;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getGender() {
        return gender;
    }

    public UserViewModel setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public Set<String> getRole() {
        return role;
    }

    public UserViewModel setRole(Set<String> role) {
        this.role = role;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public UserViewModel setLevel(String level) {
        this.level = level;
        return this;
    }

    public List<String> getTechStack() {
        return techStack;
    }

    public UserViewModel setTechStack(List<String> techStack) {
        this.techStack = techStack;
        return this;
    }
}
