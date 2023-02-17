package estm.dsic.web01.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "NotesServlet",urlPatterns = "/NotesServlet")
public class NotesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "addNote":
                    String title = request.getParameter("title");
                    String description = request.getParameter("description");
                    // Add code here to create a new note with the title and description
                    break;
                case "editNote":
                    // Add code here to edit an existing note
                    break;
                case "deleteNote":
                    // Add code here to delete an existing note
                    break;
            }
        }
        // Add code here to retrieve all notes and forward the request to the JSP
    }

}
