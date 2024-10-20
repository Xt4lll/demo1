package com.example.demo.controller;

import com.example.demo.model.Country;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/countries")
public class CountryController {

    /*private CountryDAO _countryDAO;

    @Autowired
    public CountryController(CountryDAO countryDAO) {
        _countryDAO = countryDAO;
    }*/

    private CountryRepository _countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        _countryRepository = countryRepository;
    }

    @GetMapping()
    public String list(Model model) {
        List<Country> countries = _countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "countries/countryList";
    }

    @GetMapping("/search")
    public String findByName(Model model, @RequestParam("keyword") String keyword) {
        List<Country> countries = _countryRepository.findByNameContaining(keyword);
        model.addAttribute("countries", countries);
        return "countries/countryList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Optional<Country> country = _countryRepository.findById(id);
        model.addAttribute("country", country.get());
        return "countries/countryDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "countries/countryAdd";
    }

    @PostMapping
    public String create(Country country) {
        _countryRepository.save(country);
        return "redirect:/countries";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Optional<Country> country = _countryRepository.findById(id);
        if (country == null) {
            return "redirect:/countries";
        }
        model.addAttribute("country", country.get());
        return "countries/countryEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, Country country) {
        if (id >= 0) {
            country.setId(id);
            _countryRepository.save(country);
            return "redirect:/countries";
        }
        return "redirect:/countries";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        if (id >= 0) {
            _countryRepository.deleteById(id);
            return "redirect:/countries";
        }
        return "redirect:/countries";
    }

}
