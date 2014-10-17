package org.lab.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import static java.lang.System.out;

public class ProducerConfirms{
	final String ROUTING_KEY = "myRK";
	final String EXCHANGE = "myExchange";

	ConnectionFactory factory = null;
	Connection connection = null;
	Channel channel = null;
	BasicProperties.Builder builder = null;
	BasicProperties properties = null;

	public ProducerConfirms() throws IOException{
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
		
		//Set to Confirm Select
		channel.confirmSelect();
		
		//Add the Confirmlistener
		channel.addConfirmListener(new ConfirmHandler());
	}

	public void send(String message) throws IOException, InterruptedException{

		 Map<String,Object> headers = new HashMap<String,Object>();
		 headers.put("HL7A",1);

		 builder = new BasicProperties.Builder();
		 properties = builder.deliveryMode(2)
			 .headers(headers)
		     .contentType("text/plain")
		     .build();

		 channel.basicPublish(EXCHANGE,ROUTING_KEY,properties,message.getBytes());
		 
		 //Add the Confirms
		 channel.waitForConfirmsOrDie();
	}
	
	protected class ConfirmHandler implements ConfirmListener {
		
		public void handleAck(long deliveryTag, boolean multiple)
				throws IOException {
			out.println("Message received by RabbitMQ broker.");
		}

		public void handleNack(long deliveryTag, boolean multiple)
				throws IOException {
			out.println("RabbitMQ can't accept any message...");
		}
	}

	public void destroy() throws Exception{
		channel.close();
		connection.close();
	}

}