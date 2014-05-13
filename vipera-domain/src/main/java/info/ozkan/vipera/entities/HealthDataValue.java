package info.ozkan.vipera.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Sağlık verisi alan değeri
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "HEALTH_DATA_VALUES")
public class HealthDataValue {
    /**
     * Id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Ait olduğu sağlık verisi
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "data_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private HealthData data;
    /**
     * Alan
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "field_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private HealthDataField field;
    /**
     * Değeri
     */
    @Column(name = "value")
    private Double value;

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
     * @return the data
     */
    public HealthData getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(final HealthData data) {
        this.data = data;
    }

    /**
     * @return the field
     */
    public HealthDataField getField() {
        return field;
    }

    /**
     * @param field
     *            the field to set
     */
    public void setField(final HealthDataField field) {
        this.field = field;
    }

    /**
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(final Double value) {
        this.value = value;
    }
}
