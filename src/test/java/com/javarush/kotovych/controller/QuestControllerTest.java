package com.javarush.kotovych.controller;

import com.javarush.kotovych.constants.Constants;
import com.javarush.kotovych.entity.User;
import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.quest.Question;
import com.javarush.kotovych.service.QuestService;
import com.javarush.kotovych.service.UserService;
import com.javarush.kotovych.util.SessionAttributeSetter;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestControllerTest {

    @Mock
    private QuestService questService;

    @Mock
    private UserService userService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private QuestController questController;

    @Test
    void testShowQuest_UserLoggedIn_ReturnsQuestPage() {
        String questName = "TestQuest";
        String currentPart = "start";
        long userId = 1L;
        Quest quest = new Quest();
        quest.setName(questName);

        Question question = new Question();
        question.setSituation("Test situation");
        question.setText("Test question");
        question.setFirstAnswer("Answer 1");
        question.setSecondAnswer("Answer 2");
        question.setFirstNextQuestion("Next question 1");
        question.setSecondNextQuestion("Next question 2");

        Map<String, Question> questions = new HashMap<>();
        questions.put(currentPart, question);

        quest.setQuestions(questions);

        HttpSession session = mock(HttpSession.class);
        User user = new User();
        user.setId(userId);

        when(userService.checkIfExists(userId)).thenReturn(true);
        when(userService.get(userId)).thenReturn(Optional.of(user));
        when(request.getSession()).thenReturn(session);
        when(questService.get(questName)).thenReturn(Optional.of(quest));
        when(questService.checkIfExists(questName)).thenReturn(true);

        ModelAndView result = questController.showQuest(questName, currentPart, userId, request);

        assertEquals(Constants.QUEST_TEMPLATE, result.getViewName());
        assertEquals(quest, result.getModel().get(Constants.QUEST));
        assertEquals(question, result.getModel().get(Constants.QUESTION));
    }
}
