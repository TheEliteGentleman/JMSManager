/**
 * 
 */
package za.co.sindi.jms.manager;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import za.co.sindi.jms.manager.AbstractJMSManager;
import za.co.sindi.jms.message.JMSMessage;
import za.co.sindi.jms.util.CloseUtils;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public abstract class GenericJMSManager<CF extends ConnectionFactory, DES extends Destination, CONN extends Connection, SESS extends Session, MP extends MessageProducer, MC extends MessageConsumer> extends AbstractJMSManager {

//	private CF connectionFactory;
//	private DES destination;
	private CONN connection;
	private SESS session;
	private MP messageProducer;
	private MC messageConsumer;
	private boolean initialized;
	private boolean started;

//	/**
//	 * @param connectionFactory
//	 * @param destination
//	 */
//	protected GenericsJMSManager(CF connectionFactory, DES destination) {
//		super();
//		if (connectionFactory == null) {
//			throw new IllegalArgumentException("JMS ConnectionFactory may not be null.");
//		}
//		
//		if (destination == null) {
//			throw new IllegalArgumentException("JMS Destination may not be null.");
//		}
//		
//		this.connectionFactory = connectionFactory;
//		this.destination = destination;
//	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.JMSManager#initializeJMS()
	 */
	public void initializeJMS() throws JMSException {
		// TODO Auto-generated method stub
//		connection = createConnection(connectionFactory);
		connection = createConnection(getConnectionFactory());
		session = createSession(connection);
		initialized = true;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.JMSManager#close()
	 */
	public void close() throws JMSException {
		// TODO Auto-generated method stub
		stop();
		
		CloseUtils.closeMessageConsumer(messageConsumer);
		CloseUtils.closeMessageProducer(messageProducer);
		CloseUtils.closeConnection(connection);
		CloseUtils.closeSession(session);
		
		//Deinitialize
		if (initialized) initialized = false;
 	}

	/* (non-Javadoc)
	 * @see za.co.bayport.jms.JMSManager#commit()
	 */
	public void commit() throws JMSException {
		// TODO Auto-generated method stub
		if (session != null && session.getTransacted()) {
			session.commit();
		}
	}

	/* (non-Javadoc)
	 * @see za.co.bayport.jms.JMSManager#send(za.co.bayport.jms.JMSMessage)
	 */
	public void send(JMSMessage... jmsMessages) throws JMSException {
		// TODO Auto-generated method stub
		if (jmsMessages == null) {
			throw new IllegalArgumentException("JMS Message(s) cannot be null.");
		}
		
		checkAndForceInitialization();
	
		//Create MessageProducer
		if (messageProducer == null) {
//			messageProducer = createMessageProducer(session, destination);
			messageProducer = createMessageProducer(session, getDestination());
		}
		
		//Send message
		for (JMSMessage jmsMessage : jmsMessages) {
			send(messageProducer, jmsMessage.createMessage(session));
		}
		
		//AutoCommit
		if (isAutoCommit()) {
			commit();
		}
	}

	/* (non-Javadoc)
	 * @see za.co.bayport.jms.JMSManager#receive()
	 */
	public Message receive() throws JMSException {
		// TODO Auto-generated method stub
		if (getMessageListener() != null) {
			throw new JMSException("Cannot execute current function. Asynchronous messaging was set. Call 'receiveAsync()' instead.");
		}	
		
		//force initialization
		checkAndForceInitialization();
		
		//Create message consumer
		if (messageConsumer == null) {
//			messageConsumer = createMessageConsumer(session, destination);
			messageConsumer = createMessageConsumer(session, getDestination());
		}
		
		//Begin
		if (!started) {
			connection.start();
			started = true;
		}

		Message message = null;
		
		if (getReceiveTimeout() == RECEIVE_TIMEOUT_NOWAIT) {
			message = messageConsumer.receiveNoWait();
		} else if (getReceiveTimeout() > DEFAULT_RECEIVE_TIMEOUT) {
			message = messageConsumer.receive(getReceiveTimeout());
		} else {
			message = messageConsumer.receive();
		}
		
		if(isAutoCommit()) {
			commit();
		} else if (isClientAcknowledge()) {
			if (message != null) {
				message.acknowledge();
			}
		}
		
		return message;
	}

	/* (non-Javadoc)
	 * @see za.co.bayport.jms.JMSManager#recover()
	 */
	public void recover() throws JMSException {
		// TODO Auto-generated method stub
		if (session != null) {
			session.recover();
		}
	}

	/* (non-Javadoc)
	 * @see za.co.bayport.jms.JMSManager#rollback()
	 */
	public void rollback() throws JMSException {
		// TODO Auto-generated method stub
		if (session != null && session.getTransacted()) {
			session.rollback();
		}
	}

	/* (non-Javadoc)
	 * @see za.co.bayport.jms.JMSManager#receiveAsync()
	 */
	public void receiveAsync() throws JMSException {
		// TODO Auto-generated method stub
		if (getMessageListener() == null) {
			throw new JMSException("No MessageListener provided. Apply a MessageListener by calling 'setMessageListener()'.");
		}
		
		checkAndForceInitialization();
		
		//Create message consumer
		if (messageConsumer == null) {
//			messageConsumer = createMessageConsumer(session, destination);
			messageConsumer = createMessageConsumer(session, getDestination());
		}
		
		//Create Message Consumer
		messageConsumer.setMessageListener(getMessageListener());
		
		//Begin
		if (!started) {
			connection.start();
			started = true;
		}
	}

	/**
	 * Stop the JMS Connection.
	 * 
	 * @throws JMSException
	 */
	private void stop() throws JMSException {
		// TODO Auto-generated method stub
		if (started) {
			connection.stop();
			started = false;
		}
	}
	
	/**
	 * Checks if session's acknowledge mode is <code>CLIENT_ACKNOWLEDGE</code>.
	 * 
	 * @return true, if client acknowledged, false otherwise.
	 * @throws JMSException
	 */
	private boolean isClientAcknowledge() throws JMSException {
		if (session == null) {
			return false;
		}
		
		return (session.getAcknowledgeMode() == Session.CLIENT_ACKNOWLEDGE);
	}
	
	private void checkAndForceInitialization() throws JMSException {
		if (!initialized) {
			initializeJMS();
		}
	}
	
	protected abstract CF getConnectionFactory();
	protected abstract DES getDestination();
	protected abstract CONN createConnection(CF connectionFactory) throws JMSException;
	protected abstract SESS createSession(CONN connection) throws JMSException;
	protected abstract MC createMessageConsumer(SESS session, DES destination) throws JMSException;
	protected abstract MP createMessageProducer(SESS session, DES destination) throws JMSException;
	protected abstract void send(MP messageProducer, Message message) throws JMSException;
}
