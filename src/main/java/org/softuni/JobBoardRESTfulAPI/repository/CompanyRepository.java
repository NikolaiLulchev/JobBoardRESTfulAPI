package org.softuni.JobBoardRESTfulAPI.repository;

import org.softuni.JobBoardRESTfulAPI.model.entity.CompanyEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    CompanyEntity findCompanyEntityByUsers(UserEntity user);

    //CompanyEntity findByName(String companyName);
    CompanyEntity findFirstByName(String companyName);
}
