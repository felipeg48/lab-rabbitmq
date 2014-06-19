package org.lab.rabbitmq

import org.junit.Test
import groovy.util.logging.Log4j

@Log4j
class ProducerTest{

	@Test
	void testSender() throws Exception{
		def producer = new Producer()

		3.times {
			producer.send "Hello World!"
			log.info "[$it] message sent"
		}

		producer.destroy()

	}

}