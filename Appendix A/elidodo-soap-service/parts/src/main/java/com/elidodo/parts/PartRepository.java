package com.elidodo.parts;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.elidodo.parts.ws.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.text.DecimalFormat;
import java.lang.Double;

@Component
public class PartRepository {
	private static final Map<String, Part> parts = new HashMap<>();

	@PostConstruct
	public void initData() {

   		Part frame1 = new Part();
                frame1.setId("ELI-FRAME-2017_001");
                frame1.setType("Frame");
                frame1.setDescription("Standard frame for ELI quadcopter");
                frame1.setPrice(14.75);
                frame1.setCurrency(Currency.EUR);
                parts.put(frame1.getId(), frame1);

   		Part frame2 = new Part();
                frame2.setId("ELI-FRAME-2017_003-Racer");
                frame2.setType("Frame");
                frame2.setDescription("Racer frame for High end Racing quadcopter");
                frame2.setPrice(33.05);
                frame2.setCurrency(Currency.EUR);
                parts.put(frame2.getId(), frame2);
   
   		Part frame3 = new Part();
                frame3.setId("ELI-FRAME-2016_1");
                frame3.setType("Frame");
                frame3.setDescription("Standard frame for ELI quadcopter - 2016 edition");
                frame3.setPrice(11.35);
                frame3.setCurrency(Currency.EUR);
                parts.put(frame3.getId(), frame3);

		Part motor1 = new Part();
                motor1.setId("ELI-MOTOR-MK-2207K");
                motor1.setType("Motor");
                motor1.setDescription("Racing engine 4S edition K");
                motor1.setPrice(24.55);
                motor1.setCurrency(Currency.EUR);
                parts.put(motor1.getId(), motor1);

                Part motor2 = new Part();
                motor2.setId("ELI-MOTOR-MK-2709");
                motor2.setType("Motor");
                motor2.setDescription("High revving engine 4-6S");
                motor2.setPrice(17.85);
                motor2.setCurrency(Currency.EUR);
                parts.put(motor2.getId(), motor2);
				
		Part speedcontroller1 = new Part();
                speedcontroller1.setId("ELI-SC-2017_4x25");
                speedcontroller1.setType("Speedcontroller");
                speedcontroller1.setDescription("EliDodo speedcontoller - 4 engines - ESC 4S 4x20A");
                speedcontroller1.setPrice(29.75);
                speedcontroller1.setCurrency(Currency.EUR);
                parts.put(speedcontroller1.getId(), speedcontroller1);

		Part speedcontroller2 = new Part();
                speedcontroller2.setId("ELI-SPEEDCO-Racer-115-40A");
                speedcontroller2.setType("Speedcontroller");
                speedcontroller2.setDescription("EliDodo speedcontoller 115 - single engine - max 40A");
                speedcontroller2.setPrice(12.99);
                speedcontroller2.setCurrency(Currency.EUR);
                parts.put(speedcontroller2.getId(), speedcontroller2);
				
		Part flightcontroller1 = new Part();
                flightcontroller1.setId("ELI-FLIGHTC-2017-442-basic");
                flightcontroller1.setType("Flightcontroller");
                flightcontroller1.setDescription("EliDodo flightcontoller - basic edition 442, 2017");
                flightcontroller1.setPrice(36.75);
                flightcontroller1.setCurrency(Currency.EUR);
                parts.put(flightcontroller1.getId(), flightcontroller1);

		Part flightcontroller2 = new Part();
                flightcontroller2.setId("ELI-FLIGHTC-2017-442-racer");
                flightcontroller2.setType("Flightcontroller");
                flightcontroller2.setDescription("EliDodo flightcontoller - racing edition 442, 2017");
                flightcontroller2.setPrice(53.20);
                flightcontroller2.setCurrency(Currency.EUR);
                parts.put(flightcontroller2.getId(), flightcontroller2);
				
		Part camera1 = new Part();
                camera1.setId("ELI-CAM-2016-16-9-cmos");
                camera1.setType("Camera");
                camera1.setDescription("EliDodo camera - format 16:9 - CMOS technology, 2016 edition");
                camera1.setPrice(32.75);
                camera1.setCurrency(Currency.EUR);
                parts.put(camera1.getId(), camera1);

		Part props1 = new Part();
                props1.setId("ELI-PROPS-6x4.5-basic");
                props1.setType("Props");
                props1.setDescription("EliDodo props - prop 6 inch - 6x4.5  - 2 pairs/pack, CW & CCW - basic");
                props1.setPrice(36.75);
                props1.setCurrency(Currency.EUR);
                parts.put(props1.getId(), props1);

		Part props2 = new Part();
                props2.setId("ELI-PROPS-FIB-6x4.5-race");
                props2.setType("Props");
                props2.setDescription("EliDodo props - fiber - prop 6 inch - 6x4.5  - 2 pairs/pack, CW & CCW - race");
                props2.setPrice(36.75);
                props2.setCurrency(Currency.EUR);
                parts.put(props2.getId(), props2);

	}

	public Part findPart(String id) {
		Assert.notNull(id, "The part's id must not be null");
		return parts.get(id);
	}
 
	public PartList getParts() {

                PartList result = new PartList();

                for (String key : parts.keySet()) {
                        result.getPart().add(parts.get(key));
                }

		return result;
	}

	public PartList findPartsByType(String type) {
		Assert.notNull(type, "The part's type must not be null");

                PartList result = new PartList();

                for (String key : parts.keySet()) {
                        if ( parts.get(key).getType().equals(type) ) {

                                result.getPart().add(parts.get(key));
                        }
                }


		return result;
	}


	public double getOrderAmount(OrderLineList orderLines) {

                double result = 0.0;

                if (orderLines == null ) { return result;}

                List<OrderLine> orderLinesList = orderLines.getOrderLine();

                for (int i = 0; i < orderLinesList.size(); i++) {
                        OrderLine orderLine = orderLinesList.get(i);

                        Part thisPart = parts.get(orderLine.getId());
                        double orderLineCost = orderLine.getItemCount().intValue() * thisPart.getPrice();

                        result = result + orderLineCost;

                }

                DecimalFormat twoDForm = new DecimalFormat("#.##");
                result = Double.valueOf(twoDForm.format(result));

		return result;
	}

}

