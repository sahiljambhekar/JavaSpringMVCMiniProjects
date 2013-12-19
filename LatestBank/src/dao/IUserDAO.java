package dao;

import model.LoginException;
import model.User;

public interface IUserDAO {
public User load(String userID);
public boolean authenticateUser(String userID, String password) throws LoginException;
public boolean authenticateUser(String password) throws LoginException;
public void addUser();
public void logout();
public User getUser();
}
