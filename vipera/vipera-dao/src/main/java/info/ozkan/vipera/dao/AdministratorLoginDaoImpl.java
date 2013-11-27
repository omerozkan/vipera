package info.ozkan.vipera.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.dao.login.AdministratorLoginDao;
import info.ozkan.vipera.dao.login.AdministratorLoginDaoResult;
import info.ozkan.vipera.entities.Administrator;
/**
 * Administrator login işlemleri için kullanılan dao
 * katmanı sınıfı
 * @author Ömer Özkan
 */
public class AdministratorLoginDaoImpl implements AdministratorLoginDao {
	/**
	 * Yöneticiyi bilgibankasından çekmek için JQL sorgusu
	 */
	private static final String GET_USER_JQL = "from Administrator a where c.username = :username";
	/**
	 * Persistence
	 */
	private EntityManager em;

	/**
	 * Kullanıcı adı ve parola ile bilgibankasından yöneticiyi
	 * ve sonucu gönderir
	 * @param username Kullanıcı adı
	 * @param password Parola
	 * @return Result nesnesi
	 */
	public AdministratorLoginDaoResult findUser(String username,
			String password) {
		AdministratorLoginDaoResult result =
				new AdministratorLoginDaoResult();
		Query query = em.createQuery(GET_USER_JQL);
		query.setParameter(username, password);
		List<Administrator> list = query.getResultList();

		if (checkUsername(list)) {
			result.setStatus(
				AdministratorLoginStatus.INVALID_USERNAME);
			return result;
		}

		Administrator admin = list.get(0);

		if (checkPassword(password, admin)) {
			result.setStatus(
				AdministratorLoginStatus.INVALID_PASSWORD);
			return result;
		}
		loginSuccessfull(result, admin);
		return result;
	}

	/**
	 * Login işlemi başarılı ise
	 * result nesnesine gereken değerleri tanımlar
	 * @param result sonuç
	 * @param admin yönetici
	 */
	private void loginSuccessfull(AdministratorLoginDaoResult result,
			Administrator admin) {
		result.setStatus(AdministratorLoginStatus.SUCCESS);
		result.setAdministrator(admin);
	}

	/**
	 * Yönetici parolasının eşleşip eşleşmediğini kontrol eder
	 * @param password Parola
	 * @param admin Yönetici
	 * @return Parola hatalı ise true, değilse false döner
	 */
	private boolean checkPassword(String password, Administrator admin) {
		return !password.equals(admin.getPassword());
	}

	/**
	 * Bilgibankasında o kullanıcıya ait
	 * kayıt olup olmadığını kontrol eder
	 * @param list
	 * @return
	 */
	private boolean checkUsername(List<Administrator> list) {
		return list.size() == 0 || list == null;
	}

	/**
	 * EntitiyManager
	 * @param em
	 */
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

}
