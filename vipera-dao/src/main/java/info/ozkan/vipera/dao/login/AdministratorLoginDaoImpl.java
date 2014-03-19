package info.ozkan.vipera.dao.login;

import info.ozkan.vipera.business.login.AdministratorLoginStatus;
import info.ozkan.vipera.entities.Administrator;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Administrator login işlemleri için kullanılan dao katmanı sınıfı
 * 
 * @author Ömer Özkan
 */
@Named("administratorLoginDao")
public class AdministratorLoginDaoImpl implements AdministratorLoginDao {
	/**
	 * LOGGER
	 */
	private static final Logger LOGGER = LoggerFactory
	        .getLogger(AdministratorLoginDaoImpl.class);
	/**
	 * Yöneticiyi bilgibankasından çekmek için JQL sorgusu
	 */
	private static final String GET_USER_JQL = "from Administrator a where a.username = :username";
	/**
	 * Persistence
	 */
	private EntityManager em;

	/**
	 * Kullanıcı adı ve parola ile bilgibankasından yöneticiyi ve sonucu
	 * gönderir
	 * 
	 * @param username
	 *            Kullanıcı adı
	 * @param password
	 *            Parola
	 * @return Result nesnesi
	 */
	@Transactional
	public AdministratorLoginDaoResult findUser(final String username,
	        final String password) {
		final AdministratorLoginDaoResult result = new AdministratorLoginDaoResult();
		final Query query = em.createQuery(GET_USER_JQL);
		query.setParameter("username", username);
		final List<Administrator> list = query.getResultList();

		if (checkUsername(list)) {
			LOGGER.info("User \"{}\" has not exist", username);
			result.setStatus(AdministratorLoginStatus.INVALID_USERNAME);
			return result;
		}

		final Administrator admin = list.get(0);

		if (checkPassword(password, admin)) {
			LOGGER.info("The user \"{}\"'s password is wrong", username);
			result.setStatus(AdministratorLoginStatus.INVALID_PASSWORD);
			return result;
		}
		LOGGER.info("The user {} has found", username);
		loginSuccessfull(result, admin);
		return result;
	}

	/**
	 * Login işlemi başarılı ise result nesnesine gereken değerleri tanımlar
	 * 
	 * @param result
	 *            sonuç
	 * @param admin
	 *            yönetici
	 */
	private void loginSuccessfull(final AdministratorLoginDaoResult result,
	        final Administrator admin) {
		result.setStatus(AdministratorLoginStatus.SUCCESS);
		result.setAdministrator(admin);
	}

	/**
	 * Yönetici parolasının eşleşip eşleşmediğini kontrol eder
	 * 
	 * @param password
	 *            Parola
	 * @param admin
	 *            Yönetici
	 * @return Parola hatalı ise true, değilse false döner
	 */
	private boolean checkPassword(final String password,
	        final Administrator admin) {
		return !password.equals(admin.getPassword());
	}

	/**
	 * Bilgibankasında o kullanıcıya ait kayıt olup olmadığını kontrol eder
	 * 
	 * @param list
	 * @return
	 */
	private boolean checkUsername(final List<Administrator> list) {
		return list.size() == 0 || list == null;
	}

	/**
	 * EntitiyManager
	 * 
	 * @param em
	 */
	@PersistenceContext
	public void setEntityManager(final EntityManager em) {
		this.em = em;
	}

}
