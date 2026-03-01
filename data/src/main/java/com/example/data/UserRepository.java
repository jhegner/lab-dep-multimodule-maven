package com.example.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    private final Map<Long, User> store = new HashMap<>();

    public void save(User user) {
        store.put(user.getId(), user);
    }

    public Optional<User> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<User> findAll() {
        return List.copyOf(store.values());
    }

    public boolean deleteById(long id) {
        return store.remove(id) != null;
    }
}
