import java.util.Random;


public abstract class BuildArray {
	
	final int ROWS = 12;
	final int COLUMNS = 6;	
	
	public void init(Block[][] arrayOfBlocks)
	{
		arrayFiller(arrayOfBlocks);
	}
	
	public void arrayFiller(Block[][] arrayOfBlocks) 
	{
		Random rn = new Random();
		BlockType color;
		Block newBlock;
		int random;

		int startRow = ROWS/2;
		for (int i = startRow; i < ROWS; i++) 
		{

			for (int j = 0; j < COLUMNS; j++) 
			{
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

	private boolean canIAdd(int i, int j, BlockType color) 
	{
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
		return true;
	}
}
