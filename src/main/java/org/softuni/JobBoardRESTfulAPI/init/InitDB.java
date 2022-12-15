package org.softuni.JobBoardRESTfulAPI.init;

import org.softuni.JobBoardRESTfulAPI.service.TechStackService;
import org.softuni.JobBoardRESTfulAPI.service.UserRoleService;
import org.softuni.JobBoardRESTfulAPI.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDB implements CommandLineRunner {
    private final UserService userService;
    private final TechStackService techStackService;
    private final UserRoleService userRoleService;


    public InitDB(UserService userService, TechStackService techStackService, UserRoleService userRoleService) {
        this.userService = userService;
        this.techStackService = techStackService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Initialization of UserRole
        userRoleService.initializeRoles();
        //Initialization of TechStack
        techStackService.initializeTechStack();
        //Initialize admin
        userService.initializeAdminUser();
    }
}