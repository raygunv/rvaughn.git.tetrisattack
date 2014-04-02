
import javax.swing.JPanel;

public abstract class Block extends JPanel implements Cloneable
{		

	int iLoc;
	int jLoc;
	public Block(int i, int j)
	{
		iLoc=i;
		jLoc=j;
		setSize(60,60);
	}
	
	
	public abstract boolean match(BlockType color);
	
	//public boolean match(Block color)
	
	public abstract BlockType getColor();
	public int getI()
	{
		return iLoc;
	}
	
	public int getJ()
	{
		return jLoc;
	}
	
}
