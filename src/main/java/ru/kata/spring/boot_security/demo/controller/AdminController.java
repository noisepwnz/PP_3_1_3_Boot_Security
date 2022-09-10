package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {
        private final UserService userService;


    public AdminController(UserService userService) {
        this.userService = userService;

    }


    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("MyUser", userService.findAll());
        return "index";
    }


    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "show";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";

    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }


    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
