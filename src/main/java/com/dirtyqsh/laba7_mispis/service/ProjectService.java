package com.dirtyqsh.laba7_mispis.service;

import com.dirtyqsh.laba7_mispis.DTO.ProjectDTO;
import com.dirtyqsh.laba7_mispis.model.Project;
import com.dirtyqsh.laba7_mispis.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAllWithSubcontractors();
    }

    public List<ProjectDTO> getProjectsBySubcontractor(int subcontractorId) {
        return projectRepository.findProjectsBySubcontractorId(subcontractorId);
    }


}
