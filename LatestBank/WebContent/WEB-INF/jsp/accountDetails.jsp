<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="model.Account"%>
<%@page import="model.Cheque"%>
<%@page import="dao.*"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Details Screen</title>
<%!long accountNo;
AccountDAO accdao;
Account temp;
long chequeNo;
String status;
String payee;
float amount;
Cheque tempCheque;%>
<script>
function validateForm()
{
var x=document.forms["ChequeHandler"].transactionPassword;
if(x==null||x=="")
	alert("Transaction password has to be set for stop payments")
else
	document.forms["ChequeHandler"].submit();
}
</script>
</head>
<body>
<%

//System.out.println("requested account number="+accountNo);
accdao=(AccountDAO)request.getAttribute("account");
Set<Cheque> allmyCheques=accdao.getChequeBook();
Iterator<Cheque> chequeIterator=allmyCheques.iterator();
session.setAttribute("accountNo", accountNo);
%>
<form name="ChequeHandler" action="/MyBankingApplication/accountHandler" method="post">
<table border=2>
<tr>
<th>Cheque Number</th>
<th>Status</th>
<th>Payee</th>
<th>Amount</th>
<th>Stop Payment?</th>
</tr>
<%
while(chequeIterator.hasNext())
{
	tempCheque=chequeIterator.next();
	chequeNo=tempCheque.getChequeNo();
	status=tempCheque.getStatus().toString();
	amount=tempCheque.getAmount();
	payee=tempCheque.getPayee();
%>
<tr>
<td><%=chequeNo %></td>
<td><%=status %></td>
<td><%=payee%></td>
<td><%=amount%></td>
<%
if(status.equals("UNISSUED")||status.equals("ISSUED"))
	{
	%>
	<td> <input type="checkbox" name="stopPaymentFlag" value=<%=chequeNo%>> </td>
	<%}	//if ends
	else{//No checkbox if condition fails
	%><td>N/A</td><%} %>
</tr>
<% }//while ends %>
 </table><br>
 
 Transaction Password:<input type=password name=transactionPassword />
 <input type=Submit value="STOP PAYMENT" onclick="validateForm()" >
</form>
</body>
</html>