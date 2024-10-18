package com.example.demo.controller;

import com.example.demo.DAO.AnimalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    private AnimalDAO _animalDAO;

    @Autowired
    public AnimalController(AnimalDAO animalDAO) { _animalDAO = animalDAO; }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("animal", _animalDAO.index());
        return "animal/index";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id) {
        model.addAttribute("animal", _animalDAO.show(id));
        return "animal/show";
    }
}
