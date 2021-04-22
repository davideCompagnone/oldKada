package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import beens.*;

public class MetricaDAO {

	
	 
    private static final String MAX="SELECT MAX(id_metrica) FROM metrica";
    private static final String CREA_METRICA = "INSERT INTO `metrica` (`id_metrica`, `id_revision`, `diagnostic_key`, `devideId`, `voucherId`, `vin`, `timestamp`, `value`, `unit`,`policy`, `diagnosticType`,`valueNumber`,date,time, source ) VALUES (?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
    public Integer createMetrica(Metrica metrica, Integer id_metrica, Integer id_revision) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            conn = DAO.createConnection();
            preparedStatement = conn.prepareStatement(CREA_METRICA, Statement.RETURN_GENERATED_KEYS);
            
			preparedStatement.setInt(1,id_metrica);
			preparedStatement.setInt(2,id_revision);
			preparedStatement.setString (3 ,metrica.getDiagnosticKey());
			preparedStatement.setString (4,metrica.getDeviceId());
			preparedStatement.setString (5 ,metrica.getVoucherId());
			preparedStatement.setString (7 ,metrica.getTimestamp());
			preparedStatement.setString (8 ,metrica.getValue());
			preparedStatement.setString (9 ,metrica.getUnit());
			preparedStatement.setString (10 ,metrica.getPolicy());
			preparedStatement.setString (11 ,metrica.getDiagnosticType());
			preparedStatement.setString (6 ,metrica.getVin());
			preparedStatement.setFloat (12 ,metrica.getValueNumber());
			Date date = new Date(Long.parseLong(metrica.getTimestamp()));
			preparedStatement.setDate(13, date);// (13 ,metrica.metrica.getTimestamp());
			
            preparedStatement.execute();
            //result = preparedStatement.getResultSet();
 
            
            return id_metrica;
            
            
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
    
    public Integer insertMetricList (ArrayList<Metrica> list, Integer id_metrica, Integer id_revision, String source) {
        Connection conn = null;
        
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        try {
            conn = DAO.createConnection();
            
            
            preparedStatement = conn.prepareStatement(CREA_METRICA, Statement.RETURN_GENERATED_KEYS);
            Integer index =  0;
            
            for (Metrica metrica : list ) {
			preparedStatement.setInt(1,id_metrica);
			preparedStatement.setInt(2,id_revision);
			preparedStatement.setString (3 ,metrica.getDiagnosticKey());
			preparedStatement.setString (4,metrica.getDeviceId());
			preparedStatement.setString (5 ,metrica.getVoucherId());
			preparedStatement.setString (7 ,metrica.getTimestamp());
			preparedStatement.setString (8 ,metrica.getValue());
			preparedStatement.setString (9 ,metrica.getUnit());
			preparedStatement.setString (10 ,metrica.getPolicy());
			preparedStatement.setString (11 ,metrica.getDiagnosticType());
			preparedStatement.setString (6 ,metrica.getVin());
			preparedStatement.setString (15 ,source);
			
			
//			System.out.println(metrica.getDiagnosticKey());
//			System.out.println(metrica.getValue());
////			System.out.println(metrica.getValueNumber());
//			System.out.println(metrica.getUnit());
			preparedStatement.setFloat (12 ,metrica.getValueNumber());
//			Date date = new Date(Long.parseLong(metrica.getTimestamp()));
//			Time time = new Time(Long.parseLong(metrica.getTimestamp()));
			//DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.mmm" );
		
			preparedStatement.setDate(13, metrica.getDate());// (13 ,metrica.metrica.getTimestamp());
			preparedStatement.setTime(14, metrica.getTime());
            preparedStatement.execute();
            
            System.out.println("Inserita metrica con id: "+ id_metrica + "- index: " + index++ );
            id_metrica++;
            //result = preparedStatement.getResultSet();
            }
 
            
            return id_metrica;
            
            
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
