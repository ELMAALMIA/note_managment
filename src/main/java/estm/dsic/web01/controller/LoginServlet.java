package estm.dsic.web01.controller;
import estm.dsic.web01.Model.User;
import estm.dsic.web01.dal.Database;
import estm.dsic.web01.services.AdminService;
import estm.dsic.web01.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "LoginServlet",urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static Database database;
    public LoginServlet() throws NamingException, SQLException {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Connection conn = Database.getConnection();
        User user = new User();
        String email= req.getParameter("email");
        String password = req.getParameter("password");
        user.setEmail(email);
        user.setPassword(password);
        HttpSession httpSession = req.getSession();
        UserService userService = new UserService();
        if (userService.checkUser(user)) {
            // If the authentication is successful, redirect the user to the welcome pageù
            try {
                List<User> userList = null;
                if (userService.isAdmin(user)) {
                    httpSession.setAttribute("admin", user);
                    AdminService adminService = new AdminService();
                    userList = adminService.getAllUsers();
                    req.setAttribute("users", userList);
                    req.getRequestDispatcher("AdminVue/adminPage.jsp").forward(req, resp);
                } else {
                    System.out.printf("user");
//                    req.setAttribute();

                    httpSession.setAttribute("user", user);
                    req.getRequestDispatcher("UserVue/TasksPage.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // If the authentication is unsuccessful, send an error message back to the login page
            resp.sendRedirect("ereur.jsp");
        }

    }
}
