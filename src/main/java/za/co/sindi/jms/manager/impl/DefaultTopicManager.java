/**
 * 
 */
package za.co.sindi.jms.manager.impl;

import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;

import za.co.sindi.jms.exception.ServiceLocatorException;
import za.co.sindi.jms.manager.AbstractTopicManager;
import za.co.sindi.service.locator.JNDIServiceLocator;

/**
 * @author Bienfait Sindi
 * @since 21 March 2014
 *
 */
public class DefaultTopicManager extends AbstractTopicManager {
	
	private TopicConnectionFactory connectionFactory;
	private Topic destination;
	
	/**
	 * @param factoryName
	 * @param destinationName
	 * @throws ServiceLocatorException 
	 */
	public DefaultTopicManager(String factoryName, String destinationName) throws ServiceLocatorException {
		this((TopicConnectionFactory) JNDIServiceLocator.getInstance().getConnectionFactory(factoryName), (Topic) JNDIServiceLocator.getInstance().getDestination(destinationName));
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param connectionFactory
	 * @param destination
	 */
	public DefaultTopicManager(TopicConnectionFactory connectionFactory, Topic destination) {
		super();
		// TODO Auto-generated constructor stub
		if (connectionFactory == null) {
			throw new IllegalArgumentException("JMS ConnectionFactory may not be null.");
		}
		
		if (destination == null) {
			throw new IllegalArgumentException("JMS Destination may not be null.");
		}
		
		this.connectionFactory = connectionFactory;
		this.destination = destination;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.jms.manager.GenericsJMSManager#getConnectionFactory()
	 */
	@Override
	protected TopicConnectionFactory getConnectionFactory() {
		// TODO Auto-generated method stub
		return connectionFactory;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.jms.manager.GenericsJMSManager#getDestination()
	 */
	@Override
	protected Topic getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}
}
