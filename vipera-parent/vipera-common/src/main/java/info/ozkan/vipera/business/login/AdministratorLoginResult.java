package info.ozkan.vipera.business.login;

import info.ozkan.vipera.entities.Administrator;

public class AdministratorLoginResult {
	private AdministratorLoginStatus status;
	private Administrator administrator;

	public AdministratorLoginStatus getStatus() {
		return status;
	}
	public void setStatus(AdministratorLoginStatus status) {
		this.status = status;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	
	public Administrator getAdministrator() {
		return this.administrator;
	}
}
