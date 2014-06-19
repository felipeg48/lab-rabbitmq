package org.lab.spring;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Component;

@Component
public class Consumer{
	Logger log = Logger.getLogger(Consumer.class);

	public void listen(String message) {
		log.info("@@@@@: " + message);
	}

}