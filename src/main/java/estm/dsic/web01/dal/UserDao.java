package estm.dsic.web01.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import estm.dsic.web01.Model.User;

public class UserDao {

	
	public boolean exit(User u) {
		try {
			Statement stm=Database.getConnection().createStatement();
			ResultSet rs=stm.executeQuery("SELECT * FROM T_user WHERE email='"+u.getEmail()+"' AND password='"+
					u.getPassword()+"';");
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean isAdmin(User user) throws SQLException {
		Statement statement = Database.getConnection().createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM T_user WHERE email='"+user.getEmail()+"' AND password='"+
				user.getPassword()+"';");
		if(rs.next())
			return rs.getBoolean("isAdmin");
		return false;
	}
	public User getUserByEmail(User user) throws SQLException {
		Statement statement = Database.getConnection().createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM T_user WHERE email='"+user.getEmail()+"';");
		User u = new User();
		if(rs.next()) {
			user.setUsername(rs.getString("NAME"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
		}
		return user;
	}
	public Vector<User> getAllUsers() throws SQLException {
		Statement statement = Database.getConnection().createStatement();
		ResultSet rs=statement.executeQuery("SELECT * FROM T_user;");
		Vector<User> users = new Vector<User>();

		while(rs.next()) {
		User user = new User();
			user.setUsername(rs.getString("NAME"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setId(rs.getInt("id"));
			users.add(user);
		}
		return users;
	}
	public void addUser(User user) throws SQLException {
		Statement statement = Database.getConnection().createStatement();
		statement.executeUpdate("INSERT INTO T_user (NAME,email,password,isAdmin) VALUES ('"+user.getUsername()+"','"+user.getEmail()+"','"+user.getPassword()+"',false);");
	}
	public void deleteUser(int id) throws SQLException {
		Statement statement = Database.getConnection().createStatement();
		statement.execute("DELETE FROM T_user WHERE id="+id+";");
	}
	public void updateUser(User user) throws SQLException {
		Statement statement = Database.getConnection().createStatement();
		statement.executeUpdate("UPDATE T_user SET NAME='"+user.getUsername()+"',email='"+user.getEmail()+"' WHERE email='"+user.getEmail()+"';");
	}

	public User getUser(User user) {
		try {
			Statement stm=Database.getConnection().createStatement();
			ResultSet rs=stm.executeQuery("SELECT * FROM T_user WHERE email='"+user.getEmail()+"' AND password='"+
					user.getPassword()+"';");
			if(rs.next()) {
				user.setUsername(rs.getString("NAME"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setId(rs.getInt("id"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public boolean getEmailByUser(User u){
		PreparedStatement ps;
		try {
			ps = Database.getConnection().prepareStatement("SELECT * FROM T_user WHERE email=?");
			ps.setString(1, u.getEmail());
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;}
}
