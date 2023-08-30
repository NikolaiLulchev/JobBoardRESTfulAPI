package org.softuni.JobBoardRESTfulAPI.service;

import org.modelmapper.ModelMapper;
import org.softuni.JobBoardRESTfulAPI.model.dto.OfferAddDTO;
import org.softuni.JobBoardRESTfulAPI.model.entity.CompanyEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.OfferEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.TechStackEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;
import org.softuni.JobBoardRESTfulAPI.model.enums.LevelEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.LocationEnum;
import org.softuni.JobBoardRESTfulAPI.model.enums.PositionEnum;
import org.softuni.JobBoardRESTfulAPI.model.view.OfferViewModel;
import org.softuni.JobBoardRESTfulAPI.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final ModelMapper modelMapper;
    private final OfferRepository offerRepository;
    private final UserService userService;
    private final CompanyService companyService;

    public OfferService(ModelMapper modelMapper, OfferRepository offerRepository, UserService userService, CompanyService companyService) {
        this.modelMapper = modelMapper;
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.companyService = companyService;
    }

    public void postOffer(OfferAddDTO offerModel) {
        UserEntity user = userService.getUser(offerModel.getUsername());

        CompanyEntity company = companyService.findCompanyByUser(user);

        if (company == null) {
            // Try to find the company by name
            company = companyService.findByName(offerModel.getCompanyName());

            if (company == null) {
                // If company doesn't exist, create it and associate with the user
                company = companyService.addCompany(offerModel.getCompanyName(), user);
            } else {
                // If company exists but is not associated with the user, add the user to the company
                companyService.addUserToCompany(company, user);
            }
        }

        // Create the offer entity and set its properties

        OfferEntity offer = modelMapper.map(offerModel, OfferEntity.class);
        offer.setUser(user);
        offer.setAddedOn(LocalDateTime.now());
        offer.setLocation(LocationEnum.valueOf(offerModel.getLocation()));

        // Map tech stack and associate the offer with the company
        List<TechStackEntity> techStackList = userService.getTechStackEntityList(offerModel.getTechStack());
        offer.setTechStack(techStackList);
        offer.setCompany(company);

        // Save the offer

        offerRepository.save(offer);
    }

    public List<OfferViewModel> getAllOffers(String location, String position, String level) {
        Predicate<OfferEntity> filterByLocation = offer -> {
            if (location == null || location.isBlank()) {
                return true;
            }
            return offer.getLocation() == LocationEnum.valueOf(location);
        };
        Predicate<OfferEntity> filterByPosition = offer -> {
            if (position == null || position.isBlank()) {
                return true;
            }
            return offer.getPosition() == PositionEnum.valueOf(position);
        };
        Predicate<OfferEntity> filterByLevel = offer -> {
            if (level == null || level.isBlank()) {
                return true;
            }
            return offer.getLevel() == LevelEnum.valueOf(level);
        };

        Predicate<OfferEntity> filter = filterByLocation;

        if (position != null && !position.isBlank()) {
            filter = filter.and(filterByPosition);
        }

        if (level != null && !level.isBlank()) {
            filter = filter.and(filterByLevel);
        }

        return offerRepository.findAllByOrderByAddedOnDesc().stream()
                .filter(filter)
                .map(offerEntity -> {
                    OfferViewModel offerViewModel = modelMapper.map(offerEntity, OfferViewModel.class);
                    offerViewModel.setUsername(offerEntity.getUser().getUsername());
                    offerViewModel.setCompany(offerEntity.getCompany().getName());
                    return offerViewModel;
                })
                .collect(Collectors.toList());

    }

    public OfferEntity getOfferById(Long id) {
        return offerRepository.getReferenceById(id);
    }

    public void proceedOfferTask() {
        LocalDateTime addedOn = LocalDateTime.now().minusDays(14);
        offerRepository.findByAddedOnBefore(addedOn).
                forEach((o -> o.setActive(false)));
    }
}
