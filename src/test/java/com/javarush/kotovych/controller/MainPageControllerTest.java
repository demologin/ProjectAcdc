package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainPageControllerTest {

    @Mock
    private QuestService questService;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private MainPageController mainPageController;

    @Test
    void testMainPage_UserLoggedIn_ReturnsHomePageWithUser() {
        String userId = "1";
        User user = new User();
        user.setLogin("testUser");
        Quest quest1 = new Quest();
        Quest quest2 = new Quest();
        List<Quest> quests = Arrays.asList(quest1, quest2);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(userService.checkIfExists(Long.parseLong(userId))).thenReturn(true);
        when(userService.get(Long.parseLong(userId))).thenReturn(Optional.of(user));
        when(questService.getAll()).thenReturn(quests);

        ModelAndView result = mainPageController.mainPage(userId, request);

        assertEquals(Constants.HOME_PAGE, result.getViewName());
        assertEquals(quests, result.getModel().get(Constants.QUESTS));
        assertEquals(true, result.getModel().get(Constants.LOGGED_IN));
        assertEquals("testUser", result.getModel().get(Constants.USERNAME));
    }

    @Test
    void testMainPage_UserNotLoggedIn_ReturnsHomePageWithoutUser() {
        String userId = "0";
        Quest quest1 = new Quest();
        Quest quest2 = new Quest();
        List<Quest> quests = Arrays.asList(quest1, quest2);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(userService.checkIfExists(Long.parseLong(userId))).thenReturn(false);
        when(questService.getAll()).thenReturn(quests);

        ModelAndView result = mainPageController.mainPage(userId, request);

        assertEquals(Constants.HOME_PAGE, result.getViewName());
        assertEquals(quests, result.getModel().get(Constants.QUESTS));
        assertEquals(false, result.getModel().get(Constants.LOGGED_IN));
        assertEquals(Constants.NOT_LOGGED_IN, result.getModel().get(Constants.USERNAME));
    }
}
