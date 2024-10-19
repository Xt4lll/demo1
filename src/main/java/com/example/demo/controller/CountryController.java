package com.example.demo.controller;

import com.example.demo.DAO.CountryDAO;
import com.example.demo.model.Counrty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private CountryDAO _countryDAO;

    @Autowired
    public CountryController(CountryDAO countryDAO) {
        _countryDAO = countryDAO;
    }

    @GetMapping()
    public String list(Model model) {
        List<Counrty> countries = _countryDAO.findAll();
        model.addAttribute("countries", countries);
        return "countries/countryList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Counrty country = _countryDAO.findById(id);
        model.addAttribute("country", country);
        return "countries/countryDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "countries/countryAdd";
    }

    @PostMapping
    public String create(Counrty country) {
        _countryDAO.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Counrty country = _countryDAO.findById(id);
        if (country == null) {
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "countries/countryEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, Counrty country) {
        int index = id-1;
        if (index >= 0) {
            _countryDAO.update(index, country);
            return "redirect:/countries";
        }
        return "redirect:/countries";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        int index = id-1;
        if (index >= 0) {
            _countryDAO.delete(index);
            return "redirect:/countries";
        }
        return "redirect:/countries";
    }

}
