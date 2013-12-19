package model;

public enum TransactionType
{
	DEBIT,CREDIT;
	@Override
	public String toString()
	{
		switch(this)
		{
		case CREDIT: return "CREDIT";
		case DEBIT: return "DEBIT";
		}
		return "FAILED";
	}
	
}