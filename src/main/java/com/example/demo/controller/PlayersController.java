package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/players")
public class PlayersController {

    PlayerRepository _playerRepository;
    TeamRepository _teamRepository;

    @Autowired
    public PlayersController(PlayerRepository playerRepository) {
        _playerRepository = playerRepository;
    }

    @Autowired
    public void TeamRepository(TeamRepository teamRepository) {
        _teamRepository = teamRepository;
    }

    @GetMapping()
    public String list(Model model) {
        List<Player> players = _playerRepository.findAll();
        model.addAttribute("players", players);
        return "players/playerList";
    }

    @GetMapping("/search")
    public String list(Model model, @RequestParam("keyword") String keyword) {
        List<Player> players = _playerRepository.findByNameContaining(keyword);
        model.addAttribute("players", players);
        return "players/playerList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Optional<Player> player = _playerRepository.findById(id);
        model.addAttribute("player", player.get());
        return "players/playerDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("teams", _teamRepository.findAll());
        return "players/playerAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Player player) {
        _playerRepository.save(player);
        return "redirect:/players";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Optional<Player> player = _playerRepository.findById(id);
        List<Team> teams = _teamRepository.findAll();
        if(player == null){
            return "redirect:/players";
        }
        model.addAttribute("player", player.get());
        model.addAttribute("teams", teams);
        return "players/playerEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Player player) {
        player.setId(id);
        _playerRepository.save(player);
        return "redirect:/players";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        _playerRepository.deleteById(id);
        return "redirect:/players";
    }
}