package com.example.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private UserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepository();
    }

    @Test
    void save_andFindById_returnsUser() {
        User user = new User(1L, "Alice", "alice@example.com");
        repository.save(user);

        Optional<User> found = repository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
        assertEquals("alice@example.com", found.get().getEmail());
    }

    @Test
    void findById_notFound_returnsEmpty() {
        Optional<User> found = repository.findById(99L);
        assertFalse(found.isPresent());
    }

    @Test
    void findAll_returnsAllSavedUsers() {
        repository.save(new User(1L, "Alice", "alice@example.com"));
        repository.save(new User(2L, "Bob", "bob@example.com"));

        List<User> users = repository.findAll();
        assertEquals(2, users.size());
    }

    @Test
    void deleteById_existingUser_returnsTrue() {
        repository.save(new User(1L, "Alice", "alice@example.com"));
        boolean deleted = repository.deleteById(1L);

        assertTrue(deleted);
        assertFalse(repository.findById(1L).isPresent());
    }

    @Test
    void deleteById_nonExistingUser_returnsFalse() {
        boolean deleted = repository.deleteById(99L);
        assertFalse(deleted);
    }
}
