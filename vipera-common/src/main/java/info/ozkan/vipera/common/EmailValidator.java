package info.ozkan.vipera.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Eposta adresinin geçerliliğini kontrol eden sınıf
 * 
 * @author Ömer Özkan
 * 
 */
public final class EmailValidator {
    /**
     * Email Pattern
     */
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * Pattern
     */
    private static final Pattern PATTERN = Pattern.compile(EMAIL_PATTERN);

    /**
     * private Constructor
     */
    private EmailValidator() {
    }

    /**
     * Validate hex with regular expression
     * 
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public static boolean isValid(final String hex) {
        final Matcher matcher = PATTERN.matcher(hex);
        return matcher.matches();

    }
}
