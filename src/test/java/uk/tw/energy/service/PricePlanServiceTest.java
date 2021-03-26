package uk.tw.energy.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.tw.energy.domain.ElectricityReading;
import uk.tw.energy.domain.PricePlan;
import uk.tw.energy.exceptions.ConsumptionServicesException;

public class PricePlanServiceTest {

	
	private static final float EXPECTED_COST = 0.2F;
	private static final String USER_ID_2 = "USER-ID-2";
	private PricePlanService pricePlanService;
	private List<PricePlan> pricePlansList;
	private MeterReadingService metearReadingService;
	private Map<String, List<ElectricityReading>> metterAssocReading;
	
	private PricePlan pricePlan;
	
	
	@BeforeEach
    public void setUp() {
		
		this.pricePlan = new PricePlan("Cheap Plan", "Supplier1", new BigDecimal(0.2F), new ArrayList<>());
		
		this.pricePlansList = new ArrayList<>();
		this.pricePlansList.add(pricePlan);
		
		this.metterAssocReading = new HashMap<String, List<ElectricityReading>>();
		
		this.metterAssocReading.put(USER_ID_2, Arrays.asList(createElectricityReading(), createElectricityReading()));
		
		this.metterAssocReading = new HashMap<>();
		this.metearReadingService = new MeterReadingService(this.metterAssocReading);
        this.pricePlanService = new PricePlanService(this.pricePlansList, this.metearReadingService);
    }
	
	public ElectricityReading createElectricityReading() {
		return new ElectricityReading(Instant.now().minusSeconds(1800), BigDecimal.valueOf(35.0));
	}
	
	@Test
	public void testPricePlanServiceCreation() throws Exception {
		assertNotNull(this.pricePlanService);
	}
	
	@Test
	public void testReadPreviousWeekConsumptionByUserIdFail() throws Exception {
		try {
			this.pricePlanService.readPreviousWeekConsumptionByUserId(USER_ID_2);			
		} catch (ConsumptionServicesException e) {
			assertNotNull(e);
		}
	}
	
	@Test
	public void testReadPreviousWeekConsumptionByUserId() throws Exception {
		BigDecimal consumption = this.pricePlanService.readPreviousWeekConsumptionByUserId(USER_ID_2);
		assertEquals(10*EXPECTED_COST, consumption);
	}

	@Test
	public void testGetConsumptionCostOfElectricityReadingsForEachPricePlan() throws Exception {
		throw new RuntimeException("not yet implemented");
	}

}
