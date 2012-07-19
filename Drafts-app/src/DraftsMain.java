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
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.lang.*;


public class DraftsMain extends JApplet {
	
	final int MenuBarHeight = 0;
	
	public Draught drafts;
	public Choice choice;
	public JLabel label;
	//score
	public JLabel scoreLabel;
	public CheckboxGroup CheckboxGroup;
	public Checkbox whiteRadio;
	public Checkbox blackRadio;
	public JButton newGameButton;
	public JButton resetButton;
	public JLabel Title;
	public Image DraftsLogo;
	public JLabel nameLabel;
	
	
	
	//Call for when applet is called in the browser.
	//Main setup calls in here.
	public void init() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run () {
					setForeground(Color.magenta);
					setBackground(Color.green);
					setFont(new Font("Comic Sans MS",Font.BOLD, 12));
					setLayout(null);
					drafts = new Draught();
					
					String score = "0.0";
					scoreLabel = new JLabel("Score: " + score, JLabel.RIGHT);
					
					choice = new Choice();
					choice.addItem("Wimp");
				    choice.addItem("Easy");
				    choice.addItem("Normal");
				    choice.addItem("Hard");
				    choice.addItem("Genius");
				    choice.setFont(new Font("Helvetica",Font.BOLD,12));
			        choice.select(2);
			        label = new JLabel("Toughness", JLabel.RIGHT);
			        label.setFont(new Font("Helvetica",Font.BOLD,12));
			        whiteRadio = new Checkbox("white",CheckboxGroup,false);
			        whiteRadio.setBackground(Color.lightGray);
			        whiteRadio.setFont(new Font("Dialog",Font.BOLD,12));
			        blackRadio = new Checkbox("Black",CheckboxGroup,true);
			        blackRadio.setBackground(Color.lightGray);
			        blackRadio.setFont(new Font("Dialog",Font.BOLD,12));
			        newGameButton = new JButton("New Game");
			        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));
			        resetButton = new JButton("Undo");
			        resetButton.setFont(new Font("Dialog",Font.BOLD,12));
			        Title = new JLabel("Java Drafts",JLabel.LEFT);
			        Title.setFont(new Font("Dialog",Font.BOLD + Font.ITALIC,12));
			        //DraftsLogo = getImage(getCodeBase(), "images/bill.gif");
			        nameLabel = new JLabel("by Snoochie Boochies",JLabel.LEFT);
			        nameLabel.setFont(new Font("Dialog",Font.PLAIN,12));
			        
			        
			        
			        
			        
			        add(nameLabel);
			        add(Title);
			        add(resetButton);
			        add(newGameButton);
			        add(blackRadio);
			        add(whiteRadio);
			        add(label);
			        add(choice);
			        add(drafts);
			        
			        
			        initialPositionSet();
				}
			});
		} catch(Exception e) {
			System.err.println("Creating the GUI didn't work in DraftsMain.class");
		}
	}
	//sort the GUI initialisation
	public void initialPositionSet()
    {
        // InitialPositionSet()
        resize(410,505);
        drafts.reshape(5,5+MenuBarHeight,400,400);
        choice.reshape(95,413+MenuBarHeight,106,27);
        label.reshape(2,417+MenuBarHeight,88,19);
        whiteRadio.reshape(95,448+MenuBarHeight,59,16);
        blackRadio.reshape(95,466+MenuBarHeight,67,16);
        newGameButton.reshape(284,465+MenuBarHeight,116,34);
        resetButton.reshape(178,465+MenuBarHeight,99,34);
        
        Title.reshape(212,417+MenuBarHeight,119,19);
        nameLabel.reshape(213,436+MenuBarHeight,99,19);
       
        // End of InitialPositionSet()   
      
    }
	
	//handles events like newGame and colour selection and difficulty selection.
	

	
	void eventHandler(Event e) {
		//Events themselves are defined in the next few methods. It was decided that seperate methods, although more simple code, 
		//would be better for possible code resue and abstraction/readability.
		if(e.id == Event.ACTION_EVENT && e.target == newGameButton) newGameAction();
		else if(e.id == Event.ACTION_EVENT && e.target == resetButton) resetGameAction();

		//difficulty choice.
		else if(e.id == Event.ACTION_EVENT && e.target == choice) difficultyAction(e.target);
		
	}
	
	
	void newGameAction(){
		newGame();
	}
	
	void resetGameAction() {
		resetGame();
        newGameButton.setEnabled(true);
        resetButton.setEnabled(false);
	}
	
	
	void difficultyAction(Object choiceDifficulty){
		Choice choice = (Choice)choiceDifficulty;
		int selectedChoice = choice.getSelectedIndex();
		//this gets the index of which of the 5 selections has been chosen.
		setDifficulty(selectedChoice);
	}
	
	
	//STUFF FROM BOARD.JAVA
	//there was no reason for a board.java. it just made things more messy. Although i see the point to it, it's fucking me over atm.
	Draught draughtBoard = new Draught();
	public int rowPos;
	public int colPos;
	int currentPlayer;
	boolean gameInProgess;
	DraughtsMove [] legalMoves;
	Label message;

	//there is the possibility in this method when choosing color to play as, flip where the draughts start. would probably make life alot easier.
	//STOPPED HERE ON WEDNESDAY.
	void newGame(){
		
	      draughtBoard.placeCheckers();   // Set up the pieces.
	      currentPlayer = Draught.BLACK;   // RED moves first.
	      legalMoves = draughtBoard.getLegalMoves(Draught.WHITE);  // Get RED's legal moves.
	      rowPos = -1;   // RED has not yet selected a piece to move.
	      message.setText("White:  Make your move.");
	      gameInProgess = true;
	      repaint();
		};
		
	void resetGame(){
		Object[] options = {"Yes, please",
	                "No, thanks",
	                "No eggs, no ham!"};
		if(gameInProgess == true)  JOptionPane.showOptionDialog(null,"Are you sure, there is a game in progress!!", "Quit?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
			
		message.setText("You forfieted the game, so Blacks win!");
	    gameInProgess = false;
	}; //for this make a prompt box saying are they sure, point out the current score if possible.
		
		
	//set the difficulty according to the user's choice out of 5 selections.
	int diffChoice;
	void setDifficulty(int diffChoice){
		diffChoice = this.diffChoice;
	}
	
	
	//END OF INIT STUFF AND BUTTON DECLARATIONS.
	
	
	
}
