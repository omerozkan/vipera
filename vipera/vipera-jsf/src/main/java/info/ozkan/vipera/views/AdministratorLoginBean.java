package info.ozkan.vipera.views;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adminLogin")
@Scope("request")
public class AdministratorLoginBean implements Serializable{
	private static Logger LOGGER = LoggerFactory.getLogger(AdministratorLoginBean.class);
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login()
	{
		LOGGER.info("action method is called!");
		
		return "login.xhtml";
	}
	
	public void login(ActionEvent ae)
	{
		LOGGER.info("actionListener method is called");
		FacesContext context = FacesContext.getCurrentInstance();
		if(username.isEmpty() || password.isEmpty())
		{
			context.addMessage(null, new FacesMessage("Uyarı", "Kullanıcı adınızı ve parolanızı giriniz!"));
		}
	}
}
