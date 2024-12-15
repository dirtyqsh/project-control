package com.dirtyqsh.laba7_mispis.controller;

import com.dirtyqsh.laba7_mispis.model.Project;
import com.dirtyqsh.laba7_mispis.model.WorkSubcontractor;
import com.dirtyqsh.laba7_mispis.repo.ProjectRepository;
import com.dirtyqsh.laba7_mispis.service.WorkSubcontractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String getProjects(Model model) {
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
        return "project";
    }

    @PostMapping("/add")
    public String addProject(@ModelAttribute Project project) {
        project.setCost((long) (project.getCost() * 100));
        projectRepository.save(project);
        return "redirect:/project";
    }

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable Integer id) {
        projectRepository.deleteById(id);
        return "redirect:/project";
    }

    @GetMapping("/edit/{id}")
    public String editProject(@PathVariable Integer id, Model model) {
        Project project = projectRepository.findById(id).orElseThrow();
        model.addAttribute("project", project);
        return "edit-project";
    }

    @PostMapping("/update")
    public String updateProject(@ModelAttribute Project project) {
        project.setCost((long) (project.getCost() * 100));
        projectRepository.save(project);
        return "redirect:/project";
    }
}
