package info.ozkan.vipera.business.login;

import info.ozkan.vipera.entities.Administrator;

public class LoginResult {
	private StatusCode statusCode;
	private Administrator administrator;

	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
}
