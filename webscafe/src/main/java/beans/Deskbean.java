package beans;

public class Deskbean {
	private String cuNum;
	private String cuName;
	private String seCode;
	private String seName;
	private String dkCode;
	private int dkStat;
	private String date;
	public String getCuName() {
		return cuName;
	}
	public void setCuName(String cuName) {
		this.cuName = cuName;
	}
	public String getSeName() {
		return seName;
	}
	public void setSeName(String seName) {
		this.seName = seName;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCuNum() {
		return cuNum;
	}
	public void setCuNum(String cuNum) {
		this.cuNum = cuNum;
	}
	public String getSeCode() {
		return seCode;
	}
	public void setSeCode(String seCode) {
		this.seCode = seCode;
	}
	public String getDkCode() {
		return dkCode;
	}
	public void setDkCode(String dkCode) {
		this.dkCode = dkCode;
	}
	public int getDkStat() {
		return dkStat;
	}
	public void setDkStat(int dkStat) {
		this.dkStat = dkStat;
	}
}
