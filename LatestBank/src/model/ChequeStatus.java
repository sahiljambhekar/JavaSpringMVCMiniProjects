package model;

//class Cheque Ends
public enum ChequeStatus
{
	UNISSUED,ISSUED,STOPPAYMENT,BOUNCED,SUSPECT,CLEARED;
	@Override
	public String toString() {
		switch(this)
		{
		case BOUNCED : return "BOUNCED";
		case ISSUED : return "ISSUED";
		case UNISSUED : return "UNISSUED";
		case SUSPECT : return "SUSPECT";
		case STOPPAYMENT : return "STOPPAYMENT";
		case CLEARED : return "CLEARED";
		} 
		return "UNISSUED";
}}
