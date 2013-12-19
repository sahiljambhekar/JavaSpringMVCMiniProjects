package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cheque")
public class Cheque implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private long chequeNo;
	private Date dateOfIssue;
	private Date dateOfClearing;
	private String payee;
	private float amount;
	private ChequeStatus status;
	private Account account;
	public Cheque()
	{	
		
		setStatus("UNISSUED");
	}
	@SuppressWarnings("deprecation")
	public Cheque(float amount)
	{
		this();
		setStatus("ISSUED");
		this.amount=amount;
		setDateOfIssue(new Date(2013,10,3)); //some day.
		setDateOfClearing(new Date());
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getChequeNo()
	{
		return chequeNo;
	}
	public Cheque(long chequeNo,String status,float amount,String payee) 
		 {
		this.chequeNo = chequeNo;
		this.setPayee(payee);
		this.amount = amount;
		setStatus(status);
	}
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn (name="Account_Number") //, nullable = false)
	public Account getAccount() {
		return account;
	}
	

	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
		
	
	public void setStatus(String status) {
		switch(status)
		{
		//UNISSUED,ISSUED,STOPPAYMENT,BOUNCED,SUSPECT,CLEARED;
		case "UNISSUED":this.status=ChequeStatus.UNISSUED;
						break;
		case "ISSUED":this.status=ChequeStatus.ISSUED;
		break;
		case "StopPayment":this.status=ChequeStatus.STOPPAYMENT;
		break;
		case "BOUNCED":this.status=ChequeStatus.BOUNCED;
		break;
		case "SUSPECT":this.status=ChequeStatus.SUSPECT;
		break;
		case "CLEARED" : this.status=ChequeStatus.CLEARED;
		break;
		default : this.status=ChequeStatus.UNISSUED;
		}
		
	}
	
	public String getPayee() {
		return payee;
	}
	public void setPayee(String payee) {
		this.payee = payee;
	}
	public Date getDateOfIssue() {
		return dateOfIssue;
	}
	public void setDateOfIssue(Date dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}
	public Date getDateOfClearing() {
		return dateOfClearing;
	}
	public void setDateOfClearing(Date dateOfClearing) {
		this.dateOfClearing = dateOfClearing;
	}
	public ChequeStatus getStatus() {
		return status;
	}
	public void setStatus(ChequeStatus status) {
		this.status = status;
	}
	
	public void setChequeNo(long chequeNo) {
		this.chequeNo = chequeNo;
	}
	

	
}