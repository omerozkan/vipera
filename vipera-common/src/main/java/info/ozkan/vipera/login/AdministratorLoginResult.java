package info.ozkan.vipera.login;

import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.entities.Administrator;

public class AdministratorLoginResult {
	private AdministratorLoginStatus status;
	private Administrator administrator;
	
	public Administrator getAdministrator() {
		return administrator;
	}
	
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public AdministratorLoginStatus getStatus() {
		return status;
	}

	public void setStatus(AdministratorLoginStatus statusCode) {
		this.status = statusCode;
	}
}
