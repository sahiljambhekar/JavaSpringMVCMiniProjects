import model.LoginException;
import model.User;
import dao.UserDAO;

public class BankFacade
	{
		private User user;
		private LoginException loginFailure;
		
		private transient UserDAO udao;
		
		BankFacade()
		{
			udao=new UserDAO();
		}
		
		public void loadUser(String userID,String password)
		{
			setUser(udao.load(userID));
			if(user!=null)
			{try
			{
				udao.authenticateUser(userID, password);
			}
			catch(LoginException e)
			{
			loginFailure=e;
			loginFailure.getMessage();
			}}
		}

		public User getUser() {
			return user;
		}

		public void setUser(User u) {
			this.user = u;
		}
	}

