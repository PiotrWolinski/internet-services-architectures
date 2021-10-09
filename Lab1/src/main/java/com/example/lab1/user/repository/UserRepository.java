package com.example.lab1.user.repository;

import com.example.lab1.datastore.DataStore;
import com.example.lab1.repository.Repository;
import com.example.lab1.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class UserRepository implements Repository<User, String> {

    private DataStore store;

    @Autowired
    public UserRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<User> find(String login) {
        return store.findUser(login);
    }

    @Override
    public List<User> findAll() { return store.findAllUsers(); }

    @Override
    public void create(User entity) {
        store.createUser(entity);
    }

    @Override
    public void delete(User entity) {
        throw new UnsupportedOperationException("Not provided.");
    }

    @Override
    public void update(User entity) {
        throw new UnsupportedOperationException("Not provided.");
    }

    public Optional<User> findByLoginAndPassword(String login, String password) {
        return store.findUser(login, password);
    }
}
