/**
 * 
 */
package za.co.sindi.jms.manager;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public abstract class AbstractTopicManager extends GenericJMSManager<TopicConnectionFactory, Topic, TopicConnection, TopicSession, TopicPublisher, TopicSubscriber> {

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createConnection(javax.jms.ConnectionFactory)
	 */
	@Override
	protected TopicConnection createConnection(TopicConnectionFactory connectionFactory) throws JMSException {
		// TODO Auto-generated method stub
		if (connectionFactory == null) {
			throw new IllegalArgumentException("TopicConnectionFactory may not be null.");
		}
		
		if (getAuthentication() != null) {
			return connectionFactory.createTopicConnection(getAuthentication().getUserName(), getAuthentication().getPassword());
		}
		
		return connectionFactory.createTopicConnection();
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createSession(javax.jms.Connection)
	 */
	@Override
	protected TopicSession createSession(TopicConnection connection) throws JMSException {
		// TODO Auto-generated method stub
		if (connection == null) {
			throw new IllegalArgumentException("TopicConnection may not be null.");
		}
		
		return connection.createTopicSession(isSessionTransacted(), getAcknowledgeMode().getAcknowledgeMode());
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createMessageConsumer(javax.jms.Session, javax.jms.Destination)
	 */
	@Override
	protected TopicSubscriber createMessageConsumer(TopicSession session, Topic destination) throws JMSException {
		// TODO Auto-generated method stub
		if (session == null) {
			throw new IllegalArgumentException("TopicSession may not be null.");
		}
		
		if (destination == null) {
			throw new IllegalArgumentException("Topic may not be null.");
		}
		
		return session.createSubscriber(destination);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#createMessageProducer(javax.jms.Session, javax.jms.Destination)
	 */
	@Override
	protected TopicPublisher createMessageProducer(TopicSession session, Topic destination) throws JMSException {
		// TODO Auto-generated method stub
		if (session == null) {
			throw new IllegalArgumentException("TopicSession may not be null.");
		}
		
		if (destination == null) {
			throw new IllegalArgumentException("Topic may not be null.");
		}
		
		return session.createPublisher(destination);
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.manager.impl.GenericsJMSManager#send(javax.jms.MessageProducer, javax.jms.Message)
	 */
	@Override
	protected void send(TopicPublisher messageProducer, Message message) throws JMSException {
		// TODO Auto-generated method stub
		int priority = getPriority().getPriority();
		int deliveryMode = getDeliveryMode().getDeliveryMode();
		
		if (isExplicitQoSEnabled()) {
			messageProducer.publish(message, deliveryMode, priority, getTimeToLive());
		} else {
			messageProducer.setDeliveryMode(deliveryMode);
			messageProducer.setPriority(priority);
			messageProducer.setTimeToLive(getTimeToLive());
			
			messageProducer.publish(message);
		}
	}
}
