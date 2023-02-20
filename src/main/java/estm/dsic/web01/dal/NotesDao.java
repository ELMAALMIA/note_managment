package estm.dsic.web01.dal;

import estm.dsic.web01.Model.Notes;
import estm.dsic.web01.Model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class NotesDao {
    private static Connection conn;
    private static Statement stmt;
    private static Notes note;
    public static void addNote(Notes n, User user) throws SQLException {
        conn = Database.getConnection();
        String query = "INSERT INTO Tasks (title, description, DATE_task, user_id) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, n.getTitle());
            pstmt.setString(2, n.getDescription());
            pstmt.setTimestamp(3, new Timestamp(n.getDate().getTime()));
            pstmt.setInt(4, user.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void updateNote(Notes notes) throws SQLException {
        // Logic for updating an existing note
        stmt= conn.createStatement();
        stmt.executeUpdate("UPDATE Tasks SET title =" + notes.getTitle() + ", content = " + notes.getDescription() + ", DATE_task = " + notes.getDate()
                + ", user_id = " + notes.getUser_id() + " WHERE id = " + notes.getId() + ";");
    }
    public static void deleteNote(int id) throws SQLException {
        // Logic for deleting a note
        stmt = conn.createStatement();
        stmt.executeUpdate("DELETE FROM tasks WHERE id ='"+id+"'");

    }
    public static Vector<Notes> getNotes() throws SQLException {
        // Logic for getting all notes
        Vector<Notes> tasks = new Vector<Notes>();
        conn = Database.getConnection();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tasks;");
        while (rs.next()) {
            note = new Notes();
            note.setId(rs.getInt("id"));
            note.setTitle(rs.getString("title"));
            note.setDescription(rs.getString("description"));
            note.setDate(rs.getDate("DATE_task"));
            note.setUser_id(rs.getInt("user_id"));
            tasks.add(note);
        }

        return tasks;
    }
    public static Vector<Notes> getNotesByUserId(int id) throws SQLException {
        // Logic for getting all notes by user id
        Vector<Notes> tasks = new Vector<Notes>();
        conn = Database.getConnection();
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tasks WHERE user_id = '"+id+"';");
        while (rs.next()) {
            note = new Notes();
            note.setId(rs.getInt("id"));
            note.setTitle(rs.getString("title"));
            note.setDescription(rs.getString("description"));
            note.setDate(rs.getDate("DATE_task"));
            note.setUser_id(rs.getInt("user_id"));
            tasks.add(note);
        }

        return tasks;
    }
    public static void getNoteById(int id) throws SQLException {
        // Logic for getting a note by id
        stmt = conn.createStatement();
        stmt.executeQuery("SELECT * FROM notes WHERE id = 'id';");
    }
    public static void getNotesByDate(LocalDate date) throws SQLException {
        // Logic for getting all notes by date
        stmt = conn.createStatement();
        stmt.executeQuery("SELECT * FROM notes WHERE DATE_task = 'date';");
    }
}
