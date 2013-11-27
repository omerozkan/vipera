package info.ozkan.vipera.business.login;

import info.ozkan.vipera.dao.login.AdministratorLoginDao;
import info.ozkan.vipera.dao.login.AdministratorLoginDaoResult;
import info.ozkan.vipera.entities.Administrator;
/**
 * Yönetici işlemini gerçekleştiren business sınıfı
 * @author Ömer Özkan
 *
 */
public class AdministratorLoginManagerImpl implements AdministratorLoginManager{
	/**
	 * Persistence katmanı nesnesi
	 */
	private AdministratorLoginDao loginDao;

	/**
	 * Setter AdministratorLoginDao
	 * @param loginDao
	 */
	public void setLoginDao(AdministratorLoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * Login işlemini gerçekleştirir
	 */
	public AdministratorLoginResult login(String username,
			String password) {
		AdministratorLoginDaoResult daoResult =
				loginDao.findUser(username, password);
		AdministratorLoginResult result =
				new AdministratorLoginResult();
		copyResult(daoResult, result);
		return result;
	}

	/**
	 * Persistence katmanından gelen sonucu
	 * Business katmanındaki result nesnesine
	 * aktarır
	 * @param daoResult
	 * @param result
	 */
	private void copyResult(AdministratorLoginDaoResult daoResult,
			AdministratorLoginResult result) {
		result.setStatus(daoResult.getStatus());
		Administrator admin = daoResult.getAdministrator();
		if (admin != null) {
			result.setAdministrator(admin);
		}
	}

}
