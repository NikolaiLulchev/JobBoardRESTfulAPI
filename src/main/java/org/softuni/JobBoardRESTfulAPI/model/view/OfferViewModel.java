package org.softuni.JobBoardRESTfulAPI.model.view;

import org.softuni.JobBoardRESTfulAPI.model.entity.CompanyEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

public class OfferViewModel {

    private Long id;

    private UserEntity user;

    private String title;

    private String location;

    private String position;

    private String description;

    private LocalDateTime addedOn;

    private boolean isActive;

    private String level;

    private List<String> techStack;

    private CompanyEntity company;

    public OfferViewModel() {
    }

    public Long getId() {
        return id;
    }

    public OfferViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public OfferViewModel setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public OfferViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public OfferViewModel setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public OfferViewModel setActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public OfferViewModel setLevel(String level) {
        this.level = level;
        return this;
    }

    public List<String> getTechStack() {
        return techStack;
    }

    public OfferViewModel setTechStack(List<String> techStack) {
        this.techStack = techStack;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public OfferViewModel setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public OfferViewModel setPosition(String position) {
        this.position = position;
        return this;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public OfferViewModel setCompany(CompanyEntity company) {
        this.company = company;
        return this;
    }
}
