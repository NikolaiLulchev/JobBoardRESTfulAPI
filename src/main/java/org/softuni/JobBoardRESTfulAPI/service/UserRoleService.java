package org.softuni.JobBoardRESTfulAPI.service;

import org.softuni.JobBoardRESTfulAPI.model.entity.UserRoleEntity;
import org.softuni.JobBoardRESTfulAPI.model.enums.UserRoleEnum;
import org.softuni.JobBoardRESTfulAPI.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void initializeRoles() {
        if (userRoleRepository.count() == 0) {
            Arrays.stream(UserRoleEnum.values()).map(r -> {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setRole(r);
                return userRoleEntity;
            }).forEach(userRoleRepository::save);
        }
    }

}
