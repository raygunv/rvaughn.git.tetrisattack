
import java.awt.Color;


public class blueBlock extends Block {

	/**
	 * Create the panel.
	 */
	public blueBlock(int x, int y) {
		super(x,y);
		setBackground(Color.BLUE);
	}
	
	public boolean match(BlockType color)
	{
		return color==BlockType.BLUE;
	}
	public boolean match(Block otherBlock)
	{
		return otherBlock.match(BlockType.BLUE);
	}
	public BlockType getColor()
	{
		return BlockType.BLUE;
	}
}
