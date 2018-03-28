import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Game extends Applet implements KeyListener, Runnable, MouseListener, MouseMotionListener{
	
	Image off_screen;
	Graphics off_g;
	
	UFO ufo = new UFO(400, 500, 60);

	Rect r = new Rect(50, 50, 100, 100);
	Rect r1= new Rect(400, 400, 300, 200);
	
	String[] action = {"left", "right", "up", "down"};
	//Sprite soldier = new Sprite(10,500, "/background.png", 1, 1);
			
	Animation anim1;
	
	Tank activeTank = new Tank(23,52, 45);
	
	
	background myBackground;
	
	//executes the runnable object
	Thread t;
	
	//booleans at class scope that would be used for the keys pressed
	boolean lt_Pressed = false;
	boolean rt_Pressed = false;
	boolean up_Pressed = false;
	boolean dn_Pressed = false;
	int mx;
	int my;
	
	boolean overlap = false;
	
	//init stands for initialize, and its part of the Applet class.
	public void init () {
	
		off_screen = createImage(3000, 2000);
		off_g      = off_screen.getGraphics(); //every image has a Graphics variable
		
		
		//asking Operating System for focus for the button presses.
		this.requestFocus();
		myBackground = new background(getX(), getY(), "background");
		
		//explicitly reference yourself
		//this is an applet and a key listener
		//this is an object that refers to itself and talks about itself
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		//makes this class runable because it has a run method.
		t = new Thread(this);
		t.start();
	}
	
	//clear method using an Off Screen Image to draw our image then switch it out with the current image
	//method: Double Buffering
	//1. Create your screen Image off screen
	//2. Copy your completed image on screen
	public void update(Graphics g) {
		off_g.clearRect(0, 0, 3000, 2000); //will clear the off screen image
		 
		paint(off_g);

		//animation.update();
		g.drawImage(off_screen, 0, 0, null);
	}
	
	//Method:Page Flipping
	//Create two references to two Images and you reference flip where the off_screen is always painting and the on_screen switches to
	//     the off_screen reference and off_screen paints on the old on_screen reference, and such on and such on.
	
	
	public void paint (Graphics g) {
		
		
		//soldier.draw(g);
		
		//g.drawImage(anim1.nextImage(), 550, 350, this);
		
		
		//g.drawImage(image, 500, 400, this);
		
		//myBackground.draw(g);
		//please draw the rectangle
		r.draw(g);
		r1.draw(g);
		
		ufo.draw(g);
		
		
		//please draw our tank
		activeTank.draw(g);
		
		
		//g.drawImage(animation.getSprite(), animation.getX(), animation.getY(), null);
		
		if(overlap) {
			g.drawString("overlaps", mx, my);
		}
		else{
			g.drawString("no overlap", mx, my);
		}
	}
	
	//cant paint things inside these functions because it only gets called once
	//isnt synchronized 60 times per second.
	//runs on a separate thread
	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if (code == e.VK_LEFT)		lt_Pressed = true;
		if (code == e.VK_RIGHT)		rt_Pressed = true;
		if (code == e.VK_UP)		up_Pressed = true;
		if (code == e.VK_DOWN)		dn_Pressed = true;
		
	}

	//cant paint because the function is only called in one time
	//isn't synchronized 60 times per second.
	//runs on a seperate thread.
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if (code == e.VK_LEFT)		lt_Pressed = false;
		if (code == e.VK_RIGHT)		rt_Pressed = false;
		if (code == e.VK_UP)		up_Pressed = false;
		if (code == e.VK_DOWN)		dn_Pressed = false;
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {  }


	@Override
	public void run() {
		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0; //current nanoTime of our system, we diivide it by a billion because it's too accurate. Turns to milliseconds
		double passedTime = 0;
		double unprocessedTime = 0;
		
		double frameTime = 0;
		int frames = 0; //how many frames have passed
		int fps = 0; //how many frames per second
		
		while (true) {
			
			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime; // how long has it been since lastTime updated to firstTime;
			lastTime = firstTime;
			
			unprocessedTime += passedTime;
			frameTime += passedTime;
			
			if (frameTime >= 1.0) { //if frameTime is greater than a second then do this.
				frameTime = 0;
				fps = frames;
				System.out.println(fps);
				frames = 0;
			}
			
			
			/*
			
			if(lt_Pressed) activeTank.rotateLeftBy(3);
			if(rt_Pressed) activeTank.rotateRightBy(3);
			if(up_Pressed) activeTank.moveForwardBy(3);
			if(dn_Pressed) activeTank.moveBackwardBy(3);  
			/*
			//nothing happens unless we redraw the screen 
			if(lt_Pressed) tank.moveBy(-10, 0);
			if(rt_Pressed) tank.moveBy(10, 0);
			if(up_Pressed) tank.moveBy(0, -10);
			if(dn_Pressed) tank.moveBy(0, 10);
			*/
			
			
			
			
			//asks Operating System to repaint the screen because the screen isn't up to date after the presses.
			repaint();
			frames++;
			try{
				//to make the program slower you need to make the thread go to sleep
				t.sleep(1);
			}
			catch(Exception x) { }
		}
		
	}
	
	


	@Override
	public void mouseDragged(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//this.mx is where i was, mx is where i am, so Terminal - Initial
		int dx = mx - this.mx;
		int dy = my - this.my;
		
		r.resizeBy(dx, dy);
		
		if(r.held) {
			r.moveBy(dx,dy);
		}
		
		
		
		
		//have to update the mx and my to the new location for the next calculation
		this.mx= mx;
		this.my= my;
		
		overlap = false;
		overlap = r.overlaps(r1);
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		
		
		mx = e.getX();
		my = e.getY();

		r = new Rect(mx, my, 0, 0);
			
		}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		
		
		
		if(r.contain(mx, my)) {
			r.grab();
		}
	
		}
		
		
		
		
	


	@Override
	public void mouseReleased(MouseEvent e) {
		r.drop(); 
		
	} 
	

}
