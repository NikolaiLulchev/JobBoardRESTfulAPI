package org.softuni.JobBoardRESTfulAPI.web;

import org.softuni.JobBoardRESTfulAPI.model.entity.CompanyEntity;
import org.softuni.JobBoardRESTfulAPI.model.entity.UserEntity;
import org.softuni.JobBoardRESTfulAPI.service.CompanyService;
import org.softuni.JobBoardRESTfulAPI.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
        origins = "http://localhost:4200",
        maxAge = 3600,
        allowCredentials = "true"
)
@RestController
@RequestMapping("api/v1/company")
public class CompanyController {

    private final CompanyService companyService;
    private final UserService userService;

    public CompanyController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getCompany(String username) {
        UserEntity user = userService.getUser(username);
        CompanyEntity company = companyService.findCompanyByUser(user);

        return ResponseEntity.ok(company);
    }

}
