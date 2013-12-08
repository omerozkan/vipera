package info.ozkan.vipera.jsf;

import info.ozkan.vipera.entities.Administrator;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Authorization kontrolu vs için kullanılan Listener
 * @author Ömer Özkan
 *
 */
public class AuthorizationListener implements PhaseListener{
	private static final String ADMINISTRATOR = "administrator";
	private static final String ADMINISTRATOR_PATH = "yonetim";
	private static final String LOGIN_PAGE = "login";
	/**
	 * Logger
	 */
	private static Logger LOGGER = LoggerFactory.getLogger(AuthorizationListener.class);
	/**
	 * afterPhase
	 */
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();
		HttpSession session = (HttpSession) facesContext
				.getExternalContext().getSession(true);
		if(isLoginPage(currentPage)) {
			return;
		}
		if(isAdministratorPanel(currentPage)) {
			checkAdministrator(facesContext, session);
		}
	}

	private boolean isAdministratorPanel(String currentPage) {
		return currentPage.contains(ADMINISTRATOR_PATH);
	}

	private boolean isLoginPage(String currentPage) {
		return currentPage.contains(LOGIN_PAGE);
	}

	/**
	 * Yönetici olarak oturum açılıp açılmadığını kontrol eder
	 * @param facesContext
	 * @param session
	 */
	private void checkAdministrator(FacesContext facesContext,
			HttpSession session) {
		Administrator administrator = (Administrator) session
				.getAttribute(ADMINISTRATOR);
		if(administrator == null) {
			NavigationHandler nh = facesContext
					.getApplication()
					.getNavigationHandler();
			nh.handleNavigation(facesContext, null, LOGIN_PAGE);
		}
	}
	/**
	 * BeforePhase
	 */
	public void beforePhase(PhaseEvent event) {

	}
	/**
	 * GetPhaseID
	 */
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
