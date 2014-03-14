
import javax.swing.JPanel;

public class Block extends JPanel implements Cloneable
{		

	int xLoc, yLoc;
	public Block(int x, int y)
	{
		xLoc=x;
		yLoc=y;
		setSize(60,60);
	}
	
	public boolean match(BlockType color)
	{
		return false;
	}
	public boolean match(Block color)
	{
		return false;
	}
}
