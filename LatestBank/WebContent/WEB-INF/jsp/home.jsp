<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Account"%>
<%@page import="model.User"%>
<%@page import="dao.*"%>
<%@page import="model.Individual"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Of Apna Bank</title>
<%!
IUserDAO userdao;
Individual s;
String str; 	
List<Account> userAccounts;
Iterator<Account> accountIterator;
Account temp;
%>
<script type="text/javascript">
function setAccount()
{
	document.getElementById("hiddenAccount").value=document.getElementById("selectedAccountNo").value;
	return true;
}
</script>
</head>
<body>
<h3>Welcome to Internet Banking</h3>${user}
<%
userdao=(UserDAO)request.getAttribute("user");
if(userdao==null) 
	response.sendRedirect("login.html");
else
	{
	s = (Individual)userdao.getUser();
	userAccounts=s.getUserAccounts();
	accountIterator=userAccounts.iterator();
	
	}
%>

<br><h4 align="center">Your Account Details :</h4><br>
<form name="CheckAccountDetails" onsubmit=setAccount() method="post" action="fetchAccountDetails.do"> 
<table border="2">
<tr>
<th>Account Number</th>
<th>Balance(Rs.)</th>
<th>Status</th>
<th>Access To Account/s</th>
</tr>
<% 
 while(accountIterator.hasNext())
 {
	 temp=accountIterator.next();
	 long accountNO=temp.getAccountNumber();
 %>
	<tr>
		<td><%=accountNO%>
		<td><%=temp.getBalance() %></td>
		<td><%=temp.getAccountStatus() %></td>
		<td><input type="radio" checked="checked" id="selectedAccountNo" name="selectedAccountNo" value=<%=accountNO%> ></td>
	</tr>
<%}%>
</table>
<input type="submit"  value="Press To Retrieve Account Details">
<input type="hidden" id="hiddenAccount" name="hiddenAccount">
</form>
</body>
</html>