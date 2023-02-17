package estm.dsic.web01.controller;

import estm.dsic.web01.Model.User;
import estm.dsic.web01.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "signUpServlet",urlPatterns = "/signUpServlet")
public class signUpServlet extends HttpServlet {
    UserService userService =new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setUsername(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        System.out.printf("hi servlete");
        try {
            userService.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       resp.sendRedirect("index.jsp");
    }
}
