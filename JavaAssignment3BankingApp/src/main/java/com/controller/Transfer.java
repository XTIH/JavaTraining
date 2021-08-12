package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.servlet.http.HttpSession;

import com.model.Model;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet 
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String recipientAccID = req.getParameter("accountID");
		double amount = Double.parseDouble(req.getParameter("amount"));
		
		Model m = new Model();
		if(recipientAccID!=null && !recipientAccID.isEmpty() && amount>=0)
		{
			HttpSession sess = req.getSession();
			String accountID = (String) sess.getAttribute("accountID");
			m.setAccountID(accountID);
			m.setRecipientAccountID(recipientAccID);
			m.setAmount(amount);
			boolean successfulTransaction = m.transferFunds();
			
			if(successfulTransaction)
			{
				double balance = m.getBalance();
				m.retrieveEmail();
				String email = m.getEmail();
				
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date date = new Date();
				
				String sender = "";
				String pw = "";
				String subject = "Transaction Details";
				String msg = "$"+amount+" has been transferred to "+recipientAccID+" on the "+format.format(date)+". Your new balance is: "+balance;
				
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
					resp.sendRedirect("/BankingApp/SuccessfulTransaction.html");
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				resp.sendRedirect("/BankingApp/TransactionFailed.html");
			}
		}
	}
}
