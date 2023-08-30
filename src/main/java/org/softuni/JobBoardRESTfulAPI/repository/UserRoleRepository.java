package org.softuni.JobBoardRESTfulAPI.repository;

import org.softuni.JobBoardRESTfulAPI.model.entity.UserRoleEntity;
import org.softuni.JobBoardRESTfulAPI.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    
    UserRoleEntity findFirstByRole(UserRoleEnum role);

}
