package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.Model;

@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String password = req.getParameter("password");
		String newPassword = req.getParameter("newPassword");
		String cfmPassword = req.getParameter("cfmPassword");
		
		if(newPassword!=null && !newPassword.isEmpty() && newPassword.equals(cfmPassword)) 
		{
			Model m = new Model();
			HttpSession session = req.getSession();
			String username = (String) session.getAttribute("username");
			m.setUsername(username);
			m.setPassword(password);
			m.setNewPassword(newPassword);
			
			boolean pwChanged = m.changePassword();
			if(pwChanged)
			{
				resp.sendRedirect("SuccessfulPasswordChange.html");
			}
			else
			{
				resp.sendRedirect("FailedPasswordChange.html");
			}
		}
	}
}
