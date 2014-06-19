package org.lab.spring;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer{

	final String EXCHANGE = "myExchange";
	final String RK = "myRK";

	@Autowired
	RabbitTemplate template;

	public void send(String message){
		template.convertAndSend(EXCHANGE,RK,message);
	}

}


