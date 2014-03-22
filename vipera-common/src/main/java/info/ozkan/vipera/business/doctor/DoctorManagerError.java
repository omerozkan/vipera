package info.ozkan.vipera.business.doctor;

/**
 * Hekimler üzerinde bir işlem gerçekleştirildikten sonra oluşan hata mesajları
 * 
 * @author Ömer Özkan
 * 
 */
public enum DoctorManagerError {

	TCKN_HAS_EXIST(1);
	/**
	 * Hata kodu
	 */
	private int code;

	/**
	 * Bir enum elemanı oluşturur
	 * 
	 * @param code
	 */
	private DoctorManagerError(final int code) {
		this.code = code;
	}

	/**
	 * Hata kodu
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}
}
