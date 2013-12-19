package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Individual")
public class Individual extends User {
	private static final long serialVersionUID = 1L;
	private String pancardNo;
	private String fName;
	private String lName;
	public Individual(){}
	public Individual(String userID)
	{
	super(userID);	
	}
	public Individual(String userID, String password, String address,
			String pancardNo,String fname,String lname) 
	{
		super(userID,password,address);
		this.pancardNo=pancardNo;
		this.fName=fname;
		this.lName=lname;
	}

	@Override
	public float getTotalBalance() {
		return 0;
	}


	@Override
	public boolean createAccount() {
		Account myAccount=new Account();
		userAccounts.add(myAccount);
		return true;
	}

	@Override
	public boolean createAccount(float balance,String txpass) {
		Account myAccount=new Account(balance,txpass);
		userAccounts.add(myAccount);
		return true;
	}

	@Override
	public boolean stopPaymentOfCheque(String chequeNo) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPancardNo() {
		return pancardNo;
	}

	public void setPancardNo(String pancardNo) {
		this.pancardNo = pancardNo;
	}
	public String toString()
	{
	return "Individual "+super.toString();	
	}

}
