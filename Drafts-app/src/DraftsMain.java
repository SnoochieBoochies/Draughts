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
					//setBackground(Color.green);
					draughtsPanel = new DraughtsPanel();
					
					
			        newGameButton = new JButton("New Game");
			        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));
			        newGameButton.addActionListener(new ActionListener() {
			        	 
			            public void actionPerformed(ActionEvent e)
			            {
			                //Execute when button is pressed
			            	draughtsPanel.newGame();
			            	newGameButton.setEnabled(false);
			            	quitButton.setEnabled(true);
			            	repaint();
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
	

	//END OF INIT STUFF AND BUTTON DECLARATIONS.
	
	
}
