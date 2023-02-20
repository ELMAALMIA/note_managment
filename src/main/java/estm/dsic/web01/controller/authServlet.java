package estm.dsic.web01.controller;

import estm.dsic.web01.Model.Notes;
import estm.dsic.web01.Model.User;
import estm.dsic.web01.dal.Database;
import estm.dsic.web01.services.NotesSevice;
import estm.dsic.web01.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "authServlet",urlPatterns = "/authServlet")
public class authServlet extends HttpServlet
{
    private static final long serialVersionUID = 1L;
    static Database database;
    User user = null;
    UserService userService = new UserService();
    Connection conn =null;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        user = new User();
        user.setEmail(req.getParameter("email"));
        user.setPassword( req.getParameter("password"));
        HttpSession httpSession = req.getSession();
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "login":
                    userService.login(req,resp,user,httpSession);
                    break;
                case "logout":
                    userService.logout(req,resp);
                    break;
                case "signUp":
                    try {
                        user.setUsername(req.getParameter("username"));
                        userService.signup(req,resp,user);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }

        }
    }
}
