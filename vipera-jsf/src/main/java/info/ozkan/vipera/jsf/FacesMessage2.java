package info.ozkan.vipera.jsf;

import javax.faces.application.FacesMessage;

/**
 * FacesMessage hatalarının daha düzgün gözükmesi amacı ile extend edilip
 * toString metodu değiştirilmiştir
 * 
 * @author Ömer Özkan
 * 
 */
public class FacesMessage2 extends FacesMessage {
    /**
     * constuctor
     * 
     * @param severityError
     * @param summary
     * @param detail
     */
    public FacesMessage2(final Severity severityError, final String summary,
            final String detail) {
        super(severityError, summary, detail);
    }

    /**
     * 
     */
    @Override
    public String toString() {
        return String.format("%s %s %s", super.getSeverity(),
                super.getSummary(), getDetail().toString());
    }
}
