
import java.awt.Color;


public class cyanBlock extends Block {

	/**
	 * Create the panel.
	 */
	public cyanBlock(int x, int y) {
		super(x,y);
		setBackground(Color.CYAN);
	}
	public boolean match(BlockType color)
	{
		return color==BlockType.CYAN;
	}
	public boolean match(Block otherBlock)
	{
		return otherBlock.match(BlockType.CYAN);
	}
}
