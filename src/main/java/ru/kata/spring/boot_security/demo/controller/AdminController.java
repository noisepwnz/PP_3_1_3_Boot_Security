package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Controller
public class AdminController {
        private final UserService userService;
        private final RoleService roleService;


    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;

        this.roleService = roleService;
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
        model.addAttribute("user", userService.createUser());
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user) {

        userService.add(user);
        return "redirect:/users";

    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        Set<Role> roles = roleService.getAllRoles();
        model.addAttribute("user",userService.findById(id));
        model.addAttribute("listRoles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }


    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping(("/users/new"))
    public String createUser(@ModelAttribute("newUser") User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println("Binderr");
        }
        userService.add(user);
        return "redirect:/users";
    }
}
