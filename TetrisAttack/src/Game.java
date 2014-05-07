import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Game
{
	blockHolder holder;
	JPanel myPane;
	Frame myFrame;
	Cursor myCursor;
	Timer myTimer;

	public Game(Frame frame)
	{
		myFrame=frame;
		myPane=frame.getPane();
	}
	
	public void start()
	{
		holder=new blockHolder(this);
		holder.init();
		holder.drawPane();
		//holder.setLabels(false);
		myCursor = new Cursor(this);
		myFrame.addKeyListener(myCursor);
	}
	
	public void stop()
	{
		//myPane.removeAll();
		System.out.println("Game Over");
		myCursor.stop();
		myFrame.removeKeyListener(myCursor);
		myFrame.validate();
	}
	
	public void switchBlocks(int i, int j)
	{
		holder.switchBlocks(i, j, i, j+1);
		holder.dissolveBlocks(i, j);
		//holder.dissolveBlocksMom(i, j);
	}
	public void addRow()
	{
		holder.addRow();
	}
	public Timer getTimer()
	{
		return myTimer;
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