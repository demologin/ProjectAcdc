package nkuzmischev;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/quest")
public class QuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message", "Добро пожаловать на страницу start.jsp");
        request.getRequestDispatcher("start.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String answer1 = request.getParameter("answer1");
        if ("Отклонить".equals(answer1)) {
            session.setAttribute("result", "Ты отклонил вызов. Поражение");
        } else if ("Принять".equals(answer1)) {
            String answer2 = request.getParameter("answer2");
            if ("Отказаться".equals(answer2)) {
                session.setAttribute("result", "Ты отказался от переговоров. Поражение");
            } else if ("Поднятся".equals(answer2)) {
                String answer3 = request.getParameter("answer3");
                if ("Согласиться".equals(answer3)) {
                    session.setAttribute("result", "Твою ложь разоблачили. Поражение");
                } else if ("Рассказать правду".equals(answer3)) {
                    session.setAttribute("result", "Тебя вернули домой. Победа");
                }
            }
        }

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}