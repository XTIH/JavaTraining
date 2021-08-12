package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Model 
{
	Connection con;
	static PreparedStatement pstmt;
	ResultSet res;
	
	private String name;
	private String password;
	private String email;
	private String username;
	private String accountID;
	private double balance;
	private String newPassword;
	private String recipientAccountID;
	private double amount;

	public Model()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banking_app","root","Wasdijkl9");
		} 
		catch(ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getAccountID() 
	{
		return accountID;
	}
	
	public void setAccountID(String accountID) 
	{
		this.accountID = accountID;
	}
	
	public double getBalance() 
	{
		return balance;
	}
	
	public void setBalance(double balance) 
	{
		this.balance = balance;
	}
	
	public String getNewPassword() 
	{
		return newPassword;
	}

	public void setNewPassword(String newPassword) 
	{
		this.newPassword = newPassword;
	}

	
	public String getRecipientAccountID() 
	{
		return recipientAccountID;
	}

	public void setRecipientAccountID(String recipientAccountID) 
	{
		this.recipientAccountID = recipientAccountID;
	}
	
	public double getAmount() 
	{
		return amount;
	}

	public void setAmount(double amount) 
	{
		this.amount = amount;
	}
	
	public boolean register()
	{
		try
		{
			con.setAutoCommit(false);
			String sql3 = "INSERT INTO account VALUES(?,?)";
			pstmt = con.prepareStatement(sql3);
			pstmt.setString(1, accountID);
			pstmt.setDouble(2, 0);
			int check3 = pstmt.executeUpdate();
			
			String sql2 = "INSERT INTO details VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, username);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, accountID);
			int check2 = pstmt.executeUpdate();
			
			String sql = "INSERT INTO authentication VALUES(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			int check1 = pstmt.executeUpdate();
			
			if(check1==1 && check2==1 && check3==1)
			{
				con.commit();
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean login()
	{
		try
		{
			String sql = "SELECT * FROM authentication WHERE username=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			res = pstmt.executeQuery();
			
			if(res.next()==true)
			{
				String sql2 = "SELECT name, accountID FROM details WHERE username=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1,username);
				
				res = pstmt.executeQuery();
				if(res.next()==true)
				{
					name = res.getString("name");
					accountID = res.getString("accountID");
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean changePassword()
	{
		try
		{
			String sql = "SELECT * FROM authentication WHERE username=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			res = pstmt.executeQuery();
			
			if(res.next())
			{
				String sql2 = "UPDATE authentication SET password=? WHERE username=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, newPassword);
				pstmt.setString(2, username);
				
				int numOfRowsUpdated = pstmt.executeUpdate();
				if(numOfRowsUpdated>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkFunds()
	{
		try
		{
			String sql = "SELECT balance FROM account WHERE accountID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountID);
			res = pstmt.executeQuery();
			
			if(res.next())
			{
				balance = res.getDouble("balance");
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean addFunds()
	{
		try
		{
			// query to retrieve user account balance
			String sql = "SELECT balance FROM account WHERE accountID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountID);
			res = pstmt.executeQuery();
			
			if(res.next()==true)
			{
				// retrieving the balance from the result set
				balance = res.getDouble("balance");
				balance+=amount;
				
				// query to update user account balance
				String sql2 = "UPDATE account SET balance=? WHERE accountID=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setDouble(1, balance);
				pstmt.setString(2, accountID);
				int numOfRowsUpdated = pstmt.executeUpdate();
				
				if(numOfRowsUpdated>0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean transferFunds()
	{
		try
		{
			con.setAutoCommit(false);
			// query to retrieve balance of user account
			String sql = "SELECT balance FROM account WHERE accountID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountID);
			res = pstmt.executeQuery();
			
			if(res.next())
			{
				balance = res.getDouble("balance");
				if(balance>amount && !accountID.equals(recipientAccountID))
				{
					pstmt.setString(1, recipientAccountID);
					res = pstmt.executeQuery();
					if(res.next())
					{
						double recipientBalance = res.getDouble("balance");
						
						//Deduct money from user account
						String sql3 = "UPDATE account SET balance=? WHERE accountID=?";
						pstmt = con.prepareStatement(sql3);
						balance-= amount;
						pstmt.setDouble(1, balance);
						pstmt.setString(2, accountID);
						pstmt.addBatch();
						
						//Update recipient account
						recipientBalance+= amount;
						pstmt.setDouble(1, recipientBalance);
						pstmt.setString(2, recipientAccountID);
						pstmt.addBatch();
						
						int[] count = pstmt.executeBatch();
						if(count.length>0)
						{
							con.commit();
							return true;
						}
						else
						{
							return false;
						}
					}
				}
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public void retrieveEmail()
	{
		try 
		{
			String sql = "SELECT email FROM details WHERE accountID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, accountID);
			res = pstmt.executeQuery();
			
			if(res.next())
			{
				email = res.getString("email");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}