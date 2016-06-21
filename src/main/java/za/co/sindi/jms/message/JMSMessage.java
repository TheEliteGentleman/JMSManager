/**
 * 
 */
package za.co.sindi.jms.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author Buhake Sindi
 * @since 04 July 2012
 *
 */
public interface JMSMessage {

	public Message createMessage(Session session) throws JMSException;
}
