import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyFrame extends JFrame implements KeyListener{

	  Draw drawing;

	public MyFrame(){
		this.drawing = new Draw();
	}


	public void keyPressed(KeyEvent e){
		if(e.getKeyCode() == KeyEvent.VK_W){
			drawing.moveUp();
		}
		else if(e.getKeyCode() == KeyEvent.VK_D){
			drawing.moveRight();
		}
		else if(e.getKeyCode() == KeyEvent.VK_S){
			drawing.moveDown();
		}
		else if(e.getKeyCode() == KeyEvent.VK_A){
			drawing.moveLeft();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			drawing.jump();
		}
		else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			drawing.attack();
		}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			drawing.crouch();
		}
	}
	
	public void keyReleased(KeyEvent e){
		
	}

	public void keyTyped(KeyEvent e){
		
	}


	public static void main(String args[]){
		MyFrame gameFrame = new MyFrame();
		gameFrame.setSize(600,300);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
		gameFrame.getContentPane().add(gameFrame.drawing);
		gameFrame.addKeyListener(gameFrame);
	}
}