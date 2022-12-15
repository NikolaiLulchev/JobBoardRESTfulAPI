package org.softuni.JobBoardRESTfulAPI.model.dto;

import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class OfferAddDTO {

    private UserEntity user;

    private String position;

    private String title;

    private String location;

    private String description;

    private LocalDateTime addedOn;

    private boolean isActive;

    private String level;

    private List<String> techStack;

    private String companyName;

    public OfferAddDTO() {
    }

    public UserEntity getUser() {
        return user;
    }

    public OfferAddDTO setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public OfferAddDTO setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public OfferAddDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public OfferAddDTO setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public OfferAddDTO setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public OfferAddDTO setActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public OfferAddDTO setLevel(String level) {
        this.level = level;
        return this;
    }

    public List<String> getTechStack() {
        return techStack;
    }

    public OfferAddDTO setTechStack(List<String> techStack) {
        this.techStack = techStack;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public OfferAddDTO setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
}
