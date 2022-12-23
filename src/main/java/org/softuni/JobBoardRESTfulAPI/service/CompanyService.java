package org.softuni.JobBoardRESTfulAPI.service;

import org.softuni.JobBoardRESTfulAPI.model.entity.CompanyEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;
import org.softuni.JobBoardRESTfulAPI.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity findCompanyByUser(UserEntity user) {
        return companyRepository.findCompanyEntityByUsers(user);
    }

    public CompanyEntity addCompany(String companyName, UserEntity user) {
        CompanyEntity company =new CompanyEntity();
        company.setName(companyName).getUsers().add(user);
        return companyRepository.save(company);
    }

    public CompanyEntity findByName(String companyName) {
        return companyRepository.findByName(companyName);
    }
}
