package com.jmp.services.bank.service.jms.listener;

import java.util.Date;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.jmp.services.bank.service.jms.subscriber.BankServiceSubscriber;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/test") })
public class QueueListener implements MessageListener {
	
	private static final Logger logger = Logger.getLogger(BankServiceSubscriber.class);
	
	public QueueListener() {
	}

	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				logger.info("Queue: I received a TextMessage at " + new Date());
				TextMessage msg = (TextMessage) message;
				logger.info("Message is : " + msg.getText());
			} else {
				logger.info("Not a valid message for this Queue MDB");
			}

		} catch (JMSException e) {
			logger.error(e.getMessage());
		}
	}
}
