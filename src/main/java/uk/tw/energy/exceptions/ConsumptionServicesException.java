package uk.tw.energy.exceptions;

public class ConsumptionServicesException extends RuntimeException {

	private final String userId;

	public ConsumptionServicesException(String message, String userId) {
		super(String.format(message, userId));
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String PLAN_NOT_FOUNDED = "PLAN NOT FOUNDED for %s";

}
