package org.lab.rabbitmq;

import org.junit.Test;

public class NextDeliveryConsumerTest{

	@Test
	public void testNextDeliveryConsumer() throws Exception{
		NextDeliveryConsumer consumer = new NextDeliveryConsumer();
		consumer.consume();
	}

}