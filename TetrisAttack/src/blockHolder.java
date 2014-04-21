import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class blockHolder {

	Random rn = new Random();
	int matchCounter=1;
	final int ROWS = 12;
	final int COLUMNS = 6;
	LinkedList<Block> matchBlockList=new LinkedList<Block>();
	JPanel myPane;
	JPanel myCursor;
	Frame myFrame;
	Game myGame;
	Block arrayOfBlocks[][] = new Block[ROWS][COLUMNS];
	
	public blockHolder(Game game) {
		myGame = game;
		myPane = myGame.getPane();
		myCursor = myGame.getCursor();
		myFrame = myGame.getFrame();
	}

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


	
	private boolean canIAdd(int i, int j, BlockType color) {
		
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
	
	private void relocateBlock(Block block, int i, int j)
	{
		block.iLoc=i;
		block.jLoc=j;
		arrayOfBlocks[i][j]=block;
	}
	
	public void switchBlocks(int i, int j)
	{

		Block firstBlock = arrayOfBlocks[i][j];
		Block secondBlock = arrayOfBlocks[i][j+1];
		relocateBlock(firstBlock, i, j+1);
		relocateBlock(secondBlock, i, j);
		
		disappear(i, j);
		if(arrayOfBlocks[i][j+1]!=null)
		{
			disappear(i, j+1);
		}
		drawPane();
		
	}
	
	private boolean match(int i, int j, int iInput, int jInput,  BlockType color)
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
	
	private void disappear(int i, int j)
	{
		matchCounter=1;
		int iInput=0;
		int jInput=0;
		int jMatch=0;
		int iMatch=0;
		BlockType color=arrayOfBlocks[i][j].getColor();
		if(j!=0)//left
		{
			iInput=0;
			jInput=-1;
			if(match(i, j, iInput, jInput, color))
			{
				jMatch=jInput;
				//matchBlockList.addLast(arrayOfBlocks[i][j]);
				//matchBlockList.addLast(arrayOfBlocks[i][j-1]);
				//matchBlockList.addLast(arrayOfBlocks[i][j-2]);
				
			}

		}
		if(j!=COLUMNS-1)//right
		{
			iInput=0;
			jInput=1;
			if(match(i, j, iInput, jInput, color))
			{
				jMatch=jInput;
				//matchBlockList.addLast(arrayOfBlocks[i][j]);
				//matchBlockList.addLast(arrayOfBlocks[i][j+1]);
				//matchBlockList.addLast(arrayOfBlocks[i][j+2]);
				//System.out.println(matchCounter);
			}
		}
		int matchCounterX=matchCounter;
		if(matchCounterX==3)
		{
			for(int x=0; x<matchCounterX; x++)
			{
				matchBlockList.addLast(arrayOfBlocks[i][j+x*jMatch]);
			}
		}

		matchCounter=1;
		if(i!=0)//up
		{
			iInput=-1;
			jInput=0;
			if(match(i, j, iInput, jInput, color))
			{
				iMatch=iInput;
				for(int x=0; x<matchCounter; x++)
				{	
					matchBlockList.addLast(arrayOfBlocks[i+x*iMatch][j]);
				}
				
			
			}
		}
		if(i!=ROWS-1)//down
		{
			iInput=1;
			jInput=0;
			if(match(i, j, iInput, jInput, color))
			{
				iMatch=iInput;
				for(int x=0; x<matchCounter; x++)
				{	
					matchBlockList.addLast(arrayOfBlocks[i+x*iMatch][j]);
				}
			}
		}
		
		int matchCounterY=matchCounter;
		//if(matchCounterY==3)
		//{
			//for(int x=0; x<matchCounterY; x++)
			//{	
			//	matchBlockList.addLast(arrayOfBlocks[i+x*iMatch][j]);
			//}
		//}
		if(matchCounterX>=3||matchCounterY>=3)
		{
			collapse();
		}
		else
		{
		 
		}
	}
	
	public void collapse()
	{
		int i;
		int j;
		Block hold;
		
		for(int z=1;z<=matchBlockList.size();)
		{
			System.out.println(matchBlockList.size());
 			hold=(Block)matchBlockList.removeFirst();
			i=hold.getI();
			j=hold.getJ();
			System.out.println(j + ", " + i);
			
			System.out.println(hold.getColor());
			arrayOfBlocks[i][j]=null;
			//new noneBlock(i, j);
			//System.out.println(arrayOfBlocks[i][j].getColor());
		}
	}
	public void drawPane() 
	{
		int x = 0;
		int y = 0;

		JPanel pane=myGame.getPane();
		pane.removeAll();
		for (int i = 0; i < 6; i++) 
		{
			x += 5;
			y = 0;

			for (int j = 0; j < 12; j++) 
			{
				y += 5;
				if(arrayOfBlocks[j][i]!=null)
				{
					Block b=arrayOfBlocks[j][i];
					
					b.setLocation(i * 60 + x, j * 60 + y);
					
					b.removeAll();
					addLabels(b);
					b.validate();
					
					pane.add(b);
				}
			}
		}
		pane.validate();
		pane.repaint();
	
	}
	
	private void addLabels(Block b) {
		int i=b.getI();
		int j=b.getJ();
		JLabel l=new JLabel(j+", "+i);
		l.setOpaque(true);
		l.setBackground(b.getColor().getColor());
		//l.setBackground(Color.WHITE);
		b.add(l);
		l=new JLabel(""+b.getColor());
		l.setOpaque(true);
		l.setBackground(b.getColor().getColor());
		b.add(l);
	}

/*
	public void drawPane(JPanel pane) 
	{
		int x = 0;
		int y = 0;
		
		pane.removeAll();
		
		for (int i = 0; i < 6; i++) 
		{
			x += 5;
			y = 0;

			for (int j = 0; j < 12; j++) 
			{
				y += 5;
				if(arrayOfBlocks[j][i]!=null)
				{
					pane.add(arrayOfBlocks[j][i]);
					arrayOfBlocks[j][i].setLocation(i * 60 + x, j * 60 + y);
					arrayOfBlocks[j][i].add(new JLabel(i+", "+j));
				}
			}
		}
		pane.validate();
		pane.repaint();
		
	}
*/
}
