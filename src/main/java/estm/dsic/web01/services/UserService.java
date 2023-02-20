package estm.dsic.web01.services;

import estm.dsic.web01.Model.User;
import estm.dsic.web01.dal.NotesDao;
import estm.dsic.web01.dal.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class UserService {
	
	private UserDao userdao=new UserDao();
	
	public boolean checkUser(User u){
		return userdao.exit(u);

	}
	public void addUser(User u) throws SQLException {
		userdao.addUser(u);
	}

	public boolean isAdmin(User u) throws SQLException {
		return userdao.isAdmin(u);
	}
	AdminService adminService = new AdminService();
	public void login(HttpServletRequest httpRequest, HttpServletResponse httpResponse, User user, HttpSession httpSession) throws ServletException, IOException {
		if (checkUser(user)) {
			// If the authentication is successful, redirect the user to the welcome pageù
			user = userdao.getUser(user);
			try {
				Vector<User> userList = new Vector<>();
				if (isAdmin(user)) {
					httpSession.setAttribute("admin", user);
					httpRequest.setAttribute("users", adminService.getAllUsers());
					AdminService adminService = new AdminService();
					userList = adminService.getAllUsers();
					httpSession.setAttribute("users", userList);
					httpRequest.getRequestDispatcher("AdminVue/adminPage.jsp").forward(httpRequest, httpResponse);
				} else {
					try {
						httpSession.setAttribute("notesList", NotesDao.getNotesByUserId(user.getId()));
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
					httpSession.setAttribute("user", user);
					httpRequest.getRequestDispatcher("UserVue/notes.jsp").forward(httpRequest, httpResponse);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} else {
			// If the authentication is unsuccessful, send an error message back to the login page
			httpResponse.sendRedirect("ereur.jsp");
		}
	}


	public void logout( HttpServletRequest request , HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate(); // Invalidates the session, clearing any session attributes.
		response.sendRedirect("index.jsp"); // Redirect to the login page.
	}
	public void signup(HttpServletRequest request , HttpServletResponse response ,User user) throws SQLException, IOException, ServletException {
		boolean exist = userdao.getEmailByUser(user);
		System.out.println(exist + "");
		if (exist) {
			request.setAttribute("message", "Email already exists");
			request.getRequestDispatcher("/UserVue/signUpPage.jsp").forward(request,response);
		} else {
			addUser(user);
			response.sendRedirect("index.jsp");
		}
	}
	}


