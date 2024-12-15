package com.dirtyqsh.laba7_mispis.controller;

import com.dirtyqsh.laba7_mispis.model.Project;
import com.dirtyqsh.laba7_mispis.model.Subcontractor;
import com.dirtyqsh.laba7_mispis.model.WorkSubcontractor;
import com.dirtyqsh.laba7_mispis.repo.ProjectRepository;
import com.dirtyqsh.laba7_mispis.repo.SubcontractorRepository;
import com.dirtyqsh.laba7_mispis.repo.WorkSubcontractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping("/worksubcontractor")
public class WorkSubcontractorController {

    @Autowired
    private WorkSubcontractorRepository workSubcontractorRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SubcontractorRepository subcontractorRepository;

    @GetMapping
    public String listWorkSubcontractors(Model model) {
        model.addAttribute("worksubcontractors", workSubcontractorRepository.findAll());
        return "worksubcontractor";
    }

    @PostMapping("/add")
    public String addWorkSubcontractor(
            @RequestParam double cost,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam boolean status,
            @RequestParam int projectId,
            @RequestParam int subcontractorId,
            Model model) {

        Optional<Project> project = projectRepository.findById(projectId);
        Optional<Subcontractor> subcontractor = subcontractorRepository.findById(subcontractorId);

        if (project.isPresent() && subcontractor.isPresent()) {
            WorkSubcontractor workSubcontractor = new WorkSubcontractor();
            workSubcontractor.setCost((long) (cost * 100)); // Хранение в копейках
            workSubcontractor.setStartDate(LocalDate.parse(startDate)); // Установка startDate
            workSubcontractor.setEndDate(LocalDate.parse(endDate)); // Установка endDate
            workSubcontractor.setStatus(status);
            workSubcontractor.setProject(project.get());
            workSubcontractor.setSubcontractor(subcontractor.get());

            workSubcontractorRepository.save(workSubcontractor);
        } else {
            model.addAttribute("error", "Неверный ID проекта или подрядчика");
        }
        return "redirect:/worksubcontractor";
    }


    @GetMapping("/edit/{id}")
    public String editWorkSubcontractor(@PathVariable int id, Model model) {
        Optional<WorkSubcontractor> workSubcontractor = workSubcontractorRepository.findById(id);
        if (workSubcontractor.isPresent()) {
            model.addAttribute("editWork", workSubcontractor.get());
        } else {
            model.addAttribute("error", "Работа с ID " + id + " не найдена");
        }
        return "worksubcontractor/edit";
    }

    @PostMapping("/update")
    public String updateWorkSubcontractor(
            @RequestParam int id,
            @RequestParam double cost,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam boolean status,
            @RequestParam int projectId,
            @RequestParam int subcontractorId,
            Model model) {
        Optional<WorkSubcontractor> workOptional = workSubcontractorRepository.findById(id);
        Optional<Project> projectOptional = projectRepository.findById(projectId);
        Optional<Subcontractor> subcontractorOptional = subcontractorRepository.findById(subcontractorId);

        if (workOptional.isPresent() && projectOptional.isPresent() && subcontractorOptional.isPresent()) {
            WorkSubcontractor work = workOptional.get();
            work.setCost((long) (cost * 100));
            work.setStartDate(LocalDate.parse(startDate));
            work.setEndDate(LocalDate.parse(endDate));
            work.setStatus(status);
            work.setProject(projectOptional.get());
            work.setSubcontractor(subcontractorOptional.get());

            workSubcontractorRepository.save(work);
        } else {
            String errorMessage = "Ошибка: ";
            if (!workOptional.isPresent()) {
                errorMessage += "Работа с ID " + id + " не найдена. ";
            }
            if (!projectOptional.isPresent()) {
                errorMessage += "Проект с ID " + projectId + " не найден. ";
            }
            if (!subcontractorOptional.isPresent()) {
                errorMessage += "Подрядчик с ID " + subcontractorId + " не найден.";
            }
            model.addAttribute("error", errorMessage);
            return "worksubcontractor/edit";
        }
        return "redirect:/worksubcontractor";
    }


    @GetMapping("/delete/{id}")
    public String deleteWorkSubcontractor(@PathVariable int id, Model model) {
        if (workSubcontractorRepository.existsById(id)) {
            workSubcontractorRepository.deleteById(id);
        } else {
            model.addAttribute("error", "Работа с ID " + id + " не найдена");
        }
        return "redirect:/worksubcontractor";
    }
}
