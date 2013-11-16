package info.ozkan.vipera.views;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("adminLogin")
@Scope("request")
public class AdministrationLoginBean implements Serializable{
	private static Logger LOGGER = LoggerFactory.getLogger(AdministrationLoginBean.class);
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
		LOGGER.info(username + " " + password + " was entered");
		return "index.xhtml";
	}
}
