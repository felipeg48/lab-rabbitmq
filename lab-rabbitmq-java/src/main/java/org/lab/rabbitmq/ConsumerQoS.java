package org.lab.rabbitmq;

import java.io.IOException;
import static java.lang.System.out;
import org.apache.log4j.Logger;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ConsumerQoS {
	Logger log = Logger.getLogger(ConsumerQoS.class);

	final String QUEUE = "myQueue";

	ConnectionFactory factory = null;
	Connection connection = null;
	Channel channel = null;

	public ConsumerQoS() throws Exception {
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();

		// Step. Set QoS, 1 message at a time
		channel.basicQos(1);
	}

	public void consume() throws Exception {
		channel.basicConsume(QUEUE, false,consumer);
	}

	protected DefaultConsumer consumer = new DefaultConsumer(channel) {
		public void handleDelivery(String consumerTag, Envelope envelope,
				BasicProperties properties, byte[] body) throws IOException {
			out.println("Message received: " + new String(body));
			channel.basicAck(envelope.getDeliveryTag(), false);
		}
	};

	public void destroy() throws Exception {
		channel.close();
		connection.close();
	}
}