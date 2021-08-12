package com.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Model;

@WebServlet("/Register")
public class Register extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String cPassword = req.getParameter("cPassword");
		String accountID = req.getParameter("accountID");
		
		if(password.equals(cPassword))
		{
			Model m = new Model();
			m.setName(name);
			m.setEmail(email);
			m.setUsername(username);
			m.setPassword(password);
			m.setAccountID(accountID);
			
			boolean success = m.register();
			if(success==true)
			{
				String sender = "";
				String pw = "";
				String subject = "Welcome to ABC Bank!";
				String msg = "Hi "+name+"! \n Welcome to ABC Bank! \n Your account details are as follows: \n username: "+username+"\n Account ID: "+accountID;
				
				Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", 587);
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				
				Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
				{
					protected PasswordAuthentication getPasswordAuthentication()
					{
						//sender's mail id and pwd is encapsulated inside "SendersCredentials.java"
						return new PasswordAuthentication(sender, pw);
					}
				});

				try 
				{
					//Composing the Mail
					MimeMessage mesg = new MimeMessage(session);
					mesg.setFrom(new InternetAddress(sender));
					mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(email));
					mesg.setSubject(subject);  
					mesg.setText(msg);  
					
					//Sending the Mail
					Transport.send(mesg);
					System.out.println("Mail Sent!!");
					resp.sendRedirect("/BankingApp/SuccessfulRegistration.html");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			resp.sendRedirect("/BankingApp/RegistrationFailed.html");
		}
	}
}
