package info.ozkan.vipera.jsf;

import java.net.URL;

import javax.faces.view.facelets.ResourceResolver;

/**
 * JSF Dosyalarının JAR'lardan çalışması için gereken ResourceResolver sınıfı Bu
 * kod {@linkplain http
 * ://stackoverflow.com/questions/6199458/how-to-create-a-modular
 * -jsf-2-0-application} adresinden alınarak özelleştirilmiştir.
 * 
 * @author Ömer Özkan
 */
public class FaceletsResourceResolver extends ResourceResolver {
    /**
     * Default JSF ResourceResolver
     */
    private final ResourceResolver parent;

    /**
     * Consructor
     * 
     * @param parent
     */
    public FaceletsResourceResolver(final ResourceResolver parent) {
        this.parent = parent;
    }

    /**
     * Request'in çözülmesini sağlar
     */
    @Override
    public URL resolveUrl(final String path) {
        URL url = parent.resolveUrl(path);
        if (url == null) {
            url = getClass().getResource(path);
        }

        return url;
    }

}
