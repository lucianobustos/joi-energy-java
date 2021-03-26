package uk.tw.energy.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class ConsumptionServicesExceptionTest {

	@Test
	public void testConsumptionServicesException() throws Exception {
		try {
			throw new ConsumptionServicesException(ConsumptionServicesException.PLAN_NOT_FOUNDED, "1");			
		} catch (ConsumptionServicesException e) {
			assertEquals("PLAN NOT FOUNDED for 1", e.getMessage());
		}
	}

}
