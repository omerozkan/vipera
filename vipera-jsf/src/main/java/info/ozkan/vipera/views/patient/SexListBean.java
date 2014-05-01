package info.ozkan.vipera.views.doctor;

import info.ozkan.vipera.entities.Sex;

import javax.inject.Named;

/**
 * Cinsiyet listesi
 * 
 * @author Ömer Özkan
 * 
 */
@Named("sexList")
public class SexListBean {
    /**
     * Cinsiyet listesini dizi olarak dönderir
     * 
     * @return
     */
    public Sex[] getSexList() {
        return Sex.values();
    }
}
