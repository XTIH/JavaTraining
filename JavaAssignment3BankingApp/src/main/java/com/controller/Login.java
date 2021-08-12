package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

@WebServlet("/Login")
public class Login extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Model m = new Model();
		m.setUsername(username);
		m.setPassword(password);
		
		boolean login = m.login();
		if(login==true)
		{
			String name = m.getName();
			String accountID = m.getAccountID();
			
			HttpSession session = req.getSession(true);
			session.setAttribute("username", username);
			session.setAttribute("name", name);
			session.setAttribute("accountID", accountID);
			resp.sendRedirect("/BankingApp/UserHome.jsp");
		}
		else
		{
			resp.sendRedirect("/BankingApp/FailedLogin.html");
		}
	}
}
