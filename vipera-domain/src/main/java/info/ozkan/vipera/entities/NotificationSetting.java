package info.ozkan.vipera.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Bildirim ayarlarını tanımlayan model nesnesi
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "NOTIFICATION_SETTINGS")
public class NotificationSetting implements Serializable {
    /**
     * Serial
     */
    private static final long serialVersionUID = -9164032244304457495L;
    /**
     * Bildiri id
     */
    @Column(name = "provider_id", nullable = false, unique = true)
    @Id
    private String providerId;
    /**
     * API anahtarı
     */
    @Column(name = "key")
    private String key;
    /**
     * API Parolası
     */
    @Column(name = "password")
    private String password;
    /**
     * Api aktif mi
     */
    @Column(name = "enabled")
    private Boolean enabled;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId
     *            the providerId to set
     */
    public void setProviderId(final String providerId) {
        this.providerId = providerId;
    }
}
