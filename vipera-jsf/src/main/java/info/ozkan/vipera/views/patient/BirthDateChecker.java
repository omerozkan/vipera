package info.ozkan.vipera.views.patient;

import java.util.Date;

/**
 * Doğum tarihinin geçerli olup olmadığını kontrol eden utility sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
class BirthDateChecker {
    /**
     * Utility Class
     */
    private BirthDateChecker() {

    }

    /**
     * Doğum tarihinin gelecek tarih olup olmadığını kontrol eder
     * 
     * @param date
     * @return
     */
    public static boolean checkBirthDateInPast(final Date date) {
        final Date current = new Date();
        return date.compareTo(current) <= 0;
    }
}
