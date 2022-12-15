package org.softuni.JobBoardRESTfulAPI.model.entity;

import org.softuni.JobBoardRESTfulAPI.model.enums.TechStackEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "tech_stack")
public class TechStackEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TechStackEnum techStack;

    public TechStackEntity(TechStackEnum techStackEnum) {
    }

    public TechStackEntity() {

    }


    public TechStackEnum getTechStack() {
        return techStack;
    }

    public TechStackEntity setTechStack(TechStackEnum techStack) {
        this.techStack = techStack;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return this.techStack == null ? "" : this.techStack.name();
    }
}
