package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

@WebServlet("/Check")
public class Check extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Model m = new Model();
		HttpSession session = req.getSession();
		String accountID = (String) session.getAttribute("accountID");
		m.setAccountID(accountID);
		boolean retrieval = m.checkFunds();
		
		if(retrieval==true)
		{
			double balance = m.getBalance();
			session.setAttribute("balance", balance);
			resp.sendRedirect("/BankingApp/Balance.jsp");
		}
		else
		{
			resp.sendRedirect("/BankingApp/RetrievalFailed.html");
		}
	}
}
