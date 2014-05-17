package info.ozkan.vipera.business.login;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.login.DoctorLoginDao;
import info.ozkan.vipera.entities.Doctor;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Hekim paneline login girişini yönetir
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorLoginManager")
public class DoctorLoginManager implements AuthenticationProvider {
    /**
     * dao nesnesi
     */
    @Inject
    private DoctorLoginDao doctorLoginDao;

    /**
     * Login işlemini gerçekleştirir
     */
    public Authentication authenticate(final Authentication authentication)
            throws AuthenticationException {
        final Authentication authToken;
        final DoctorLoginResult result = getLoginResult(authentication);
        final DoctorLoginStatus status = result.getStatus();
        if (status.equals(DoctorLoginStatus.SUCCESS)) {
            authToken = createToken(result);
        } else if (status.equals(DoctorLoginStatus.INVALID_USERNAME)) {
            throw new UsernameNotFoundException("Doctor not found: "
                    + authentication.getPrincipal());
        } else {
            throw new BadCredentialsException("Invalid password");
        }
        return authToken;
    }

    /**
     * Giriş başarılı olduğu durumda token nesnesi üretir
     * 
     * @param result
     * @return
     */
    private UsernamePasswordAuthenticationToken createToken(
            final DoctorLoginResult result) {
        final Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(Role.ROLE_DOCTOR);
        final Doctor doctor = result.getDoctor();
        final String doctorPassword = doctor.getPassword();
        final UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(doctor, doctorPassword,
                        authorities);
        return token;
    }

    /**
     * Kullanıcının girdiği tckn ve parolayı sistemden sorgular
     * 
     * @param authentication
     * @return
     */
    private DoctorLoginResult
            getLoginResult(final Authentication authentication) {
        final Long tckn = (Long) authentication.getPrincipal();
        final String password = authentication.getCredentials().toString();
        return doctorLoginDao.find(tckn, password);
    }

    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}