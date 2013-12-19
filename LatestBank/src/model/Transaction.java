package model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction implements Serializable,Comparable<Object>{
private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private long trasactionID;
private String description;
private float amount;
@Column(name="transactionTime")
private Calendar timestamp;
private TransactionType transactionType;
{	
	timestamp=Calendar.getInstance();
}
Transaction(){
	
}


public Transaction(String description, float amount,
		TransactionType transactionType) {
	this.description = description;
	this.amount = amount;
	this.transactionType = transactionType;
}


public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
	this.amount = amount;
}
public Calendar getTimestamp() {
	return timestamp;
}
public void setTimestamp(Calendar timestamp) {
	this.timestamp = timestamp;
}
public TransactionType getTransactionType() {
	return transactionType;
}
public void setTransactionType(TransactionType transactionType) {
	this.transactionType = transactionType;
}
public long getTrasactionID() {
	return trasactionID;
}
public void setTrasactionID(long trasactionID) {
	this.trasactionID = trasactionID;
}
@Override
public String toString()
{
	return "Transaction Id:"+trasactionID+" Desc:"+description+" Amount Rs."+amount;
}

@Override
public int compareTo(Object t) {
	if(t instanceof Transaction)
	{	Transaction temp=(Transaction)t;
		if(temp.getTimestamp().after(this.getTimestamp()))
			return -1;
		else if(temp.getTimestamp().before(this.getTimestamp()))
			return 1;
		else if(temp.getTimestamp().equals(this.getTimestamp()))
			return -1;
			
	}
	return 0;
}




}
