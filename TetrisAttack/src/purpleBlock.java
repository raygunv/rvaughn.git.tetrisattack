
import java.awt.Color;


public class purpleBlock extends Block {

	/**
	 * Create the panel.
	 */
	public purpleBlock(int x, int y) {
		super(x,y);
		setBackground(Color.MAGENTA);
	}
	public boolean match(BlockType color)
	{
		return color==BlockType.PURPLE;
	}
	public boolean match(Block otherBlock)
	{
		return otherBlock.match(BlockType.PURPLE);
	}
}
