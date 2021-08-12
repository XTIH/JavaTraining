<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Details</title>
<link href="Balance.css" rel="stylesheet">
</head>
<body>
	<header>
		<div>ABC Bank</div>
		<div id="nav">
			<a class="navBar" href="/BankingApp/UserHome.jsp">Home</a>
			<div class="divder"> | </div>
			<a class="navBar" href="/BankingApp/Profile.html">Profile</a>
			<div class="divder"> | </div>
			<a class="navBar" href="Logout">Log Out</a>
		</div>
	</header>
	<table>
		<tr>
			<th>Account Number</th>
			<th>Balance</th>
		</tr>
		<tr>
			<td><%out.println(session.getAttribute("accountID"));%></td>
			<td><%out.println(session.getAttribute("balance"));%></td>
		</tr>
	</table>
</body>
</html>