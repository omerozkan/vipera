package info.ozkan.vipera.business.login;

import info.ozkan.vipera.business.role.Role;
import info.ozkan.vipera.dao.login.PatientLoginDao;
import info.ozkan.vipera.entities.Patient;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Hasta yetkilendirme yöneticisi
 * 
 * @author Ömer Özkan
 * 
 */
@Named("patientLoginManager")
public class PatientLoginManager implements AuthenticationProvider {
    /**
     * veri katmanı
     */
    @Inject
    private PatientLoginDao patientLoginDao;

    /**
     * Login işlemini gerçekleştirir
     */
    public Authentication authenticate(final Authentication authentication) {
        final Authentication authToken;
        final PatientLoginResult result = getLoginResult(authentication);
        final PatientLoginStatus status = result.getStatus();
        if (status.equals(PatientLoginStatus.SUCCESS)) {
            authToken = createToken(result);
        } else if (status.equals(PatientLoginStatus.INVALID_USERNAME)) {
            throw new UsernameNotFoundException("Patient not found: "
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
            final PatientLoginResult result) {
        final Collection<? extends GrantedAuthority> authorities =
                AuthorityUtils.createAuthorityList(Role.ROLE_PATIENT);
        final Patient patient = result.getPatient();
        final String patientPassword = patient.getPassword();
        final UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(patient,
                        patientPassword, authorities);
        return token;
    }

    /**
     * Kullanıcının girdiği tckn ve parolayı sistemden sorgular
     * 
     * @param authentication
     * @return
     */
    private PatientLoginResult getLoginResult(
            final Authentication authentication) {
        final Long tckn = (Long) authentication.getPrincipal();
        final String password = authentication.getCredentials().toString();
        return patientLoginDao.find(tckn, password);
    }

    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
