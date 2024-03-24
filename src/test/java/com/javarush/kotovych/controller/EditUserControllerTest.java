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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EditUserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private EditUserController editUserController;

    @Test
    void getEditUserPage_UserExists_ReturnsEditPage() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        ModelAndView expectedModelAndView = new ModelAndView(Constants.EDIT_USER);
        expectedModelAndView.addObject(Constants.USER, user);
        when(userService.checkIfExists(userId)).thenReturn(true);
        when(userService.get(userId)).thenReturn(Optional.of(user));

        ModelAndView resultModelAndView = editUserController.getEditUserPage(userId);

        assertEquals(expectedModelAndView.getViewName(), resultModelAndView.getViewName());
        assertEquals(expectedModelAndView.getModel().get(Constants.USER), resultModelAndView.getModel().get(Constants.USER));
    }

    @Test
    void getEditUserPage_UserDoesNotExist_ReturnsRedirectToMainPage() {
        long userId = 1L;
        when(userService.checkIfExists(userId)).thenReturn(false);
        ModelAndView expectedModelAndView = new ModelAndView(Constants.MAIN_PAGE_REDIRECT);

        ModelAndView resultModelAndView = editUserController.getEditUserPage(userId);

        assertEquals(expectedModelAndView.getViewName(), resultModelAndView.getViewName());
    }

    @Test
    void editUser_ValidEditData_UpdatesUserAndRedirectsToMainPage() {
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        String newUsername = "newUsername";
        String newPassword = "newPassword";
        when(userService.checkIfExists(userId)).thenReturn(true);
        when(userService.get(userId)).thenReturn(Optional.of(user));

        ModelAndView resultModelAndView = editUserController.editUser(newUsername, newPassword, userId);

        verify(userService, times(1)).update(user);
        assertEquals(Constants.MAIN_PAGE_REDIRECT, resultModelAndView.getViewName());
    }

    @Test
    void editUser_InvalidEditData_ReturnsRedirectToEditPage() {
        long userId = 1L;
        String newUsername = "";
        String newPassword = "newPassword";

        ModelAndView resultModelAndView = editUserController.editUser(newUsername, newPassword, userId);

        assertEquals("redirect:/" + Constants.EDIT_USER, resultModelAndView.getViewName());
    }
}
