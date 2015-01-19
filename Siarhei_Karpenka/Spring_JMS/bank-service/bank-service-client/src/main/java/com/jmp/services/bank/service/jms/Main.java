package com.jmp.services.bank.service.jms;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.jmp.services.bank.service.jms.subscriber.BankServiceSubscriber;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws JMSException, NamingException {
		logger.info("Begin TopicRecvClient, now=" + System.currentTimeMillis());
		BankServiceSubscriber client = new BankServiceSubscriber();
		client.recvSync();
		client.stop();
		logger.info("End TopicRecvClient");
		System.exit(0);
	}
}
