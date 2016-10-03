
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public char  board[][] = new char [4][4];
	
	Random random = new Random();
	private int row;
	private int column;
	int TOTAL_ROWS=4;
	int TOTAL_COLUMNS=4;
	int TOTAL_GOLD;
	int TOTAL_PIT = 3;
	int TOTAL_WUMPUS = 1;
	int TOTAL_CG;
	int GOLD_COLLECT=0;
	int FLAG=0;
	char GOLD_CHARACTER ='g';
	char PIT_CHARACTER='p';
	char WUMPUS_CHARACTER='w';
	char CLEAR_GROUND_CHARACTER='.';
	char PLAYER_CHARACTER = '*' ; //Not sure of this.
	Gold[] goldItem;
	Pit[] pitItem;
	Wumpus[] wumpItem;
	ClearGround[] cgItem;
	//Bondaries
	final int TOP_CORNER=0;
	final int DOWN_CORNER=3;
	final int RIGHT_CORNER=3;
	final int LEFT_CORNER=0;
	int TARGET_CORNER;
	//Boundaries
	
	
	
   public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
   
	public int getGold() {
		return TOTAL_GOLD;
	}

	public void setGold(int gold) {
		this.TOTAL_GOLD=gold;
	}
   
   /**
	 * Initiates all the GameItem
	 * Sets the position of all the GameItem
	 * Maps the position of all the GameItem with Board
	 */
	
	
	private void setBoard(){
		TOTAL_GOLD = goldGenerator();
		goldItem = new Gold[TOTAL_GOLD];
		pitItem = new Pit[TOTAL_PIT];
		wumpItem=new Wumpus[TOTAL_WUMPUS];
		TOTAL_CG=cgGenerator(); //what is the use of this variable
		cgItem=new ClearGround[TOTAL_CG];
		for(int i=0;i<TOTAL_GOLD;i++)
		{
			goldItem[i] = new Gold(GOLD_CHARACTER);
			setPosition(goldItem[i]);
			//goldItem[i].display(); /* to sets the individual Gold item position in Board
			//System.out.println("Printing done");
			
		}
		
		//System.out.println("Entered Gold");
		
		/**
		 * we can create objects for clear Ground at last after setting the player
		 */
		
		
		
		
		for(int i=0;i<TOTAL_PIT;i++)
		{
			pitItem[i] = new Pit(PIT_CHARACTER);
			setPosition(pitItem[i]);
			//pitItem[i].display(); /* to sets the individual Gold item position in Board
			//System.out.println("Printing done");
			
		}
		
		//System.out.println("Entered PIT");
		for(int i=0;i<TOTAL_WUMPUS;i++){
			wumpItem[i]=new Wumpus(WUMPUS_CHARACTER);
			setPosition(wumpItem[i]);
		}
		//System.out.println("Entered Wumpus");
		
		setPosition(); //Sets the Player
	/*	System.out.println("Entered Player");
		
		System.out.println("Going to Enter the Clear ground"); 
		*/
		for(int i=0;i<TOTAL_CG;i++){
			cgItem[i]=new ClearGround(CLEAR_GROUND_CHARACTER);
			
		} 
		setPosition(cgItem);
		//System.out.println("Entered Clear ground"); 
	
}
	/**
	 * End of function setBoard
	 * 
	 */
	
	
	
	/**
	 * Prints the complete board with all the GameItems and player
	 * 
	 */
	public void display(){
		System.out.println("Enterd into displayed");
		
		for(int i=0;i<TOTAL_ROWS;i++)
		{	
			System.out.print("|");
			for(int j=0;j<TOTAL_COLUMNS;j++){
				if(board[i][j] != 0 )
					System.out.print(board[i][j]);
				else
					System.out.print("_");
				System.out.print("|");
				
			}	
			System.out.print("\n");	
				
		}
	}
	/**
	 * End of the function display(), to display the complete board
	 * 
	 */
	
	
	
	
	private void senseNearBy(){
	
		
	}
	
	
	private void menu(){
		System.out.println("");
		System.out.println("=====Wumpus=====");
		System.out.println("1) Move Player Left");
		System.out.println("2) Move Player Right");
		System.out.println("3) Move Player Up");
		System.out.println("4) Move Player Down");
		System.out.println("5) Quit");
		
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		switch(a){
		case 1:
			boundCheck();
			moveLeft();
			break;
		case 2:
			boundCheck();
			moveRight();
			break;
		case 3:
			boundCheck();
			moveUp();
			break;
		case 4:
			boundCheck();
			moveDown();
			break;
		case 5:
			System.out.println("Printing of exit");
			break;
		default :
			System.out.println("Please enter valid input");
			Game g=new Game();
			g.runGame();
		}
		
	}

	/**
	 * Driver method to initiate the game and control the game
	 */
	public void runGame(){
		setBoard();
		display();
		menu();
		
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
		int  TOTAL_GOLD=random.nextInt((3-1)+1)+1;
		int numberOfGold= TOTAL_GOLD;
		System.out.println("Gold number is "+numberOfGold);
		setGold(numberOfGold);
		return numberOfGold;
		
	}

	private int cgGenerator(){
		int numberOfCG=11-TOTAL_GOLD;
		//System.out.println("clear ground number "+numberOfCG);
		return numberOfCG;
	}
	/**
	 *End of function goldGenerator to generate the Number of Gold GameItem for the game.
	 */
