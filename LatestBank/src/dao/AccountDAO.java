package dao;

import java.util.ArrayList;
import java.util.Set;
import model.Account;
import model.Cheque;
import model.MySessionFactory;
import model.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;

public class AccountDAO {
Account account;
Session session;

public Set<Transaction> getAccountTrasactions() {
	return account.getAccountTrasactions();
}
public Account load(long accountNumber)
{
	session=MySessionFactory.getInstance();
	
	Query query=session.createQuery("from Account acc where acc.accountNumber = ?");
	query.setLong(0, accountNumber);
	@SuppressWarnings("unchecked")
	ArrayList<Account> accountlist=(ArrayList<Account>)query.list();
	if(accountlist.isEmpty()==false)
		{
		account=accountlist.get(0);
		return account;
		}
	return null;
}

/*The below method would return the monthly transactions*/
public ArrayList<Transaction> getMonthlyStatement(int month)
{	
	return account.getMonthlyStatement(month);
}
public Set<Cheque> getChequeBook() {
	return account.getChequeBook();
}
public void issueCheckBook() {
	session.beginTransaction();
	account.issueCheckBook();
	session.persist(account);
	session.getTransaction().commit();
}


}
