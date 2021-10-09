package com.example.lab1.car.repository;

import com.example.lab1.car.entity.Engine;
import com.example.lab1.datastore.DataStore;
import com.example.lab1.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class EngineRepository implements Repository<Engine, String> {

    private DataStore store;

    @Autowired
    public EngineRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Engine> find(String name) {
        return store.findEngine(name);
    }

    @Override
    public List<Engine> findAll() { return store.findAllEngines(); }

    @Override
    public void create(Engine engine) { store.createEngine(engine); }

    @Override
    public void delete(Engine engine) { throw new UnsupportedOperationException("Operation not provided"); }

    @Override
    public void update(Engine engine) { throw new UnsupportedOperationException("Operation not provided"); }
}
