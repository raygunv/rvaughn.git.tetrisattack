
import java.awt.Color;


public class yellowBlock extends Block {

	/**
	 * Create the panel.
	 */
	public yellowBlock(int x, int y) {
		super(x,y);
		setBackground(Color.YELLOW);
	}
	public boolean match(BlockType color)
	{
		return color==BlockType.YELLOW;
	}
	public boolean match(Block otherBlock)
	{
		return otherBlock.match(BlockType.YELLOW);
	}
}
