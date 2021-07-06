package carGame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;
//import java.io.IOException;
//import java.net.URL;
//
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.UnsupportedAudioFileException;
public class CarPanel extends JPanel implements ActionListener, KeyListener{
	int speed;  //speed of car
	int distance;  //distance between two opponents car
	JLabel label;   //label to display background image
	ImageIcon bgImage1;  //background image 1
	ImageIcon bgImage2;  //background image 2
	Image car1Image;//car image
	Image car2Image;
	Image car3Image;
	Image policeImage;  //police car image
	int width=1100;    //width of panel
	int height =742;    //height of panel
	int carWidth;    //width of car
	int carHeight ;   //height of car
	int policeCarWidth =50;   //width of police car
	int policeCarHeight = 88;  //height of police car
	int policeCarYpos=0;   //x position of police car
	int policeCarXpos=280;  //y position of police car
	int carXpos=275;     //x position of car
	int carYpos=742;   //y position of car
	int delay =100;    //delay of the background screen
	static int diff=1;    //difficulty 
	long score=0;   //score 
	long level=0;//level of the game , to determine the increase in speed
	static int carNo = 1;
	Random random;
	ArrayList<Rectangle> position; //arrayList position that will store the opponent cars as rectangle
	Timer timer;
	static boolean running =false;      //tells whether car is running or not
	static boolean colision=false;    //tells whether car has collision or not
	URL file3 ;   //file URL that will give path of running audio file
	AudioInputStream runningCarAudio;  //running car audio stream
	Clip runningCar;    //clip of running car audio
	URL file2 ;   //file URL that will give path of collision audio file
	AudioInputStream colloidAudio;  //collision car audio stream
	Clip colloid;    //clip of collision car audio
	
	
	CarPanel(){	
		timer = new Timer(20,this);
		position = new ArrayList<Rectangle>();
		random = new Random();
		distance = 300;
		bgImage1 = new ImageIcon(getClass().getClassLoader().getResource("race1.png"));
		bgImage2 = new ImageIcon(getClass().getClassLoader().getResource("race2.png"));
		car1Image = new ImageIcon(getClass().getClassLoader().getResource("car1.png")).getImage();
		car2Image = new ImageIcon(getClass().getClassLoader().getResource("car2.png")).getImage();
		car3Image = new ImageIcon(getClass().getClassLoader().getResource("car3.png")).getImage();
		policeImage = new ImageIcon(getClass().getClassLoader().getResource("policeCar.png")).getImage();
		
		if(carNo==1) {
			carWidth=60;
			carHeight=77;
		}else if(carNo==2) {
			carWidth=60;
			carHeight=108;
		}else if(carNo==3) {
			carWidth=60;
			carHeight=167;
		}
		
		
		
		label = new JLabel();
		label.setPreferredSize(new Dimension(width , height));
				
		
		timer.start();
		colision = false;
		running =true;
		label.setIcon(new ImageIcon("res/race1.png"));
		
		//setting the speed and delay according to difficulty
		if(diff==0) {
			delay=150;
			speed =3;
			lane(true);
			lane(true);
		}
		else if(diff==1) {
			delay = 100;
			speed =5;
			lane(true);
			lane(true);
			lane(true);
		}else if(diff==2) {
			delay=65;
			speed =8;
			lane(true);
			lane(true);
			lane(true);
			lane(true);
		}else if(diff==3) {
			delay=50;
			speed =10;
			lane(true);
			lane(true);
			lane(true);
			lane(true);
		}

		
//----------running audio part ends-----------//
				try {
			runningAudio();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//----------running audio part ends-----------//
				
//----------collision audio part start-----------//				
				try {
					collisionAudio();
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//----------collision audio part ends-----------//		

		
		this.add(label);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setPreferredSize(new Dimension(width,height));
		backGround thread2 = new backGround();   //calling the background thread class
		thread2.start();    //starting the thread

		
	}
	

	//audio when the car hits
	public void collisionAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		
	
		file2 = getClass().getClassLoader().getResource("crash.wav");
		colloidAudio = AudioSystem.getAudioInputStream(file2);
		colloid = AudioSystem.getClip();
		colloid.open(colloidAudio);	
		
		
	}
	//audio when car is running
	public void runningAudio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		

		file3 = getClass().getClassLoader().getResource("running.wav");
		runningCarAudio = AudioSystem.getAudioInputStream(file3);
		runningCar = AudioSystem.getClip();
		runningCar.open(runningCarAudio);	
	    runningCar.loop(40);
	 
		
		
		
	}
	
