/**
 * 
 */
package za.co.sindi.jms.authentication;

/**
 * @author Bienfait Sindi
 * @since 11 July 2012
 *
 */
public class UsernamePasswordAuthentication implements Authentication {

	private String userName;
	private String password;
	
	/**
	 * @param userName
	 * @param password
	 */
	public UsernamePasswordAuthentication(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.authentication.Authentication#getUserName()
	 */
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}

	/* (non-Javadoc)
	 * @see com.neurologic.jms.authentication.Authentication#getPassword()
	 */
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
}
