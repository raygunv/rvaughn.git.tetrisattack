import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public  class Cursor extends JPanel implements ActionListener, KeyListener {
	blockHolder holder;
	final int ROWS = 12;
	final int COLUMNS = 6;
	int xCur=ROWS/2;
	int yCur=COLUMNS/2-1;
	
	
	public Cursor(blockHolder bh){
		holder=bh;
		setLocation(yCur*60 + (yCur)*5+1, xCur*60 + (xCur)*5+1);
		setBackground(new Color(255, 240, 245));
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setSize(133, 68);
		holder.drawCursor(this);
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
		
		setLocation(yCur*60 + yCur*5+1, xCur*60 + xCur*5+1);
		holder.drawCursor(this);
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
