package ru.kata.spring.boot_security.demo.service;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

     User findById(Integer id);

    List<User> findAll();

    void update(int id, User updatedUser);

    void delete(int id);
}
