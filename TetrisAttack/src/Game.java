import javax.swing.JPanel;


public class Game
{
	blockHolder holder;
	static JPanel pane;
	Cursor myCursor;
	
	public void start()
	{
		holder=new blockHolder();
		holder.arrayFiller();
		holder.drawPane(pane);
		myCursor = new Cursor(this);
	}

	public Game(JPanel contentPane)
	{
		pane=contentPane;
	}
	
	public void switchBlocks(int i, int j)
	{
		holder.switchBlocks(i, j);
	}
	
	public Cursor getCursor()
	{
		return myCursor;
	}
	
	public void drawCursor(JPanel cursor)
	{
		pane.add(cursor);
	
	}
}