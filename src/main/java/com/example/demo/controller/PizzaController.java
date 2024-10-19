package com.example.demo.controller;

import com.example.demo.DAO.PizzaDAO;
import com.example.demo.model.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pizza")
public class PizzaController {

    private PizzaDAO _pizzaDAO;

    @Autowired
    public void setPizzaDAO(PizzaDAO pizzaDAO) {
        this._pizzaDAO = pizzaDAO;
    }

    @GetMapping()
    public String list(Model model) {
        List<Pizza> pizzas = _pizzaDAO.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizza/pizzaList";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Pizza pizza = _pizzaDAO.findById(id);
        model.addAttribute("pizza", pizza);
        return "pizza/pizzaDetail";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        return "pizza/pizzaAdd";
    }

    @PostMapping
    public String create(@ModelAttribute Pizza pizza) {
        _pizzaDAO.save(pizza);
        return "redirect:/pizza";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Pizza pizza = _pizzaDAO.findById(id);
        if (pizza == null) {
            return "redirect:/pizza";
        }
        model.addAttribute("pizza", pizza);
        return "pizza/pizzaEdit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable int id, @ModelAttribute Pizza pizza) {
        int index = id-1;
        if(index >= 0){
            _pizzaDAO.update(index, pizza);
            return "redirect:/pizza";
        }
        return "redirect:/pizza";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        int index = id-1;
        if(index >= 0){
            _pizzaDAO.delete(index);
            return "redirect:/pizza";
        }
        return "redirect:/pizza";
    }

}
