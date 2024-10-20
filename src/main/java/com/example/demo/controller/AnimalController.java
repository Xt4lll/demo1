package com.example.demo.controller;

import com.example.demo.DAO.AnimalDAO;
import com.example.demo.model.Animal;
import com.example.demo.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/animal")
public class AnimalController {

    /*private AnimalDAO _animalDAO;

    @Autowired
    public AnimalController(AnimalDAO animalDAO) {
        _animalDAO = animalDAO;
    }*/

    private AnimalRepository _animalRepository;

    @Autowired
    public void setAnimalRepository(AnimalRepository animalRepository) {
        this._animalRepository = animalRepository;
    }

    @GetMapping()
    public String list(Model model) {
        //List<Animal> animals = _animalDAO.findAll();
        List<Animal> animals = _animalRepository.findAll();
        model.addAttribute("animals", animals);
        return "animal/animalList";
    }

    @GetMapping("/search")
    public String findByName(Model model, @RequestParam("keyword") String keyword) {
        List<Animal> animals = _animalRepository.findByNameContaining(keyword);
        model.addAttribute("animals", animals);
        return "animal/animalList";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id) {
        Optional<Animal> animal = _animalRepository.findById(id);
        model.addAttribute("animal", animal.get());
        return "animal/animalDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "animal/animalAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Animal animal) {
        _animalRepository.save(animal);
        return "redirect:/animal";
    }

    @GetMapping("/edit/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Optional<Animal> animal = _animalRepository.findById(id);
        if (animal == null) {
            return "redirect:/animal";
        }
        model.addAttribute("animal", animal.get());
        return "animal/animalEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute Animal animal, @PathVariable int id) {
        if (id >= 0) {
            animal.setId(id);
            _animalRepository.save(animal);
            return "redirect:/animal";
        }
        return "redirect:/animal";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        if (id >= 0) {
            _animalRepository.deleteById(id);
            return "redirect:/animal";
        }
        return "redirect:/animal";
    }
}
