/**
 * 
 */
package za.co.sindi.jms.message.impl;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import za.co.sindi.jms.message.AbstractJMSMessage;

/**
 * @author Buhake Sindi
 * @since 04 July 2012
 *
 */
public class JMSTextMessage extends AbstractJMSMessage {

	private final String text;
	
	/**
	 * 
	 */
	public JMSTextMessage() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	public JMSTextMessage(String text) {
		super();
//		if (text == null) {
//			throw new IllegalArgumentException("Text may not be null.");
//		}
		
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.jms.message.AbstractJMSMessage#createMessageInternally(javax.jms.Session)
	 */
	@Override
	protected TextMessage createMessageInternally(Session session) throws JMSException {
		// TODO Auto-generated method stub
		if (text == null) {
			return session.createTextMessage();
		}
		
		return session.createTextMessage(text);
	}
}
