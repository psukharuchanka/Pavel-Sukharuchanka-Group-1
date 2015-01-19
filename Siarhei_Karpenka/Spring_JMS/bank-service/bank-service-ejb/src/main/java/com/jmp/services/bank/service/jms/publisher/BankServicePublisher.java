package com.jmp.services.bank.service.jms.publisher;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class BankServicePublisher {

	private static final Logger logger = Logger.getLogger(BankServicePublisher.class);

	private TopicConnection conn = null;
	private TopicSession session = null;
	private Topic topic = null;

	public void setupPubSub() throws JMSException, NamingException {
		logger.info("Setting up BankServicePublisher");
		InitialContext iniCtx = new InitialContext();
		Object tmp = iniCtx.lookup("java:/ConnectionFactory");
		TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
		conn = tcf.createTopicConnection();
		topic = (Topic) iniCtx.lookup("topic/test");
		session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		conn.start();
		logger.info("BankServicePublisher successfully");
	}

	public void sendAsync(String text) throws JMSException, NamingException {
		logger.info("Begin sending Asynchronous messages");
		setupPubSub();
		TopicPublisher send = session.createPublisher(topic);
		TextMessage tm = session.createTextMessage(text);
		send.publish(tm);
		logger.info("sendAsync, sent text=" + tm.getText());
		send.close();
		logger.info("End sendAsync");
	}

	public void stop() throws JMSException {
		conn.stop();
		session.close();
		conn.close();
	}
}
