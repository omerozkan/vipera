package info.ozkan.vipera.dao.login;

import info.ozkan.vipera.business.login.PatientLoginResult;

/**
 * Hasta login işlemi için veritabanı işlemlerini gerçekleştiren Dao arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface PatientLoginDao {

    /**
     * Veritabanında tckn ve parola ile hasta arar
     * 
     * @param tckn
     * @param password
     * @return
     */
    PatientLoginResult find(Long tckn, String password);

}
