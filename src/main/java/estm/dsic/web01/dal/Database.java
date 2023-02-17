package estm.dsic.web01.dal;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Database {
	private static Connection cnx;


	public static Connection getConnection() {
		
		if (cnx==null)
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/db_notes");
			 cnx = ds.getConnection();
			if(cnx == null) {
					System.out.println("error connecction");
			}
			return cnx;
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnx;
		
	}
}
