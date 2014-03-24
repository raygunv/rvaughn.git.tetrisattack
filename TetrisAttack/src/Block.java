
import javax.swing.JPanel;

public abstract class Block extends JPanel implements Cloneable
{		

	static int iLoc;
	static int jLoc;
	public Block(int i, int j)
	{
		iLoc=i;
		jLoc=j;
		setSize(60,60);
	}
	
	
	public abstract boolean match(BlockType color);
	
	//public boolean match(Block color)
	
	public abstract BlockType getColor();
	public static int getI()
	{
		return iLoc;
	}
	
	public static int getJ()
	{
		return jLoc;
	}
	
}
