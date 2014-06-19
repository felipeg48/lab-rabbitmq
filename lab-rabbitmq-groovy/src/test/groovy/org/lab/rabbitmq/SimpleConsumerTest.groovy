package org.lab.rabbitmq

import org.junit.Test
import groovy.util.logging.Log4j

@Log4j
public class SimpleConsumerTest{

	@Test
	void testSimpleConsumer(){
		Consumer consumer = new Consumer()
		log.info "@@@@@@ > ${consumer.consume()}"
	}

}