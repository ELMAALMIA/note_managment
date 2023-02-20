package estm.dsic.web01.controller;

import estm.dsic.web01.Model.Notes;
import estm.dsic.web01.Model.User;
import estm.dsic.web01.dal.NotesDao;
import estm.dsic.web01.services.NotesSevice;
import estm.dsic.web01.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@WebServlet(name = "NotesServlet",urlPatterns = "/NotesServlet")
public class NotesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String title ;
        String description ;
       String noteId ;// Assuming you have passed the ID in a parameter named "i

        Date date =Date.from(LocalDate.now().atStartOfDay().toInstant(java.time.ZoneOffset.UTC));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (action != null) {
            switch (action) {
                case "AddNote":
                    title= request.getParameter("title");
                    description = request.getParameter("description");
                    // Retrieve the current list of notes from the session
                  Vector<Notes> notesList = (Vector<Notes>) session.getAttribute("notesList");
                    // If the list is null, create a new one
                    if (notesList == null) {
                        notesList = new Vector<>();
                    }
                    // Create a new Notes object and add it to the list
                    Notes note = new Notes(notesList.size(),title, description, date);
                    notesList.add(note);
                    // Store the updated list in the session
                    session.setAttribute("notesList", notesList);
                    request.getRequestDispatcher("UserVue/notes.jsp").forward(request,response);
                    break;
                case "modifyNote":
                    title = request.getParameter("edit-title");
                    description = request.getParameter("edit-description");
                    noteId = request.getParameter("edit-id");
                    System.out.println(noteId);
                    // Retrieve the current list of notes from the session
                            Vector notesList1 = (Vector) session.getAttribute("notesList");
                    // If the list is null, create a new one
                    if (notesList1 == null) {
                        notesList1 = new Vector();
                    }
                    for (int i = 0; i < notesList1.size(); i++) {
                        System.out.println(notesList1.get(i).toString());
                    }
                    // Create a new Notes object and add it to the list
                    Notes note1 = new Notes(Integer.parseInt(noteId),title, description, date);
                    System.out.printf(note1.toString());

                    notesList1.set(Integer.parseInt(noteId),note1);
                    // Store the updated list in the session
                    session.setAttribute("notesList", notesList1);
                    request.getRequestDispatcher("UserVue/notes.jsp").forward(request,response);


                    break;
                case "deleteNote":
                    noteId = request.getParameter("id_delete");
                    // Retrieve the current list of notes from the session
                    Vector notesList2 = (Vector) session.getAttribute("notesList");
                    // If the list is null, create a new one
                    if (notesList2 == null) {
                        notesList2 = new Vector();
                    }
                    // Create a new Notes object and add it to the list
                    notesList2.remove(Integer.parseInt(noteId));
                    // Store the updated list in the session
                    session.setAttribute("notesList", notesList2);
                    request.getRequestDispatcher("UserVue/notes.jsp").forward(request,response);
                    break;
                    // save button it for save data from session to database(save all change -> delete update and add)
                case "save":
                    NotesSevice notesSevice = new NotesSevice();
                    Vector<Notes> notesList3 = (Vector<Notes>) session.getAttribute("notesList");
                    try {
                        notesSevice.addNotesSession(notesList3 ,user);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    request.getRequestDispatcher("UserVue/notes.jsp").forward(request,response);
                    break;
                    case "save change":
                        NotesSevice notesS = new NotesSevice();
                        Vector<Notes> notesListsave = (Vector<Notes>) session.getAttribute("notesList");
                        try {
                            notesS.addNotesSession(notesListsave ,user);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        request.getRequestDispatcher("index.jsp").forward(request,response);
                        break;
                case "Don't save":
                    UserService userService = new UserService();
                    session.invalidate(); // Invalidates the session, clearing any session attributes.
                    response.sendRedirect("index.jsp"); // Redirect to the login page.
                    break;
            }
            // Add code here to retrieve all notes and forward the request to the JSP
        }

    }
}
