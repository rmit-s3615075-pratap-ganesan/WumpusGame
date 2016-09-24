import java.util.Random;

public class Game {
	
	public char  board[][] = new char [4][4];
	
	Random random = new Random();
	int MAX_GOLD=3;
	int MIN_GOLD=1;
	int TOTAL_GOLD;
	int TOTAL_PIT = 1;
	int TOTAL_WUMPUS = 1;
	char GOLD_CHARACTER ='g';
	Gold[] goldItem;
	
	
	
	/**
	 * Initiates all the GameItem
	 * Sets the position of all the GameItem
	 * Maps the position of all the GameItem with Board
	 */
	private void setBoard(){
		
		TOTAL_GOLD = goldGenerator();
		goldItem = new Gold[TOTAL_GOLD];
		
		
		for(int i=0;i<TOTAL_GOLD;i++)
		{
			goldItem[i] = new Gold(GOLD_CHARACTER);
			setPosition(goldItem[i]);
			//goldItem[i].display(); /* to sets the individual Gold item position in Board
			//System.out.println("Printing done");
			
		}
}
	/**
	 * End of function setBoard
	 * 
	 */
	
	
	
	
	
	
	/**
	 * Prints the complete board with all the GameItems and player
	 * 
	 */
	void display(){
		
		for(int i=0;i<4;i++)
		{	
			System.out.print("|");
			for(int j=0;j<4;j++){
				if(board[i][j] != 0 )
					System.out.print(board[i][j]);
				else
					System.out.print("_");
				System.out.print("|");
				
			}	
			System.out.println("");	
		}
	}
	/**
	 * End of the function display(), to display the complete board
	 * 
	 */
	
	
	
	
	private void senseNearBy(){}
	
	
	private void menu(){}
	
	
	/**
	 * Driver method to initiate the game and control the game
	 */
	public void runGame(){
		
		setBoard();
		display();
		
	}
	/**
	 * End of function runGame()
	 * 
	 */
	
	
	/**
	 * Random Generation of Number of Gold Item from MIN of 1 to MAX to 3
	 * @return numberofGold - Total Number of Gold GameItem for the game
	 */
	private int goldGenerator(){
		int numberOfGold = random.nextInt(MAX_GOLD);
		while (numberOfGold < MIN_GOLD || numberOfGold > MAX_GOLD)
			numberOfGold = random.nextInt(MAX_GOLD);
		return numberOfGold;
	}
	/**
	 *End of function goldGenerator to generate the Number of Gold GameItem for the game.
	 */
	
	
	
	
		
	/**
	 * Sets the position(Row and Column) of the individual GameItem
	 * Maps the individual GameItem to board
	 * Takes care of the overridden position problem among GameItem
	 * @param gameObject - references of individual object to Parent GameItem Object
	 */
	private void setPosition (GameItem gameObject)
	{
		int randomRow = random.nextInt(4);
		int randomColumn = random.nextInt(4);
		
		while(board[randomRow][randomColumn] != 0){
			randomRow = random.nextInt(4);
			randomColumn = random.nextInt(4);
			
		}
		gameObject.setRowPosition(randomRow);
		gameObject.setColumnPosition(randomColumn);
		board[randomRow][randomColumn]  = gameObject.getGameItemCharacter();
	}
	/**
	 * End of the function setPosition
	 */
		
		
		
	

}
