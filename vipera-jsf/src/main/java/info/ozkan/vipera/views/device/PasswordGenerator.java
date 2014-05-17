package info.ozkan.vipera.views.device;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Parola üreten utility sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public final class PasswordGenerator {
    /**
     * private constructor
     */
    private PasswordGenerator() {
    }

    /**
     * Yeni bir parola üretir
     * 
     * @return
     */
    public static String generate() {
        return RandomStringUtils.random(16, true, true);
    }
}
