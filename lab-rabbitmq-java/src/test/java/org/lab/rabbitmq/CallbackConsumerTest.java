package org.lab.rabbitmq;

import org.junit.Test;

public class CallbackConsumerTest{

	@Test
	public void testCallbackConsumer() throws Exception{
		CallbackConsumer consumer = new CallbackConsumer();
		consumer.consume();
	}

}