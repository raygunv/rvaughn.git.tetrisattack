
import java.awt.Color;


public class greenBlock extends Block {

	/**
	 * Create the panel.
	 */
	public greenBlock(int x, int y) {
		super(x,y);
		setBackground(Color.GREEN);
	}
	public boolean match(BlockType color)
	{
		return color==BlockType.GREEN;
	}
	public boolean match(Block otherBlock)
	{
		return otherBlock.match(BlockType.GREEN);
	}
	public BlockType getColor()
	{
		return BlockType.GREEN;
	}
}
