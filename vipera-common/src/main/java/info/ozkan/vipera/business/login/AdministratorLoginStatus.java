package info.ozkan.vipera.business.login;

public enum AdministratorLoginStatus {
	SUCCESS(0),
	INVALID_USERNAME(1),
	INVALID_PASSWORD(2);
	
	private int code;
	private AdministratorLoginStatus(int code) {
		this.setCode(code);
	}
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
}
