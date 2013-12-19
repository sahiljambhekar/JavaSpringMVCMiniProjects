package model;
/*A class that handsles all User-Level 
 * login exceptions.
 */
public class LoginException extends Exception {
private static final long serialVersionUID = 1L;
private String loginErrorMessage;
LoginException()
{}
public LoginException(String message)
{	super(message);
	loginErrorMessage=message;
}
public LoginException(int retriesLeft)
{
	if(retriesLeft==0)
		loginErrorMessage="Sorry But You have tried incorrect password " +
						   "3 times and hence account has been locked. Contact System Admin";
	else if(retriesLeft<=3)
		loginErrorMessage="Careful.....You have entered password wrong and you just have : " +
				   +(retriesLeft-1)+" attempts left after which your account will be locked";
	
}
public String getLoginErrorMessage() {
	return loginErrorMessage;
}
}