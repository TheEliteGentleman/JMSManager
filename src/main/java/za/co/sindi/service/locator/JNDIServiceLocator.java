/**
 * 
 */
package za.co.sindi.service.locator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import za.co.sindi.jms.exception.ServiceLocatorException;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public class JNDIServiceLocator {

	private InitialContext context;
	private Map<String, Object> cache;
	private static JNDIServiceLocator instance;

	/**
	 * 
	 */
	private JNDIServiceLocator() throws ServiceLocatorException {
		// TODO Auto-generated constructor stub
		initializeCache();
		clearCache();
		
		//Setup InitialContext
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new ServiceLocatorException("Unable to initialize IntialContext.", e);
		}
	}
	
	/**
	 * Create a new cache, if one hasn't been created before.
	 */
	private void initializeCache() {
		if (cache == null) {
			cache = Collections.synchronizedMap(new HashMap<String, Object>());
		}
	}
	
	/**
	 * Clear cache, if necessary.
	 */
	private void clearCache() {
		if (cache != null && !cache.isEmpty()) {
			cache.clear();
		}
	}

	/**
	 * @return the instance
	 * @throws ServiceLocatorException 
	 */
	public static JNDIServiceLocator getInstance() throws ServiceLocatorException {
		if (instance == null) {
			instance = new JNDIServiceLocator();
		}
		
		return instance;
	}
	
	/**
	 * Returns an object cached, if cached, else do a JNDI lookup, cache the object and return the same looked-up object.
	 * 
	 * @param jndiName
	 * @return
	 * @throws ServiceLocatorException
	 */
	public Object getObject(String jndiName) throws ServiceLocatorException {
		Object object = null;
		try {
			if (jndiName != null && !jndiName.isEmpty()) {
				initializeCache();
				
				if (cache.containsKey(jndiName)) {
					object = cache.get(jndiName);
				} else {
					object = context.lookup(jndiName);
					cache.put(jndiName, object);
				}
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new ServiceLocatorException(e.getLocalizedMessage(), e);
		}
		
		return object;
	}
	
	/**
	 * Returns a JMS {@link ConnectionFactory}. If <code>not null</code>, one should typecast it to either {@link TopicConnectionFactory} or {@link QueueConnectionFactory}.
	 * 
	 * @param jndiName
	 * @return
	 * @throws ServiceLocatorException
	 */
	public ConnectionFactory getConnectionFactory(String jndiName) throws ServiceLocatorException {
		Object o = getObject(jndiName);
		if (o != null) {
			return (ConnectionFactory)o;
		}
		
		return null;
	}
	
	/**
	 * Returns a JMS {@link Destination}. One should typecast to it's relevant destination ({@link Queue} or {@link Topic}).
	 * 
	 * @param jndiMessage
	 * @return
	 * @throws ServiceLocatorException
	 */
	public Destination getDestination(String jndiName) throws ServiceLocatorException {
		Object o = getObject(jndiName);
		if (o != null) {
			return (Destination)o;
		}
	
		return null;
	}
}
