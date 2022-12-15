package org.softuni.JobBoardRESTfulAPI.service;

import org.modelmapper.ModelMapper;
import org.softuni.JobBoardRESTfulAPI.model.dto.OfferAddDTO;
import org.softuni.JobBoardRESTfulAPI.model.entity.CompanyEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.OfferEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;
import org.softuni.JobBoardRESTfulAPI.model.view.OfferViewModel;
import org.softuni.JobBoardRESTfulAPI.repository.OfferRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
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

    public void postOffer(Principal principal, OfferAddDTO offerModel) {

        UserEntity user = userService.getUser(principal.getName());
        CompanyEntity company = companyService.findCompany(user);

        OfferEntity offer = modelMapper.map(offerModel, OfferEntity.class);

        offer.setTechStack(userService.getTechStackEntityList(offerModel.getTechStack()));
        offer.setUser(user);
        offer.setAddedOn(LocalDateTime.now());
        if (company != null) {
            offer.setCompany(company);
        }
        offer.getCompany().getUsers().add(user);

        offerRepository.save(offer);
    }

    public List<OfferViewModel> getAllOffers() {
        return offerRepository.findAllByOrderByAddedOnDesc().stream()
                .map(offerEntity -> modelMapper.map(offerEntity, OfferViewModel.class)).collect(Collectors.toList());
    }

    public OfferEntity getOfferById(Long id) {
        return offerRepository.getById(id);
    }

    public void proceedOfferTask() {
        LocalDateTime addedOn = LocalDateTime.now().minusDays(14);
        offerRepository.findByAddedOnBefore(addedOn).
                forEach((o -> o.setActive(false)));
    }
}
