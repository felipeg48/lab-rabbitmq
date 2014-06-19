package org.lab.rabbitmq

import groovy.util.logging.Log4j

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.QueueingConsumer

@Log4j
class NextDeliveryConsumer{

	final String QUEUE = "myQueue"

	def factory = null
	def connection = null
	def channel = null

	NextDeliveryConsumer(){
		factory = new ConnectionFactory()
		factory.host = "localhost"
		connection = factory.newConnection()
		channel = connection.createChannel()
	}

    void consume(){
		def delivery = null
		def consumer = new QueueingConsumer(channel)
		channel.basicConsume QUEUE, false, consumer

		while (true) {
			 delivery = consumer.nextDelivery()
			 if(delivery){
			 	def message = new String(delivery.body);
			 	log.info "@@@@@@: $message"
			 	channel.basicAck delivery.envelope.deliveryTag, false
		 	 }
		 }
	}

	void destroy(){
		channel.close()
		connection.close()
	}
}