/**
 * 
 */
package za.co.sindi.jms.message.impl;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import za.co.sindi.jms.message.AbstractJMSMessage;

/**
 * @author Bienfait Sindi
 * @since 09 July 2012
 *
 */
public class JMSObjectMessage extends AbstractJMSMessage {

	private final Serializable value;
	
	/**
	 * 
	 */
	public JMSObjectMessage() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param value
	 */
	public JMSObjectMessage(Serializable value) {
		super();
		
//		if (serializable == null) {
//			throw new IllegalArgumentException("JMS Serializable object may not be null.");
//		}
		
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.jms.message.AbstractJMSMessage#createMessageInternally(javax.jms.Session)
	 */
	@Override
	protected ObjectMessage createMessageInternally(Session session) throws JMSException {
		// TODO Auto-generated method stub
		if (value == null) {
			return session.createObjectMessage();
		}
		
		return session.createObjectMessage(value);
	}
}
