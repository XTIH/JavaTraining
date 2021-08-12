<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Homepage</title>
<link href="UserHomepage.css" rel="stylesheet">
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
	<div>
		<h1><%out.println("Welcome "+session.getAttribute("name"));%></h1>
	</div>
	<hr>
	<form action="Account.html">
		<div id="account">
			<button type="submit">Savings Account <%out.println(session.getAttribute("accountID"));%></button>
		</div>
	</form>
</body>
</html>