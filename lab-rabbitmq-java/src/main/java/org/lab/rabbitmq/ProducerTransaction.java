package org.lab.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducerTransaction {
	final String ROUTING_KEY = "myRK";
	final String EXCHANGE = "myExchange";

	ConnectionFactory factory = null;
	Connection connection = null;
	Channel channel = null;
	BasicProperties.Builder builder = null;
	BasicProperties properties = null;

	public ProducerTransaction() throws IOException {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();		
	}

	public void send(String message) throws IOException, InterruptedException {

		//Step. Set the TX into the Channel (Transaction Mode)
		channel.txSelect();
		
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("HL7A", 1);

		builder = new BasicProperties.Builder();
		properties = builder.deliveryMode(2).headers(headers)
				.contentType("text/plain").build();

		channel.basicPublish(EXCHANGE, ROUTING_KEY, properties,
				message.getBytes());
		
		//Step. Commit
		channel.txCommit();

	}

	public void destroy() throws Exception {
		channel.close();
		connection.close();
	}

}