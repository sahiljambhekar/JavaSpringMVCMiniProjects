
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.crypto.Data;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import dao.UserDAO;

import model.*;
//Only for testing 

public class MAIN {
	public static void main(String[] args) throws InterruptedException {
		
/*		Session session=MySessionFactory.getInstance();
		session.beginTransaction();
		User test=(Individual)session.load(Individual.class,new Long(1));
		Account acc1=test.getUserAccounts().get(0);
		Account acc2=test.getUserAccounts().get(1);
		System.out.println(acc1.getYearlyStatement(2013));
		*/
		Session session=MySessionFactory.getInstance();
		
		session.beginTransaction();
		UserDAO udao=new UserDAO();
		User temp=udao.load("h.ried");
		System.out.println(temp.getAddress());
		//temp.getUserAccounts().get(0).issueCheckBook();
		//session.persist(temp);
		session.getTransaction().commit();
		
		//System.out.println(acc1.getMonthlyStatement(9).size());
		//System.out.println(acc1.getNTransactions(10));
		//acc1.fundsTransfer(acc2,300,"xyzzy");
		//session.persist(test);
		//session.getTransaction().commit();		
		
		
		//session.persist(reid);
		//session.persist(test);
		
		/*
		 * 
		 Individual individual1=new Individual("test", "pass", "Jhumri", "AOLPJ", "test", "user");
		Individual individual2=new Individual("h.ried", "pass", "Jhumri", "AOLPJ", "harry", "reid");
		
		individual1.createAccount();
		individual2.createAccount();
		
		Account a1=individual1.getUserAccounts().get(0);
		Account a2=individual2.getUserAccounts().get(0);
		
		session.persist(a1);
		session.persist(a2);
		a1.setBalance(10000);
		a1.fundsTransfer(a2, 500);
		session.persist(individual1);
		session.persist(individual2);	
		*/
		
		//System.out.println(User.authenticateUser("test","pass1"));
		
		
		/*System.out.println(reid);
		System.out.println(test);*/
	/*	Calendar start=Calendar.getInstance();
		start.set(2013,8,9);
		Calendar end=Calendar.getInstance();
		end.set(2013,10,13);
		
		
		session.beginTransaction();
		User reid=User.getUser("h.ried");
		System.out.println(reid.getUserAccounts().get(0).getTransactions(start, end));
		session.getTransaction().commit();
		
		

		//DataAccessObject.getUserAccounts("h.reid");
		//User u=new Individual("h.reid");
		//u.getUserAccounts();
		/* Account sender=new Account(16000.00f);
		 System.out.println(sender.getaccountNumber());*/
		//Account receiver=new Account(100);
		//Account.Cheque myCheque=sender.new Cheque(50000);
		//sender.clearance(myCheque, receiver);			
		
		//sender.setTransactionPassword("bhurr");
		//sender.fundsTransfer(receiver,3000 );*/
		//System.out.println(DataAccessObject.authenticateUser("Tom","Tom"));
		//long start=System.currentTimeMillis();
		//DataAccessObject.createIndividual(new Individual("Harry Reaid","myPass",null,"AOLPJ88D", "Harry", "Reid"));
		//long end=System.currentTimeMillis();
		//System.out.println("it took "+(end-start));
		
		//System.out.println(acc.getBalance());
	}
}


