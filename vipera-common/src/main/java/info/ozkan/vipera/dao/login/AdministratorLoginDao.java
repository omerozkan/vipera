package info.ozkan.vipera.dao.login;

import info.ozkan.vipera.login.AdministratorLoginResult;

public interface AdministratorLoginDao {

	AdministratorLoginResult findUser(String username, String password);

}
