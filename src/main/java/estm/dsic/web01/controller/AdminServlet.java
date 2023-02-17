package estm.dsic.web01.controller;
import estm.dsic.web01.Model.User;
import estm.dsic.web01.services.AdminService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@WebServlet(name = "AdminServlet",urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    // list of users
List<User> users = new ArrayList<>();
    User user = new User();
    AdminService adminService = new AdminService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("addUser")) {
            // Logic for adding a new user
            user.setUsername(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            try {
                addUser(user);
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
//            user.setPassword(request.getParameter("passwordmodify"));
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
            user.setId(Integer.parseInt(request.getParameter("id")));
            // Logic for deleting a user
            try {
                deleteUser(user.getId());
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

