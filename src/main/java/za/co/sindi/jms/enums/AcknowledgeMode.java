/**
 * 
 */
package za.co.sindi.jms.enums;

import javax.jms.Session;

/**
 * The JMS Acknowledge mode.
 * 
 * <p >The known acknowledge mode JMS provides are:
 * 
 *  <ul>
 * 		<li>AUTO_ACKNOWLEDGE (constant <em>1</em>)</li>,
 * 		<li>CLIENT_ACKNOWLEDGE (constant <em>2</em>)</li>,
 * 		<li>DUPS_OK_ACKNOWLEDGE (constant <em>3</em>)</li>,
 * </ul>
 * 
 * <p/>For more info, visit the JMS {@link javax.jms.Session} javadoc.
 * 
 * @author Buhake Sindi
 * @since 10 July 2012
 *
 */
public enum AcknowledgeMode {
	AUTO_ACKNOWLEDGE(Session.AUTO_ACKNOWLEDGE),
	CLIENT_ACKNOWLEDGE(Session.CLIENT_ACKNOWLEDGE),
	DUPS_OK_ACKNOWLEDGE(Session.DUPS_OK_ACKNOWLEDGE)
	;
	private final int acknowledgeMode;

	/**
	 * @param session
	 */
	private AcknowledgeMode(final int acknowledgeMode) {
		this.acknowledgeMode = acknowledgeMode;
	}
	
	/**
	 * Returns a {@link AcknowledgeMode} based on its value representation.
	 * 
	 * @param value a JMS delivery mode.
	 * @return
	 */
	public static AcknowledgeMode valueOf(final int value) {
		if (value < 1 || value > 3) {
			throw new IllegalArgumentException("Invalid JMS acknowledge mode (" + value + ").");
		}
		
		AcknowledgeMode acknowledgeMode = null;
		switch (value) {
			case Session.AUTO_ACKNOWLEDGE: 
				acknowledgeMode = AUTO_ACKNOWLEDGE;
			break;
			
			case Session.CLIENT_ACKNOWLEDGE:
				acknowledgeMode = CLIENT_ACKNOWLEDGE;
			break;
			
			case Session.DUPS_OK_ACKNOWLEDGE: 
				acknowledgeMode = DUPS_OK_ACKNOWLEDGE;
			break;

			default: break;
		}
		
		return acknowledgeMode;
	}

	/**
	 * @return the acknowledgeMode
	 */
	public final int getAcknowledgeMode() {
		return acknowledgeMode;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = super.toString();
		switch (acknowledgeMode) {
			case Session.AUTO_ACKNOWLEDGE: 
				s = "AUTO_ACKNOWLEDGE";
			break;
			
			case Session.CLIENT_ACKNOWLEDGE:
				s = "CLIENT_ACKNOWLEDGE";
			break;
			
			case Session.DUPS_OK_ACKNOWLEDGE: 
				s = "DUPS_OK_ACKNOWLEDGE";
			break;
	
			default: break;
		}
		
		return s;
	}
}
