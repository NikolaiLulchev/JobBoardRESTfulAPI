package org.softuni.JobBoardRESTfulAPI.service;

import lombok.AllArgsConstructor;
import org.softuni.JobBoardRESTfulAPI.model.entity.TechStackEntity;
import org.softuni.JobBoardRESTfulAPI.model.enums.TechStackEnum;
import org.softuni.JobBoardRESTfulAPI.repository.TechStackRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class TechStackService {

    private final TechStackRepository techStackRepository;

    public List<TechStackEntity> allTechStack() {
        return techStackRepository.findAll();
    }

    public void initializeTechStack() {
        if (techStackRepository.count() == 0) {
            Arrays.stream(TechStackEnum.values()).map(t -> {
                TechStackEntity techStack = new TechStackEntity();
                techStack.setTechStack(t);
                return techStack;
            }).forEach(techStackRepository::save);
        }
    }
}
