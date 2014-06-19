package org.lab.rabbitmq

import groovy.util.logging.Log4j

import com.rabbitmq.client.ConnectionFactory

@Log4j
class Consumer{

	final String QUEUE = "myQueue"

	def factory = null;
	def connection = null;
	def channel = null;

	Consumer(){
		factory = new ConnectionFactory()
		factory.host = "localhost"
		connection = factory.newConnection()
		channel = connection.createChannel()
	}

	String consume(){
		def response = channel.basicGet(QUEUE,true)
		if(response)
			return new String(response.body)
		"NO MESSAGE!"
	}

	void destroy(){
		channel.close()
		connection.close()
	}
}