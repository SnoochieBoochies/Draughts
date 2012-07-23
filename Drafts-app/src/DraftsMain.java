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


public class DraftsMain extends JApplet {
	
	final int MenuBarHeight = 0;
	
	public Board drafts;
	//MovingAdapter ma = new MovingAdapter();
	public Choice choice;
	public JLabel difficultyLabel;
	public CheckboxGroup CheckboxGroup;
	public JButton newGameButton;
	public JButton resetButton;
	public JLabel Title;
	public JLabel playerLabel;
	public JLabel AILabel;
	public Graphics g;
	public Image blackPiece;
	
	
	//Call for when applet is called in the browser.
	//Main setup calls in here.
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run () {
					setForeground(Color.green);
					setBackground(Color.green);
					setFont(new Font("Comic Sans MS",Font.BOLD, 12));
					setLayout(null);
					drafts = new Board();
					
					
					
					choice = new Choice();
				    choice.addItem("Easy");
				    choice.addItem("Normal");
				    choice.addItem("Hard");
				    choice.setFont(new Font("Helvetica",Font.BOLD,12));
			        choice.select(1);
			        difficultyLabel = new JLabel("Difficulty level: ", JLabel.RIGHT);
			        difficultyLabel.setFont(new Font("Helvetica",Font.BOLD,12));
			        newGameButton = new JButton("New Game");
			        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));
			        resetButton = new JButton("Reset Game");
			        resetButton.setFont(new Font("Dialog",Font.BOLD,12));
			        Title = new JLabel("<html><u>Draughts in Java!</u></html>",JLabel.LEFT);
			        Title.setFont(new Font("Dialog",Font.BOLD,12));
			  

			        playerLabel = new JLabel("Player Colour = BLACK", JLabel.RIGHT);


			        AILabel = new JLabel("Computer Colour = WHITE", JLabel.RIGHT);
			      
			       // addMouseMotionListener(ma);
				 //   addMouseListener(ma);
			        
			        
			       
			        add(Title);
			        add(resetButton);
			        add(newGameButton);
			        add(difficultyLabel);
			        add(choice);
			        add(playerLabel);
			        add(AILabel);
			        add(drafts);

			        
			        
			        initialPositionSet();
				}
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//sort the GUI initialisation
	public void initialPositionSet()
    {
        // InitialPositionSet()
        setSize(810,805);
        drafts.setBounds(45,25,720,720);
        
        choice.setBounds(670,720,106,27);
        difficultyLabel.setBounds(580,720,88,19);
        
        newGameButton.setBounds(280,720,116,54);
        resetButton.setBounds(406,720,116,54);
        
        //player + AI labels to show the user who's who. Need a pic to go along once a pic of a piece is made.
        playerLabel.setBounds(0, 720, 136, 29);
        AILabel.setBounds(-1, 760, 156, 29);
        //pieces for this are made in paint();
        Title.setBounds(350,-5,119,19);

       
        // End of InitialPositionSet()   
      
    }
	
	//handles events like newGame and colour selection and difficulty selection.
	

	
	public void handleEvent(ActionEvent e) {
		//Events themselves are defined in the next few methods. It was decided that seperate methods, although more simple code, 
		//would be better for possible code resue and abstraction/readability.
		/*
		if(e.id == Event.ACTION_EVENT && e.target == newGameButton) newGameAction();
		else if(e.id == Event.ACTION_EVENT && e.target == resetButton) resetGameAction();

		//difficulty choice.
		else if(e.id == Event.ACTION_EVENT && e.target == choice) difficultyAction(e.target);
		
		return super.handleEvent(e);
		*/
		
		Object src = e.getSource();
		if(src == newGameButton){
			//drafts.newGame();
			newGameButton.setEnabled(false);
			resetButton.setEnabled(true);
		}
		else if(src == resetButton){
			//drafts.resetGame();
			newGameButton.setEnabled(true);
			resetButton.setEnabled(false);
		}
		else if(src == choice) difficultyAction(src);
		repaint();
	}

	/*
	void newGameAction(){
		drafts.newGame();
	}
	
	void resetGameAction() {
		drafts.resetGame();
        newGameButton.setEnabled(true);
        resetButton.setEnabled(false);
	}
	*/
	
	void difficultyAction(Object choiceDifficulty){
		Choice choice = (Choice)choiceDifficulty;
		int selectedChoice = choice.getSelectedIndex();
		//this gets the index of which of the 5 selections has been chosen.
		//drafts.setDifficulty(selectedChoice);
	}
	
	
	
	//END OF INIT STUFF AND BUTTON DECLARATIONS.
	/*
	public boolean mouseDown(Event e, int x, int y){
		Graphics g = getGraphics();
		
		for(x = 0; x < 8; x++) {
			for(y= 0; y< 8; y++) {
				
			}
		}
		return rootPaneCheckingEnabled;
		
	}
	*/
	/*
	class MovingAdapter extends MouseAdapter {
		int preX, preY;
		public void mousePressed(MouseEvent e) {
			preX = e.getX();
			preY = e.getY();
		}
		public void mouseDragged(MouseEvent e) {
			int dx = e.getX() - preX;
			int dy = e.getY() - preY;
			
			preX+=dx;
			preY+=dy;
			repaint();
		}
	}
	*/
	
}
