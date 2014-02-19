import java.util.Random;

import javax.swing.JPanel;

public class blockHolder {

	Random rn = new Random();
	int yMatchCounter=1;
	int xMatchCounter=1;

	Block arrayOfBlocks[][] = new Block[6][12];

	public void arrayFiller() {
		final int ROWS = 12;
		final int COLUMNS = 6;

		BlockType color;
		Block newBlock;
		int random;

		int startRow = ROWS/2;
		for (int i = 0; i < COLUMNS; i++) {

			for (int j = startRow; j < ROWS; j++) {

				//random = rn.nextInt(6) + 1;
				// System.out.println(random);

				do {
					random = rn.nextInt(6);
				} while (!isolateBlock(i, j, random));
				
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
private boolean isolateBlock(int i, int j,  int random) {
	xMatchCounter=1;
	yMatchCounter=1;
	if(i!=0)
	{
		if(matchLeft(i, j, random))
		{
			return false;
		}
	}
	//if(i!=5)
	//{
	//	matchRight(i, j, random);
	//}
	//if(j!=0)
	//{
	//	matchUp(i, j, random);
	//}
	//if(j!=11)
	//{
	//	matchDown(i, j, random);
	//}
	return true;
		
		
		
	}
public boolean matchUp(int i, int j,  int random)
{
	if(arrayOfBlocks[i][j++]==null||!arrayOfBlocks[i][j].match(arrayOfBlocks[i][j++]))
	{
		return false;
	}
	else 
	{
		j++;
		yMatchCounter++;
		matchUp(i, j, random);
		return true;
	}
	
}
public boolean matchDown(int i, int j, int random)
{
	if(arrayOfBlocks[i][j-1]==null||!arrayOfBlocks[i][j].match(arrayOfBlocks[i][j-1]))
	{
		return false;
	}
	else 
	{
		j--;
		yMatchCounter++;
		matchDown(i, j, random);
		return true;
	}
	
}
public boolean matchLeft(int i, int j,  int random)
{
	assert(i!=0);
	if(arrayOfBlocks[i--][j]==null||!arrayOfBlocks[i][j].match(arrayOfBlocks[i--][j]))
	{
		return false;
	}
	else 
	{
		i--;
		xMatchCounter++;
		if(xMatchCounter==3)
		{
			return true;
		}
		
		return matchLeft(i, j, random);
	}
	
	
}
public boolean matchRight(int i, int j,  int random)
{
	if(arrayOfBlocks[i++][j]==null||!arrayOfBlocks[i][j].match(arrayOfBlocks[i++][j]))
	{
		return false;
	}
	else 
	{
		i++;
		xMatchCounter++;
		matchRight(i, j, random);
		return true;
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

				pane.add(arrayOfBlocks[i][j]);
				arrayOfBlocks[i][j].setLocation(i * 60 + x, j * 60 + y);

			}
		}
	}

}
