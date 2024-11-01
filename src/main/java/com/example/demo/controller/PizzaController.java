package com.example.demo.controller;

import com.example.demo.DAO.PizzaDAO;
import com.example.demo.model.Pizza;
import com.example.demo.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    private PizzaRepository _pizzaRepository;

    @Autowired
    public void setPizzaDAO(PizzaRepository pizzaRepository) {
        this._pizzaRepository = pizzaRepository;
    }

    @GetMapping()
    public String list(Model model) {
        List<Pizza> pizzas = _pizzaRepository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizza/pizzaList";
    }

    @GetMapping("/search")
    public String findByName(Model model, @RequestParam("keyword") String keyword) {
        List<Pizza> pizzas = _pizzaRepository.findByNameContaining(keyword);
        model.addAttribute("pizzas", pizzas);
        return "pizza/pizzaList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Optional<Pizza> pizza = _pizzaRepository.findById(id);
        model.addAttribute("pizza", pizza.get());
        return "pizza/pizzaDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "pizza/pizzaAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Pizza pizza) {
        _pizzaRepository.save(pizza);
        return "redirect:/pizza";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Pizza> pizza = _pizzaRepository.findById(id);
        if (pizza == null) {
            return "redirect:/pizza";
        }
        model.addAttribute("pizza", pizza.get());
        return "pizza/pizzaEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Pizza pizza) {
        if(id >= 0){
            pizza.setId(id);
            _pizzaRepository.save(pizza);
            return "redirect:/pizza";
        }
        return "redirect:/pizza";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        if(id >= 0){
            _pizzaRepository.deleteById(id);
            return "redirect:/pizza";
        }
        return "redirect:/pizza";
    }

}
