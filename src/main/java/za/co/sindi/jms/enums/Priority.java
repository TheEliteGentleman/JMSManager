/**
 * 
 */
package za.co.sindi.jms.enums;

/**
 * The JMS Priority.
 * <p />The Java JMS API states that the priority <b>must</b> be between 0 (lowest) and 9 (highest).
 * 
 * @author Buhake Sindi
 * @since 05 July 2012
 *
 */
public enum Priority {
	/**
	 * Priority 0 (lowest).
	 */
	ZERO(0)
	
	/**
	 * Priority 1.
	 */
	,ONE(1)
	
	/**
	 * Priority 2.
	 */
	,TWO(2)
	
	/**
	 * Priority 3.
	 */
	,THREE(3)
	
	/**
	 * Priority 4.
	 */
	,FOUR(4)
	
	/**
	 * Priority 5.
	 */
	,FIVE(5)
	
	/**
	 * Priority 6.
	 */
	,SIX(6)
	
	/**
	 * Priority 7.
	 */
	,SEVEN(7)
	
	/**
	 * Priority 8.
	 */
	,EIGHT(8)
	
	/**
	 * Priority 9 (highest).
	 */
	,NINE(9)
	;
	private final int priority;

	/**
	 * @param priority
	 */
	private Priority(final int priority) {
		this.priority = priority;
	}
	
	/**
	 * Returns a {@link Priority} based on its integer value.
	 * 
	 * @param priority
	 * @return
	 * @throws IllegalArgumentException if priority is less than 0 or greater than 9.
	 */
	public static Priority valueOf(final int priority) {
		if (priority < 0 || priority > 9) {
			throw new IllegalArgumentException("Invalid JMS priority " + priority + ".");
		}
		
		Priority priorityEnum = null;
		switch (priority) {
			case 0:
				priorityEnum =  ZERO;
			break;
			
			case 1:
				priorityEnum =  ONE;
			break;
				
			case 2:
				priorityEnum =  TWO;
			break;
				
			case 3:
				priorityEnum =  THREE;
			break;
				
			case 4:
				priorityEnum =  FOUR;
			break;
			
			case 5:
				priorityEnum =  FIVE;
			break;
			
			case 6:
				priorityEnum =  SIX;
			break;
			
			case 7:
				priorityEnum =  SEVEN;
			break;
			
			case 8:
				priorityEnum =  EIGHT;
			break;
			
			case 9:
				priorityEnum =  NINE;
			break;
			
		default:
			break;
		}
		
		return priorityEnum;
	}

	/**
	 * @return the priority
	 */
	public final int getPriority() {
		return priority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(priority);
	}
}
