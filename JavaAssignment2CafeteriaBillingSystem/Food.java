
public class Food 
{
	private int id;
	private String name;
	private double price;
	
	public Food(int id, String name, double price)
	{
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public int getID()
	{
		return id;
	}
	
	public void setID(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		if(price>0)
		{
			this.price = price;
		}
		else 
		{
			System.out.println("Please enter a valid price!");
		}
	}
}
