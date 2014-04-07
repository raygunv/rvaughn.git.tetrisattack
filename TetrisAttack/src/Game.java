import javax.swing.JPanel;


public class Game
{
	blockHolder holder=new blockHolder();
	static JPanel pane;
	Cursor myCursor=new Cursor(holder);
	public void start()
	{
		
		holder.arrayFiller();
		holder.drawPane(pane);
	}

	public Game(JPanel contentPane)
	{
		pane=contentPane;
	}
	public Cursor getCursor()
	{
		return myCursor;
	}
	public static void drawCursor(JPanel cursor)
	{
		pane.add(cursor);

		
	}
}