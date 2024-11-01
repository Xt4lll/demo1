package com.example.demo.controller;

import com.example.demo.DAO.GameDAO;
import com.example.demo.model.Game;
import com.example.demo.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameRepository _gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository) {
        _gameRepository = gameRepository;
    }

    @GetMapping()
    public String list(Model model) {
        List<Game> games = _gameRepository.findAll();
        model.addAttribute("games", games);
        return "games/gameList";
    }

    @GetMapping("/search")
    public String list(Model model, @RequestParam("keyword") String keyword) {
        List<Game> games = _gameRepository.findByNameContaining(keyword);
        model.addAttribute("games", games);
        return "games/gameList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Optional<Game> game = _gameRepository.findById(id);
        model.addAttribute("game", game.get());
        return "games/gameDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "games/gameAdd";
    }

    @PostMapping
    public String create(Game game) {
        _gameRepository.save(game);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Optional<Game> game = _gameRepository.findById(id);
        if(game == null){
            return "redirect:/games";
        }
        model.addAttribute("game", game.get());
        return "games/gameEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, Game game) {
        if(id >= 0){
            game.setId(id);
            _gameRepository.save(game);
            return "redirect:/games";
        }
        return "redirect:/games";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        if(id >= 0){
            _gameRepository.deleteById(id);
            return "redirect:/games";
        }
        return "redirect:/games";
    }
}
