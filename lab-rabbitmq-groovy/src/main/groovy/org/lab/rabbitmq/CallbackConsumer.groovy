package org.lab.rabbitmq

import groovy.util.logging.Log4j

import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.AMQP.BasicProperties
import com.rabbitmq.client.Envelope

@Log4j
class CallbackConsumer{

	final String QUEUE = "myQueue"

	def factory = null
	def connection = null
	def channel = null

	CallbackConsumer(){
		factory = new ConnectionFactory()
		factory.host="localhost"
		connection = factory.newConnection()
		channel = connection.createChannel()
	}


	void consume(){
		channel.basicConsume QUEUE, false, new DefaultConsumer(channel){

						void handleDelivery(String consumerTag,
		                                    Envelope envelope,
		                                    BasicProperties properties,
		                                    byte[] body){
					 		log.info "@@@@@@: ${new String(body)}"
				     		channel.basicAck envelope.deliveryTag, false
						}
					}
	}

	void destroy(){
		channel.close()
		connection.close()
	}
}