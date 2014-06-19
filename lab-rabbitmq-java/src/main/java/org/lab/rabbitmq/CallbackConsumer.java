package org.lab.rabbitmq;

import org.apache.log4j.Logger;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;

import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class CallbackConsumer{
    Logger log = Logger.getLogger(Consumer.class);

	final String QUEUE = "myQueue";

	ConnectionFactory factory = null;
	Connection connection = null;
	Channel channel = null;

	public CallbackConsumer() throws Exception{
		factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();
	}

	public void consume() throws Exception{
		channel.basicConsume(QUEUE, false,
		     new DefaultConsumer(channel) {
		         @Override
		         public void handleDelivery(String consumerTag,
		                                    Envelope envelope,
		                                    BasicProperties properties,
		                                    byte[] body)
		             throws IOException
		         {
					 log.info("@@@@@@: " + new String(body));
				     channel.basicAck(envelope.getDeliveryTag(), false);
		         }
		     });
	}

	public void destroy() throws Exception{
		channel.close();
		connection.close();
	}
}