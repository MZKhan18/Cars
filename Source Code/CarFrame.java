package carGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

//import gameCode.Panel;

public class CarFrame extends JFrame implements ActionListener , KeyListener {
	CarPanel panel;
	JMenuBar menuBar;
	JMenu game;
	JMenuItem newGame;
	JMenuItem exit;
	JMenuItem cancel;
	JMenu difficulty;
	JMenuItem easy;
	JMenuItem medium;
	JMenuItem hard;
	JMenuItem veryHard;
	JMenu help;
	JMenuItem showControl;
	JMenuItem showHelp;
	JMenu cars;
	JMenuItem car1;
	JMenuItem car2;
	JMenuItem car3;
	
	
	
	CarFrame(){
		menuBar = new JMenuBar(); //the main menu bar
		
		game = new JMenu("Game"); //items of menu bar, the code of game menu begins
		
		newGame = new JMenuItem("New Game"); //items of game menu of menu bar
		exit = new JMenuItem("Exit Game");
		cancel = new JMenuItem("Cancel");
		
		game.add(newGame); 
		game.add(exit);
		game.add(cancel);
		
		newGame.addActionListener(this);
		exit.addActionListener(this);
		
		menuBar.add(game);  //here the game menu code is finished
		
		difficulty = new JMenu("Difficulty"); //the code of difficulty menu begins here
		
		easy = new JMenuItem("Easy");
		medium = new JMenuItem("Medium");
		hard = new JMenuItem("Hard");
		veryHard = new JMenuItem("Very Hard");

		
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		difficulty.add(veryHard);
		
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		veryHard.addActionListener(this);
		
		menuBar.add(difficulty);  //the code of difficulty ends here
		
		help = new JMenu("Help");   //code of controls meny starts
		showControl = new JMenuItem("Controls");
		showHelp = new JMenuItem("Instructions");
		
		showHelp.addActionListener(this);
		showControl.addActionListener(this);
		help.add(showControl);
		help.add(showHelp);
		
		menuBar.add(help);
		
		cars = new JMenu("Cars");  //code of car menu starts
		
		car1 = new JMenuItem("Racing Car");
		car2 = new JMenuItem("Normal Car");
		car3 = new JMenuItem("Formula 1 Car");
		
		
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		
	
		
		car1.addActionListener(this);
		car2.addActionListener(this);
		car3.addActionListener(this);
		
		menuBar.add(cars);
		
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("logo.jpg"));
		  panel = new CarPanel();
		  panel.addKeyListener(this);
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  this.add(panel);
		  this.addKeyListener(this);
		  this.setJMenuBar(menuBar);
		  this.setIconImage(image.getImage());
		  this.pack();
		  this.setResizable(false);
		  this.setLocationRelativeTo(null);
		  this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==newGame && (CarPanel.colision || !CarPanel.running)) {
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==exit) {
			System.exit(0);
		}
		if(e.getSource()==easy && (CarPanel.colision || !CarPanel.running)) {
			CarPanel.diff=0;
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==medium && (CarPanel.colision || !CarPanel.running)) {
			CarPanel.diff=1;
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==hard && (CarPanel.colision || !CarPanel.running)) {
			CarPanel.diff=2;
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==veryHard && (CarPanel.colision || !CarPanel.running)) {
			CarPanel.diff=3;
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==showControl ) {
			JOptionPane.showMessageDialog(null, "UP Key - Move UP\n"
					+ "DOWN Key - Move DOWN\n"
					+ "RIGHT Key - Move RIGHT\n"
					+ "LEFT Key - Move LEFT\n\n"
					+ "FOR FASTER MOVEMENT\n"
					+ "NumPad 8 - Move UP\n"
					+ "NumPad 2 - Move DOWN\n"
					+ "NumPad 4 - Move LEFT\n"
					+ "NumPad 6 - Move RIGHT\n\n"
					+ "Space Bar - Pause/Play", 
					"Controls",JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==showHelp) {
			JOptionPane.showMessageDialog(null, "In this game you get points when the opponent cars passes your cars\n"
					+ "If you the opponent car hits your car then the game is over\n"
					+ "You can change the difficulty level and choose your favoourite car from menu\n"
					+ "NOTE: the menu items will only work when the game is paused or game is over.", 
					"Instructions",JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==car1 && (CarPanel.colision || !CarPanel.running)) {
			CarPanel.carNo=1;
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==car2 && (CarPanel.colision || !CarPanel.running)) {
			CarPanel.carNo=2;
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==car3 && (CarPanel.colision || !CarPanel.running)) {
			CarPanel.carNo=3;
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER && (CarPanel.colision || !CarPanel.running)) {
			this.remove(panel);
			panel = new CarPanel();
			this.add(panel);
			panel.addKeyListener(this);
			panel.requestFocusInWindow();
			SwingUtilities.updateComponentTreeUI(this);
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
