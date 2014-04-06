package info.ozkan.vipera.entities;

/**
 * Hekimlerin Ünvanlarını belirleyen enum
 * 
 * @author Ömer Özkan
 * 
 */
public enum DoctorTitle {
	EMPTY(0, ""), DOCTOR(1, "Dr."), SPECIALIST(2, "Uz. Dr."), OPERATOR(3,
	        "Op. Dr."), ASISTANT_PROF(4, "Yrd. Doç. Dr."), ASSOCICATE_PROF(5,
	        "Doç. Dr."), PROFESSOR(6, "Prof. Dr.");

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

	@Override
	public String toString() {
		return key.toString();
	}
}
