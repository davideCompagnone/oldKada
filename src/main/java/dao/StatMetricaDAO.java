package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import  beens.*;

public class StatMetricaDAO {

	
	 
    private static final String MAX="SELECT MAX(id_metrica) FROM metrica";
    private static final String conta_occorrenze = "select count(*) from octo_dia.metrica where diagnostic_key like ? and id_revision = ?";
    	String getPolicy="select distinct (policy) from octo_dia.metrica where diagnostic_key like ? and id_revision=?";	
    	String getDiagnosticType = "select distinct (diagnosticType) from octo_dia.metrica where diagnostic_key like ? and id_revision=?";
    	String getUnit="select distinct (unit) from octo_dia.metrica where diagnostic_key like ? and id_revision = ?";
    		String minVAlue = "select min(valueNumber) from octo_dia.metrica where diagnostic_key like ? and id_revision = ?";
    		String maxVAlue = "select max(valueNumber) from octo_dia.metrica where diagnostic_key like ? and id_revision = ?";
    		String avgVAlue = "select avg(valueNumber) from octo_dia.metrica where diagnostic_key like ? and id_revision = ?";
        	
    	public Integer contaOccorrenze(String diagnostica, Integer revision ) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        Integer value=0;
        
        try {
            conn = DAO.createConnection();
            preparedStatement = conn.prepareStatement(conta_occorrenze, Statement.RETURN_GENERATED_KEYS);
            
			preparedStatement.setString(1,diagnostica);
			preparedStatement.setInt(2,revision);
	
			
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            
            if (result.next() && result != null) {
                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
            	value=result.getInt(1);
            	//System.out.println(id);
            }
            
            return value;
            
            
        } catch (SQLException e) {
        	e.printStackTrace();
            return -1;
        } finally {
//            try {
//                result.close();
//            } catch (Exception rse) {
//                rse.printStackTrace();
//            }
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
    		
    		
    		public String policy(String diagnostica, Integer revision ) {
    	        Connection conn = null;
    	        PreparedStatement preparedStatement = null;
    	        ResultSet result = null;
    	        
    	        String value="";
    	        
    	        try {
    	            conn = DAO.createConnection();
    	            preparedStatement = conn.prepareStatement(getPolicy, Statement.RETURN_GENERATED_KEYS);
    	            
    				preparedStatement.setString(1,diagnostica);
    				preparedStatement.setInt(2,revision);
    		
    				
    	            preparedStatement.execute();
    	            result = preparedStatement.getResultSet();
    	 
    	            
    	            while(result.next() && result != null) {
    	                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
    	            	value+=result.getString(1)+",";
    	            	//System.out.println(id);
    	            }
    	            
    	            return value;
    	            
    	            
    	        } catch (SQLException e) {
    	        	e.printStackTrace();
    	            return null;
    	        } finally {
//    	            try {
//    	                result.close();
//    	            } catch (Exception rse) {
//    	                rse.printStackTrace();
//    	            }
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
    	   
    		public String diagnosticType(String diagnostica, Integer revision ) {
    	        Connection conn = null;
    	        PreparedStatement preparedStatement = null;
    	        ResultSet result = null;
    	        
    	        String value="";
    	        
    	        try {
    	            conn = DAO.createConnection();
    	            preparedStatement = conn.prepareStatement(getDiagnosticType, Statement.RETURN_GENERATED_KEYS);
    	            
    				preparedStatement.setString(1,diagnostica);
    				preparedStatement.setInt(2,revision);
    		
    				
    	            preparedStatement.execute();
    	            result = preparedStatement.getResultSet();
    	 
    	            
    	            while(result.next() && result != null) {
    	                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
    	            	value+=result.getString(1)+",";
    	            	//System.out.println(id);
    	            }
    	            
    	            return value;
    	            
    	            
    	        } catch (SQLException e) {
    	        	e.printStackTrace();
    	            return null;
    	        } finally {
//    	            try {
//    	                result.close();
//    	            } catch (Exception rse) {
//    	                rse.printStackTrace();
//    	            }
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
    	 
    		public String unit(String diagnostica, Integer revision ) {
    	        Connection conn = null;
    	        PreparedStatement preparedStatement = null;
    	        ResultSet result = null;
    	        
    	        String value="";
    	        
    	        try {
    	            conn = DAO.createConnection();
    	            preparedStatement = conn.prepareStatement(getUnit, Statement.RETURN_GENERATED_KEYS);
    	            
    				preparedStatement.setString(1,diagnostica);
    				preparedStatement.setInt(2,revision);
    		
    				
    	            preparedStatement.execute();
    	            result = preparedStatement.getResultSet();
    	 
    	            
    	            while(result.next() && result != null) {
    	                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
    	            	value+=result.getString(1)+",";
    	            	//System.out.println(id);
    	            }
    	            
    	            return value;
    	            
    	            
    	        } catch (SQLException e) {
    	        	e.printStackTrace();
    	            return null;
    	        } finally {
//    	            try {
//    	                result.close();
//    	            } catch (Exception rse) {
//    	                rse.printStackTrace();
//    	            }
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
    	 
    		public Float min(String diagnostica, Integer revision ) {
    	        Connection conn = null;
    	        PreparedStatement preparedStatement = null;
    	        ResultSet result = null;
    	        
    	        Float value=0f;
    	        
    	        try {
    	            conn = DAO.createConnection();
    	            preparedStatement = conn.prepareStatement(minVAlue, Statement.RETURN_GENERATED_KEYS);
    	            
    				preparedStatement.setString(1,diagnostica);
    				preparedStatement.setInt(2,revision);
    		
    				
    	            preparedStatement.execute();
    	            result = preparedStatement.getResultSet();
    	 
    	            
    	            if (result.next() && result != null) {
    	                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
    	            	value=result.getFloat(1);
    	            	//System.out.println(id);
    	            }
    	            
    	            return value;
    	            
    	            
    	        } catch (SQLException e) {
    	        	e.printStackTrace();
    	            return -1f;
    	        } finally {
//    	            try {
//    	                result.close();
//    	            } catch (Exception rse) {
//    	                rse.printStackTrace();
//    	            }
    	            try {
    	                preparedStatement.close();
    	            } catch (Exception sse) {
    	               // sse.printStackTrace();
    	            }
    	            try {
    	                conn.close();
    	            } catch (Exception cse) {
    	                //cse.printStackTrace();
    	            }
    	        }
    	 
    	        
    	        
    	    }
    	    
    		public Float max(String diagnostica, Integer revision ) {
    	        Connection conn = null;
    	        PreparedStatement preparedStatement = null;
    	        ResultSet result = null;
    	        
    	        Float value=0f;
    	        
    	        try {
    	            conn = DAO.createConnection();
    	            preparedStatement = conn.prepareStatement(maxVAlue, Statement.RETURN_GENERATED_KEYS);
    	            
    				preparedStatement.setString(1,diagnostica);
    				preparedStatement.setInt(2,revision);
    		
    				
    	            preparedStatement.execute();
    	            result = preparedStatement.getResultSet();
    	 
    	            
    	            if (result.next() && result != null) {
    	                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
    	            	value=result.getFloat(1);
    	            	//System.out.println(id);
    	            }
    	            
    	            return value;
    	            
    	            
    	        } catch (SQLException e) {
//    	        	e.printStackTrace();
    	            return -1f;
    	        } finally {
//    	            try {
//    	                result.close();
//    	            } catch (Exception rse) {
//    	                rse.printStackTrace();
//    	            }
    	            try {
    	                preparedStatement.close();
    	            } catch (Exception sse) {
//    	                sse.printStackTrace();
    	            }
    	            try {
    	                conn.close();
    	            } catch (Exception cse) {
//    	                cse.printStackTrace();
    	            }
    	        }
    	 
    	        
    	        
    	    }
    	    
    		public Float media(String diagnostica, Integer revision ) {
    	        Connection conn = null;
    	        PreparedStatement preparedStatement = null;
    	        ResultSet result = null;
    	        
    	        Float value=0f;
    	        
    	        try {
    	            conn = DAO.createConnection();
    	            preparedStatement = conn.prepareStatement(avgVAlue, Statement.RETURN_GENERATED_KEYS);
    	            
    				preparedStatement.setString(1,diagnostica);
    				preparedStatement.setInt(2,revision);
    		
    				
    	            preparedStatement.execute();
    	            result = preparedStatement.getResultSet();
    	 
    	            
    	            if (result.next() && result != null) {
    	                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
    	            	value=result.getFloat(1);
    	            	//System.out.println(id);
    	            }
    	            
    	            return value;
    	            
    	            
    	        } catch (SQLException e) {
//    	        	e.printStackTrace();
    	            return -1f;
    	        } finally {
//    	            try {
//    	                result.close();
//    	            } catch (Exception rse) {
//    	                rse.printStackTrace();
//    	            }
    	            try {
    	                preparedStatement.close();
    	            } catch (Exception sse) {
//    	                sse.printStackTrace();
    	            }
    	            try {
    	                conn.close();
    	            } catch (Exception cse) {
//    	                cse.printStackTrace();
    	            }
    	        }
    	 
    	        
    	        
    	    }
    	    
    		
    public Integer maxID() {
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
