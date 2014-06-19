package org.lab.spring

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Producer{

	final String EXCHANGE = "myExchange"
	final String RK = "myRK"

	@Autowired
	RabbitTemplate template

	void send(message){
		template.convertAndSend EXCHANGE,RK,message
	}

}


