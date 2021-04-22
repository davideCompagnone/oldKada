package beens;

public class Diagnostica {
	
	private int id;
	private String diagnosticKey;
	private String codifica;
	private Float scala;
	private String misura;
	private Float min;
	private Float max;
	private String info;
	private String evmDescription;
	
	
	public Diagnostica(int id, String diagnosticKey, String codifica, Float scala, String misura, Float min, Float max,
			String info, String evmDescription) {
		super();
		this.id = id;
		this.diagnosticKey = diagnosticKey;
		this.codifica = codifica;
		this.scala = scala;
		this.misura = misura;
		this.min = min;
		this.max = max;
		this.info = info;
		this.evmDescription = evmDescription;
	}


	public String getEvmDescription() {
		return evmDescription;
	}


	public void setEvmDescription(String evmDescription) {
		this.evmDescription = evmDescription;
	}


	public Diagnostica() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDiagnosticKey() {
		return diagnosticKey;
	}


	public void setDiagnosticKey(String diagnosticKey) {
		this.diagnosticKey = diagnosticKey;
	}


	public String getCodifica() {
		return codifica;
	}


	public void setCodifica(String codifica) {
		this.codifica = codifica;
	}


	public Float getScala() {
		return scala;
	}


	public void setScala(Float scala) {
		this.scala = scala;
	}


	public String getMisura() {
		return misura;
	}


	public void setMisura(String misura) {
		this.misura = misura;
	}


	public Float getMin() {
		return min;
	}


	public void setMin(Float min) {
		this.min = min;
	}


	public Float getMax() {
		return max;
	}


	public void setMax(Float max) {
		this.max = max;
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}
	
	
	

}
