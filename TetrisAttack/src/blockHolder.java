import java.util.Random;

import javax.swing.JPanel;

public class blockHolder {

	Random rn = new Random();
	int yMatchCounter=0;
	int xMatchCounter=0;

	Block arrayOfBlocks[][] = new Block[6][12];

	public void arrayFiller() {
		final int ROWS = 12;
		final int COLUMNS = 6;

		BlockType color;
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
						redBlock red = new redBlock(i, j);
						arrayOfBlocks[i][j] = red;
						break;

					case BLUE:
						blueBlock blue = new blueBlock(i, j);
						arrayOfBlocks[i][j] = blue;
						break;

					case CYAN:
						cyanBlock cyan = new cyanBlock(i, j);
						arrayOfBlocks[i][j] = cyan;
						break;
					case PURPLE:
						purpleBlock purple = new purpleBlock(i, j);
						arrayOfBlocks[i][j] = purple;
						break;

					case YELLOW:
						yellowBlock yellow = new yellowBlock(i, j);
						arrayOfBlocks[i][j] = yellow;
						break;

					case GREEN:
						greenBlock green = new greenBlock(i, j);
						arrayOfBlocks[i][j] = green;
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
		
	}
	else 
	{
		i--;
		xMatchCounter++;
		matchLeft(i, j, random);
		return true;
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
