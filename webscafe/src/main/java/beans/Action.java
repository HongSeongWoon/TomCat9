package beans;

public class Action {
	private boolean isRedirect;
	private String page;
	private String page1;
	public boolean isRedirect() {
		return isRedirect;
	}
	public String getPage1() {
		return page1;
	}
	public void setPage1(String page1) {
		this.page1 = page1;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
}
