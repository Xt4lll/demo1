package com.example.demo.controller;

import com.example.demo.DAO.AnimalDAO;
import com.example.demo.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    private AnimalDAO _animalDAO;

    @Autowired
    public AnimalController(AnimalDAO animalDAO) {
        _animalDAO = animalDAO;
    }

    @GetMapping()
    public String list(Model model) {
        List<Animal> animals = _animalDAO.findAll();
        model.addAttribute("animals", animals);
        return "animal/animalList";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id) {
        Animal animal = _animalDAO.findById(id);
        model.addAttribute("animal", animal);
        return "animal/animalDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "animal/animalAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Animal animal) {
        _animalDAO.save(animal);
        return "redirect:/animal";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Animal animal = _animalDAO.findById(id);
        if (animal == null) {
            return "redirect:/animal";
        }
        model.addAttribute("animal", animal);
        return "animal/animalEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute Animal animal, @PathVariable int id) {
        int index = id-1;
        if (index >= 0) {
            _animalDAO.update(index, animal);
            return "redirect:/animal";
        }
        return "redirect:/animal";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        int index = id-1;
        System.out.println(index);
        if (index >= 0) {
            _animalDAO.delete(index);
            return "redirect:/animal";
        }
        return "redirect:/animal";
    }
}
