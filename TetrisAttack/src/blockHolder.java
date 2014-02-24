import java.util.Random;

import javax.swing.JPanel;

public class blockHolder {

	Random rn = new Random();
	int MatchCounter=1;
	final int ROWS = 12;
	final int COLUMNS = 6;
	

	Block arrayOfBlocks[][] = new Block[ROWS][COLUMNS];

	public void arrayFiller() {
		

		BlockType color;
		Block newBlock;
		int random;

		int startRow = ROWS/2;
		for (int i = startRow; i < ROWS; i++) {

			for (int j = 0; j < COLUMNS; j++) {

				//random = rn.nextInt(6) + 1;
				// System.out.println(random);

				do {
					random = rn.nextInt(6);
				} while (!canIAdd(i, j, BlockType.values()[random]));
				
				color = BlockType.values()[random];
				switch (color) {
					case RED:
						newBlock = new redBlock(i, j);
						arrayOfBlocks[i][j] = newBlock;
						break;

					case BLUE:
						newBlock = new blueBlock(i, j);
						arrayOfBlocks[i][j] = newBlock;
						break;

					case CYAN:
						newBlock = new cyanBlock(i, j);
						arrayOfBlocks[i][j] = newBlock;
						break;
					case PURPLE:
						newBlock = new purpleBlock(i, j);
						arrayOfBlocks[i][j] = newBlock;
						break;

					case YELLOW:
						newBlock = new yellowBlock(i, j);
						arrayOfBlocks[i][j] = newBlock;
						break;

					case GREEN:
						newBlock = new greenBlock(i, j);
						arrayOfBlocks[i][j] = newBlock;
						break;
						
					default:
						System.out.println("Should never get here!!!");
				}
			}
		}

	}
/*
	private boolean isolateBlock(int i1, int i2, int j1, int j2, int random) {
		
		if(arrayOfBlocks[i2][j2]==null)
		{
			return false;
		}
		else if(arrayOfBlocks[i1][j1].match(arrayOfBlocks[i2][j2]))
		{
			//i--;
			isolateBlock(i2, j2, random);
		}
			
		return true;
	}
*/
private boolean canIAdd(int i, int j, BlockType color) {
	MatchCounter=1;
	int iInput;
	int jInput;
	if(j!=0)//left
	{
		iInput=0;
		jInput=-1;
		if(match(i, j, iInput, jInput, color))
		{
			return false;
		}
	}
	if(j!=COLUMNS-1)//right
	{
		iInput=0;
		jInput=1;
		if(match(i, j, iInput, jInput, color))
		{
			return false;
		}
	}
	MatchCounter=1;
	if(i!=0)//up
	{
		
		iInput=-1;
		jInput=0;
		if(match(i, j, iInput, jInput, color))
		{
			return false;
		}
	}
	if(i!=ROWS-1)//down
	{
		iInput=1;
		jInput=0;
		if(match(i, j, iInput, jInput, color))
		{
			return false;
		}
	}
	return true;
		
		
		
}

public boolean match(int i, int j, int iInput, int jInput,  BlockType color)
{
	i+=iInput;
	j+=jInput;
	if(arrayOfBlocks[i][j]==null||!(arrayOfBlocks[i][j]).match(color))
	{
		return false;
	}
	else 
	{
		
		
		MatchCounter++;
		if(MatchCounter==3)
		{
			return true;
		}
		else if(j!=0)//need to fix this
		{
			return match(i, j, iInput, jInput, color);
		}
		return false;
	}
	
	
}

public void paneFiller(JPanel pane) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < 6; i++) {
			x += 5;
			y = 0;

			for (int j = 6; j < 12; j++) {
				y += 5;

				pane.add(arrayOfBlocks[j][i]);
				arrayOfBlocks[j][i].setLocation(i * 60 + x, j * 60 + y);

			}
		}
	}

}
