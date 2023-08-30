package org.softuni.JobBoardRESTfulAPI.repository;

import org.softuni.JobBoardRESTfulAPI.model.entity.TechStackEntity;
import org.softuni.JobBoardRESTfulAPI.model.enums.TechStackEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechStackRepository extends JpaRepository<TechStackEntity, Long> {

    TechStackEntity findByTechStack(TechStackEnum techStackEnum);
}
