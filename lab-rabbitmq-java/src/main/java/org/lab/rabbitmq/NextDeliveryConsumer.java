package org.lab.rabbitmq;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class NextDeliveryConsumer{
    Logger log = Logger.getLogger(Consumer.class);

	final String QUEUE = "myQueue";

	ConnectionFactory factory = null;
	Connection connection = null;
	Channel channel = null;

	public NextDeliveryConsumer() throws Exception{
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
	}

	public void consume() throws Exception{
		QueueingConsumer.Delivery delivery = null;
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(QUEUE, false, consumer);

		while (true) {
			 delivery = consumer.nextDelivery(10);
			 if(delivery!=null){
			 	String message = new String(delivery.getBody());
			 	log.info("@@@@@@: " + message);
			 	channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		 	 }
			 //log.info("@@@@@@: About to cancel...");
			 //Thread.sleep(10000L);
			 //channel.basicCancel(consumer.getConsumerTag());
			 //log.info("@@@@@@: Channel cancelled");
		 }
	}

	public void destroy() throws Exception{
		channel.close();
		connection.close();
	}
}