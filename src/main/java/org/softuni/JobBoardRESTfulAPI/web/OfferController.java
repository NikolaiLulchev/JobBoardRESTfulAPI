package org.softuni.JobBoardRESTfulAPI.web;

import org.modelmapper.ModelMapper;
import org.softuni.JobBoardRESTfulAPI.model.dto.OfferAddDTO;
import org.softuni.JobBoardRESTfulAPI.model.entity.OfferEntity;
import org.softuni.JobBoardRESTfulAPI.model.view.OfferViewModel;
import org.softuni.JobBoardRESTfulAPI.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(
        origins = "http://localhost:4200",
        maxAge = 3600,
        allowCredentials = "true"
)
@RestController
@RequestMapping("api/v1/offers")
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<OfferViewModel>> getAllOffers(@RequestParam(required = false) String location,
                                                             @RequestParam(required = false) String position,
                                                             @RequestParam(required = false) String level) {
        List<OfferViewModel> offers = offerService.getAllOffers(location, position, level);
        return ResponseEntity.ok(offers);
    }

//    @GetMapping
//    public ResponseEntity<List<OfferViewModel>> getAllOffers() {
//        List<OfferViewModel> offers = offerService.getAllOffers();
//        return ResponseEntity.ok(offers);
//    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferViewModel> getOfferBiId(@PathVariable Long offerId) {
        OfferEntity offerEntity = offerService.getOfferById(offerId);
        OfferViewModel offer = modelMapper.map(offerEntity,
                OfferViewModel.class);
        return ResponseEntity.ok(offer);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addOffer(@RequestBody @Valid OfferAddDTO offerModel,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        this.offerService.postOffer(offerModel);
        return ResponseEntity.ok(offerModel);
    }
}
