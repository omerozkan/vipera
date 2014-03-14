package info.ozkan.vipera.business.login;

import info.ozkan.vipera.dao.login.AdministratorLoginDao;
import info.ozkan.vipera.dao.login.AdministratorLoginDaoResult;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Yönetici işlemini gerçekleştiren business sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public class AdministratorLoginManager implements AuthenticationProvider {
	protected static final String ROLE_ADMIN = "ROLE_ADMIN";
	/**
	 * Persistence katmanı nesnesi
	 */
	@Autowired
	private AdministratorLoginDao loginDao;

	/**
	 * Setter AdministratorLoginDao
	 * 
	 * @param loginDao
	 */
	public void setLoginDao(final AdministratorLoginDao loginDao) {
		this.loginDao = loginDao;
	}

	/**
	 * Login işlemini gerçekleştirir
	 */
	public Authentication authenticate(final Authentication authentication)
	        throws AuthenticationException {
		final AdministratorLoginDaoResult result = loginDao.findUser(
		        authentication.getPrincipal().toString(), authentication
		                .getCredentials().toString());
		final AdministratorLoginStatus status = result.getStatus();
		if (status.equals(AdministratorLoginStatus.SUCCESS)) {
			final Collection<? extends GrantedAuthority> authorities = AuthorityUtils
			        .createAuthorityList(ROLE_ADMIN);

			final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
			        result.getAdministrator(), result.getAdministrator()
			                .getPassword(), authorities);
			return token;
		} else if (status.equals(AdministratorLoginStatus.INVALID_USERNAME)) {
			throw new UsernameNotFoundException("User not found: "
			        + authentication.getPrincipal());
		} else {
			throw new BadCredentialsException("Invalid password");
		}
	}

	/**
	 * @see {@link AuthenticationProvider#supports(Class)}
	 */
	public boolean supports(final Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}
