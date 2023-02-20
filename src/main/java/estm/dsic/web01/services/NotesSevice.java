package estm.dsic.web01.services;

import estm.dsic.web01.Model.Notes;
import estm.dsic.web01.Model.User;
import estm.dsic.web01.dal.NotesDao;

import java.sql.SQLException;
import java.util.Vector;

public class NotesSevice {
    static NotesDao notesDao=new NotesDao();
    public static Vector<Notes> getAllNotes() throws SQLException {
        return notesDao.getNotes();
    }
    public static void addNotes(Notes n,User user) throws SQLException {
        notesDao.addNote(n, user );
    }
    public static void updateNotes(Notes n) throws SQLException {
        notesDao.updateNote(n);
    }
    public static void deleteNotes(int id) throws SQLException {
        notesDao.deleteNote(id);
    }
    public void addNotesSession(Vector<Notes> notesList, User user) throws SQLException {
        Vector<Notes> notesListDB = notesDao.getNotes();
        for (Notes note : notesList) {
            System.out.printf(note.getTitle()+" "+note.getDescription());
            if (notesListDB.contains(note)) {
                notesDao.updateNote(note);
            } else {
                notesDao.addNote(note, user);
            }
        }
        for (Notes note : notesListDB) {
            if (!notesList.contains(note)) {
                notesDao.deleteNote(note.getId());
            }
        }
    }


}
