package info.ozkan.vipera.entities;

/**
 * Hesabın aktif olup olmadığını tanımlayan enum
 * 
 * @author Ömer Özkan
 * 
 */
public enum Authorize {
    DISABLE(0, "Devre Dışı"), ENABLE(1, "Aktif");
    /**
     * Key
     */
    private int key;
    /**
     * title
     */
    private String title;

    /**
     * private constructor
     * 
     * @param key
     */
    private Authorize(final int key, final String title) {
        this.key = key;
        this.title = title;
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(final int key) {
        this.key = key;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(final String title) {
        this.title = title;
    }
}
