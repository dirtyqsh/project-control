package com.dirtyqsh.laba7_mispis.service;

import com.dirtyqsh.laba7_mispis.model.WorkSubcontractor;
import com.dirtyqsh.laba7_mispis.repo.ProjectRepository;
import com.dirtyqsh.laba7_mispis.repo.SubcontractorRepository;
import com.dirtyqsh.laba7_mispis.repo.WorkSubcontractorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkSubcontractorService {
    private final WorkSubcontractorRepository workSubcontractorRepository;
    private final ProjectRepository projectRepository;
    private final SubcontractorRepository subcontractorRepository;

    public WorkSubcontractorService(WorkSubcontractorRepository workSubcontractorRepository,
                                    ProjectRepository projectRepository,
                                    SubcontractorRepository subcontractorRepository) {
        this.workSubcontractorRepository = workSubcontractorRepository;
        this.projectRepository = projectRepository;
        this.subcontractorRepository = subcontractorRepository;
    }

    public WorkSubcontractor createWorkSubcontractor(WorkSubcontractor workSubcontractor) {
        if (!projectRepository.existsById(workSubcontractor.getProject().getId())) {
            throw new IllegalArgumentException("Проект ID " + workSubcontractor.getProject().getId() + " не найден");
        }
        if (!subcontractorRepository.existsById(workSubcontractor.getSubcontractor().getId())) {
            throw new IllegalArgumentException("Подрядчик ID " + workSubcontractor.getSubcontractor().getId() + " не найден");
        }
        return workSubcontractorRepository.save(workSubcontractor);
    }

    public WorkSubcontractor updateWorkSubcontractor(int id, WorkSubcontractor updatedWorkSubcontractor) {
        WorkSubcontractor existing = workSubcontractorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Работа подрядчика ID " + id + " не найдена"));

        if (!projectRepository.existsById(updatedWorkSubcontractor.getProject().getId())) {
            throw new IllegalArgumentException("Проект ID " + updatedWorkSubcontractor.getProject().getId() + " не найден");
        }
        if (!subcontractorRepository.existsById(updatedWorkSubcontractor.getSubcontractor().getId())) {
            throw new IllegalArgumentException("Подрядчик ID " + updatedWorkSubcontractor.getSubcontractor().getId() + " не найден");
        }

        existing.setCost(updatedWorkSubcontractor.getCost());
        existing.setStartDate(updatedWorkSubcontractor.getStartDate());
        existing.setEndDate(updatedWorkSubcontractor.getEndDate());
        existing.setStatus(updatedWorkSubcontractor.isStatus());
        existing.setProject(updatedWorkSubcontractor.getProject());
        existing.setSubcontractor(updatedWorkSubcontractor.getSubcontractor());

        return workSubcontractorRepository.save(existing);
    }

    public void deleteWorkSubcontractor(int id) {
        workSubcontractorRepository.deleteById(id);
    }

    public Optional<WorkSubcontractor> getWorkSubcontractor(int id) {
        return workSubcontractorRepository.findById(id);
    }
}
