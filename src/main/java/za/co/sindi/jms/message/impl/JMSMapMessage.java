/**
 * 
 */
package za.co.sindi.jms.message.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;

import za.co.sindi.jms.message.AbstractJMSMessage;

/**
 * @author Bienfait Sindi
 * @since 10 March 2014
 *
 */
public class JMSMapMessage extends AbstractJMSMessage {
	
	private Map<String, Object> map = new LinkedHashMap<String, Object>();
	
	/**
	 * 
	 */
	public JMSMapMessage() {
		this(null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param map
	 */
	public JMSMapMessage(Map<String, Object> map) {
		super();
		if (map != null) {
			this.map.putAll(map);
		}
	}

	public void setBoolean(String key, boolean value) {
		map.put(key, value);
	}
	
	public void setByte(String key, byte value) {
		map.put(key, value);
	}
	
	public void setBytes(String key, byte[] value) {
		map.put(key, value);
	}
	
	public void setChar(String key, char value) {
		map.put(key, value);
	}
	
	public void setDouble(String key, double value) {
		map.put(key, value);
	}
	
	public void setFloat(String key, float value) {
		map.put(key, value);
	}
	
	public void setInt(String key, int value) {
		map.put(key, value);
	}
	
	public void setLong(String key, long value) {
		map.put(key, value);
	}
	
	public void setObject(String key, Object value) {
		map.put(key, value);
	}
	
	public void setString(String key, String value) {
		map.put(key, value);
	}

	/* (non-Javadoc)
	 * @see za.co.sindi.jms.message.AbstractJMSMessage#createMessageInternally(javax.jms.Session)
	 */
	@Override
	protected MapMessage createMessageInternally(Session session) throws JMSException {
		// TODO Auto-generated method stub
		MapMessage message = session.createMapMessage();
		if (message != null && !map.isEmpty()) {
			for (Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				
				if (value instanceof Boolean) {
					message.setBoolean(key, (Boolean) value);
				} else if (value instanceof Byte) {
					message.setByte(key, (Byte) value);
				} else if (value instanceof byte[]) {
					message.setBytes(key, (byte[]) value);
				} else if (value instanceof Character) {
					message.setChar(key, (Character) value);
				} else if (value instanceof Double) {
					message.setDouble(key, (Double) value);
				} else if (value instanceof Float) {
					message.setFloat(key, (Float) value);
				} else if (value instanceof Integer) {
					message.setInt(key, (Integer) value);
				} else if (value instanceof Long) {
					message.setLong(key, (Long) value);
				} else if (value instanceof String) {
					message.setString(key, (String) value);
				} else {
					message.setObject(key, value);
				}
			}
		}
		
		return message;
	}
}