private void boundCheck(){
	if(column ==0 ){
		Object temp1= board[row][3] ==PLAYER_CHARACTER;
		Object temp2=board[row][3]==WUMPUS_CHARACTER;
		Object temp3=board[row][3]==PIT_CHARACTER;
		Object temp4=board[row][3]==CLEAR_GROUND_CHARACTER;
		Object temp5=board[row][3]==GOLD_CHARACTER;
		if(temp1.equals(temp2)){
			gameEnd();
		}
		else if(temp1.equals(temp3)){
			gameEnd();
		}
		else if(temp1.equals(temp4)){
			Object temp=board[row][0];
			board[row][0]=board[row][3];
			board[row][3]=(char) temp;
			updatePlayer(row ,3);
		}
		else if(temp1.equals(temp5)){
			Object temp=board[row][0];
			board[row][0]=board[row][3];
			board[row][3]=(char) temp;
			updatePlayer(row ,3);
			System.out.println("***** Congrats Gold recieved *****");
			 GOLD_COLLECT++;
			   goldCount(GOLD_COLLECT);
			    FLAG++;
		}
	}
	else if(column ==3){
		Object temp=board[row][3];
		board[row][3]=board[row][0];
		board[row][0]=(char) temp;
		updatePlayer(row , 0);
	}
	else if(row==0){
		Object temp=board[0][column];
		board[0][column]=board[3][column];
		board[3][column]=(char) temp;
		updatePlayer(3 ,column);
	}
	else if(row==3){
		Object temp=board[3][column];
		board[3][column]=board[0][column];
		board[0][column]=(char) temp;
		updatePlayer(0,column);
	}
}
/*public void goldRecieved(){
		System.out.println("***** Congrats Gold recieved *****");
		 GOLD_COLLECT++;
		   goldCount(GOLD_COLLECT);
		    FLAG++;
		    display();
		    menu();
	
} */
	public void moveLeft(){
		
		if(column==LEFT_CORNER)
		{
			TARGET_CORNER=RIGHT_CORNER;
		}
		else
		{
			TARGET_CORNER =column;
		}
		
		if(PLAYER_CHARACTER == board[row][column] && WUMPUS_CHARACTER ==board[row][TARGET_CORNER]){
			System.out.println("===== Player is Eaten by Wumpus =====");
			gameEnd();
		}
		else if(PLAYER_CHARACTER == board[row][column] && CLEAR_GROUND_CHARACTER == board[row][TARGET_CORNER]){
			Object temp= board[row][TARGET_CORNER];
			board[row][TARGET_CORNER]=board[row][column];
			board[row][column]=(char) temp;
			updatePlayer( row, TARGET_CORNER);
			 display();
			 menu();
			
		}
		else if(PLAYER_CHARACTER == board[row][column] && GOLD_CHARACTER ==board[row][TARGET_CORNER]){
			board[row][TARGET_CORNER]=board[row][column];
			board[row][column]='.';
			//goldRecieved();
			System.out.println("***** Congrats Gold recieved *****");
			 GOLD_COLLECT++;
			   goldCount(GOLD_COLLECT);
			    FLAG++;
			    updatePlayer( row, TARGET_CORNER);
			    display();
			    menu();
			
			   
			  
		}
		else if( PLAYER_CHARACTER == board[row][column] && PIT_CHARACTER ==board[row][TARGET_CORNER]){
			 System.out.println("===== Player fallen in Pit =====");
		      gameEnd();
		}
		
	}
	public void moveRight(){ 
		
		if(column==RIGHT_CORNER)
		{
			TARGET_CORNER=LEFT_CORNER;
		}
		else
		{
			TARGET_CORNER =column;
		}
		
		
		
		if(PLAYER_CHARACTER == board[row][column] && WUMPUS_CHARACTER ==board[row][TARGET_CORNER]){
			System.out.println("===== Player is Eaten by Wumpus =====");
			gameEnd();
		}
		else if(PLAYER_CHARACTER == board[row][column] && CLEAR_GROUND_CHARACTER == board[row][TARGET_CORNER]){
			Object temp= board[row][TARGET_CORNER];
			board[row][TARGET_CORNER]=board[row][column];
			board[row][column]=(char) temp;
			updatePlayer( row, TARGET_CORNER);
			 display();
			    menu();
		
		}
		else if(PLAYER_CHARACTER == board[row][column] && GOLD_CHARACTER ==board[row][TARGET_CORNER]){
			board[row][TARGET_CORNER]=board[row][column];
			board[row][column]='.';
			//goldRecieved();
			System.out.println("***** Congrats Gold recieved *****");
			 GOLD_COLLECT++;
			   goldCount(GOLD_COLLECT);
			    FLAG++;
			    updatePlayer( row, TARGET_CORNER);
			    display();
			    menu();
			
			   
	
		}
		else if( PLAYER_CHARACTER == board[row][column] && PIT_CHARACTER ==board[row][TARGET_CORNER]){
			 System.out.println("===== Player fallen in Pit =====");
		      gameEnd();
		}
	}
	public void moveUp(){
		
		if(row==TOP_CORNER)
		{
			TARGET_CORNER=DOWN_CORNER;
		}
		else
		{
			TARGET_CORNER =row;
		}
		
		
		
		if(PLAYER_CHARACTER == board[row][column] && WUMPUS_CHARACTER ==board[TARGET_CORNER][column]){
			System.out.println("===== Player is Eaten by Wumpus =====");
			gameEnd();
		}
		else if(PLAYER_CHARACTER == board[row][column] && CLEAR_GROUND_CHARACTER == board[TARGET_CORNER][column]){
			Object temp= board[TARGET_CORNER][column];
			board[TARGET_CORNER][column]=board[row][column];
			board[row][column]=(char) temp;
			updatePlayer( TARGET_CORNER, column);
			 display();
			    menu();
		
		}
		else if(PLAYER_CHARACTER == board[row][column] && GOLD_CHARACTER ==board[TARGET_CORNER][column]){
			board[row-1][column]=board[row][column];
			board[row][column]='.';
			//goldRecieved();
			System.out.println("***** Congrats Gold recieved *****");
			 GOLD_COLLECT++;
			   goldCount(GOLD_COLLECT);
			    FLAG++;
			    updatePlayer( TARGET_CORNER, column);
			    display();
			    menu();
			 
			    
		}
		else if( PLAYER_CHARACTER == board[row][column] && PIT_CHARACTER ==board[TARGET_CORNER][column]){
			 System.out.println("===== Player fallen in Pit =====");
		      gameEnd();
		}
	}
		
	public void moveDown(){
		
		if(row==DOWN_CORNER)
		{
			TARGET_CORNER=TOP_CORNER;
		}
		else
		{
			TARGET_CORNER =row;
		}
		
		
		if(PLAYER_CHARACTER == board[row][column] && WUMPUS_CHARACTER ==board[TARGET_CORNER][column]){
			System.out.println("===== Player is Eaten by Wumpus =====");
			gameEnd();
		}
		else if(PLAYER_CHARACTER == board[row][column] && CLEAR_GROUND_CHARACTER == board[TARGET_CORNER][column]){
			Object temp= board[TARGET_CORNER][column];
			board[TARGET_CORNER][column]=board[row][column];
			board[row][column]=(char) temp;
			updatePlayer( TARGET_CORNER, column);
			 display();
			    menu();
			    
		}
		else if(PLAYER_CHARACTER == board[row][column] && GOLD_CHARACTER ==board[TARGET_CORNER][column]){
			board[TARGET_CORNER][column]=board[row][column];
			board[row][column]='.';
			//goldRecieved();
			System.out.println("***** Congrats Gold recieved *****");
			 GOLD_COLLECT++;
			   goldCount(GOLD_COLLECT);
			    FLAG++;
			    updatePlayer( TARGET_CORNER, column);
			    display();
			    menu();
			
			  
		}
		else if( PLAYER_CHARACTER == board[row][column] && PIT_CHARACTER ==board[TARGET_CORNER][column]){
			 System.out.println("===== Player fallen in Pit =====");
		      gameEnd();
		}
	}
	
	private void goldCount(int gold){
		int goldGen = getGold();
		if(GOLD_COLLECT == goldGen){
		gameEnd();
	}
	}
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
	
	private void setPosition ()
	{
		int randomRow = random.nextInt(4);
		int randomColumn = random.nextInt(4);
		
		while(board[randomRow][randomColumn] != 0){
			randomRow = random.nextInt(4);
			randomColumn = random.nextInt(4);
			
		}
		board[randomRow][randomColumn]  = PLAYER_CHARACTER;
		setRow(randomRow);
		setColumn(randomColumn);
		
	}
	
	private void updatePlayer(int rrow,int ccolumn){
		int row=rrow;
		int column=ccolumn;
		setRow(row);
		setColumn(column);
	board[row][column]=PLAYER_CHARACTER;
	}

	
	private void setPosition(ClearGround[] cg)
	{	
		System.out.println("Total Number of Clear Ground is "+cg.length);
		int currentCG =0;
		for(int i=0;i<TOTAL_ROWS;i++)
		{	
			for(int j=0;j<TOTAL_COLUMNS;j++){
				if(board[i][j] == 0 ){
					display();
					board[i][j] = CLEAR_GROUND_CHARACTER;
					cg[currentCG].setRowPosition(i);
					cg[currentCG].setColumnPosition(j);
					//System.out.println("Entered CG for position "+(currentCG+1));
					currentCG+=1;					
				}
				
			}	
					
		}
	}

	
	public void gameEnd(){
		if(FLAG == 0){   
			System.out.println("=====GAME END=====");
			System.exit(0); 
		}
		else{
			System.out.println("Gold Collected is "+GOLD_COLLECT);
			System.out.println("*****Congrats you played well*****");
			System.exit(0); 
		}
		
	}

		
	

}
