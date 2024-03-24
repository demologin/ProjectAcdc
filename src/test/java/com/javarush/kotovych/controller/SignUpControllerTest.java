package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.exception.AppException;
import com.javarush.kotovych.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SignUpControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private SignUpController signUpController;

    @Test
    void testGetSignupPage_ReturnsSignupPage() {
        ModelAndView result = signUpController.getSignupPage();

        assertEquals(Constants.SIGNUP, result.getViewName());
    }

    @Test
    void testSignUp_UserDoesNotExist_CreatesUserAndRedirectsToMainPage() {
        String username = "testUser";
        String password = "testPassword";
        User user = new User(username, password);

        when(userService.checkIfExists(username)).thenReturn(false);

        ModelAndView result = signUpController.signUp(username, password, response);

        verify(userService, times(1)).create(user);
        verify(response, times(1)).addCookie(any());

        assertEquals(Constants.MAIN_PAGE_REDIRECT, result.getViewName());
    }

    @Test
    void testSignUp_UserAlreadyExists() {
        String username = "existingUser";
        String password = "testPassword";

        when(userService.checkIfExists(username)).thenReturn(true);
        ModelAndView modelAndView = signUpController.signUp(username, password, response);

        boolean containsKey = modelAndView.getModel().containsKey(Constants.ERROR);
        String key = modelAndView.getModel().get(Constants.ERROR).toString();

        assertTrue(containsKey);
        assertEquals(key, Constants.FAILED_TO_CREATE_USER_BECAUSE_IT_ALREADY_EXISTS);
    }
}
