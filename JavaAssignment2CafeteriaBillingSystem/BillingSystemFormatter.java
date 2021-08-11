import java.util.List;
import java.util.Map;

public class BillingSystemFormatter 
{
	//format the menu items
	static String menuFormatter(List<Food> menu, int i)
	{
		String temp="";
		//allocate 6 characters for int ID and justify it
		String tempID = String.format("%-8d", menu.get(i).getID());
		//allocate 19 characters for String menu items and justify it
		String tempName = String.format("%-19s", menu.get(i).getName());
		//round off double price to 2 decimal places
		String tempPrice = "$"+String.format("%.2f", menu.get(i).getPrice());
		temp = tempID+tempName+tempPrice;
		return temp;
	}
	
	//format the receipt 
	static void receiptFormatter(Map<Integer,Integer> orderList, List<Food> menu, Map<Integer,Double> subTotalList)	
	{
		//looping through the entire order list map and retrieving each key and value pair
		for(Map.Entry<Integer, Integer> entry : orderList.entrySet())
		{
			int tempID = entry.getKey();
			String tempName = menu.get(tempID-1).getName();
			int tempQty = entry.getValue();
			double tempUnitPrice = menu.get(tempID-1).getPrice();
			double tempSubTotal = subTotalList.get(tempID);
			
			//formatting the various variables for nicer presentation
			String formattedTempID = String.format("%-6d", tempID);
			String formattedTempName = String.format("%-19s", tempName);
			String formattedTempUnitPrice = "$"+String.format("%-12.2f", tempUnitPrice);
			String formattedTempQty = String.format("%-11d", tempQty);
			String formattedTempSubTotal = "$"+String.format("%.2f", tempSubTotal);
			
			String temp = formattedTempID+formattedTempName+formattedTempUnitPrice+formattedTempQty+formattedTempSubTotal;
			System.out.println(temp);
		}
	}
	
	//format the bill
	static void billFormatter(double total, double tax, double tip, double grandTotal)
	{
		System.out.println("Total (before additional charges): $"+ String.format("%.2f",total));
		System.out.println("Tax (6.75%): $"+String.format("%.2f", tax));
		System.out.println("Tip: $"+String.format("%.2f", tip));
		System.out.println("Grand total: $"+String.format("%.2f", grandTotal));
	}
}
