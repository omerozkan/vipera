package info.ozkan.vipera.entities;

/**
 * Hekimlerin Ünvanlarını belirleyen enum
 * 
 * @author Ömer Özkan
 * 
 */
public enum DoctorTitle {
	EMPTY(null, ""), DOCTOR(0, "Dr."), SPECIALIST(1, "Uz. Dr."), OPERATOR(2,
	        "Op. Dr."), ASISTANT_PROF(3, "Yrd. Doç. Dr."), ASSOCICATE_PROF(4,
	        "Doç. Dr."), PROFESSOR(5, "Prof. Dr.");

	/**
	 * anahtar
	 */
	private Integer key;
	/**
	 * Ünvan
	 */
	private String title;

	private DoctorTitle(final Integer key, final String value) {
		this.key = key;
		title = value;
	}

	/**
	 * @return the key
	 */
	public Integer getKey() {
		return key;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
}
