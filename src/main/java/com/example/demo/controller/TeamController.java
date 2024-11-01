package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.model.Sponsor;
import com.example.demo.model.Team;
import com.example.demo.repositories.CoachRepository;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.SponsorRepository;
import com.example.demo.repositories.TeamRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        teams.forEach(team -> Hibernate.initialize(team.getSponsors()));
        model.addAttribute("teams", teams);
        return "teams/teamList";
    }

    @GetMapping("/search")
    public String findByName(Model model, @RequestParam("keyword") String keyword) {
        List<Team> teams = _teamRepository.findByNameContaining(keyword);
        model.addAttribute("teams", teams);
        return "teams/teamList";
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable int id) {
        Optional<Team> team = _teamRepository.findById(id);
        model.addAttribute("team", team.get());
        return "teams/teamDetail";
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
    public String create(@ModelAttribute Team team, Model model, @RequestParam List<Integer> sponsorIds) {
        if (team.getCoach() != null && team.getCoach().getId() != 0) {
            team.setCoach(_coachRepository.findById(team.getCoach().getId()).orElse(null));
        }

        // Получаем спонсоров по ID и добавляем их в команду
        for (Integer sponsorId : sponsorIds) {
            Sponsor sponsor = _sponsorRepository.findById(sponsorId).orElse(null);
            if (sponsor != null) {
                team.getSponsors().add(sponsor);
            }
        }

        _teamRepository.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Optional team = _teamRepository.findById(id);
        if(team == null){
            return "redirect:/teams";
        }
        model.addAttribute("team", team.get());
        model.addAttribute("sponsors", _sponsorRepository.findAll());
        model.addAttribute("players", _playerRepository.findAll());
        model.addAttribute("coaches", _coachRepository.findAll());
        return "teams/teamEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id,
                         @ModelAttribute Team team,
                         @RequestParam(required = false) List<Integer> sponsorIds) {
        Optional<Team> existingTeamOptional = _teamRepository.findById(id);
        if (!existingTeamOptional.isPresent()) {
            return "redirect:/teams";
        }

        Team existingTeam = existingTeamOptional.get();

        existingTeam.setName(team.getName());

        if (team.getCoach() != null && team.getCoach().getId() != 0) {
            existingTeam.setCoach(_coachRepository.findById(team.getCoach().getId()).orElse(null));
        }

        existingTeam.getSponsors().clear();
        if (sponsorIds != null) {
            for (Integer sponsorId : sponsorIds) {
                Sponsor sponsor = _sponsorRepository.findById(sponsorId).orElse(null);
                if (sponsor != null) {
                    existingTeam.getSponsors().add(sponsor);
                }
            }
        }

        _teamRepository.save(existingTeam);
        return "redirect:/teams";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        _teamRepository.deleteById(id);
        return "redirect:/teams";
    }

}