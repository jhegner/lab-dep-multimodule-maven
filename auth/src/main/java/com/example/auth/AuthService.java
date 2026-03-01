package com.example.auth;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private final Map<String, String> userStore = new HashMap<>();

    public void register(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username must not be blank");
        }
        if (userStore.containsKey(username)) {
            throw new IllegalStateException("Username already registered: " + username);
        }
        // NOTE: this is a dummy implementation; production code should hash passwords.
        userStore.put(username, password);
    }

    public boolean authenticate(Credentials credentials) {
        if (credentials == null) {
            return false;
        }
        String stored = userStore.get(credentials.getUsername());
        return stored != null && stored.equals(credentials.getPassword());
    }

    public boolean isRegistered(String username) {
        return userStore.containsKey(username);
    }
}
