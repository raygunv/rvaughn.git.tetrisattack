import java.awt.Color;


public class noneBlock extends Block {

	/**
	 * Create the panel.
	 */
	public noneBlock(int x, int y) {
		super(x, y);
		setBackground(Color.BLACK);
	}

	@Override
	public boolean match(BlockType color) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BlockType getColor() {
		// TODO Auto-generated method stub
		return BlockType.NONE;
	}



	
}
