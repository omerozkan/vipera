package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.entities.DoctorTitle;

import javax.inject.Named;

/**
 * Hekimlerin ünvan listesini veren bean sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
@Named("doctorTitleList")
public class DoctorTitleListBean {
    /**
     * Hekim ünvan listesi
     * 
     * @return
     */
    public DoctorTitle[] getDoctorTitles() {
        return DoctorTitle.values();
    }
}
