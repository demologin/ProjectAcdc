package com.javarush.kotovych.constants;

import java.util.List;

public class Constants {
    public static final String NOT_LOGGED_IN = "not logged in";
    public static final String QUESTS = "quests";
    public static final String LOGGED_IN = "loggedIn";
    public static final String CURRENT_PART = "currentPart";
    public static final String START = "start";
    public static final String MAIN_PAGE_REDIRECT = "redirect:/";
    public static final String NAME = "name";
    public static final String HOME_PAGE = "home";
    public static final String GAME_OVER = "game-over";
    public static final String QUEST = "quest";
    public static final String QUESTION = "question";
    public static final String WIN_TEMPLATE = "winTemplate";
    public static final String GAME_OVER_TEMPLATE = "game-over-template";
    public static final String QUEST_TEMPLATE = "questTemplate";
    public static final String EDIT_USER = "edit-user";
    public static final String USER = "user";
    public static final String SIGNUP = "signup";
    public static final String USERS = "users";
    public static final String USER_LIST = "user-list";
    public static final String CREATE_QUEST = "create-quest";
    public static final String FAILED_TO_CREATE_USER_BECAUSE_IT_ALREADY_EXISTS = "Failed to create user because it already exists";
    public static final String USER_WITH_USERNAME_CREATED = "User with username {} created";
    public static final String DEFAULT_ID = "0";
    public static final String JSON = "json";
    public static final String LOGIN = "login";
    public static final String USER_NOT_FOUND_LOGGER = "User {} not found";
    public static final String ERROR = "error";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String COULD_NOT_PARSE_QUEST_JSON = "Could not parse quest json";
    public static final String REDIRECTING_TO_QUEST_LOG = "redirecting to quest {}";
    public static final String USER_IS_TRYING_AGAIN_QUEST_LOG = "user is trying again quest {}";
    public static final String USER_EDITS_ACCOUNT_LOG = "user {} edits account";
    public static final String ACCOUNT_UPDATED_LOG = "account {} updated";
    public static final String USER_LOGGED_IN_LOG = "user {} logged in";
    public static final String QUEST_CREATED_LOG = "quest {} created";

    public static final int DEFAULT_COOKIE_LIVING_TIME = 24 * 60 * 60;

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ID = "id";
    public static final String WIN = "win";
    public static final List<String> DEFAULT_QUESTS = List.of("/javaQuest.json");
    public static final String REDIRECT_QUEST_NAME = "redirect:/quest?name=";
}
