package org.lab.rabbitmq;

import org.junit.Test;
import org.apache.log4j.Logger;

public class SimpleConsumerTest{
	Logger log = Logger.getLogger(SimpleConsumerTest.class);

	@Test
	public void testSimpleConsumer() throws Exception{
		Consumer consumer = new Consumer();
		String message = consumer.consume();
		log.info("@@@@@@ > " +  message);
	}

}