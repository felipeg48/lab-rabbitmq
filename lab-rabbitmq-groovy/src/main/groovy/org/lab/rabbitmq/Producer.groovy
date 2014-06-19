package org.lab.rabbitmq

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.AMQP.BasicProperties

class Producer{
	final String ROUTING_KEY = "myRK"
	final String EXCHANGE = "myExchange"

	def factory = null
	def connection = null
	def channel = null
	def builder = null
	def properties = null

	Producer(){
		factory = new ConnectionFactory()
		factory.host = "localhost"
		connection = factory.newConnection()
		channel = connection.createChannel()
	}

	void send(message){
		 builder = new BasicProperties.Builder()
		 properties = builder.deliveryMode(2)
			 .headers( "HL7A":1 )
		     .contentType("text/plain")
		     .build();

		 channel.basicPublish EXCHANGE,ROUTING_KEY,properties,message.bytes
	}

	void destroy(){
		channel.close()
		connection.close()
	}

}