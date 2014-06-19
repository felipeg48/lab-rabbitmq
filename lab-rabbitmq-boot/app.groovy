
@Log
@Configuration
@EnableRabbitMessaging
class RabbitExample implements CommandLineRunner {

	final String QUEUE = "myQueue"
	final String EXCHANGE = "myExchange"
	final String RK = "myRK"


	@Autowired
	RabbitTemplate rabbitTemplate


	@Bean
	SimpleMessageListenerContainer container(CachingConnectionFactory connectionFactory) {
		return new SimpleMessageListenerContainer(
			connectionFactory: connectionFactory,
			queueNames: [QUEUE],
			messageListener: new MessageListenerAdapter(new Receiver(), "receive"))
	}


	void run(String... args) {
		log.info "Sending RabbitMQ message..."
		rabbitTemplate.convertAndSend EXCHANGE,RK, "Greetings from Spring Boot via RabbitMQ"
	}

}



@Log
class Receiver {
	def receive(String message) {
		log.info "Received ${message}"
	}
}