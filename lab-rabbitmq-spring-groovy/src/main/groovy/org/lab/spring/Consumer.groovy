package org.lab.spring

import org.springframework.stereotype.Component
import groovy.util.logging.Log4j

@Log4j
@Component
class Consumer{

	void listen(String message) {
		log.info "@@@@@: $message"
	}

}