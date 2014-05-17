package info.ozkan.vipera.dao.login;

import info.ozkan.vipera.login.AdministratorLoginResult;

/**
 * Yönetici login işlemlerini yapan persistence katmanı sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public interface AdministratorLoginDao {
    /**
     * Veritabanından yönetici arar
     * 
     * @param username
     *            Kullanıcı adı
     * @param password
     *            Parola
     * @return
     */
    AdministratorLoginResult findUser(String username, String password);

}
