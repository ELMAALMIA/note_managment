package estm.dsic.web01.controller;
import estm.dsic.web01.Model.User;
import estm.dsic.web01.services.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "AdminServlet",urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    // vector to store the users
    User user = new User();
    AdminService adminService = new AdminService();
    Vector users = new Vector();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("AddUser")) {
            // Logic for adding a new user
            user.setUsername(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            try {
                adminService.addUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                users = adminService.getAllUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("users",users);
            request.getRequestDispatcher("AdminVue/adminPage.jsp").forward(request,response);

        } else if (action.equals("updateUser")) {
            // Logic for updating an existing user
            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("emailmodify"));
            try {
                updateUser(user);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                users = adminService.getAllUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("users",users);
            request.getRequestDispatcher("AdminVue/adminPage.jsp").forward(request,response);
        } else if (action.equals("deleteUser")) {
            user.setId(Integer.parseInt(request.getParameter("id_delete")));
            // Logic for deleting a user
            try {
                deleteUser(user.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // fefresh the list of users
            try {
                users = adminService.getAllUsers();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("users",users);
            request.getRequestDispatcher("AdminVue/adminPage.jsp").forward(request,response);
        }else if (action.equals("save")){
            // Logic for saving a user
            HttpSession session = request.getSession();
            try {
                request.setAttribute("users",adminService.getAllUsers());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("AdminVue/adminPage.jsp").forward(request,response);
        }
    }
    private void addUser(User user) throws SQLException {
        // Logic to add a new user to the data source
        adminService.addUser(user);

    }
    private void updateUser(User user) throws SQLException {
        // Logic to update an existing user in the data source
        adminService.updateUser(user);
    }
    private void deleteUser(int userId) throws SQLException{
        // Logic to delete an existing user from the data source
        adminService.deleteUser(userId);
    }
}

