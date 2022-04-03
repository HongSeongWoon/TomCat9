package beans;

public class Employees {
	private String seCode;
	private String emCode;
	private String emPassword;
	private int emStCode;
	private String emName;
	private String seName;
	private String emDate;
	
	public int getEmStCode() {
		return emStCode;
	}
	public void setEmStCode(int emStCode) {
		this.emStCode = emStCode;
	}
	public String getEmName() {
		return emName;
	}
	public void setEmName(String emName) {
		this.emName = emName;
	}
	public String getSeName() {
		return seName;
	}
	public void setSeName(String seName) {
		this.seName = seName;
	}
	public String getEmDate() {
		return emDate;
	}
	public void setEmDate(String emDate) {
		this.emDate = emDate;
	}

	public String getSeCode() {
		return seCode;
	}
	public void setSeCode(String seCode) {
		this.seCode = seCode;
	}
	public String getEmCode() {
		return emCode;
	}
	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	public String getEmPassword() {
		return emPassword;
	}
	public void setEmPassword(String emPassword) {
		this.emPassword = emPassword;
	}
}
