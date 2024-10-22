package com.example.demo.controller;

import com.example.demo.model.Sponsor;
import com.example.demo.repositories.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sponsors")
public class SponsorController {

    private SponsorRepository _sponsorRepository;

    @Autowired
    public void setSponsorRepository(SponsorRepository sponsorRepository){
        this._sponsorRepository = sponsorRepository;
    }

    @GetMapping()
    public String list(Model model){
        List<Sponsor> sponsors = _sponsorRepository.findAll();
        model.addAttribute("sponsors", sponsors);
        return "sponsors/sponsorList";
    }

    @GetMapping("/search")
    public String list(Model model, @RequestParam("keyword") String keyword){
        List<Sponsor> sponsors = _sponsorRepository.findByNameContaining(keyword);
        model.addAttribute("sponsors", sponsors);
        return "sponsors/sponsorList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model){
        Optional<Sponsor> sponsor = _sponsorRepository.findById(id);
        model.addAttribute("sponsor", sponsor.get());
        return "sponsors/sponsorDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model){
        return "sponsors/sponsorAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Sponsor sponsor, Model model){
        _sponsorRepository.save(sponsor);
        return "redirect:/sponsors";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model){
        Optional<Sponsor> sponsor = _sponsorRepository.findById(id);
        if(sponsor == null){
            return "redirect:/sponsors";
        }
        model.addAttribute("sponsor", sponsor.get());
        return "sponsors/sponsorEdit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute Sponsor sponsor, @PathVariable int id){
        sponsor.setId(id);
        _sponsorRepository.save(sponsor);
        return "redirect:/sponsors";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        _sponsorRepository.deleteById(id);
        return "redirect:/sponsors";
    }

}
