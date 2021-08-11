import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class BillingSystem 
{
	//menu list that contains all the food objects
	private static List<Food> menu = new ArrayList<Food>();
	//contains the ID of the food item ordered and the quantity ordered
	private static Map<Integer, Integer> orderList = new TreeMap<Integer, Integer>();
	//contains the ID of the food item ordered and its corresponding subtotal
	private static Map<Integer, Double> subTotalList = new TreeMap<Integer, Double>();
	
	private static Scanner scan = new Scanner(System.in);
	
	private static String name;
	private static int tableNo;
	//total bill before taxes and tips
	private static double total;
	//total amount of tax paid
	private static double tax;
	//the total tip that will be paid by the customer
	private static double tip;
	//final bill, which includes tax and tips
	private static double grandTotal;
	
	static 
	{
		Food item1 = new Food(1, "Burger", 10.50);
		Food item2 = new Food(2, "Pasta", 7);
		Food item3 = new Food(3, "Coffee", 2.50);
		Food item4 = new Food(4, "Cake", 5);
		
		menu.add(item1);
		menu.add(item2);
		menu.add(item3);
		menu.add(item4);
	}
	
	public static void main(String[] args)
	{
		generalDetails();
		displayMenu();
		orderItems();
		promptUserForMoreItems();
		Calculation.calculateSubTotal(orderList, menu, subTotalList);
		total = Calculation.calculateTotal(subTotalList);
		tax = Calculation.calculateTax(total);
		//tip = Calculation.calculateTip(total, tax, scan);
		tip = Calculation.calculateTip(total, tax);
		grandTotal = Calculation.grandTotal(total, tax, tip);
		displayReceipt();
	}
	
	//collect the name of the waiter/waitress and table number
	private static void generalDetails()
	{
		System.out.println("Enter the name of waiter:");
		name = scan.nextLine();
		System.out.println("Enter table number:");
		boolean check = false;
		//while check is false, keep trying to get a valid table number
		while(check==false)
		{
			try
			{
				tableNo = Integer.parseInt(scan.nextLine());
				check = true;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid table number!");
			}	
		}
	}
	
	//display the menu
	private static void displayMenu()
	{
		System.out.println("________________________________");
		System.out.println("              Menu              ");
		System.out.println("________________________________");
		System.out.println("ID      Item Name          Price");
		System.out.println("________________________________");
		for(int i=0;i<menu.size();i++)
		{
			String menuItem = BillingSystemFormatter.menuFormatter(menu,i);
			System.out.println(menuItem);
		}
		System.out.println("________________________________");
		System.out.println("");
	}
	
	//get input (S/N of the menu item and quantity desired) and store it in the orderList map
	private static void orderItems()
	{
		System.out.println("Enter the S/N of the item:");
		boolean checkID = false;
		boolean checkQty = false;
		while(checkID==false)
		{
			try 
			{
				int id = Integer.parseInt(scan.nextLine());
				/*if the id is greater than 0, less than the menu size, i.e the ID is valid and the order list does not contain the id of
				item ordered, add the ID of the item ordered and the quantity in the order list*/
				if(id<=menu.size() && id>0 && !orderList.containsKey(id))
				{
					checkID = true;
					System.out.println("Enter the quantity desired:");
					while(checkQty==false)
					{
						try 
						{
							int quantity = Integer.parseInt(scan.nextLine());
							orderList.put(id, quantity);
							checkQty = true;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Please enter a valid quantity!");
						}
					}
				}
				/*if the id is greater than 0, less than the menu size, i.e the ID is valid and the order list contains the id of item 
				ordered, retrieve existing quantity, add it up together and store the ID and the updated quantity in the order list*/
				else if(id<=menu.size() && id>0 && orderList.containsKey(id))
				{
					checkID = true;
					int existingQty = orderList.get(id);
					System.out.println("Enter the quantity desired:");
					while(checkQty==false)
					{
						try
						{
							int quantity = Integer.parseInt(scan.nextLine());
							int newQty = existingQty + quantity;
							orderList.put(id, newQty);
							checkQty = true;
						}
						catch(NumberFormatException e)
						{
							System.out.println("Please enter a valid quantity!");
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				}
				else
				{
					System.out.println("Please enter a valid menu item!");
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid integer!");
				System.out.println("Enter the S/N of the item:");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	//check with the waiter/waitress if he/she wants to add more items
	private static void promptUserForMoreItems()
	{
		boolean check = false;
		System.out.println("Any more items to add? (Y/N)");
		while(check==false)
		{
			String userInput = scan.nextLine();
			if(userInput.equalsIgnoreCase("N"))
			{
				check = true;
			}
			else if(userInput.equalsIgnoreCase("Y"))
			{
				orderItems();
				System.out.println("Any more items to add? (Y/N)");
			}
			else
			{
				System.out.println("Please enter a valid input (Y/N)");
			}
		}
		
	}
	
	//displaying the final bill
	private static void displayReceipt()
	{
		System.out.println("__________________________________________________________");
		System.out.println("Receipt                                                   ");
		System.out.println("__________________________________________________________");
		System.out.println("Waiter/waitress: "+name);
		System.out.println("Table Number: "+tableNo);
		System.out.println("__________________________________________________________");
		System.out.println("ID    Item Name          Unit Price   Quantity   Sub-total");
		System.out.println("__________________________________________________________");
		BillingSystemFormatter.receiptFormatter(orderList,menu,subTotalList);
		System.out.println("__________________________________________________________");
		BillingSystemFormatter.billFormatter(total, tax, tip, grandTotal);
	}
}

