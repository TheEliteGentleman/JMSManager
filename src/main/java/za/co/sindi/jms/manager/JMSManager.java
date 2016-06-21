/**
 * 
 */
package za.co.sindi.jms.manager;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import za.co.sindi.jms.authentication.Authentication;
import za.co.sindi.jms.enums.AcknowledgeMode;
import za.co.sindi.jms.enums.DeliveryMode;
import za.co.sindi.jms.enums.Priority;
import za.co.sindi.jms.message.JMSMessage;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public interface JMSManager {
	
	//Receive Timeout
	public static final long DEFAULT_RECEIVE_TIMEOUT = 0;
	public static final long RECEIVE_TIMEOUT_NOWAIT = -1;
	
	//MessageProducer Priority
	public static final Priority LOWEST_PRIORITY = Priority.ZERO;
	public static final Priority HIGHEST_PRIORITY = Priority.NINE;
	public static final Priority DEFAULT_PRIORITY = Priority.valueOf(Message.DEFAULT_PRIORITY);
	
	//DeliveryMode
	public static final DeliveryMode DEFAULT_DELIVERY_MODE = DeliveryMode.valueOf(Message.DEFAULT_DELIVERY_MODE);
	
	//Acknowledge mode
	public static final AcknowledgeMode DEFAULT_ACKNOWLEDGE_MODE = AcknowledgeMode.AUTO_ACKNOWLEDGE;

	public boolean isSessionTransacted();
	public void setSessionTransacted(boolean sessionTransacted);
	public AcknowledgeMode getAcknowledgeMode();
	public void setAcknowledgeMode(AcknowledgeMode acknowledgeMode);
	public ExceptionListener getExceptionListener();
	public void setExceptionListener(ExceptionListener exceptionListener);
	public boolean isExplicitQoSEnabled();
	public void setExplicitQoSEnabled(boolean explicitQosEnabled);
	public DeliveryMode getDeliveryMode();
	public void setDeliveryMode(DeliveryMode deliveryMode);
	public Priority getPriority();
	public void setPriority(Priority priority);
	public long getTimeToLive();
	public void setTimeToLive(long timeToLive);
	public long getReceiveTimeout();
	public void setReceiveTimeout(long receiveTimeout);
	public MessageListener getMessageListener();
	public void setMessageListener(MessageListener messageListener);
	public boolean isAutoCommit();
	public void setAutoCommit(boolean autoCommit);
	public void setAuthentication(Authentication authentication);
	
	//JMS methods.
	public void commit() throws JMSException;
	public void initializeJMS() throws JMSException;
	public Message receive() throws JMSException;
	public void receiveAsync() throws JMSException;
	public void recover() throws JMSException;
	public void rollback() throws JMSException;
	public void send(JMSMessage... jmsMessages) throws JMSException;
	public void close() throws JMSException;
}
