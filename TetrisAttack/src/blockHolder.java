import java.util.Random;

import javax.swing.JPanel;

public class blockHolder {

	Random rn = new Random();
	int matchCounter=1;
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

	/* Just for fun EGV ...
	private boolean canIAddByEGV(int i, int j, BlockType color) {
		// k=0 go left; k=1 go down
		for (int k=0; k<=1; k++)
		{
			matchCounter=1;
			int iInput=k*-1; // k=0 gives 0 (left) and k=1 gives -1 (down)
			int jInput=k-1; // k=0 gives -1 (left) and k=1 gives 0 (down)
			if ((jInput<0 && j!=0) // check boundary left
					|| (iInput<0 && i!= 0)) //check boundary down
			{
				if(match(i, j, iInput, jInput, color))
					return false;
			}
		}
		return true;
	} */
	
private boolean canIAdd(int i, int j, BlockType color) {
	// GRADING: don't run code that you don't need; for canIadd you only
	// need left and up but you will need all four in some other method
	matchCounter=1;
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
	/*if(j!=COLUMNS-1)//right
	{
		iInput=0;
		jInput=1;
		if(match(i, j, iInput, jInput, color))
		{
			return false;
		}
	}
	*/
	matchCounter=1;
	if(i!=0)//up
	{
		iInput=-1;
		jInput=0;
		if(match(i, j, iInput, jInput, color))
		{
			return false;
		}
	}
	/*if(i!=ROWS-1)//down
	{
		iInput=1;
		jInput=0;
		if(match(i, j, iInput, jInput, color))
		{
			return false;
		}
	}
	*/
	return true;
	
}
public void switchBlocks(int i, int j)
{
	
	Block copyBlock =new Block(arrayOfBlocks[i][j]);
	
	arrayOfBlocks[i][j]=arrayOfBlocks[i+1][j];
	arrayOfBlocks[i+1][j]=copyBlock;
}
public boolean match(int i, int j, int iInput, int jInput,  BlockType color)
{
	assert(i>=0 && i<=ROWS);
	assert(j>=0 && j<=COLUMNS);
	i+=iInput;
	j+=jInput;
	if(i==ROWS||i==-1||j==-1||j==COLUMNS)
	{
		return false;
	}
	else if(arrayOfBlocks[i][j]==null||!(arrayOfBlocks[i][j]).match(color))
	{
		return false;
	}
	else 
	{	
		matchCounter++;
		if(matchCounter==3)
		{
			return true;
		}
	}
	return match(i, j, iInput, jInput, color);
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
