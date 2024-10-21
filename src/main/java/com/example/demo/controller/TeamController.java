package com.example.demo.controller;

import com.example.demo.model.Team;
import com.example.demo.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {
    private TeamRepository _teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        _teamRepository = teamRepository;
    }

    @GetMapping()
    public String teams(Model model) {
        List<Team> teams = _teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "teams/teamList";
    }


}
