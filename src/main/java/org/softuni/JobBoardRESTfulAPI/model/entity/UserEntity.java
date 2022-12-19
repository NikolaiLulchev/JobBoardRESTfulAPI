package org.softuni.JobBoardRESTfulAPI.model.entity;

import org.softuni.JobBoardRESTfulAPI.model.enums.GenderEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.LevelEnum;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Size(min = 4, max = 20)
    private String username;

    @Column(nullable = false)
    @Size(min = 4)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Transient
    private Integer age;

//    @Column(nullable = false)
    private LocalDate dateOfBirth;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public UserEntity setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> role = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TechStackEntity> techStack = new ArrayList<>();

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String lastName;

    public UserEntity() {
    }


    public List<TechStackEntity> getTechStack() {
        return techStack;
    }

    public UserEntity setTechStack(List<TechStackEntity> techStack) {
        this.techStack = techStack;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public UserEntity setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public Set<UserRoleEntity> getRole() {
        return role;
    }

    public UserEntity setRole(Set<UserRoleEntity> role) {
        this.role = role;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public UserEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