	public class backGround extends Thread{  // class to create a background thread to run our background
	public void run()
	{ 
		
		while(!colision)
		{
			try
			{	
				label.setIcon(new ImageIcon("res/race2.png"));
				Thread.sleep(delay);
				label.setIcon(new ImageIcon("res/race1.png"));
				Thread.sleep(delay);
//				lane(true);  //calling the opponents car
			}catch(Exception e){}
		}
	
	}
}


	
	public void lane(boolean first) {   //method to return in which lane the opponent car will come
		int l = random.nextInt()%8;
		l = Math.abs(l);
		if(l<4)
			policeCarXpos = 280 + l*(15+policeCarWidth);
		else
			policeCarXpos = 560 + (l-4)*(15+policeCarWidth);
		

		if(first)
			position.add(new Rectangle(policeCarXpos,policeCarYpos-100-(position.size()*distance),policeCarWidth, policeCarHeight));
		else
			position.add(new Rectangle(policeCarXpos,position.get(position.size()-1).y-(distance+100),policeCarWidth, policeCarHeight));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(carNo==1) {
		g.drawImage(car1Image, carXpos, carYpos-carHeight,null);
		}else if(carNo==2) {
			g.drawImage(car2Image, carXpos, carYpos-carHeight,null);
		}else if(carNo==3) {
			g.drawImage(car3Image, carXpos, carYpos-carHeight,null);
		}
		
		for(Rectangle rect:position) {
			g.drawImage(policeImage, rect.x, rect.y, policeCarWidth, policeCarHeight, null);
		}
		  g.setColor(Color.white);
		  g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,50));   //setting font of text
		  g.drawString("Score", 900, 50);
		  g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,40));
		  g.drawString(""+score, 900, 100);
		  
		  if(colision) {
			  g.setColor(Color.RED);
			  g.setFont(new Font(Font.SERIF,Font.BOLD,100));
			  g.drawString("Game Over", 290, 360);
			  g.setFont(new Font(Font.SERIF,Font.BOLD,40));
			  g.drawString("Press Enter To Restart", 337, 420);
			  
		  }
		  
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub		
		
		Rectangle rect;   //creating opponent cars
		for(int i=0; i<position.size();i++) {
			rect = position.get(i);
			rect.y +=speed;
		}
		
//		checking for collision
		for(Rectangle r:position) {
			if(r.x>=carXpos-30 && r.x<=carXpos+carWidth-20 && r.y + policeCarHeight >carYpos-carHeight && r.y + policeCarHeight <carYpos+carHeight) {
				timer.stop();
				colision = true;
				runningCar.stop();
				colloid.start();

			}
		}
		
			//removing the car when they go out of screen
		for(int i=0; i<position.size();i++) {
			rect = position.get(i);
			if(rect.y +rect.height>height+50) {
				position.remove(rect);
				lane(false);
				score +=10;
				level +=10;
			}
		}
		//increasing the speed of game according to level
		if(diff==0 && level >100) {
			speed++;
			level=0;
		}else if(diff==1 && level>150){
			speed++;
			level=0;
		}else if(diff==2 && level>300) {
			speed++;
			level=0;
		}
		else if(diff==3 && level>350) {
			speed++;
			level=0;
		}
		
		repaint();
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		SwingUtilities.updateComponentTreeUI(this);
		if(!colision && running) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			if(carXpos+carWidth<825+5) {
				carXpos +=10;
			}
			break;
		case KeyEvent.VK_NUMPAD6:
			if(diff!=3) {
			if(carXpos+carWidth<825+5) {
				carXpos +=20;
			}
			}else {
				if(carXpos+carWidth<825+7) {
					carXpos +=40;
				}
			}
			break;
		case KeyEvent.VK_LEFT:
			if(carXpos>275+5) {
				carXpos -=10;
			}
			break;
		case KeyEvent.VK_NUMPAD4:
			if(diff!=3) {
			if(carXpos>275+5) {
				carXpos -=20;
			}
			}else {
				if(carXpos>275+20) {
					carXpos -=40;
				}
			}
			break;
		case KeyEvent.VK_UP:
			if(carYpos-carHeight>20) {
				carYpos -=10;
			}
			break;
		case KeyEvent.VK_NUMPAD8:
			if(diff!=3) {
			if(carYpos-carHeight>20) {
				carYpos -=20;
			}
			}else {
				if(carYpos-carHeight>25) {
					carYpos -=40;
				}
			}
			break;
		case KeyEvent.VK_DOWN:
			if(carYpos<742) {
				carYpos +=10;
			}
			break;
		case KeyEvent.VK_NUMPAD2:
			if(diff!=3) {
			if(carYpos<742) {
				carYpos +=20;
			}
			}else {
				if(carYpos<742) {
					carYpos +=40;
				}
			}
			break;

				}
		}
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			if(running && !colision) {
				running =false;
				runningCar.stop();
				timer.stop();
				}			   
			else if(!running && !colision){
				running=true;
				runningCar.loop(40);
				timer.start();
				}
				break;
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
