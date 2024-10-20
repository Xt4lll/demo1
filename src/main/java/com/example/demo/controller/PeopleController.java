package com.example.demo.controller;

import com.example.demo.DAO.PersonDAO;
import com.example.demo.model.Person;
import com.example.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonRepository _personRepository;

    @Autowired
    public PeopleController(PersonRepository personRepository) {
        _personRepository = personRepository;
    }

    @GetMapping()
    public String list(Model model) {
        List<Person> people = _personRepository.findAll();
        model.addAttribute("people", people);
        return "people/peopleList";
    }

    @GetMapping("/search")
    public String findByName(Model model, @RequestParam("keyword") String keyword) {
        List<Person> people = _personRepository.findByNameContaining(keyword);
        model.addAttribute("people", people);
        return "people/peopleList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Optional<Person> person = _personRepository.findById(id);
        model.addAttribute("person", person.get());
        return "people/personDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "people/personAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Person person) {
        _personRepository.save(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Optional<Person> person = _personRepository.findById(id);
        if (person == null) {
            return "redirect:/people";
        }
        model.addAttribute("person", person.get());
        return "people/personEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute Person person, @PathVariable int id) {
        if(id >= 0){
            person.setId(id);
            _personRepository.save(person);
            return "redirect:/people";
        }
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        if(id >= 0){
            _personRepository.deleteById(id);
            return "redirect:/people";
        }
        return "redirect:/people";
    }


}
