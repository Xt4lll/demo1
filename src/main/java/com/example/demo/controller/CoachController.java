package com.example.demo.controller;

import com.example.demo.model.Coach;
import com.example.demo.repositories.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/coaches")
public class CoachController {

    private CoachRepository _coachRepository;

    @Autowired
    public CoachController(CoachRepository coachRepository) {
        _coachRepository = coachRepository;
    }

    @GetMapping()
    public String list(Model model) {
        List<Coach> coaches = _coachRepository.findAll();
        model.addAttribute("coaches", coaches);
        return "coaches/coachesList";
    }

    @GetMapping("/search")
    public String findByName(Model model, @RequestParam("keyword") String keyword) {
        List<Coach> coach = _coachRepository.findByNameContaining(keyword);
        model.addAttribute("coach", coach);
        return "coaches/coachesList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Optional<Coach> coach = _coachRepository.findById(id);
        model.addAttribute("coach", coach.get());
        return "coaches/coachDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "coaches/coachAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Coach coach) {
        _coachRepository.save(coach);
        return "redirect:/coaches";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Optional<Coach> coach = _coachRepository.findById(id);
        if (coach == null) {
            return "redirect:/coaches";
        }
        model.addAttribute("coach", coach.get());
        return "coaches/coachEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Coach coach) {
        coach.setId(id);
        _coachRepository.save(coach);
        return "redirect:/coaches";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        _coachRepository.deleteById(id);
        return "redirect:/coaches";
    }

}
