import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;
import javax.swing.Timer;


public class Draw extends JComponent{

	private BufferedImage image;
	private BufferedImage backgroundImage;
	private URL resource = getClass().getResource("run0.png");


	public int x = 20;
	public int y = 180;
	public int height = 0;
	public int width = 0;
	public int state = 0;

	public Random randomizer;

	public int enemyCount;
	Opponent[] slime = new Opponent[10];


	public Draw(){
		randomizer = new Random();
		spawnEnemy();

		try{
			image =ImageIO.read(resource);
			backgroundImage = ImageIO.read(getClass().getResource("background.gif"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		height = image.getHeight();
		width = image.getWidth();

		startGame();
	}

	public void startGame(){
		Thread gameThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						for(int c = 0; c < slime.length; c++){
							if(slime[c]!=null){
								slime[c].moveTo(x,y);
								repaint();
							}
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}

	public void spawnEnemy(){
		if(enemyCount < 10){
			slime[enemyCount] = new Opponent(randomizer.nextInt(500), randomizer.nextInt(500), this);
			enemyCount++;
		}
	}


	public void reloadImage(){
		state++;
		if(state == 0){
			resource = getClass().getResource("run0.png");
		}
		else if(state == 1){
			resource = getClass().getResource("run1.png");
		}
		else if(state == 2){
			resource = getClass().getResource("run2.png");
			
		}
		else if(state == 3){
			resource = getClass().getResource("run3.png");
		
		}
		else if(state == 4){
			resource = getClass().getResource("run4.png");
			
		}
		else if(state == 5){
			resource = getClass().getResource("run5.png");
			state = 0;
		}
		try{
			image =ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void jumpAnimation(){
		Thread thread1 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("jump"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread1.start();
	}

	public void attackAnimation(){
		Thread thread2 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("attack"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					for(int x=0; x<slime.length; x++){
					if(slime[x]!=null){
						if(slime[x].contact){
							slime[x].life = slime[x].life - 10;
						}
					}
				}
			}
		});
		thread2.start();
	}
	public void crouchAnimation(){
		Thread thread3 = new Thread(new Runnable(){
			public void run(){
				for(int ctr = 0; ctr < 5; ctr++){
					try {
						if(ctr==4){
							resource = getClass().getResource("run0.png");
						}
						else{
							resource = getClass().getResource("crouch"+ctr+".png");
						}
						
						try{
							image = ImageIO.read(resource);
						}
						catch(IOException e){
							e.printStackTrace();
						}
				        repaint();
				        Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		thread3.start();
	}

	public void attack(){
		attackAnimation();
	}

	public void jump(){
		jumpAnimation();
	}
	public void crouch(){
		crouchAnimation();
	}

	public void moveUp(){
		y = y - 5;
		reloadImage();
		repaint();
		checkCollision();
	}

	public void moveDown(){
		y = y + 5;
		reloadImage();
		repaint();
		checkCollision();
	}

	public void moveRight(){
		x = x + 5;
		reloadImage();
		repaint();
		checkCollision();
	}

	public void moveLeft(){
		x = x - 5;
		reloadImage();
		repaint();
		checkCollision();
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawImage(backgroundImage, 0, 0, this);
		g.drawImage(image, x,y, this);
	}
}