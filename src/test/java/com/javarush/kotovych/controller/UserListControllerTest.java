package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserListControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserListController userListController;

    @Test
    void testGetUserListPage_UserExists_ReturnsUserListPage() {
        long userId = 1L;
        User user = new User("testUser", "testPassword");
        Collection<User> userList = Collections.singletonList(user);

        when(userService.getAll()).thenReturn(userList);
        when(userService.checkIfExists(userId)).thenReturn(true);
        when(userService.get(userId)).thenReturn(Optional.of(user));

        ModelAndView result = userListController.getUserListPage(userId);

        assertEquals(Constants.USER_LIST, result.getViewName());
        assertEquals(userList, result.getModel().get(Constants.USERS));
        assertEquals("testUser", result.getModel().get(Constants.USERNAME));
    }

    @Test
    void testGetUserListPage_UserDoesNotExist_ReturnsRedirectToMainPage() {
        long userId = 0L;

        when(userService.checkIfExists(userId)).thenReturn(false);

        ModelAndView result = userListController.getUserListPage(userId);

        assertEquals(Constants.MAIN_PAGE_REDIRECT, result.getViewName());
    }
}
