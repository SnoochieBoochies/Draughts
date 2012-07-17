//not an actual main, it just calls the actual logic classes and sets up the applet GUI.
import javax.swing.JApplet;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.lang.*;
import Board;

public class Draughts extends JApplet {
	final int MenuBarHeight = 0;
	
	public Checker drafts;
	public Choice choice;
	public JLabel label;
	//score
	public Jlabel scoreLabel;
	public CheckboxGroup CheckboxGroup;
	public Checkbox redRadio;
	public Checkbox blackRadio;
	public JButton newGameButton;
	public Label colorLabel;
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
					drafts = new Checker(this);
					
					String score;
					scoreLabel = new Jlabel("Score: ", score, JLabel.SOUTH_EAST);
					
					choice = new Choice();
					choice.addItem("Wimp");
				    choice.addItem("Easy");
				    choice.addItem("Normal");
				    choice.addItem("Hard");
				    choice.addItem("Genius");
				    choice.setFont(new Font("Helvetica",Font.BOLD,12));
			        choice.select(2);
			        label = new JLabel("Toughness",JLabel.RIGHT);
			        label.setFont(new Font("Helvetica",Font.BOLD,12));
			        redRadio = new Checkbox("Red",CheckboxGrpInDrafts,false);
			        redRadio.setBackground(Color.lightGray);
			        redRadio.setFont(new Font("Dialog",Font.BOLD,12));
			        blackRadio = new Checkbox("Black",CheckboxGrpInDrafts,true);
			        blackRadio.setBackground(Color.lightGray);
			        blackRadio.setFont(new Font("Dialog",Font.BOLD,12));
			        newGameButton = new JButton("New Game");
			        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));
			        colorLabel = new JLabel("Color",JLabel.RIGHT);
			        colorLabel.setFont(new Font("Dialog",Font.BOLD,12));
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
			        add(colorLabel);
			        add(newGameButton);
			        add(blackRadio);
			        add(redRadio);
			        add(label);
			        add(choice);
			        add(drafts);
			        
			        
			        InitialPositionSet();
				}
			});
		} catch(Exception e) {
			System.err.println("Creating the GUI didn't work in DraftsMain.class");
		}
	}
	public void InitialPositionSet()
    {
        // InitialPositionSet()
        resize(410,505);
        drafts.reshape(5,5+MenuBarHeight,400,400);
        choice.reshape(95,413+MenuBarHeight,106,27);
        label.reshape(2,417+MenuBarHeight,88,19);
        redRadio.reshape(95,448+MenuBarHeight,59,16);
        blackRadio.reshape(95,466+MenuBarHeight,67,16);
        newGameButton.reshape(284,465+MenuBarHeight,116,34);
        colorLabel.reshape(46,453+MenuBarHeight,43,19);
        resetButton.reshape(178,465+MenuBarHeight,99,34);
        
        Title.reshape(212,417+MenuBarHeight,119,19);
        nameLabel.reshape(213,436+MenuBarHeight,99,19);
       // infoButton.reshape(6,477+MenuBarHeight,76,22);
        // End of InitialPositionSet()
        
        //DraftsInfo.show();
        //DraftsInfo.InitialPositionSet();
    }
}
