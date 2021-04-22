package beens;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Metrica {
	
	private String diagnosticKey;
	private String deviceId;
	private String voucherId;
	private String vin;
	private String timestamp;
	private String value;
	private String unit;
	private String policy;
	private String diagnosticType;
	
	private Date date; 
	private Time time;
	 


	
	
	
	


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDateTime() {
		this.date= new Date(Long.parseLong(timestamp));
		this.time= new Time(Long.parseLong(timestamp));
	}
	
	public void setDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        java.util.Date parsed;
        java.sql.Date sql =null;
		try {
			parsed = format.parse(date);
//			System.out.println(parsed);
		    sql = new java.sql.Date(parsed.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   
        
		this.date= sql;
		
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}
	
	public void setTime(String time) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date parsed;
        java.sql.Time sql =null;
		try {
			parsed = format.parse(time);
		    sql = new java.sql.Time(parsed.getTime());
//			System.out.println(parsed);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   
        
		this.time= sql;
	}

	@Override
	public String toString() {
		return "Metrica [diagnosticKey=" + diagnosticKey + ", deviceId=" + deviceId + ", voucherId=" + voucherId
				+ ", vin=" + vin + ", timestamp=" + timestamp + ", value=" + value + ", unit=" + unit + ", policy="
				+ policy + ", diagnosticType=" + diagnosticType + "]";
	}
	
	public String toCSV() {
		return diagnosticKey + ";" + deviceId + ";" + voucherId
		+ ";" + vin + ";" + timestamp + ";" + value + ";" + unit + ";"
		+ policy + ";" + diagnosticType ;
		
	}
	public String getDiagnosticKey() {
		return diagnosticKey;
	}
	public void setDiagnosticKey(String diagnosticKey) {
		this.diagnosticKey = diagnosticKey;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(String voucherId) {
		this.voucherId = voucherId;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		this.date= new Date(Long.parseLong(timestamp));
		this.time= new Time(Long.parseLong(timestamp));
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPolicy() {
		return policy;
	}
	public void setPolicy(String policy) {
		this.policy = policy;
	}
	public String getDiagnosticType() {
		return diagnosticType;
	}
	public void setDiagnosticType(String diagnosticType) {
		this.diagnosticType = diagnosticType;
	}
	
	public Float getValueNumber() {
		
		if(this.diagnosticKey.compareToIgnoreCase("0_10000_faultraw") != 0  && this.diagnosticKey.compareToIgnoreCase("0_573_dtce") != 0 && this.diagnosticKey.compareToIgnoreCase("Fault Report RAW DATA")!= 0 && this.diagnosticKey.compareToIgnoreCase("DTC code Energica")!= 0   ) return Float.valueOf(this.value);
		else {return -1f;}
	}

 
}
