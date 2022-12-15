package org.softuni.JobBoardRESTfulAPI.model.entity;


import org.softuni.JobBoardRESTfulAPI.model.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEntity(UserRoleEnum userRoleEnum) {
    }

    public UserRoleEntity() {

    }

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRoleEntity setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleEntity)) return false;
        if (!super.equals(o)) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return getRole() == that.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }

    @Override
    public String toString() {
        return role.name();
    }
}
