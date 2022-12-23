package org.softuni.JobBoardRESTfulAPI.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.softuni.JobBoardRESTfulAPI.model.enums.LevelEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.LocationEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.PositionEnum;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private PositionEnum position;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private LocationEnum location;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String description;

    @Column(nullable = false)
    private LocalDateTime addedOn;

    @Column(nullable = false)
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    private LevelEnum level;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TechStackEntity> techStack = new ArrayList<>();

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private CompanyEntity company;

    public OfferEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public OfferEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public OfferEntity setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public OfferEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public LocationEnum getLocation() {
        return location;
    }

    public OfferEntity setLocation(LocationEnum location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public OfferEntity setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public OfferEntity setActive(boolean active) {
        isActive = active;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public OfferEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public List<TechStackEntity> getTechStack() {
        return techStack;
    }

    public OfferEntity setTechStack(List<TechStackEntity> techStack) {
        this.techStack = techStack;
        return this;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public OfferEntity setCompany(CompanyEntity company) {
        this.company = company;
        return this;
    }
}


