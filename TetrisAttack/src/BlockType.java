
import java.awt.Color;

public enum BlockType {
	RED (Color.RED), 
	BLUE (Color.BLUE), 
	CYAN (Color.CYAN), 
	PURPLE (Color.MAGENTA), 
	YELLOW (Color.YELLOW), 
	GREEN (Color.GREEN),
	NONE (Color.BLACK);

	private final Color color;
	
	BlockType(Color c) {
		this.color = c;
	}

	public Color getColor() { return color; }
	

}