package info.ozkan.vipera.entities;

/**
 * Cinsiyet
 * 
 * @author Ömer Özkan
 * 
 */
public enum Sex {
    FEMALE(0, "Kadın"), MALE(1, "Erkek");
    /**
     * Key
     */
    private int key;
    /**
     * Name
     */
    private String title;

    /**
     * private constructor
     * 
     * @param id
     * @param title
     */
    private Sex(final int key, final String title) {
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
