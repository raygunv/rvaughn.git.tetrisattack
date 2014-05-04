import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class blockHolder {
	Random rn = new Random();
	int matchCounter = 1;
	final int ROWS = 12;
	final int COLUMNS = 6;
	int colorMatchX=0;
	int colorMatchY=0;
	LinkedList<Block> matchBlockList = new LinkedList<Block>();
	JPanel myPane;
	JPanel myCursor;
	Frame myFrame;
	Game myGame;
	Block arrayOfBlocks[][] = new Block[ROWS][COLUMNS];
		
	boolean labelsOnOff=true;
	
	public void setLabels(boolean onOff)
	{
		labelsOnOff=onOff;
	}
	
	public blockHolder(Game game) {
		myGame = game;
		myPane = myGame.getPane();
		myCursor = myGame.getCursor();
		myFrame = myGame.getFrame();
	}

	public void arrayFiller() {
		Random rn = new Random();
		BlockType color;
		Block newBlock;
		int random;

		int startRow = ROWS / 2;
		for (int i = startRow; i < ROWS; i++) {

			for (int j = 0; j < COLUMNS; j++) {
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

	private boolean canIAdd(int i, int j, BlockType color) {
		matchCounter = 1;
		int iInput;
		int jInput;
		if (j != 0)// left
		{
			iInput = 0;
			jInput = -1;
			if (match(i, j, iInput, jInput, color)) {
				return false;
			}
		}
		matchCounter = 1;
		if (i != 0)// up
		{
			iInput = -1;
			jInput = 0;
			if (match(i, j, iInput, jInput, color)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean fall()
	{ 
		boolean falling=false;
		for(int x=0; x<6;x++)
		{
			for (int i = 5; i >=0; i--)
			{
				for (int j = 10; j >= 1; j--)
				{
					if(arrayOfBlocks[j+1][i]==null)
					{
						switchBlocks(j, i, j+1, i);
						falling=true;
					}
				}
			}
		}
		return falling;
	}

	private void relocateBlock(Block block, int i, int j) 
	{
		if(block!=null)
		{
			block.iLoc = i;
			block.jLoc = j;
			arrayOfBlocks[i][j] = block;
		}
		else
		{
			arrayOfBlocks[i][j]=null;
		}
	}
	
	public void switchBlocks(int i, int j, int i2, int j2)
	{
		Block firstBlock = arrayOfBlocks[i][j];
		Block secondBlock = arrayOfBlocks[i2][j2];
		relocateBlock(firstBlock, i2, j2);
		relocateBlock(secondBlock, i, j);
	}
	
	public void dissolveBlocks(int i, int j)
	{
		disappear(i, j);
		if (arrayOfBlocks[i][j + 1] != null) 
		{
			disappear(i, j + 1);
		}
		if(fall())//4 in a row doesn't work
		{
			for (j = 0; j < 6; j++) 
			{
				for ( i = 0; i < 12; i++) 
				{
					disappear(i, j);
				//if (arrayOfBlocks[i][j + 1] != null) 
				//{
				//	disappear(i, j + 1);
				//}
				}
			}
		}
		drawPane();
	}
	
	private boolean match(int i, int j, int iInput, int jInput, BlockType color) {
		assert (i >= 0 && i <= ROWS);
		assert (j >= 0 && j <= COLUMNS);
		i += iInput;
		j += jInput;
		if (i == ROWS || i == -1 || j == -1 || j == COLUMNS) {
			return false;
		} else if (arrayOfBlocks[i][j] == null
				|| !(arrayOfBlocks[i][j]).match(color)) {
			return false;
		} else {
			matchCounter++;
			if (matchCounter == 3) {
				return true;
			}
		}
		return match(i, j, iInput, jInput, color);
	}

	private void disappear(int i, int j) {
		// left and right
		if(arrayOfBlocks[i][j]==null)
		{
			return;
		}
		matchCounter = 1;
		disappearX(i, j);
		int matchCounterX = matchCounter;

		// up and down
		matchCounter = 1;
		disappearY(i, j);
		int matchCounterY = matchCounter;

		if (matchCounterX >= 3) {
			matchBlockList.addLast(arrayOfBlocks[i][j]);
			collapseX();
			fall();
		}
		if (matchCounterY >= 3&&arrayOfBlocks[i][j]!=null){
			matchBlockList.addLast(arrayOfBlocks[i][j]);
			collapseY();
			fall();
		}
	}

	private void disappearX(int i, int j) {
		int iInput = 0;
		int jInput = 0;

		BlockType color = arrayOfBlocks[i][j].getColor();
		if (j != 0) // left
		{
			jInput = -1;
			match(i, j, iInput, jInput, color);
		}
		if (j != COLUMNS - 1) // right
		{
			jInput = 1;
			match(i, j, iInput, jInput, color);
		}
	}

	private void disappearY(int i, int j) {
		int iInput = 0;
		int jInput = 0;

		BlockType color = arrayOfBlocks[i][j].getColor();
		if (i != 0) // up
		{
			iInput = -1;
			match(i, j, iInput, jInput, color);
		}
		if (i != ROWS - 1) // down
		{
			iInput = 1;
			match(i, j, iInput, jInput, color);
		}
	}

	public void collapseY() {
		Block middle = (Block) matchBlockList.removeFirst();
		assert (middle != null);
		int i = middle.getI();
		int j = middle.getJ();
		BlockType middleColor = middle.getColor(); 

		for (int k = -2; k <= 2; k++) {
			// Row boundary condition
			if (i+k==-1||i+k==-2||arrayOfBlocks[i + k][j]== null) {
				System.out.println("Row boundary: i="+i+"  k="+k);
				continue;
			}
			System.out.println("i is "+i+"  k is "+k);
			if (i + k == ROWS-1) {
				if (arrayOfBlocks[i + k][j].match(middleColor))
				{
					arrayOfBlocks[i + k][j] = null;
				}
				break;
			}
			assert (i + k >= 0);
			assert (i + k < ROWS);
			assert (arrayOfBlocks[i + k][j] != null);
			if(k==-2)
			{
				System.out.println("here");
				if(arrayOfBlocks[i+k][j].match(arrayOfBlocks[i+k+1][j].getColor())&&arrayOfBlocks[i+k][j].match(middleColor))
				{
					System.out.println("here");
					arrayOfBlocks[i + k][j] = null;
				}
				
			}
			//else if(k==2&&arrayOfBlocks[i+k-1][j]!=null)
			//{
			//	System.out.println("here2");
			//	if(arrayOfBlocks[i+k-1][j]!=null)
			//	{
			//		arrayOfBlocks[i+k][j]=null;
			//	}
			//df}
		
			else if (arrayOfBlocks[i + k][j]!=null&&arrayOfBlocks[i + k][j].match(middleColor)) {
				arrayOfBlocks[i + k][j] = null;
			}
		}
	}

	public void collapseX() {
		Block middle = (Block) matchBlockList.removeFirst();
		int i = middle.getI();
		int j = middle.getJ();
		BlockType middleColor = middle.getColor();
		assert (middle != null);
		
		for (int k = -2; k <= 2; k++) {
			System.out.println(k);
			if (j+k==-1||j+k==-2||arrayOfBlocks[i][j+k]== null) {
				k++;
				continue;
			}
			if (j+k==COLUMNS-1) {
				if (arrayOfBlocks[i][j+k].match(middleColor))
				{
					colorMatchX++;
					arrayOfBlocks[i][j+k] = null;
				}
				break;
			}
			assert (j + k >= 0);
			assert (j + k < COLUMNS);
			assert (arrayOfBlocks[i][j + k] != null);
			
			if (arrayOfBlocks[i][j + k].match(middleColor)) {
				colorMatchX++;
				arrayOfBlocks[i][j + k] = null;
			}
		}
		
	}
	
	public void drawPane() {
		int x = 0;
		int y = 0;

		JPanel pane = myGame.getPane();
		pane.removeAll();
		for (int i = 0; i < 6; i++) {
			x += 5;
			y = 0;

			for (int j = 0; j < 12; j++) {
				y += 5;
				if (arrayOfBlocks[j][i] != null) {
					Block b = arrayOfBlocks[j][i];

					b.setLocation(i * 60 + x, j * 60 + y);

					b.removeAll();
					if (labelsOnOff) addLabels(b);
					b.validate();

					pane.add(b);
				}
			}
		}
		pane.validate();
		pane.repaint();
	}

	private void addLabels(Block b) {
		int i = b.getI();
		int j = b.getJ();
		JLabel l = new JLabel(j + ", " + i);
		l.setOpaque(true);
		l.setBackground(b.getColor().getColor());
		// l.setBackground(Color.WHITE);
		b.add(l);
		l = new JLabel("" + b.getColor());
		l.setOpaque(true);
		l.setBackground(b.getColor().getColor());
		b.add(l);
	}

}
