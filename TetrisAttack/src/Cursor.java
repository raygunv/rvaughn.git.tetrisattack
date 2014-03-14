import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public  class Cursor implements ActionListener, KeyListener {
	blockHolder holder;
	final int ROWS = 12;
	final int COLUMNS = 6;
	int xCur=ROWS/2;
	int yCur=COLUMNS/2-1;
	public Cursor(blockHolder bh){
		holder=bh;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if(k.getKeyChar()==' ')
		{
			holder.switchBlocks(xCur, yCur);
		}
		if(k.getKeyChar()=='d')
		{
			if(yCur<COLUMNS-2)
			{
				yCur++;
			}
		}
		if(k.getKeyChar()=='w')
		{
			if(xCur>0)
			{
				xCur--;
			}
		}
		if(k.getKeyChar()=='a')
		{
			if(yCur>0)
			{
				yCur--;
			}
		}
		if(k.getKeyChar()=='s')
		{
			if(xCur<ROWS-1)
			{
				xCur++;
			}
		}
		
		
		System.out.println(xCur + ", " + yCur);
	}
	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		
	}

}
