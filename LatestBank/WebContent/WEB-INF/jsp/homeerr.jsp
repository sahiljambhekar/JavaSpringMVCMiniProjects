<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%!
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
<%
String userID=(String)request.getAttribute("UserID");
if(userID==null) 
	response.sendRedirect("login.html");
else
	{
	Individual s = new Individual(userID);
	userAccounts=s.getUserAccounts();
	accountIterator=userAccounts.iterator();
	
	}
%>
<h6>Welcome to Internet Banking !!<%=userID %></h6>
<br><h3 align="center">Your Account Details :</h3><br>
<form name="CheckAccountDetails" onsubmit=setAccount() method="post" action="/MyBankingApplication/accountDetails.jsp"> 
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