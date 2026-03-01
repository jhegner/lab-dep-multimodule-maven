package com.example.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
        authService.register("john", "secret");
    }

    @Test
    void authenticate_validCredentials_returnsTrue() {
        assertTrue(authService.authenticate(new Credentials("john", "secret")));
    }

    @Test
    void authenticate_wrongPassword_returnsFalse() {
        assertFalse(authService.authenticate(new Credentials("john", "wrong")));
    }

    @Test
    void authenticate_unknownUser_returnsFalse() {
        assertFalse(authService.authenticate(new Credentials("ghost", "secret")));
    }

    @Test
    void authenticate_nullCredentials_returnsFalse() {
        assertFalse(authService.authenticate(null));
    }

    @Test
    void register_blankUsername_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> authService.register("", "pass"));
    }

    @Test
    void register_duplicateUsername_throwsException() {
        assertThrows(IllegalStateException.class,
                () -> authService.register("john", "newpass"));
    }

    @Test
    void isRegistered_existingUser_returnsTrue() {
        assertTrue(authService.isRegistered("john"));
    }

    @Test
    void isRegistered_unknownUser_returnsFalse() {
        assertFalse(authService.isRegistered("nobody"));
    }
}
