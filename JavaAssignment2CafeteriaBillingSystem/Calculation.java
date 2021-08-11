import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Calculation 
{
	//calculate the subtotal of each item (price*quantity) and store it in a TreeMap
	static void calculateSubTotal(Map<Integer,Integer> orderList, List<Food> menu, Map<Integer,Double> subTotalList)
	{
		for(Map.Entry<Integer, Integer> entry : orderList.entrySet())
		{
			try
			{
				int tempID = entry.getKey();
				double tempPrice = menu.get(tempID-1).getPrice();
				int quantity = entry.getValue();
				double subTotal = tempPrice*quantity;
				subTotalList.put(tempID, subTotal);
			}
			catch(IndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//calculating the total amount (adding up all the subtotal)
	static double calculateTotal(Map<Integer,Double> subTotalList)
	{
		double temp = 0;
		for(double subTotal: subTotalList.values())
		{
			temp = temp+subTotal;
		}
		return temp;
	}
	
	//calculating the tax (6.75% of total bill) and formatting it to 2 decimal places
	static double calculateTax(double total)
	{
		double tempTax = 0.0675*total;
		String formattedTaxString = String.format("%.2f", tempTax);
		double temp = Double.parseDouble(formattedTaxString);
		return temp;
	}
	
	//setting the tip amount
	//static double calculateTip(double total, double tax, Scanner scan)
	static double calculateTip(double total, double tax)
	{
		Scanner scan = new Scanner(System.in);
		double temp = 0;
		double priceAfterTax = total+tax;
		double minTip = 0.1*priceAfterTax;
		String formattedTip = String.format("%.2f", minTip);
			
		System.out.println("Does customer want to tip more than "+formattedTip+"? (Y/N)");
		boolean check = false;
		while(check==false)
		{
			String userInput = scan.nextLine();
			if(userInput.equalsIgnoreCase("N"))
			{
				try
				{
					temp = Double.parseDouble(formattedTip);
					check = true;
				}
				catch(NumberFormatException e)
				{
					System.out.println("Please enter a valid tip!");
				}
			}
			else if(userInput.equalsIgnoreCase("Y"))
			{
				System.out.println("Enter amount that customer wishes to tip:");
				double customerTip = Double.parseDouble(scan.nextLine());
					
				while(customerTip<minTip)
				{
					System.out.println("Please enter a tip amount that is greater than "+formattedTip);
					try
					{
						customerTip = Double.parseDouble(scan.nextLine());
					}
					catch(NumberFormatException e)
					{
						System.out.println("Please enter a valid tip!");
					}
					}
				temp = customerTip;
				check = true;
			}
			else
			{
				System.out.println("Please enter a valid input");
			}
		}
		return temp;
	}
		
	//calculating the final bill (add the total bill, tax and tip)
	static double grandTotal(double total, double tax, double tip)
	{
		double temp = total+tax+tip;
		return temp;
	}
}
