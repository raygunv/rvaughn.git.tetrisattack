
import java.awt.Color;


public class redBlock extends Block {

	/**
	 * Create the panel.
	 */
	public redBlock(int x, int y) {
		super(x, y);
		setBackground(Color.RED);
	}
	public boolean match(BlockType color)
	{
		return color==BlockType.RED;
	}
	public boolean match(Block otherBlock)
	{
		return otherBlock.match(BlockType.RED);
	}
	public BlockType getColor()
	{
		return BlockType.RED;
	}
}
