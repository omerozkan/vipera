package info.ozkan.vipera.business.login;

import info.ozkan.vipera.dao.login.AdministratorLoginDao;
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
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
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
        final AdministratorLoginResult result = loginDao.findUser(
                authentication.getPrincipal().toString(), authentication
                        .getCredentials().toString());
        final AdministratorLoginStatus status = result.getStatus();
        if (status.equals(AdministratorLoginStatus.SUCCESS)) {
            final Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                    .createAuthorityList(ROLE_ADMIN);

            final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    result.getAdministrator(), result.getAdministrator()
                            .getPassword(), authorities);
            LOGGER.info("{} has been authenticated for admin panel", result
                    .getAdministrator().getUsername());
            return token;
        } else if (status.equals(AdministratorLoginStatus.INVALID_USERNAME)) {
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
     * @see {@link AuthenticationProvider#supports(Class)}
     */
    public boolean supports(final Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
