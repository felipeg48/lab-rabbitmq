beans{

	xmlns context:"http://www.springframework.org/schema/context"
	xmlns rabbit:"http://www.springframework.org/schema/rabbit"

	context.'component-scan'('base-package': "org.lab.spring")

	rabbit.'connection-factory'(id:"rabbitConnectionFactory", host:"localhost")
	rabbit.template(id:"rabbitTemplate",'connection-factory':"rabbitConnectionFactory")
	rabbit.'listener-container'(id:"listener", 'connection-factory':"rabbitConnectionFactory"){
			rabbit.listener(ref:"consumer",  method:"listen", 'queue-names':"myQueue")
	}

}