package info.ozkan.vipera.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Yönetici Entity sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "ADMINISTRATORS")
public class Administrator implements Serializable {
    /**
     * serial
     */
    private static final long serialVersionUID = 5726328017305109395L;
    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    /**
     * Kullanıcı adı
     */
    @Column(name = "username", unique = true)
    private String username;
    /**
     * Parola
     */
    @Column(name = "password")
    private String password;
    /**
     * Yönetici adı
     */
    @Column(name = "name")
    private String name;

    /**
     * Yönetici eposta
     */
    @Column(name = "email")
    private String email;

    /**
     * Yönetici üyelik aktifliği
     */
    @Column(name = "enabled")
    @Enumerated(EnumType.ORDINAL)
    private Authorize enabled;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the enabled
     */
    public Authorize getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     *            the enabled to set
     */
    public void setEnabled(final Authorize enabled) {
        this.enabled = enabled;
    }
}
