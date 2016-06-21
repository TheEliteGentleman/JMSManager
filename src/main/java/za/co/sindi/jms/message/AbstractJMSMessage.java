/**
 * 
 */
package za.co.sindi.jms.message;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author Bienfait Sindi
 * @since 10 March 2014
 *
 */
public abstract class AbstractJMSMessage implements JMSMessage {

	/* (non-Javadoc)
	 * @see za.co.sindi.jms.message.JMSMessage#createMessage(javax.jms.Session)
	 */
	public Message createMessage(Session session) throws JMSException {
		// TODO Auto-generated method stub
		if (session == null) {
			throw new IllegalArgumentException("JMS Session may not be null.");
		}
		
		return createMessageInternally(session);
	}

	protected abstract Message createMessageInternally(Session session) throws JMSException;
}
