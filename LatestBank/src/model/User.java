package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class User implements IUser,Serializable {
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="uid")
private long uid;
private String userID;
private String password;
private String address;
private int retriesLeft;


@OneToMany(cascade=CascadeType.ALL)
protected List<Account> userAccounts=new ArrayList<Account>();

public String getPassword() {
	return password;
}
class LoginAttempt
{	private String attemptedUserId;
	private String attemptedPassword;
	
	public static final int maxLoginAttempts=3;
	
	LoginAttempt(String UID,String pass)
	{
		setAttemptedUserId(UID);
		setAttemptedPassword(pass);
	}

	public String getAttemptedUserId() {
		return attemptedUserId;
	}

	public void setAttemptedUserId(String attemptedUserId) {
		this.attemptedUserId = attemptedUserId;
	}

	public String getAttemptedPassword() {
		return attemptedPassword;
	}

	public void setAttemptedPassword(String attemptedPassword) {
		this.attemptedPassword = attemptedPassword;
	}

	public int getRetriesLeft() {
		return retriesLeft;
	}

	public void setRetriesLeft(int rL) {
		retriesLeft = rL;
	}
}
User()
{}
User(String userID,String password,String address)
{
	this.userID=userID;
	this.password=password;
	this.address=address;
}
User(String userID)
{
	this.userID=userID;
}
public String getUserID() {
	return userID;
}

public void setUserID(String userID) {
	this.userID = userID;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public void setPassword(String password) {
	this.password = password;
}



public List<Account> getUserAccounts()
{	
	return userAccounts;
}
@Override 
public void changeAddress(String newAddress)
{
	setAddress(newAddress);
}
public long getUID() {
	return uid;
}
public void setUID(long uID) {
	uid = uID;
}
public int getRetriesLeft() {
	return retriesLeft;
}
public void setRetriesLeft(int retriesLeft) {
	this.retriesLeft = retriesLeft;
}
public String toString()
{
	return "userID:\t"+userID;
}


}//class User Ends.
