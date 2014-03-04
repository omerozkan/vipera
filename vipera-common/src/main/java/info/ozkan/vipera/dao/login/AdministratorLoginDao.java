package info.ozkan.vipera.dao.login;

public interface AdministratorLoginDao {

	AdministratorLoginDaoResult findUser(String username, String password);

}
