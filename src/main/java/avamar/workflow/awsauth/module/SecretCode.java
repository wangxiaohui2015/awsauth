package avamar.workflow.awsauth.module;

/**
 * Secret code
 * 
 * @author sunny
 *
 */
public class SecretCode {
	private String userName;
	private String secretKey;
	private int secretCode;
	private int second;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public int getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(int secretCode) {
		this.secretCode = secretCode;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
}
