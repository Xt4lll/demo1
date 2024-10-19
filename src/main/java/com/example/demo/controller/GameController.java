package com.example.demo.controller;

import com.example.demo.DAO.GameDAO;
import com.example.demo.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameDAO _gameDAO;

    @Autowired
    public GameController(GameDAO gameDAO) {
        _gameDAO = gameDAO;
    }

    @GetMapping()
    public String list(Model model) {
        List<Game> games = _gameDAO.findAll();
        model.addAttribute("games", games);
        return "games/gameList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Game game = _gameDAO.findById(id);
        model.addAttribute("game", game);
        return "games/gameDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "games/gameAdd";
    }

    @PostMapping
    public String create(Game game) {
        _gameDAO.save(game);
        return "redirect:/games";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable int id, Model model) {
        Game game = _gameDAO.findById(id);
        if(game == null){
            return "redirect:/games";
        }
        model.addAttribute("game", game);
        return "games/gameEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, Game game) {
        int index = id-1;
        if(index >= 0){
            _gameDAO.update(index, game);
            return "redirect:/games";
        }
        return "redirect:/games";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        int index = id-1;
        if(index >= 0){
            _gameDAO.delete(index);
            return "redirect:/games";
        }
        return "redirect:/games";
    }
}
