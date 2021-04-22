package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import  beens.Diagnostica;
import beens.Metrica;

public class DiagnosticListDAO {
	
	String insertDiagnostic ="INSERT INTO `diagnostic_list` (`id_diagnostic`, `diagnostic_key`) VALUES (?, ?)";
	String insertDiagnosticComplete ="insert into octo_dia.diagnostic_list (id_diagnostic,diagnostic_key,codifica,scala,unita_misura,`minValue`,`maxValue`,infoCodifica,description_key) values (?,?,?,?,?,?,?,?,?)";

	String deleteAll = "DELETE  from diagnostic_list";
	String selectALL = "SELECT *   from diagnostic_list";
	
	public Integer createConf(String s, Integer id) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            conn = DAO.createConnection();
         //inserimento riga per riga 
            preparedStatement = conn.prepareStatement(insertDiagnostic);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,s);
             preparedStatement.execute();
             //result = preparedStatement.getResultSet();
             return id;
            
            
        } catch (SQLException e) {
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
	
	
	public Integer deleteConf() {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            conn = DAO.createConnection();
         //inserimento riga per riga 
            preparedStatement = conn.prepareStatement(deleteAll,Statement.RETURN_GENERATED_KEYS);

             preparedStatement.execute();
//             result = preparedStatement.getResultSet();
             return 1;
            
            
        } catch (SQLException e) {
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


	public int createConfCompleta(Integer id, String diagnostic_type, String codifica, float scala, String misura,
			float min, float max, String info, String description) {
		// TODO Auto-generated method stub
		 Connection conn = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet result = null;
	        
	        try {
	            conn = DAO.createConnection();
	         //inserimento riga per riga 
	        	// (id_diagnostic,diagnostic_key,codifica,scala,unita_misura,`minValue`,`maxValue`,infoCodfica) 

	            preparedStatement = conn.prepareStatement(insertDiagnosticComplete);
	            preparedStatement.setInt(1,id);
	            preparedStatement.setString(2,diagnostic_type);
	            preparedStatement.setString(3,codifica);
	            preparedStatement.setFloat(4,scala);
	            preparedStatement.setString(5,misura);
	            preparedStatement.setFloat(6,min);
	            preparedStatement.setFloat(7,max);
	            preparedStatement.setString(8,info);
	            preparedStatement.setString(9,description);
	             preparedStatement.execute();
	             //result = preparedStatement.getResultSet();
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
	
	
	public ArrayList<Diagnostica> getConfCompleta() {
		// TODO Auto-generated method stub
		 Connection conn = null;
	        PreparedStatement preparedStatement = null;
	        ResultSet result = null;
	        ArrayList<Diagnostica> list= new ArrayList<Diagnostica>();
        	// (id_diagnostic,diagnostic_key,codifica,scala,unita_misura,`minValue`,`maxValue`,infoCodfica) 

	        
	        try {
	            conn = DAO.createConnection();
	         //inserimento riga per riga 
	            preparedStatement = conn.prepareStatement(selectALL);
	            preparedStatement.execute();
	            result = preparedStatement.getResultSet();
	            //ciclare su tutte le tuple della query e per ognunga creare un oggetto
	            while(result.next()) {
	            	Diagnostica d = new Diagnostica();
	            	
	            	d.setId(result.getInt("id_diagnostic"));
	            	d.setDiagnosticKey(result.getString("diagnostic_key"));
	            	d.setCodifica(result.getString("codifica"));
	            	d.setScala(result.getFloat("scala"));
	            	d.setMisura(result.getString("unita_misura"));
	            	d.setMin(result.getFloat("minValue"));
	            	d.setMax(result.getFloat("maxValue"));
	            	d.setInfo(result.getString("infoCodifica"));
	            	d.setEvmDescription(result.getString("description_key"));
	            	
	            	list.add(d);
	            }
	            
	            //
	             return list;
	            
	            
	        } catch (SQLException e) {
	        	e.printStackTrace();
	            return list;
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
   

}
