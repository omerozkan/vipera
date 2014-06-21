package info.ozkan.vipera.views.device;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Parola üreten utility sınıfı
 * 
 * @author Ömer Özkan
 * 
 */
public final class PasswordGenerator {
    private static final String ALGORITHM = "MD5";
    private static final String CHARSET = "utf-8";
    /**
     * Parola boyutu
     */
    private static final int SIZE = 16;

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
        return RandomStringUtils.random(SIZE, true, true);
    }

    public static String generate(final String uniqueString) {
        String str;
        try {
            String generated = generate();
            generated += uniqueString;
            MessageDigest md;
            md = MessageDigest.getInstance(ALGORITHM);
            byte[] md5 = new byte[54];
            md.update(generated.getBytes(CHARSET), 0, generated.length());
            md5 = md.digest();
            str = convertedToHex(md5);

        } catch (final NoSuchAlgorithmException e) {
            str = generate();
        } catch (final UnsupportedEncodingException e) {
            str = generate();
        }
        return str.substring(SIZE);
    }

    private static String convertedToHex(final byte[] data) {
        final StringBuffer buf = new StringBuffer();

        for (int i = 0; i < data.length; i++) {
            int halfOfByte = (data[i] >>> 4) & 0x0F;
            int twoHalfBytes = 0;

            do {
                if ((0 <= halfOfByte) && (halfOfByte <= 9)) {
                    buf.append((char) ('0' + halfOfByte));
                }

                else {
                    buf.append((char) ('a' + (halfOfByte - 10)));
                }

                halfOfByte = data[i] & 0x0F;

            } while (twoHalfBytes++ < 1);
        }
        return buf.toString();
    }
}
