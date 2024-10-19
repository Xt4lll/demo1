package com.example.demo.controller;

import com.example.demo.DAO.PersonDAO;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO _personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        _personDAO = personDAO;
    }

    @GetMapping()
    public String list(Model model) {
        List<Person> people = _personDAO.findAll();
        model.addAttribute("people", people);
        return "people/peopleList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Person person = _personDAO.findById(id);
        model.addAttribute("person", person);
        return "people/personDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "people/personAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Person person) {
        _personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Person person = _personDAO.findById(id);
        if (person == null) {
            return "redirect:/people";
        }
        model.addAttribute("person", person);
        return "people/personEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute Person person, @PathVariable int id) {
        int index = id-1;
        if(index >= 0){
            _personDAO.update(index, person);
            return "redirect:/people";
        }
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        int index = id-1;
        if(index >= 0){
            _personDAO.delete(index);
            return "redirect:/people";
        }
        return "redirect:/people";
    }


}
