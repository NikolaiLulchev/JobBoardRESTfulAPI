package org.softuni.JobBoardRESTfulAPI.repository;

import org.softuni.JobBoardRESTfulAPI.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    List<OfferEntity> findAllByOrderByAddedOnDesc();

    List<OfferEntity> findByAddedOnBefore(LocalDateTime addedOn);
}
