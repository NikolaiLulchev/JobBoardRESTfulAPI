package org.softuni.JobBoardRESTfulAPI.model.view;

import lombok.Data;
import org.softuni.JobBoardRESTfulAPI.model.entity.CompanyEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OfferViewModel {

    private Long id;

    private String username;

    private String title;

    private String location;

    private String position;

    private String description;

    private LocalDateTime addedOn;

    private boolean isActive;

    private String level;

    private List<String> techStack;

    private CompanyEntity company;

}
