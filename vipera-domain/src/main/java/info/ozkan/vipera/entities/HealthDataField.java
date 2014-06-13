package info.ozkan.vipera.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sağlık verisi alanı
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "HEALTH_DATA_FIELDS")
public class HealthDataField implements Serializable, Cloneable {
    /**
     * Serial
     */
    private static final long serialVersionUID = -4037256988314012301L;
    /**
     * alanin idsi
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * alan adı unique
     */
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    /**
     * Alanın başlığı
     */
    @Column(name = "title")
    private String title;
    /**
     * Birim
     */
    @Column(name = "unit")
    private String unit;
    /**
     * Üst sınır
     */
    @Column(name = "upper_limit")
    private Double upperLimit;
    /**
     * Alt sınır
     */
    @Column(name = "lower_limit")
    private Double lowerLimit;

    /**
     * Hekim bilgilendirilebilir
     */
    @Column(name = "notification", nullable = false)
    private Boolean notification;

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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     *            the unit to set
     */
    public void setUnit(final String unit) {
        this.unit = unit;
    }

    /**
     * @return the upperLimit
     */
    public Double getUpperLimit() {
        return upperLimit;
    }

    /**
     * @param upperLimit
     *            the upperLimit to set
     */
    public void setUpperLimit(final Double upperLimit) {
        this.upperLimit = upperLimit;
    }

    /**
     * @return the lowerLimit
     */
    public Double getLowerLimit() {
        return lowerLimit;
    }

    /**
     * @param lowerLimit
     *            the lowerLimit to set
     */
    public void setLowerLimit(final Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    /**
     * @return the notification
     */
    public Boolean getNotification() {
        return notification;
    }

    /**
     * @param notification
     *            the notification to set
     */
    public void setNotification(final Boolean notification) {
        this.notification = notification;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (final CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
