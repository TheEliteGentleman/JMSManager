/**
 * 
 */
package za.co.sindi.jms.enums;

/**
 * JMS Delivery mode.
 * 
 * <p />There are only 2 types of delivery mode:
 * <ul>
 * 		<li>NON_PERSISTENT (constant <em>1</em>)</li>, and
 * 		<li>PERSISTENT (constant <em>2</em>)</li>.
 * </ul>
 * 
 * <p/>For more info, visit the JMS {@link javax.jms.DeliveryMode} javadoc.
 * 
 * @author Buhake Sindi
 * @since 10 July 2012
 *
 */
public enum DeliveryMode {
	NON_PERSISTENT(javax.jms.DeliveryMode.NON_PERSISTENT)
	,PERSISTENT(javax.jms.DeliveryMode.PERSISTENT)
	;
	private final int deliveryMode;

	/**
	 * @param deliveryMode
	 */
	private DeliveryMode(final int deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	
	/**
	 * Returns a {@link DeliveryMode} based on its value representation.
	 * 
	 * @param value a JMS delivery mode.
	 * @return
	 */
	public static DeliveryMode valueOf(final int value) {
		if (value != 1 && value != 2) {
			throw new IllegalArgumentException("Invalid JMS Delivery code (" + value + "). Value must be 1 (NON_PERSISTENT) or 2 (PERISTENT).");
		}
		
		DeliveryMode deliveryMode = null;
		switch (value) {
			case javax.jms.DeliveryMode.NON_PERSISTENT: 
				deliveryMode = NON_PERSISTENT;
			break;
			case javax.jms.DeliveryMode.PERSISTENT:
				deliveryMode = PERSISTENT;
			break;

			default: break;
		}
		
		return deliveryMode;
	}

	/**
	 * @return the deliveryMode
	 */
	public final int getDeliveryMode() {
		return deliveryMode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = super.toString();
		switch (deliveryMode) {
			case javax.jms.DeliveryMode.NON_PERSISTENT: 
				s = "NON_PERSISTENT";
			break;
			case javax.jms.DeliveryMode.PERSISTENT:
				s = "PERSISTENT";
			break;
	
			default: break;
		}
		
		return s;
	}
}
