//not an actual main, it just calls the actual logic classes and sets up the applet GUI.
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.*;


public class DraftsMain extends JApplet implements ActionListener {
	
	final int MenuBarHeight = 0;
	

	public DraughtsPanel draughtsPanel; 
	public Choice choice;
	public JLabel difficultyLabel;
	public CheckboxGroup CheckboxGroup;
	public JButton newGameButton;
	public JButton quitButton;
	public JLabel Title;
	public JLabel playerLabel;
	public JLabel AILabel;
	
	
	//Call for when applet is called in the browser.
	//Main setup calls in here.
	public void init() {
		
					/*
					setForeground(Color.green);
					setBackground(Color.green);
					setFont(new Font("Comic Sans MS",Font.BOLD, 12));
					*/
					setLayout(null);
					setBackground(Color.green);
					draughtsPanel = new DraughtsPanel();
					
					
					choice = new Choice();
				    choice.addItem("Beginner");
				    choice.addItem("Normal");
				    choice.addItem("1337");
				    choice.setFont(new Font("Helvetica",Font.BOLD,12));
			        choice.select(1);
			        difficultyLabel = new JLabel("Difficulty level: ", JLabel.RIGHT);
			        difficultyLabel.setFont(new Font("Helvetica",Font.BOLD,12));
			        newGameButton = new JButton("New Game");
			        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));
			        newGameButton.addActionListener(new ActionListener() {
			        	 
			            public void actionPerformed(ActionEvent e)
			            {
			                //Execute when button is pressed
			            	draughtsPanel.newGame();
			            	newGameButton.setEnabled(false);
			            	quitButton.setEnabled(true);
			            }
			        });
			        quitButton = new JButton("Quit Game");
			        quitButton.setFont(new Font("Dialog",Font.BOLD,12));
			        quitButton.addActionListener(new ActionListener() {
			        	 
			            public void actionPerformed(ActionEvent e)
			            {
			                //Execute when button is pressed
			            	draughtsPanel.quitGame();
			            	newGameButton.setEnabled(true);
			            	quitButton.setEnabled(false);
			            }
			        });
			        Title = new JLabel("<html><u>Draughts in Java!</u></html>",JLabel.LEFT);
			        Title.setFont(new Font("Dialog",Font.BOLD,12));
			  

			        playerLabel = new JLabel("Player Colour = BLACK", JLabel.RIGHT);


			        AILabel = new JLabel("Computer Colour = WHITE", JLabel.RIGHT);
			      
			      
			        
			        
			       
			        add(Title);
			        add(draughtsPanel);
			        add(quitButton);
			        add(newGameButton);
			        add(difficultyLabel);
			        add(choice);
			        add(playerLabel);
			        add(AILabel);


			        
			        
			        initialPositionSet();
			        repaint();
			
			
		
		
	}
	//sort the GUI initialisation
	public void initialPositionSet()
    {
        // InitialPositionSet()

        setSize(719,805);
        //board.setBounds(45,25,720,720);
        draughtsPanel.setBounds(0,0,720,720);

        choice.setBounds(670,720,106,27);
        difficultyLabel.setBounds(580,720,88,19);
        
        newGameButton.setBounds(280,720,116,54);
        quitButton.setBounds(406,720,116,54);
        
        //player + AI labels to show the user who's who. Need a pic to go along once a pic of a piece is made.
        playerLabel.setBounds(0, 720, 136, 29);
        AILabel.setBounds(-1, 760, 156, 29);
        //pieces for this are made in paint();
        Title.setBounds(350,-5,119,19);

       
        // End of InitialPositionSet()   
      
    }

	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	/*
	void difficultyAction(Object choiceDifficulty){
		Choice choice = (Choice)choiceDifficulty;
		int selectedChoice = choice.getSelectedIndex();
		//this gets the index of which of the 5 selections has been chosen.
		board.setDifficulty(selectedChoice);
	}
	*/
	
	//END OF INIT STUFF AND BUTTON DECLARATIONS.
	
	
}
