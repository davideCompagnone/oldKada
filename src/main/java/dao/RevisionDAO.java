package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beens.Metrica;

public class RevisionDAO {

	private static final String MAX="SELECT MAX(id_revision) FROM revision";
	private static final String CREA_REVISION = "INSERT INTO `revision` (`id_revision`, `date`, note) VALUES (?, ?,?)";
    
	
	
	  public Integer createRevision(String note) {
	        Connection conn = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet result = null;
	        
	        try {
	            conn = DAO.createConnection();
	            preparedStatement = conn.prepareStatement(CREA_REVISION, Statement.RETURN_GENERATED_KEYS);
	            
	            Integer id = maxID()+1;
				preparedStatement.setInt(1,id);
				
				
				Date date = new Date(System.currentTimeMillis());
				
	            java.sql.Date dat= new java.sql.Date(date.getTime()); 
	            
				preparedStatement.setDate(2, dat);	
				preparedStatement.setString(3, note);		
	           
	            preparedStatement.execute();
	            result = preparedStatement.getResultSet();
	 
	            
	            return id;
	            
	            
	        } catch (SQLException e) {
	        	  e.printStackTrace();
	            return -1;
	        } finally {
//	            try {
//	                result.close();
//	            } catch (Exception rse) {
//	                rse.printStackTrace();
//	            }
	            try {
	                preparedStatement.close();
	            } catch (Exception sse) {
	                sse.printStackTrace();
	            }
	            try {
	                conn.close();
	            } catch (Exception cse) {
	                cse.printStackTrace();
	            }
	        }
	 
	        
	        
	    }
	   
	
	public static Integer maxID() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            conn = DAO.createConnection();
            preparedStatement = conn.prepareStatement(MAX, Statement.RETURN_GENERATED_KEYS);
            Integer id=0;
			
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
  
            if (result.next() && result != null) {
                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
            	id=result.getInt(1);
            	System.out.println(id);
            }
            
            return id;
            
            
        } catch (SQLException e) {
            return -1;
        } finally {
            try {
                result.close();
            } catch (Exception rse) {
                rse.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (Exception sse) {
                sse.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception cse) {
                cse.printStackTrace();
            }
        }
 
        
        
    }
   
	
}
