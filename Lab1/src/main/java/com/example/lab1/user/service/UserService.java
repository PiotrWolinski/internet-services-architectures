package com.example.lab1.user.service;

import com.example.lab1.digest.Sha256Utility;
import com.example.lab1.user.entity.User;
import com.example.lab1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) { this.repository = repository; }

    public Optional<User> find(String login) { return repository.findById(login); }

    public Optional<User> find(String login, String password) { return repository.findByLoginAndPassword(login, Sha256Utility.hash(password)); }

    public List<User> findAll() { return repository.findAll(); }

    @Transactional
    public User create(User user) { return repository.save(user); }

    @Transactional
    public void delete(String login) { repository.deleteById(login); }

    @Transactional
    public void update(User user) { repository.save(user); }

}
