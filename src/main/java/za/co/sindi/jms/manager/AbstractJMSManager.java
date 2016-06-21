/**
 * 
 */
package za.co.sindi.jms.manager;

import javax.jms.ExceptionListener;
import javax.jms.Message;
import javax.jms.MessageListener;

import za.co.sindi.jms.authentication.Authentication;
import za.co.sindi.jms.enums.AcknowledgeMode;
import za.co.sindi.jms.enums.DeliveryMode;
import za.co.sindi.jms.enums.Priority;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public abstract class AbstractJMSManager implements JMSManager {
	
	//For session creation
	private boolean sessionTransacted;
	private AcknowledgeMode acknowledgeMode = DEFAULT_ACKNOWLEDGE_MODE;
	private ExceptionListener exceptionListener;
	
	//For message delivery
	private boolean explicitQoSEnabled = false;
	private DeliveryMode deliveryMode = DEFAULT_DELIVERY_MODE;
	private Priority priority = DEFAULT_PRIORITY;
	private long timeToLive = Message.DEFAULT_TIME_TO_LIVE;
	
	//For message retrieval
	private long receiveTimeout = DEFAULT_RECEIVE_TIMEOUT;
	private MessageListener messageListener;
	
	//AutoCommit
	private boolean autoCommit = false;
	
	//Authentication
	private Authentication authentication;
	
	/**
	 * @return the sessionTransacted
	 */
	public boolean isSessionTransacted() {
		return sessionTransacted;
	}

	/**
	 * @param sessionTransacted the sessionTransacted to set
	 */
	public void setSessionTransacted(boolean sessionTransacted) {
		this.sessionTransacted = sessionTransacted;
	}

	/**
	 * @return the acknowledgeMode
	 */
	public AcknowledgeMode getAcknowledgeMode() {
		return acknowledgeMode;
	}

	/**
	 * @param acknowledgeMode the acknowledgeMode to set
	 */
	public void setAcknowledgeMode(AcknowledgeMode acknowledgeMode) {
		this.acknowledgeMode = acknowledgeMode;
	}

	/**
	 * @return the exceptionListener
	 */
	public ExceptionListener getExceptionListener() {
		return exceptionListener;
	}

	/**
	 * @param exceptionListener the exceptionListener to set
	 */
	public void setExceptionListener(ExceptionListener exceptionListener) {
		this.exceptionListener = exceptionListener;
	}

	/**
	 * @return the explicitQoSEnabled
	 */
	public boolean isExplicitQoSEnabled() {
		return explicitQoSEnabled;
	}

	/**
	 * @param explicitQoSEnabled the explicitQoSEnabled to set
	 */
	public void setExplicitQoSEnabled(boolean explicitQoSEnabled) {
		this.explicitQoSEnabled = explicitQoSEnabled;
	}

	/**
	 * @return the deliveryMode
	 */
	public DeliveryMode getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * @param deliveryMode the deliveryMode to set
	 */
	public void setDeliveryMode(DeliveryMode deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * @return the timeToLive
	 */
	public long getTimeToLive() {
		return timeToLive;
	}

	/**
	 * @param timeToLive the timeToLive to set
	 */
	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	/**
	 * @return the receiveTimeout
	 */
	public long getReceiveTimeout() {
		return receiveTimeout;
	}

	/**
	 * @param receiveTimeout the receiveTimeout to set
	 */
	public void setReceiveTimeout(long receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
	}

	/**
	 * @return the messageListener
	 */
	public MessageListener getMessageListener() {
		return messageListener;
	}

	/**
	 * @param messageListener the messageListener to set
	 */
	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	/**
	 * @return the autoCommit
	 */
	public boolean isAutoCommit() {
		return autoCommit;
	}

	/**
	 * @param autoCommit the autoCommit to set
	 */
	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}

	/**
	 * @param authentication the authentication to set
	 */
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	
	/**
	 * @return the authentication
	 */
	public Authentication getAuthentication() {
		return authentication;
	}
}
