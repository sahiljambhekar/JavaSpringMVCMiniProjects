package model;

public enum AccountStatus
{
	OPEN,CLOSED,LOCKED,SUSPENDED;
	@Override
	public String toString()
	{
		switch(this)
		{
		case CLOSED: return "CLOSED";
		case OPEN  : return "OPEN";
		case LOCKED: return "LOCKED";
		case SUSPENDED : return "SUSPENDED";
		}
		return "OPENED";
	}
}