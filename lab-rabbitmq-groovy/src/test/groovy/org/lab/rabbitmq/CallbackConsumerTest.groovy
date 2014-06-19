package org.lab.rabbitmq

import org.junit.Test

class CallbackConsumerTest{

	@Test
	void testCallbackConsumer() throws Exception{
		CallbackConsumer consumer = new CallbackConsumer()
		consumer.consume()
	}

}