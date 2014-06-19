package org.lab.spring

import org.junit.Test
import org.junit.Ignore
import org.junit.runner.RunWith

import org.springframework.context.support.GenericGroovyApplicationContext


class MainTest{

	final ctx = new GenericGroovyApplicationContext("classpath:META-INF/groovy/rabbitmqContext.groovy")

	@Test
	void testProducer(){
		def producer = ctx.getBean("producer")

		3.times{
			producer.send "Hello World"
		}

		sleep 1000
	}

}