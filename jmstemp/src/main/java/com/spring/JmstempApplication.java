package com.spring;

import java.util.Date;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

@SpringBootApplication
public class JmstempApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JmstempApplication.class, args);
		
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		
		System.out.println("Sending Message.....");
		
		jmsTemplate.convertAndSend("jms.message.mq.dev", new Message(10001,"THIS LINE IS A MESSAGE ",new Date()));
	
		System.out.println("-----Message Sent-----");
	}

	@Bean
	 public JmsListenerContainerFactory<?> myFactory(
	                            ConnectionFactory connectionFactory,
	                            DefaultJmsListenerContainerFactoryConfigurer configurer) {
	    	
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        
	     // anonymous class
	        factory.setErrorHandler(
	            new ErrorHandler() {
	              @Override
	              public void handleError(Throwable t) {
	                System.err.println("An error has occurred in the transaction");
	              }
	        });
	       
	        // lambda function
	        //factory.setErrorHandler(t -> System.err.println("An error has occurred in the transaction"));
	        
	        
	        // This provides all boot's default to this factory, including the message converter
	        configurer.configure(factory, connectionFactory);
	        // You could still override some of Boot's default if necessary.
	        
	        return factory;
	    }
	 
	    @Bean
	    public MessageConverter jacksonJmsMessageConverter() {
	    	
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        converter.setTargetType(MessageType.TEXT);
	        converter.setTypeIdPropertyName("_type");
	        
	        return converter;
	    }
}
