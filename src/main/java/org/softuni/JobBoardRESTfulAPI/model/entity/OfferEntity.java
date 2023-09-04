package org.softuni.JobBoardRESTfulAPI.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private CompanyEntity company;

}


