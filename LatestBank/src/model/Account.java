package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Accounts")
public class Account implements Serializable {

private static final long serialVersionUID = 1L;
public transient static final float defaultAccountBalance=0;

/*Below 4 fields are stored in DB*/

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="Account_Number")
private long accountNumber;

private float balance;
private AccountStatus accountStatus; //default as OPEN
private String transactionPassword;

@OneToMany(cascade=CascadeType.ALL)
@JoinColumn(name = "Account_Number")
private Set<Cheque> chequeBook=new HashSet<Cheque>();

@OneToMany(cascade=CascadeType.ALL)
@JoinColumn(name = "Account_Number")
private Set<Transaction> accountTrasactions=new HashSet<Transaction>();
public Account()
{
	balance=defaultAccountBalance;
	accountStatus=AccountStatus.OPEN;
	transactionPassword="xyzzy";
	
}

public Set<Transaction> getAccountTrasactions() {
	return accountTrasactions;
}

public void setAccountTrasactions(Set<Transaction> accountTrasactions) {
	this.accountTrasactions = accountTrasactions;
}

public void issueCheckBook() {
	/*create 10 cheques in a cheque book*/
	for(int i=0;i<10;i++)
	{	
		Cheque temp=new Cheque();
		chequeBook.add(temp);
	}
}
public Account(float balance,String tranactionPassword) {
	accountStatus=AccountStatus.OPEN;
	this.transactionPassword=tranactionPassword;
	this.balance=balance;
}
/*Below Method Clears an outbound check from one account*/
public boolean clearance(Cheque outboundCheque,Account receiver)
{	
	if(getBalance()<outboundCheque.getAmount())
	{	outboundCheque.setStatus("BOUNCED");
		throw new RuntimeException("Insufficient Funds Exception");
	}
	else
		{
		setBalance(getBalance()-outboundCheque.getAmount());
		receiver.credit(outboundCheque.getAmount(),"Cheque Deposit from :"+accountNumber);
		outboundCheque.setStatus("CLEARED");
		return true;
		}
}
public HashSet<Transaction> getTransactions(Calendar start,Calendar end)
{	Calendar transactionTimeStamp;
	/*System.err.println("Start= "+start);
	System.err.println("End= "+end);*/
	if(start.after(end)) //user has entered dates wrongly.
	{	System.err.println("Date wrong!");
		return null;
	}
	else
	{	HashSet<Transaction> requestedTransactions=new HashSet<Transaction>();
		for(Transaction temp:accountTrasactions)
		{	
			transactionTimeStamp=temp.getTimestamp();
			/*System.out.println(transactionTimeStamp);*/
			if(transactionTimeStamp.after(start)&&transactionTimeStamp.before(end))
				requestedTransactions.add(temp);
							
		}
		return requestedTransactions;
	}
	
}
public float getBalance()
{
	return balance;
}

/*All credits to the said account have to be synchronized*/
/*A Credit transaction to an account will increase it's balance*/
public boolean credit(float creditAmount,String description)
{	
	if(accountStatus==AccountStatus.OPEN||accountStatus==AccountStatus.LOCKED)
	{
	setBalance(balance+creditAmount);
	accountTrasactions.add(new Transaction(description, creditAmount, TransactionType.CREDIT));
	//System.out.println("Account Credited");
	return true;
	}
	else
	{
	return false;	
	}

	
}
/*Debit transaction to an account will decrease it's balance*/
public synchronized boolean debit(float debitAmount,String description)
{	if(accountStatus.equals(AccountStatus.OPEN)&&getBalance()>=debitAmount)
		{
		setBalance(balance-debitAmount);
		accountTrasactions.add(new Transaction(description, debitAmount, TransactionType.DEBIT));
		//System.out.println("Account Debited");
		return true;
		}
	else
		return false;
} 

public void setBalance(float updatedBalance) {
	
	this.balance=updatedBalance; 

}


public void setTransactionPassword(String transactionPassword) {
	this.transactionPassword = transactionPassword;
}
public boolean fundsTransfer(Account receiver,float transferAmount,String txpassword)
{	
	System.out.println("account status is "+this.getAccountStatus().toString());
	/*
	Funds can only be transferred if the account is open
	and only when the transacation password entered by the user
	equals the t/x password that is valid for the account.
	*/
	System.out.println("T/x password was correct entered: "+transactionPassword.equals(txpassword));
	if(!accountStatus.toString().equals("OPEN")||!(transactionPassword.equals(txpassword)))
		return false;
	
	//check if sufficient funds in this account
	if(getBalance()>=transferAmount)
	   	{	
		System.out.println("Transaction Authenticated!");
		debit(transferAmount, "Transferred to A/c No:"+receiver.getAccountNumber());
		receiver.credit(transferAmount, "Transferred from A/c No:"+accountNumber);
		return true;
		
	   	}
	return false;
}
/*/*The below Functions gives a result of the latest N (n) transactions
 * that happend in an Account.
 **/
public List<Transaction> getNTransactions(int n)
{
	TreeSet<Transaction> nTransactions=new TreeSet<Transaction>();
	ArrayList<Transaction> result=new ArrayList<Transaction>();
	int maxTransactions=accountTrasactions.size();
	//System.out.println(accountTrasactions);
	//System.out.println("No of transactions in HashsEt "+maxTransactions);
	if(maxTransactions==0)
		return null;
	int noOfTransactions= n<=maxTransactions?n:maxTransactions;
	
	/*To have all the queries sorted in reverse
	 * chronological way.
	 */
	
	nTransactions.addAll(accountTrasactions);
	//System.out.println("N Transaction size : "+nTransactions.size());
	Iterator<Transaction> allTransIterator=nTransactions.iterator();
	
	/*loop would add last the required transactions*/
	
	for(int i=0;i<=noOfTransactions&&allTransIterator.hasNext();)
	{
		result.add(allTransIterator.next());
		i++;
	}
	return result;
}//function ends
/*0 is January in Java but stored as 1 in underlying DB*/
public ArrayList<Transaction> getMonthlyStatement(int month)
{	Transaction temp;
	ArrayList<Transaction> result=new ArrayList<Transaction>();
	Iterator<Transaction> transacationIterator=accountTrasactions.iterator();
	while(transacationIterator.hasNext())
	{
		temp=transacationIterator.next();
		if(temp.getTimestamp().get(Calendar.MONTH)==month)
			result.add(temp);
	}
	return result;
}

public ArrayList<Transaction> getYearlyStatement(int year)
{	Transaction temp;
	ArrayList<Transaction> result=new ArrayList<Transaction>();
	Iterator<Transaction> transacationIterator=accountTrasactions.iterator();
	while(transacationIterator.hasNext())
	{
		temp=transacationIterator.next();
		if(temp.getTimestamp().get(Calendar.YEAR)==year)
			result.add(temp);
	}
	return result;
}	
		
	


@Override
public String toString()
{
	return ("Account No:"+accountNumber+"\tBalance:Rs."+getBalance());
}

public void setAccountStatus(AccountStatus accountStatus) {
	this.accountStatus = accountStatus;
}

public long getAccountNumber() {
	return accountNumber;
}

public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
}

public AccountStatus getAccountStatus() {
	return accountStatus;
}

public String getTransactionPassword() {
	return transactionPassword;
}

public Set<Cheque> getChequeBook() {
	return chequeBook;
}

public void setChequeBook(HashSet<Cheque> chequeBook) {
	this.chequeBook = chequeBook;
}

}


