import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe 
{
	static int counter = 1;
	static boolean check = false;
	
	public static void main(String[] args)
	{
		char[][] grid = {{' ','|',' ','|',' '}, 
				{'-','+','-','+','-'}, 
				{' ','|',' ','|',' '}, 
				{'-','+','-','+','-'},
				{' ','|',' ','|',' '}};
		Scanner scan = new Scanner(System.in);
		
		displayGrid(grid);
		
		try
		{
			while(check==false && counter<=9)
			{
				//if counter is odd, it is player's turn. else, it is CPU turn.
				if(counter%2==1 && counter!=9)
				{
					System.out.println("Enter a number from 1-9: ");
					int userInput = scan.nextInt();
					counter = updateGrid(grid, userInput, counter);
					displayGrid(grid);
					check = checkWinner(grid);
				}
				else if(counter%2==0)
				{
					System.out.println("CPU turn: ");
					Random r = new Random();
					int cpuInput = r.nextInt(9)+1;
					counter = updateGrid(grid, cpuInput, counter);
					displayGrid(grid);
					check = checkWinner(grid);
				}
				else if(counter==9)
				{
					System.out.println("Enter a number from 1-9: ");
					int userInput = scan.nextInt();
					counter = updateGrid(grid, userInput, counter);
					displayGrid(grid);
					check = checkWinner(grid);
					if(check==false)
					{
						System.out.println("No moves left! It is a draw!");
						check = true;
					}
				}
			}
		}
		catch(InputMismatchException e)
		{
			System.out.println("Please enter an integer");
		}
		scan.close();
	}
	
	static void displayGrid(char[][] grid)
	{
		for(char[]x: grid)
		{
			for(char y: x)
			{
				System.out.print(y);
			}
			System.out.println();
		}
	}
	
	static int updateGrid(char[][] grid, int position, int turn)
	{
		char symbol = ' ';
		if(turn%2==1)
		{
			symbol = 'O';
		}
		else if(turn%2==0)
		{
			symbol = 'X';
		}
		
		switch(position)
		{
		case 1:
			if(grid[0][0]==' ')
			{
				grid[0][0] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 2:
			if(grid[0][2]==' ')
			{
				grid[0][2] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 3:
			if(grid[0][4]==' ')
			{
				grid[0][4] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 4:
			if(grid[2][0]==' ')
			{
				grid[2][0] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 5:
			if(grid[2][2]==' ')
			{
				grid[2][2] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 6:
			if(grid[2][4]==' ')
			{
				grid[2][4] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 7:
			if(grid[4][0]==' ')
			{
				grid[4][0] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 8:
			if(grid[4][2]==' ')
			{
				grid[4][2] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		case 9:
			if(grid[4][4]==' ')
			{
				grid[4][4] = symbol;
				++turn;
			}
			else
			{
				System.out.println("Position has been chosen already! Please choose another position");
			}
			break;
		default:
			System.out.println("Please enter a valid integer from 1-9");
		}
		return turn;
	}
	
	static boolean checkWinner(char[][] grid)
	{
		boolean winnerDetermined = false;
		
		//check horizontal lines
		if(grid[0][0]=='O' && grid[0][2]=='O' && grid[0][4]=='O')
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][0]=='X' && grid[0][2]=='X' && grid[0][4]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		else if(grid[2][0]=='O' && grid[2][2]=='O' && grid[2][4]=='O')
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[2][0]=='X' && grid[2][2]=='X' && grid[2][4]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		else if(grid[4][0]=='O' && grid[4][2]=='O' && grid[4][4]=='O')
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[4][0]=='X' && grid[4][2]=='X' && grid[4][4]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		//check vertical lines
		else if(grid[0][0]=='O' && grid[2][0]=='O' && grid[4][0]=='O')
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][0]=='X' && grid[2][0]=='X' && grid[4][0]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][2]=='O' && grid[2][2]=='O' && grid[4][2]=='O')
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][2]=='X' && grid[2][2]=='X' && grid[4][2]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][4]=='O' && grid[2][4]=='O' && grid[4][4]=='O')
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][4]=='X' && grid[2][4]=='X' && grid[4][4]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		//check diagonal lines
		else if(grid[0][0]=='O' && grid[2][2]=='O' && grid[4][4]=='O')
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][0]=='X' && grid[2][2]=='X' && grid[4][4]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		else if((grid[0][4]=='O' && grid[2][2]=='O' && grid[4][0]=='O'))
		{
			System.out.println("Player is the winner");
			winnerDetermined = true;
		}
		else if(grid[0][4]=='X' && grid[2][2]=='X' && grid[4][0]=='X')
		{
			System.out.println("CPU is the winner");
			winnerDetermined = true;
		}
		return winnerDetermined;
	}
}
