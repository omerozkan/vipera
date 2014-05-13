package info.ozkan.vipera.dao.login;

import info.ozkan.vipera.business.login.DoctorLoginResult;

/**
 * Login işlemi için veritabanı sorgularını içeren dao arayüzü
 * 
 * @author Ömer Özkan
 * 
 */
public interface DoctorLoginDao {

    /**
     * TCKN ve parola ile hekim arar
     * 
     * @param tckn
     * @param password
     * @return
     */
    DoctorLoginResult find(Long tckn, String password);

}
