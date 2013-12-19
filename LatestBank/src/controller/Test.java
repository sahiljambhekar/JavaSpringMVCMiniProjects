package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.LoginException;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import dao.AccountDAO;
import dao.UserDAO;

@Controller
public class Test {

private String message;
protected Log logger = LogFactory.getLog(getClass());

@RequestMapping(value="/authenticate",method=RequestMethod.POST)
public ModelAndView handleRequest
(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

String username=request.getParameter("username");
String password=request.getParameter("password");
//System.out.println(username+"\t"+password);

UserDAO udao=new UserDAO();
User user=udao.load(username);
System.out.println(user);
try{
if(user==null)
{	message="Invalid User ID";
	return new ModelAndView("loginError","message",message);
}

else if(udao.authenticateUser(password))
{	ModelAndView mav=new ModelAndView("home","user",udao);
	return mav ;
}}//try ends
catch(LoginException e) 
{
	ModelAndView mav=new ModelAndView("loginError","message",e.toString());
	return mav ;
}

return new ModelAndView("loginError","message","Unhandled error. Pls try later");
}
@RequestMapping(value="/fetchAccountDetails",method=RequestMethod.POST)
public ModelAndView fetchAccountDetails(HttpServletRequest request, HttpServletResponse response)
{	long accountNo=Long.parseLong(request.getParameter("hiddenAccount"));
	AccountDAO accdao=new AccountDAO();
	accdao.load(accountNo);
	return new ModelAndView("accountDetails","account",accdao);
}

public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

}
