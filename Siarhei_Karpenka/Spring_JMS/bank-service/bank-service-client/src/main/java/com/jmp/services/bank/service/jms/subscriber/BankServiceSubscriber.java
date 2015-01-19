package com.jmp.services.bank.service.jms.subscriber;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

public class BankServiceSubscriber {
	
	private static final Logger logger = Logger.getLogger(BankServiceSubscriber.class);
	
	private TopicConnection conn = null;
	private TopicSession session = null;
	private Topic topic = null;

	public void setupPubSub() throws JMSException, NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, "remote://localhost:4447"));
		env.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		env.put(Context.SECURITY_PRINCIPAL, "admin");
		env.put(Context.SECURITY_CREDENTIALS, "2a0923285184943425d1f53ddd58ec7a");
		InitialContext iniCtx = new InitialContext(env);
		Object tmp = iniCtx.lookup("java:/ConnectionFactory");
		TopicConnectionFactory tcf = (TopicConnectionFactory) tmp;
		conn = tcf.createTopicConnection();
		topic = (Topic) iniCtx.lookup("topic/test");
		session = conn.createTopicSession(false, TopicSession.AUTO_ACKNOWLEDGE);
		conn.start();
	}

	public void recvSync() throws JMSException, NamingException {
		logger.info("Begin receiving Sync");
		setupPubSub();
		TopicSubscriber recv = session.createSubscriber(topic);
		Message msg = recv.receive(500000);
		if (msg == null) {
			logger.error("Timed out waiting for msg");
		} else {
			logger.info("TopicSubscriber.recv, msgt=" + msg);
		}
	}

	public void stop() throws JMSException {
		conn.stop();
		session.close();
		conn.close();
	}
}