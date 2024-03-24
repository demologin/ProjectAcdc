package nkuzmischev;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class QuestServletTest {

    @Test
    public void testDoPost() throws Exception {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        HttpSession session = Mockito.mock(HttpSession.class);


        when(request.getParameter("answer1")).thenReturn("Принять");
        when(request.getParameter("answer2")).thenReturn("Поднятся");
        when(request.getParameter("answer3")).thenReturn("Рассказать правду");


        when(request.getSession()).thenReturn(session);


        QuestServlet questServlet = new QuestServlet();
        questServlet.doPost(request, response);


        assertEquals("Тебя вернули домой. Победа", session.getAttribute("result"));
    }
}