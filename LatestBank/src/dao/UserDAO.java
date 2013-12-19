package dao;

import java.util.ArrayList;



import org.hibernate.Query;
import org.hibernate.Session;

import model.LoginException;
import model.MySessionFactory;
import model.User;

public class UserDAO implements IUserDAO {
	Session session;
	private User user;
	@Override
	public User load(String userID) {
		session=MySessionFactory.getInstance();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("from User u where u.userID = ?");
		query.setString(0, userID);
		ArrayList<User> userlist=(ArrayList<User>)query.list();
		if(userlist.isEmpty()==false)
			{
			user=userlist.get(0);
			return user;}
		return null;
	}

	@Override
	public void addUser() {
		// TODO Auto-generated method stub

	}
	public boolean authenticateUser(String userID, String password) throws LoginException {
		user=load(userID);
		//No Such UserID in the database. Phew!
		if(user!=null) 
			return false;
		else  //userId found in database
		{	
			if(load(userID).getRetriesLeft()<=0)
				throw new LoginException(load(userID).getRetriesLeft());
			//Check if password matches in DB 
			if(load(userID).getPassword().equals(password))
				{//and user has retries left
				if(load(userID).getRetriesLeft()>0)
					return true;
				else 
					return false;
				}
			else //userID is correct but password is wrong=>Decrease retries left.
				{
				load(userID).setRetriesLeft((load(userID).getRetriesLeft()-1));
				session.beginTransaction();
				session.persist(load(userID));
				session.getTransaction().commit();
				}
				
		}
				
		return false;
	}

	@Override
	public boolean authenticateUser(String password) throws LoginException {
		if(user==null) 
			return false;
		else  //userId found in database
		{	
			if(user.getRetriesLeft()<=0)
				throw new LoginException(user.getRetriesLeft());
			//Check if password matches in DB 
			if(user.getPassword().equals(password))
				{//and user has retries left
				if(user.getRetriesLeft()>0)
					return true;
				else 
					return false;
				}
			else //userID is correct but password is wrong=>Decrease retries left.
				{
				user.setRetriesLeft((user.getRetriesLeft()-1));
				session.beginTransaction();
				session.persist(user);
				session.getTransaction().commit();
				}
				
		}
		return false;
	}
	/*Below method would logout the user and close the session*/
	@Override
	public void logout() {
		// TODO Auto-generated method stub
		if(session==null)
			return;
		else
			{
			session.close();
			System.out.println("User has susccessfully logged out");
			}
	}
	@Override
	public User getUser() {
		return user;
	}
	public String toString()
	{
		return user.toString();
	}

}
