package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

	
	 	public static final String DB_URL = "jdbc:mysql://localhost:3307/";
	    public static final String DB_NAME = "octo_dia";
	    public static final String DB_USER = "octo_user";
	    public static final String DB_PASSWORD = "Octo1234";
	    public static final String timezone= "?serverTimezone=UTC";
	    
	    
	    /**
	 *Il metodo restitusice la connessione al DB 
	 * @return Connection
	 * @author compagnoneda
	 */
	     public static Connection createConnection() {
	        Connection conn = null;
	        try {
	            
	            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	            conn = DriverManager.getConnection(
	                    DB_URL + DB_NAME + timezone, DB_USER, DB_PASSWORD);
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	        	e.printStackTrace();
	        }
	        
	        catch (InstantiationException ex) {
	            ex.printStackTrace();
	        } catch (IllegalAccessException ex) {
	            ex.printStackTrace();
	        
	        }
	        return conn;
	    }


	

}
