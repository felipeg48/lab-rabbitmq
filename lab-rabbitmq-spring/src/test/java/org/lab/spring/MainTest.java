package org.lab.spring;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:META-INF/spring/rabbitmq-context.xml"})
public class MainTest{

	@Autowired
	Producer producer;

	@Test
	public void testProducer() throws Exception{

		for(int i=0; i < 3; i++)
			producer.send("Hello World");

		Thread.sleep(1000);
	}

}