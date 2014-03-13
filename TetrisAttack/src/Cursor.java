import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public  class Cursor implements ActionListener, KeyListener {
	blockHolder bH=new blockHolder();
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if(k.getKeyChar()==' ')
		bH.switchBlocks(0, 0);
	}
	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		System.out.println("sean-released: " + k.getKeyChar());
	}
	@Override
	public void keyTyped(KeyEvent k) {
		// TODO Auto-generated method stub
		System.out.println("sean-typed: " + k.getKeyChar());
	}

}
