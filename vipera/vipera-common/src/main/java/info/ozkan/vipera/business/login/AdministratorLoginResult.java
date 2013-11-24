package info.ozkan.vipera.business.login;

import info.ozkan.vipera.entities.Administrator;

public class AdministratorLoginResult {
	private AdministratorLoginStatus statusCode;
	private Administrator administrator;

	public AdministratorLoginStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(AdministratorLoginStatus statusCode) {
		this.statusCode = statusCode;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
}
