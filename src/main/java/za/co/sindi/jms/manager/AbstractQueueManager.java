/**
 * 
 */
package za.co.sindi.jms.manager;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public abstract class AbstractQueueManager extends GenericJMSManager<QueueConnectionFactory, Queue, QueueConnection, QueueSession, QueueSender, QueueReceiver> {
	
	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createConnection(javax.jms.ConnectionFactory)
	 */
	@Override
	protected QueueConnection createConnection(QueueConnectionFactory connectionFactory) throws JMSException {
		// TODO Auto-generated method stub
		if (connectionFactory == null) {
			throw new IllegalArgumentException("QueueConnectionFactory may not be null.");
		}
		
		if (getAuthentication() != null) {
			return connectionFactory.createQueueConnection(getAuthentication().getUserName(), getAuthentication().getPassword());
		}
		
		return connectionFactory.createQueueConnection();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createSession(javax.jms.Connection)
	 */
	@Override
	protected QueueSession createSession(QueueConnection connection) throws JMSException {
		// TODO Auto-generated method stub
		if (connection == null) {
			throw new IllegalArgumentException("QueueConnection may not be null.");
		}
		
		return connection.createQueueSession(isSessionTransacted(), getAcknowledgeMode().getAcknowledgeMode());
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createMessageConsumer(javax.jms.Session, javax.jms.Destination)
	 */
	@Override
	protected QueueReceiver createMessageConsumer(QueueSession session, Queue destination) throws JMSException {
		// TODO Auto-generated method stub
		if (session == null) {
			throw new IllegalArgumentException("QueueSession may not be null.");
		}
		
		if (destination == null) {
			throw new IllegalArgumentException("Queue may not be null.");
		}
		
		return session.createReceiver(destination);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createMessageProducer(javax.jms.Session, javax.jms.Destination)
	 */
	@Override
	protected QueueSender createMessageProducer(QueueSession session, Queue destination) throws JMSException {
		// TODO Auto-generated method stub
		if (session == null) {
			throw new IllegalArgumentException("QueueSession may not be null.");
		}
		
		if (destination == null) {
			throw new IllegalArgumentException("Queue may not be null.");
		}
		
		return session.createSender(destination);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#send(javax.jms.MessageProducer, javax.jms.Message)
	 */
	@Override
	protected void send(QueueSender messageProducer, Message message) throws JMSException {
		// TODO Auto-generated method stub
		int priority = getPriority().getPriority();
		int deliveryMode = getDeliveryMode().getDeliveryMode();
		
		if (isExplicitQoSEnabled()) {
			messageProducer.send(message, deliveryMode, priority, getTimeToLive());
		} else {
			messageProducer.setDeliveryMode(deliveryMode);
			messageProducer.setPriority(priority);
			messageProducer.setTimeToLive(getTimeToLive());
			
			messageProducer.send(message);
		}
	}
}
