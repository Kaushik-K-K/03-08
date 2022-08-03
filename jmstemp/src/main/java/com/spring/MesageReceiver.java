package com.spring;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MesageReceiver {
	
	@JmsListener(destination = "jms.message.mq.dev")
	public void receiveMessage(Message msg) {
		System.out.println("Message Received : " + msg);
	}
}
