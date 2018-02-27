import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;




class tools{
	static String[] numb = {"   ", "0","1","2","3","4","5", "6","7"};
	static String line = "  +-----------------+";
	
	public static void properprint(String[] ar){ //this method prints the String arrays without brackets or commas
		String formattedString = Arrays.toString(ar)
	    .replace(",", "")  //remove the commas
	    .replace("[", "")  //remove the right bracket
	    .replace("]", "");  //remove the left bracket
		System.out.println(formattedString); //thanks to someone on StackOverflow
	}
	
	
	public static void printTable(String[][] table ) {
		
		properprint(numb);
		System.out.println(line);
		for(int i = 0; i<=7; i ++) {
		properprint(table[i]);			
		}
		System.out.println(line);
		properprint(numb);
		
	}
		
	
}


class userHandling{
	
	// checks if the input is not on the table
	public static boolean isTable(int x1, int y1) {
		
		return  x1<0 || x1>8 || y1<0 || y1>8;
	}
	
	// checks if the position is on the table and if the piece belongs to him
	public static boolean isPlayer(String a, int x1, int y1, String [][] checkers) {
		
		return ( isTable(x1,y1) || checkers[y1][x1+1] != a );
		
	}
	
	//checks if there are not any possible moves with the selected piece
	public static boolean areMoves(int x1, int y1, String [][] checkers, boolean player) {
		
		if(player) {	
		return checkers[y1+1][x1]!=" " && checkers[y1+1][x1+2]!= " ";
		}
		return checkers[y1-1][x1]!=" " && checkers[y1-1][x1+2]!= " ";
		
	}
	
	
	public static boolean isMove(int x1, int y1, int x2, int y2, String[][] checkers, boolean player) {
		if (player){
		return isTable(x2,y2) || y2 != y1+1 || Math.abs(x2-x1) != 1 || checkers[y2][x2+1] != " ";
		}
		return isTable(x2,y2) || y2 != y1-1 || Math.abs(x2-x1) != 1 || checkers[y2][x2+1] != " ";
	}
	
	
	
}


	class userInput{
		
		public static int scanner() {
			
			
			int x1 = 0;
			boolean valid = false;
			
			while(!valid) {
			
			
				try {		
					Scanner sc = new Scanner(System.in);
					x1 = sc.nextInt();
					valid = true;
					
				}catch(InputMismatchException e) {
					x1 = 0;
					System.out.println("Invalid input; must be an integer.");	
					System.out.println("Try again:");

				}
			}
			return x1;
		}	
	}

public class checkers {
	
	
	
	
	
	public static void main(String[] args) {
		
		
		
		
		String[] a0 = {"0 |"," ","1"," ","1"," ","1"," ","1","|"};
		String[] a1 = {"1 |","1"," ","1"," ","1"," ","1"," ", "|"};
		String[] a2 = {"2 |"," ","1"," ","1"," ","1"," ","1","|"};
		String[] a3 = {"3 |"," "," "," "," "," "," "," "," ","|"};
		String[] a4 = {"4 |"," "," "," "," "," "," "," "," ","|"};
		String[] a5 = {"5 |","2"," ","2"," ","2"," ","2"," ","|"};
		String[] a6 = {"6 |"," ","2"," ","2"," ","2"," ","2","|"};
		String[] a7 = {"7 |","2"," ","2"," ","2"," ","2"," ","|"};
		String[][] checkers = {a0,a1,a2,a3,a4,a5,a6,a7};	
		
		//defining the table as an array of String arrays so it's easier to operate
		
		System.out.println("Welcome to checkers in Java! \n");
		tools.printTable(checkers);
		
		Scanner sc = new Scanner(System.in);
		
		int i = 0; //we define a variable to see whose turn is it 
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		for(;;) {
		
			if (i%2==0){
				for(;;) { //this loop runs until the user inputs a valid position
				System.out.println("\n Player 1's turn.");
				System.out.println("Coordinate of piece to move:");
				System.out.println("Enter X:");
				x1 = userInput.scanner();
				System.out.println("Enter Y:");
				y1 = userInput.scanner();
				
				if (userHandling.isPlayer("1", x1, y1, checkers)) { 
					
					System.out.println("Invalid position!");
					
				} else if(userHandling.areMoves(x1, y1, checkers, true)) {
					
					System.out.println("No possible moves.");
					
				} else {
					break;
				}
			}
				
			for(;;) {	
			System.out.println("\n Coordinate of new position:");
			System.out.println("Enter X:");
			x2 = userInput.scanner();
			System.out.println("Enter Y:");
			y2 = userInput.scanner();
			
			if(userHandling.isMove(x1, y1, x2, y2, checkers, true)) {  
				
				System.out.println("Invalid position");	
				
				} else {
					
					break;
				}
			}	
			checkers[y1][x1+1] = " "; //the "move", we make the previous position empty and the new one "1"
			checkers[y2][x2+1] = "1";
			
			tools.printTable(checkers);
			
			
			
			i += 1; //increase the counter to know that it's the other player's turn
			
			} else {
				
				
				for(;;) {
					System.out.println("\n Player 2's turn.");
					System.out.println("Coordinate of piece to move:");
					System.out.println("Enter X:");
					x1 = userInput.scanner();
					System.out.println("Enter Y:");
					y1 = userInput.scanner();
					
					if (userHandling.isPlayer("2", x1, y1, checkers)) { 
						
						System.out.println("Invalid position!");
						
					} else if(userHandling.areMoves(x1, y1, checkers, false)) {
						
						System.out.println("No possible moves.");
						
					} else {
						break;
					}
					
				}
					
				for(;;) {	
				System.out.println("Coordinate of new position:");
				System.out.println("Enter X:");
				x2 = userInput.scanner();
				System.out.println("Enter Y:");
				y2 = userInput.scanner();
				
				if(userHandling.isMove(x1, y1, x2, y2, checkers, false)) { 
				
					System.out.println("Invalid position! \n");	
					
					} else {
						
						break;
					}
				}	
				checkers[y1][x1+1] = " ";
				checkers[y2][x2+1] = "2";
				
				tools.printTable(checkers);
				
				i += 1;
				
				
				
			}
			
		}
		
	}

}
