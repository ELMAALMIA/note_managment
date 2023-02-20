package estm.dsic.web01.services;

import estm.dsic.web01.Model.User;
import estm.dsic.web01.dal.UserDao;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class AdminService {
    UserDao userdao=new UserDao();
    public User getUserByEmail(User u) throws SQLException {
        return userdao.getUserByEmail(u);
    }
    public  Vector<User> getAllUsers() throws SQLException {
        return userdao.getAllUsers();
    }
    public void addUser(User u) throws SQLException {
        userdao.addUser(u);
    }
    public void deleteUser(int id) throws SQLException {
        userdao.deleteUser(id);
    }
    public void updateUser(User u) throws SQLException {
        userdao.updateUser(u);
    }
    public void saveSession(User u) throws SQLException {
        System.out.printf("");
    }

}
