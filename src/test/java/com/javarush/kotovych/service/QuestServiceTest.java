package com.javarush.kotovych.service;

import com.javarush.kotovych.quest.Quest;
import com.javarush.kotovych.repository.QuestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestServiceTest {

    @Mock
    private QuestRepository questRepository;

    @InjectMocks
    private QuestService questService;

    @Test
    void createQuestAndDelete_SuccessfulCreationAndDeletion() {
        Quest quest = new Quest();
        quest.setName("Test Quest");
        questService.create(quest);
        questService.delete(quest);
        verify(questRepository, times(1)).create(quest);
        verify(questRepository, times(1)).delete(quest);
    }

    @Test
    void getAllQuests_ReturnsCorrectNumberOfQuests() {
        Quest quest1 = new Quest();
        Quest quest2 = new Quest();
        List<Quest> quests = Arrays.asList(quest1, quest2);
        when(questRepository.getAll()).thenReturn(quests);
        Collection<Quest> result = questService.getAll();
        assertEquals(2, result.size());
    }

    @Test
    void updateQuest_SuccessfulUpdate() {
        Quest quest = new Quest();
        quest.setName("Test Quest");
        questService.update(quest);
        verify(questRepository, times(1)).update(quest);
    }

    @Test
    void deleteQuest_SuccessfulDeletion() {
        Quest quest = new Quest();
        quest.setName("Test Quest");
        questService.delete(quest);
        verify(questRepository, times(1)).delete(quest);
    }

    @Test
    void getQuestById_QuestExists_ReturnsQuest() {
        Quest quest = new Quest();
        quest.setId(1L);
        when(questRepository.get(1L)).thenReturn(Optional.of(quest));
        Optional<Quest> result = questService.get(1L);
        assertTrue(result.isPresent());
        assertEquals(quest, result.get());
    }

    @Test
    void getQuestById_QuestDoesNotExist_ReturnsEmptyOptional() {
        when(questRepository.get(1L)).thenReturn(Optional.empty());
        Optional<Quest> result = questService.get(1L);
        assertFalse(result.isPresent());
    }

    @Test
    void getQuestByName_QuestExists_ReturnsQuest() {
        Quest quest = new Quest();
        quest.setName("Test Quest");
        when(questRepository.get("Test Quest")).thenReturn(Optional.of(quest));
        Optional<Quest> result = questService.get("Test Quest");
        assertTrue(result.isPresent());
        assertEquals(quest, result.get());
    }

    @Test
    void getQuestByName_QuestDoesNotExist_ReturnsEmptyOptional() {
        when(questRepository.get("Nonexistent Quest")).thenReturn(Optional.empty());
        Optional<Quest> result = questService.get("Nonexistent Quest");
        assertFalse(result.isPresent());
    }

    @Test
    void createIfNotExists_QuestDoesNotExist_CreatesQuest() {
        Quest quest = new Quest();
        quest.setName("New Quest");
        when(questRepository.get("New Quest")).thenReturn(Optional.empty());
        questService.createIfNotExists(quest);
        verify(questRepository, times(1)).create(quest);
    }

    @Test
    void createIfNotExists_QuestExists_DoesNotCreateQuest() {
        Quest quest = new Quest();
        quest.setName("Existing Quest");
        when(questRepository.get("Existing Quest")).thenReturn(Optional.of(quest));
        questService.createIfNotExists(quest);
        verify(questRepository, never()).create(quest);
    }
}
