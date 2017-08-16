package com.elidodo.parts;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import com.elidodo.parts.ws.Part;
import com.elidodo.parts.ws.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class PartRepository {
	private static final Map<String, Part> parts = new HashMap<>();

	@PostConstruct
	public void initData() {
		Part motor1 = new Part();
		motor1.setId("MK-2207K");
		motor1.setType("Motor");
		motor1.setName("Racing engine 4S edition K");
		motor1.setCount(4);
		motor1.setPrice(24.55);
		motor1.setCurrency(Currency.EUR);

		parts.put(motor1.getId(), motor1);

		Part motor2 = new Part();
		motor2.setId("MK-2709");
		motor2.setType("Motor");
		motor2.setName("High revving engine 4-6S");
		motor2.setCount(2);
		motor2.setPrice(17.85);
		motor2.setCurrency(Currency.EUR);

		parts.put(motor2.getId(), motor2);

		Part speedController1 = new Part();
		speedController1.setId("SC-187-119");
		speedController1.setType("Speedcontroller");
		speedController1.setName("Racing speedcontroller racing edition");
		speedController1.setCount(1);
		speedController1.setPrice(36.95);
		speedController1.setCurrency(Currency.EUR);

		parts.put(speedController1.getId(), speedController1);

	}

	public Part findPart(String id) {
		Assert.notNull(id, "The part's id must not be null");
		return parts.get(id);
	}
}

