package com.dirtyqsh.laba7_mispis.controller;

import com.dirtyqsh.laba7_mispis.DTO.ProjectDTO;
import com.dirtyqsh.laba7_mispis.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ProjectService projectService;


    @GetMapping("/")
    public String index(Model model) {
        List<ProjectDTO> projects = projectService.getAllProjects();
        projects.forEach(project -> System.out.println(project.getSubcontractors()));
        model.addAttribute("projects", projects);
        return "index";
    }

    @GetMapping("/subcontractor/{id}")
    public String getProjectsBySubcontractor(@PathVariable("id") int subcontractorId, Model model) {
        List<ProjectDTO> projects = projectService.getProjectsBySubcontractor(subcontractorId);
        model.addAttribute("projects", projects);
        return "subcontractor-projects";
    }


}
