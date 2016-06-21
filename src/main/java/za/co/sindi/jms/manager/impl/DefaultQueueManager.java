/**
 * 
 */
package za.co.sindi.jms.manager.impl;

import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;

import za.co.sindi.jms.exception.ServiceLocatorException;
import za.co.sindi.jms.manager.AbstractQueueManager;
import za.co.sindi.service.locator.JNDIServiceLocator;

/**
 * @author Bienfait Sindi
 * @since 21 March 2014
 *
 */
public class DefaultQueueManager extends AbstractQueueManager {

	private QueueConnectionFactory connectionFactory;
	private Queue destination;
	
	/**
	 * @param factoryName
	 * @param destinationName
	 * @throws ServiceLocatorException 
	 */
	public DefaultQueueManager(String factoryName, String destinationName) throws ServiceLocatorException {
		this((QueueConnectionFactory) JNDIServiceLocator.getInstance().getConnectionFactory(factoryName), (Queue) JNDIServiceLocator.getInstance().getDestination(destinationName));
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param connectionFactory
	 * @param destination
	 */
	public DefaultQueueManager(QueueConnectionFactory connectionFactory, Queue destination) {
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
	protected QueueConnectionFactory getConnectionFactory() {
		// TODO Auto-generated method stub
		return connectionFactory;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.jms.manager.GenericsJMSManager#getDestination()
	 */
	@Override
	protected Queue getDestination() {
		// TODO Auto-generated method stub
		return destination;
	}
}
