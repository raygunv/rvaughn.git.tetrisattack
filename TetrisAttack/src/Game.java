import javax.swing.JPanel;
import javax.swing.JFrame;

public class Game
{
	blockHolder holder;
	JPanel myPane;
	Frame myFrame;
	Cursor myCursor;

	public Game(Frame frame)
	{
		myFrame=frame;
		myPane=frame.getPane();
	}
	
	public void start()
	{
		holder=new blockHolder(this);
		holder.arrayFiller();
		holder.drawPane(myPane);
		myCursor = new Cursor(this);
		myFrame.addKeyListener(myCursor);
	}
	
	public void switchBlocks(int i, int j)
	{
		holder.switchBlocks(i, j);
	}
	
	public JPanel getPane()
	{
		return myPane;
	}
	public Frame getFrame()
	{
		return myFrame;
	}
	
	public Cursor getCursor()
	{
		return myCursor;
	}
	
	public void drawCursor(JPanel cursor)
	{
		myPane.add(cursor);
	
	}
}