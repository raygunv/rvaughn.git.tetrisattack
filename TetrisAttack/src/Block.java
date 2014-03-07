
import javax.swing.JPanel;

public class Block extends JPanel
{		

	int xLoc, yLoc;
	public Block(int x, int y)
	{
		xLoc=x;
		yLoc=y;
		setSize(60,60);
	}
	public Block(Block oldBlock)
	{
		this.xLoc=oldBlock.xLoc;
		this.yLoc=oldBlock.yLoc;
		this.setSize(60,60);
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
