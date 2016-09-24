
public class GameItem {
	
	private int row;
	private int column;
	
	
	private char gameItemCharacter;
	public GameItem(char c){
		
		gameItemCharacter =c;
		
	}
	
	public char getGameItemCharacter()
	{
		return gameItemCharacter;
	}
	
	
	
	public void setRowPosition(int x){
		
		this.row = x;
		
	}
	
	
	public void setColumnPosition(int y){
		
		this.column = y;
	}
	
	
	public void display(){
		
		for(int i=0;i<4;i++)
		{	
			System.out.print("|");
			for(int j=0;j<4;j++){
				if(i == row && j == column)
					System.out.print(gameItemCharacter);
				else
					System.out.print("_");
				System.out.print("|");
				
			}	
			System.out.println("");	
		}
		
		
	
	
		
		
	}

}
