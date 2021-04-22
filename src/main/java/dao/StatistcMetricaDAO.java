package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import   beens.*;

public class StatistcMetricaDAO {

	private static final String stat = "select  max(valueNumber) , min(valueNumber), avg(valueNumber) as media, " + 
			"m.diagnostic_key, policy , count(value) as occorrenze , unit ,  dl.id_diagnostic ," + 
			"dl.diagnostic_key , codifica, scala, unita_misura, dl.`minValue` ,dl.`maxValue` , dl.infoCodifica , dl.description_key " + 
			"from octo_dia.metrica m, diagnostic_list dl " + 
			"where  id_revision = ? " + 
			"and (m.diagnostic_key =  dl.diagnostic_key or m.diagnostic_key =  dl.description_key )" + 
			"group by m.diagnostic_key , policy , unit " + 
			"order by m.diagnostic_key ,  policy , value asc";
	
	public ArrayList<StatisticMetrica> contaOccorrenze(Integer revision ) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        
        ArrayList<StatisticMetrica> list = new  ArrayList<StatisticMetrica>();
        
        try {
            conn = DAO.createConnection();
            preparedStatement = conn.prepareStatement(stat, Statement.RETURN_GENERATED_KEYS);
            
			
			preparedStatement.setInt(1,revision);
	
			
            preparedStatement.execute();
            result = preparedStatement.getResultSet();
 
            
            while (result.next() && result != null) {
                //utente = new Utente(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getString(5),result.getString(6),result.getInt(7),result.getString(8),result.getInt(9));
            	//value=result.getInt(1);
            	//System.out.println(id);
            	StatisticMetrica sm = new StatisticMetrica();
            	
            	sm.setMax(result.getFloat(1));
            	sm.setMin(result.getFloat(2));
            	sm.setMedia(result.getFloat(3));
            	sm.setOccorrenze(result.getInt(6));
            	
            	Metrica m = new Metrica();
            	m.setDiagnosticKey(result.getString(4));
            	m.setPolicy(result.getString(5));
            	m.setUnit(result.getString(7));
            	sm.setM(m);
            	
            	Diagnostica d = new Diagnostica();
            	d.setId(result.getInt(8));
            	d.setDiagnosticKey(result.getString(9));
            	d.setCodifica(result.getString(10));
            	d.setScala(result.getFloat(11));
            	d.setMisura(result.getString(12));
            	d.setMax(result.getFloat(14));
            	d.setMin(result.getFloat(13));
            	d.setInfo(result.getString(15));
            	d.setEvmDescription(result.getString(16));
            	sm.setD(d);
            	
            	//System.out.println(sm.toString());
            	
            	list.add(sm);
            	
            	
            	
            }
            
            return list;
            
            
        } catch (SQLException e) {
        	e.printStackTrace();
            return list;
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
    	
	
}
