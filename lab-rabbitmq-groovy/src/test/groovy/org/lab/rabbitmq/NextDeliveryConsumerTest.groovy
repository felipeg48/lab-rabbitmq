package org.lab.rabbitmq

import org.junit.Test

class NextDeliveryConsumerTest{

	@Test
	void testNextDeliveryConsumer(){
		NextDeliveryConsumer consumer = new NextDeliveryConsumer()
		consumer.consume()
	}

}