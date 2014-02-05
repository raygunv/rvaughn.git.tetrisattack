
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
}
