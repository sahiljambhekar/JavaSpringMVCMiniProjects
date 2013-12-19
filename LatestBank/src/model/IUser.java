package model;

public interface IUser {

public float getTotalBalance();
public void changeAddress(String newAddress);
public boolean createAccount();
public boolean createAccount(float initialBalance,String initialTransactionPassword);
public boolean stopPaymentOfCheque(String chequeNo);
 
}

