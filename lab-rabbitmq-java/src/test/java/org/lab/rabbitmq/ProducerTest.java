package org.lab.rabbitmq;

import org.junit.Test;

public class ProducerTest{


	@Test
	public void testSender() throws Exception{
		Producer producer = new Producer();

		for(int i=0; i<3; i++)
			producer.send("Hello World!");

		producer.destroy();
	}

}