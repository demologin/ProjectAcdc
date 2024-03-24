package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.CookieSetter;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LogInControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private LogInController logInController;

    @Test
    void testGetLoginPage_ReturnsLoginPage() {
        ModelAndView result = logInController.getLoginPage();

        assertEquals(Constants.LOGIN, result.getViewName());
    }

    @Test
    void testLogIn_CorrectInformation_RedirectsToMainPageAndSetsCookie() {
        String username = "testUser";
        String password = "password123";
        User user = new User();
        user.setId(1L);
        when(userService.checkIfCorrect(username, password)).thenReturn(true);
        when(userService.get(username)).thenReturn(Optional.of(user));

        ModelAndView result = logInController.logIn(username, password, response);

        verify(response, times(1)).addCookie(any());
        assertEquals(Constants.MAIN_PAGE_REDIRECT, result.getViewName());
    }

    @Test
    void testLogIn_IncorrectInformation_ReturnsLoginPageWithError() {
        String username = "testUser";
        String password = "password123";
        when(userService.checkIfCorrect(username, password)).thenReturn(false);

        ModelAndView result = logInController.logIn(username, password, response);

        assertEquals(Constants.LOGIN, result.getViewName());
        assertEquals(Constants.USER_NOT_FOUND, result.getModel().get(Constants.ERROR));
    }
}
