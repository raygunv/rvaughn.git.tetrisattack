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
		if(arrayOfBlocks[i][j+1]!=null||j+1>COLUMNS)
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
		// left and right
		matchCounter=1;
		disappearX(i,j);
		int matchCounterX = matchCounter;

		// up and down
		matchCounter=1;
		disappearY(i,j);
		int matchCounterY = matchCounter;

		// remove matched blocks
		if(matchCounterX>=3||matchCounterY>=3)
		{
			matchBlockList.addLast(arrayOfBlocks[i][j]);
			collapse();
		}
	}

	private void disappearX(int i, int j)
	{
		int iInput=0;
		int jInput=0;
		//int jMatch=0;
		BlockType color=arrayOfBlocks[i][j].getColor();
		if(j!=0)//left
		{
			jInput=-1;
			match(i, j, iInput, jInput, color);
			/*if(match(i, j, iInput, jInput, color))
			{
				jMatch=jInput;
			}*/
		}
		if(j!=COLUMNS-1)//right
		{
			//iInput=0;
			jInput=1;
			match(i, j, iInput, jInput, color);
			/*if(match(i, j, iInput, jInput, color))
			{
				jMatch=jInput;
			}*/
		}
	}
	
	private void disappearY(int i, int j)
	{
		int iInput=0;
		int jInput=0;
		//int iMatch=0;
		BlockType color=arrayOfBlocks[i][j].getColor();
		if(i!=0) //up
		{
			iInput=-1;
			match(i, j, iInput, jInput, color);
			/*if(match(i, j, iInput, jInput, color))
			{
				iMatch=iInput;
			}*/
		}
		if(i!=ROWS-1) //down
		{
			iInput=1;
			match(i, j, iInput, jInput, color);
			/*if(match(i, j, iInput, jInput, color))
			{
				iMatch=iInput;
			}*/
		}
	}
	
	public void collapse()
	{
		assert(matchBlockList.size()==1);
		Block middle=(Block)matchBlockList.removeFirst();
		assert(middle!=null);
		int i=middle.getI();
		int j=middle.getJ();
		BlockType middleColor=middle.getColor(); // PROB#1 fix
		assert(arrayOfBlocks[i][j]!=null);
			
//		int boundary = 0;
		
		// REAGAN - I don't think you can use this one variable "k" to look 
		// in both the x-dir and y-dir all at the same time, can you?
		// Especially since you modify k right here to solve the row
		// boundary condition
		for(int k=-2; k<=2; k++)
		{
			/*boundary=i+k;
			if(boundary==-1) // REAGAN - why doesn't this need to be -2 AND -1?
			{
				k++;
			}
			if(k==ROWS-1) // REAGAN - why doesn't this need to be ROWS-1 and ROWS-2?
			{
				break;
			}*/
			
			// REAGAN - how about this for checking row boundary conditions?
			k=Math.max(0,k);
			if (i+k>=ROWS-2)
			{
				break;
			}
			assert(i+k>=0);
			assert(i+k<ROWS);
			assert(arrayOfBlocks[i+k][j]!=null);
			
			// REAGAN - now you have to check column boundary conditions before proceeding
			// REAGAN - I moved that code here, but I don't think it works
			//if(i+k-1>=ROWS||i+k+1<=0||j+k+1<=0||j+k-1>COLUMNS) 
			if(j+k+1<=0||j+k-1>COLUMNS) // REAGAN - Row boundary already handled
			{
				break;
			}			
			assert(j+k>=0);
			assert(j+k<COLUMNS);
			assert(arrayOfBlocks[i][j+k]!=null);
	
			// REAGAN - PROB#1
			// REAGAN - you have already "deleted" arrayOfBlocks[i][j], but you are still using it
			// assert(arrayOfBlocks[i][j]!=null);
			//else if(arrayOfBlocks[i][j].match(arrayOfBlocks[i+k][j].getColor())) PROB#1

			//System.out.println(arrayOfBlocks[i+k][j].getColor());
			//System.out.println(arrayOfBlocks[i+k][j].getI()+", "+arrayOfBlocks[i+k][j].getJ());
			if(arrayOfBlocks[i+k][j].match(middleColor)) // PROB#1 fix
			{
				arrayOfBlocks[i+k][j]=null;
			}
			//else if(arrayOfBlocks[i][j].match(arrayOfBlocks[i][j+k].getColor())) PROB#1
			else if(arrayOfBlocks[i][j+k].match(middleColor)) // PROB#1 fix
			{
				arrayOfBlocks[i][j+k]=null;
			}
			//System.out.println(j + ", " + i);
			//System.out.println(hold.getColor());
		}
	}

	
/*	public void collapse() // REAGAN version
	{
		int i;
		int j;
		int boundary;
		Block hold;
//		Block hold1;
		assert(matchBlockList.size()==1);
//		for(int z=1;z<=matchBlockList.size();)
//		{
//			System.out.println(matchBlockList.size());
			hold=(Block)matchBlockList.removeFirst();
			assert(hold!=null);
			i=hold.getI();
			j=hold.getJ();
			
			for(int k=-2; k<=2; k++)
			{
				boundary=i+k;
				if(boundary==-1) // REAGAN - why do this?
				{
					k++;
				}
				if(boundary==ROWS-1)
				{
					break;
				}
					
//				System.out.println(k);
				assert(i+k>=0);
				assert(i+k<ROWS);
				assert(arrayOfBlocks[i+k][j]!=null);
				assert(arrayOfBlocks[i][j]!=null);  // REAGAN - seems that this block already gone
				System.out.println(arrayOfBlocks[i+k][j].getColor());
				System.out.println(arrayOfBlocks[i+k][j].getI()+", "+arrayOfBlocks[i+k][j].getJ());
				if(i+k-1>=ROWS||i+k+1<=0||j+k+1<=0||j+k-1>COLUMNS)
				{
					break;
				}
				else if(arrayOfBlocks[i][j].match(arrayOfBlocks[i+k][j].getColor()))
				{
					arrayOfBlocks[i+k][j]=null;
				}
				else if(arrayOfBlocks[i][j].match(arrayOfBlocks[i][j+k].getColor()))
				{
					arrayOfBlocks[i][j+k]=null;
				}
	 			
	//		}
			System.out.println(j + ", " + i);
			System.out.println(hold.getColor());
			//arrayOfBlocks[i][j]=null;
			//new noneBlock(i, j);
			//System.out.println(arrayOfBlocks[i][j].getColor());
		}
	}
*/
	
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

	/*private void disappear(int i, int j)
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
			}

		}
		if(j!=COLUMNS-1)//right
		{
			iInput=0;
			jInput=1;
			if(match(i, j, iInput, jInput, color))
			{
				jMatch=jInput;
			}
		}
		int matchCounterX=matchCounter;
		if(matchCounterX==3)
		{
				matchBlockList.addLast(arrayOfBlocks[i][j]);
		}

		matchCounter=1;
		if(i!=0) //up
		{
			iInput=-1;
			jInput=0;
			if(match(i, j, iInput, jInput, color))
			{
				iMatch=iInput;
							
			}
		}
		if(i!=ROWS-1) //down
		{
			iInput=1;
			jInput=0;
			if(match(i, j, iInput, jInput, color))
			{
				iMatch=iInput;
			}
		}
		
		int matchCounterY=matchCounter;
		if(matchCounterY==3)
		{
				matchBlockList.addLast(arrayOfBlocks[i][j]);
		}
		if(matchCounterX>=3||matchCounterY>=3)
		{
			collapse();
		}
	}*/
	
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
