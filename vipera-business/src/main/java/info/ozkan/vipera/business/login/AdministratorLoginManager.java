package info.ozkan.vipera.business.login;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.login.AdministratorLoginDao;
import info.ozkan.vipera.entities.Administrator;
import info.ozkan.vipera.login.AdministratorLoginResult;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Yönetici işlemini gerçekleştiren business sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("administratorLoginManager")
public class AdministratorLoginManager implements AuthenticationProvider {
    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AdministratorLoginManager.class);
    /**
     * Persistence katmanı nesnesi
     */
    @Inject
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
    public Authentication authenticate(final Authentication authentication) {
        final AdministratorLoginResult result = findUserAndGetResult(authentication);
        final AdministratorLoginStatus status = result.getStatus();

        if (validCredential(status)) {
            final Administrator administrator = result.getAdministrator();
            final String username = administrator.getUsername();
            LOGGER.info("{} has been authenticated for admin panel", username);
            return createToken(result);
        } else if (userNotFound(status)) {
            LOGGER.error("\"{}\" username has not found",
                    authentication.getPrincipal());
            throw new UsernameNotFoundException("User not found: "
                    + authentication.getPrincipal());
        } else {
            LOGGER.error(
                    "\"{}\" has failed to login because of bad credentials",
                    authentication.getPrincipal());
            throw new BadCredentialsException("Invalid password");
        }
    }

    /**
     * AdministratorLoginResult nesnesinden UsernamePasswordAuthenticationToken
     * nesnesi üretir
     * 
     * @param result
     * @return
     */
    private Authentication createToken(final AdministratorLoginResult result) {
        final Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(Role.ROLE_ADMIN);
        final Administrator administrator = result.getAdministrator();
        final String password = administrator.getPassword();
        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                administrator, password, authorities);

        return token;
    }

    /**
     * Kullanıcı bulunmadı mı?
     * 
     * @param status
     * @return
     */
    private boolean userNotFound(final AdministratorLoginStatus status) {
        return status.equals(AdministratorLoginStatus.INVALID_USERNAME);
    }

    /**
     * Girilen bilgiler geçerli mi?
     * 
     * @param status
     * @return
     */
    private boolean validCredential(final AdministratorLoginStatus status) {
        return status.equals(AdministratorLoginStatus.SUCCESS);
    }

    /**
     * Authentication nesnesinden gereken kullanıcı adı ve parola bilgilerini
     * alarak veri katmanından gereken sonucu elde eder
     * 
     * @param authentication
     * @return
     */
    private AdministratorLoginResult findUserAndGetResult(
            final Authentication authentication) {
        final String username = authentication.getPrincipal().toString();
        final String password = authentication.getCredentials().toString();
        final AdministratorLoginResult result = loginDao.findUser(username,
                password);
        return result;
    }

    /**
     * @see {@link AuthenticationProvider#supports(Class)}
     */
    public boolean supports(final Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
