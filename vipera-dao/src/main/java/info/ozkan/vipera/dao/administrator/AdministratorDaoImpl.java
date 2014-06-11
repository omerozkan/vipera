package info.ozkan.vipera.dao.administrator;

import info.ozkan.vipera.business.administrator.AdministratorManagerResult;
import info.ozkan.vipera.business.administrator.AdministratorManagerStatus;
import info.ozkan.vipera.entities.Administrator;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * {@link AdministratorDao} arayüzü implementasyonu
 * 
 * @author Ömer Özkan
 * 
 */
@Named("administratorDao")
public class AdministratorDaoImpl implements AdministratorDao {
    /**
     * Veritabanında kayıtlı bütün yöneticileri dönderen JQL sorgusu
     */
    private static final String JQL_SELECT_ALL = "from Administrator";
    /**
     * Entitiy manager
     */
    private EntityManager em;

    public AdministratorManagerResult getAll() {
        AdministratorManagerResult result;
        final Query query = em.createQuery(JQL_SELECT_ALL);
        final List<Administrator> list = query.getResultList();
        result = createSuccessResult();
        result.setAdministrators(list);
        return result;
    }

    /**
     * Başarılı bir sonuç nesnesi üretir
     * 
     * @return
     */
    private AdministratorManagerResult createSuccessResult() {
        AdministratorManagerResult result;
        result = new AdministratorManagerResult();
        result.setStatus(AdministratorManagerStatus.SUCCESS);
        return result;
    }

    /**
     * @param entityManager
     *            the entityManager to set
     */
    @PersistenceContext
    public void setEntityManager(final EntityManager entityManager) {
        em = entityManager;
    }

    public AdministratorManagerResult update(final Administrator administrator) {
        em.merge(administrator);
        return createSuccessResult();
    }

    public AdministratorManagerResult add(final Administrator administrator) {
        return update(administrator);
    }

    public AdministratorManagerResult delete(final Administrator administrator) {
        em.remove(em.merge(administrator));
        return createSuccessResult();
    }
}
