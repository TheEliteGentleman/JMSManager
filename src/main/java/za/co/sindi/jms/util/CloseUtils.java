/**
 * 
 */
package za.co.sindi.jms.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public final class CloseUtils {

	private static final Logger logger = Logger.getLogger(CloseUtils.class.getName());
	
	/**
	 * Hidden for good reason.
	 */
	private CloseUtils() {
		//NOOP
	}
	
	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, e.getLocalizedMessage(), e);
			} finally {
				connection = null;			//Free resources.
			}
		}
	}
	
	public static void closeSession(Session session) {
		if (session != null) {
			try {
				session.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, e.getLocalizedMessage(), e);
			} finally {
				session = null;
			}
		}
	}

	public static void closeMessageProducer(MessageProducer producer) {
		if (producer != null) {
			try {
				producer.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, e.getLocalizedMessage(), e);
			} finally {
				producer = null;
			}
		}
	}
	
	public static void closeMessageConsumer(MessageConsumer consumer) {
		if (consumer != null) {
			try {
				consumer.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				logger.log(Level.WARNING, e.getLocalizedMessage(), e);
			} finally {
				consumer = null;
			}
		}
	}
}
