import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public  class Cursor extends JPanel implements ActionListener, KeyListener {
//	blockHolder holder;
	Game myGame;
	final int ROWS = 12;
	final int COLUMNS = 6;
	int xCur=ROWS/2;
	int yCur=COLUMNS/2-1;
		
	Timer timer;
	int tspeed=10000;
	public Cursor(Game mg){
//		holder=bh;
		myGame = mg;
		setLocation(yCur*60 + (yCur)*5+1, xCur*60 + (xCur)*5+1);
		setBackground(new Color(255, 240, 245));
		setBorder(new LineBorder(new Color(0, 0, 0), 3));
		setSize(133, 68);
		myGame.drawCursor(this);
		timer=new Timer(tspeed, this);
		timer.start();
	}
	
	public void stop()
	{
		timer.stop();
		this.setEnabled(false); // disable keylistener
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		myGame.addRow();
		timer.stop();
		if(xCur>0)
		{
			xCur--;
		}
		myGame.drawCursor(this);
		timer.start();
		
	}
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if(k.getKeyChar()==' ')
		{
			myGame.switchBlocks(xCur, yCur);
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
		if(k.getKeyChar()=='p')
		{
			myGame.addRow();
			if(xCur>0)
			{
				xCur--;
			}
		}
		
		setLocation(yCur*60 + yCur*5+1, xCur*60 + xCur*5+1);
		myGame.drawCursor(this);
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
