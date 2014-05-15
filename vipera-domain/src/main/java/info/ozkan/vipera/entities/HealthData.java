package info.ozkan.vipera.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * Hasta sağlık verisi
 * 
 * @author Ömer Özkan
 * 
 */
@Entity
@Table(name = "HEALTH_DATAS")
public class HealthData implements Serializable {
    /**
     * Serial
     */
    private static final long serialVersionUID = 4483608900132291874L;

    /**
     * Id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /**
     * Hasta
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @Cascade(CascadeType.SAVE_UPDATE)
    private Patient patient;
    /**
     * Tarih
     */
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    /**
     * Gönderen
     */
    @Column(name = "send_by")
    private String sendBy;
    /**
     * Değerler
     */
    @OneToMany(mappedBy = "data", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<HealthDataValue> values;
    /**
     * arama işleminin hızlı gerçekleşmesi için oluşturulan map
     */
    @Transient
    private Map<String, HealthDataValue> fieldValueMap;

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
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient
     *            the patient to set
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * @return the sendBy
     */
    public String getSendBy() {
        return sendBy;
    }

    /**
     * @param sendBy
     *            the sendBy to set
     */
    public void setSendBy(final String sendBy) {
        this.sendBy = sendBy;
    }

    /**
     * @return the values
     */
    public List<HealthDataValue> getValues() {
        return values;
    }

    /**
     * @param values
     *            the values to set
     */
    public void setValues(final List<HealthDataValue> values) {
        this.values = values;
    }

    /**
     * Bir alanın değerler içerisinde yer alıp almadığını kontrol eder
     * 
     * @param field
     * @return
     */
    public boolean exist(final HealthDataField field) {
        checkFieldValueMap();
        return fieldValueMap.containsKey(field.getName());
    }

    /**
     * Alana karşılık gelen değeri dönderir
     * 
     * @param field
     * @return
     */
    public HealthDataValue getValue(final HealthDataField field) {
        checkFieldValueMap();
        final String fieldName = field.getName();
        if (fieldValueMap.containsKey(fieldName)) {
            return fieldValueMap.get(fieldName);
        }
        return null;
    }

    /**
     * Eğer fieldValueMap null ise yeni bir fieldValueMap üretir
     */
    private void checkFieldValueMap() {
        if (fieldValueMap == null) {
            initializeFieldValueMap();
        }
    }

    /**
     * Lazy loading için fieldValueMap alanını ilklendirir
     */
    private void initializeFieldValueMap() {
        fieldValueMap = new HashMap<String, HealthDataValue>();
        for (final HealthDataValue value : values) {
            fieldValueMap.put(value.getField().getName(), value);
        }
    }

}
