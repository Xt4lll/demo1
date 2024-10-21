package com.example.demo.controller;

import com.example.demo.model.Team;
import com.example.demo.repositories.CoachRepository;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.SponsorRepository;
import com.example.demo.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {
    private TeamRepository _teamRepository;
    private SponsorRepository _sponsorRepository;
    private PlayerRepository _playerRepository;
    private CoachRepository _coachRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        _teamRepository = teamRepository;
    }

    @Autowired
    public void setSponsorRepository(SponsorRepository sponsorRepository) {
        _sponsorRepository = sponsorRepository;
    }

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        _playerRepository = playerRepository;
    }

    @Autowired
    public void setCoachRepository(CoachRepository coachRepository) {
        _coachRepository = coachRepository;
    }

    @GetMapping()
    public String teams(Model model) {
        List<Team> teams = _teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "teams/teamList";
    }

    @GetMapping("/search")
    public String teams(Model model, @RequestParam("keyword") String keyword) {
        List<Team> teams = _teamRepository.findByNameContaining(keyword);
        model.addAttribute("teams", teams);
        return "teams/teamList";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("sponsors", _sponsorRepository.findAll());
        model.addAttribute("players", _playerRepository.findAll());
        model.addAttribute("coaches", _coachRepository.findAll());
        return "teams/teamAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Team team, Model model) {
        _teamRepository.save(team);
        return "redirect:/teams";
    }

}