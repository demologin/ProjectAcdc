package com.javarush.kotovych.service;

import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setLogin("testUser");
        testUser.setPassword("password123");
    }

    @Test
    void createUser() {
        User user = new User();
        user.setLogin("testUser");

        userService.create(user);

        verify(userRepository, times(1)).create(user);
    }

    @Test
    void updateUser() {
        userService.update(testUser);

        verify(userRepository, times(1)).update(testUser);
    }

    @Test
    void deleteUser() {
        userService.delete(testUser);

        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void getAllUsers() {
        User user1 = new User();
        User user2 = new User();
        when(userRepository.getAll()).thenReturn(Arrays.asList(user1, user2));

        Collection<User> result = userService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
    }

    @Test
    void getUserById_UserExists() {
        when(userRepository.get(1L)).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.get(1L);

        assertTrue(result.isPresent());
        assertEquals(testUser, result.get());
    }

    @Test
    void getUserById_UserDoesNotExist() {
        when(userRepository.get(2L)).thenReturn(Optional.empty());

        Optional<User> result = userService.get(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void getUserByUsername_UserExists() {
        when(userRepository.get("testUser")).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.get("testUser");

        assertTrue(result.isPresent());
        assertEquals(testUser, result.get());
    }

    @Test
    void getUserByUsername_UserDoesNotExist() {
        when(userRepository.get("nonexistentUser")).thenReturn(Optional.empty());

        Optional<User> result = userService.get("nonexistentUser");

        assertFalse(result.isPresent());
    }

    @Test
    void checkIfCorrect_CorrectPassword() {
        when(userRepository.get("testUser")).thenReturn(Optional.of(testUser));

        boolean result = userService.checkIfCorrect("testUser", "password123");

        assertTrue(result);
    }

    @Test
    void checkIfCorrect_IncorrectPassword() {
        when(userRepository.get("testUser")).thenReturn(Optional.of(testUser));

        boolean result = userService.checkIfCorrect("testUser", "wrongPassword");

        assertFalse(result);
    }

    @Test
    void checkIfExistsById_UserExists() {
        when(userRepository.get(1L)).thenReturn(Optional.of(testUser));

        boolean result = userService.checkIfExists(1L);

        assertTrue(result);
    }

    @Test
    void checkIfExistsById_UserDoesNotExist() {
        when(userRepository.get(2L)).thenReturn(Optional.empty());

        boolean result = userService.checkIfExists(2L);

        assertFalse(result);
    }

    @Test
    void checkIfExistsByUsername_UserExists() {
        when(userRepository.get("testUser")).thenReturn(Optional.of(testUser));

        boolean result = userService.checkIfExists("testUser");

        assertTrue(result);
    }

    @Test
    void checkIfExistsByUsername_UserDoesNotExist() {
        when(userRepository.get("nonexistentUser")).thenReturn(Optional.empty());

        boolean result = userService.checkIfExists("nonexistentUser");

        assertFalse(result);
    }
}
