package beens;

public class StatisticMetrica  {

	private Metrica m;
	private Diagnostica d;
	private Float max;
	private Float min;
	private Float media;
	private Integer occorrenze;
	
	
	public Metrica getM() {
		return m;
	}
	public void setM(Metrica m) {
		this.m = m;
	}
	public Diagnostica getD() {
		return d;
	}
	public void setD(Diagnostica d) {
		this.d = d;
	}
	
	public Float getMax() {
		return max;
	}
	public void setMax(Float max) {
		this.max = max;
	}
	public Float getMin() {
		return min;
	}
	public void setMin(Float min) {
		this.min = min;
	}
	public Float getMedia() {
		return media;
	}
	public void setMedia(Float media) {
		this.media = media;
	}
	public Integer getOccorrenze() {
		return occorrenze;
	}
	public void setOccorrenze(Integer occorrenze) {
		this.occorrenze = occorrenze;
	}
	
	public boolean checkMax() {
		return max<=d.getMax();
	}
	
	public boolean checkMin() {
		return min >= d.getMin();
	}
	
	public boolean checkUnit() {
		
		return m.getUnit().compareTo(d.getMisura()) == 0;
	}
	
	public boolean checkScale() {
//		System.out.println(min/d.getScala()%1);
		return (min/d.getScala())%1 == 0  &&  (max/d.getScala())%1 == 0 ;
	}
	
	
}
