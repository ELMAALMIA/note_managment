package estm.dsic.web01.services;

import estm.dsic.web01.Model.User;
import estm.dsic.web01.dal.UserDao;

import java.sql.SQLException;
import java.util.List;

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
	}}

