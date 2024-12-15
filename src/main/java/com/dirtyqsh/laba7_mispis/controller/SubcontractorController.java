package com.dirtyqsh.laba7_mispis.controller;

import com.dirtyqsh.laba7_mispis.model.Subcontractor;
import com.dirtyqsh.laba7_mispis.repo.SubcontractorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/subcontractor")
public class SubcontractorController {
    private final SubcontractorRepository subcontractorRepository;


    public SubcontractorController(SubcontractorRepository subcontractorRepository) {
        this.subcontractorRepository = subcontractorRepository;
    }

    @GetMapping
    public String getSubcontractor(Model model) {
        List<Subcontractor> subcontractors = subcontractorRepository.findAll();
        model.addAttribute("subcontractors", subcontractors);
        return "subcontractor";
    }

    @PostMapping("/add")
    public String addSubcontractor(@ModelAttribute Subcontractor subcontractor) {
        subcontractorRepository.save(subcontractor);
        return "redirect:/subcontractor";
    }

    @GetMapping("delete/{id}")
    public String deleteSubcontractor(@PathVariable Integer id) {
        subcontractorRepository.deleteById(id);
        return "redirect:/subcontractor";
    }

    @GetMapping("edit/{id}")
    public String editSubcontractor(@PathVariable Integer id, Model model) {
        Subcontractor subcontractor = subcontractorRepository.findById(id).orElseThrow();
        model.addAttribute("subcontractor", subcontractor);
        return "edit-sub";
    }

    @PostMapping("/update")
    public String updateSubcontractor(@ModelAttribute Subcontractor subcontractor) {
        subcontractorRepository.save(subcontractor);
        return "redirect:/subcontractor";
    }
}
