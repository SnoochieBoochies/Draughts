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

public class DraftsMain extends JApplet {
	//public Board board;
	public Checker drafts;
	public Choice choice;
	public Label label;
	//score
	public Jlabel scoreLabel;
	public CheckboxGroup CheckboxGroup;
	public Checkbox redRadio;
	public Checkbox blackRadio;
	public Button newGameButton;
	public Label colorLabel;
	public Button resetButton;
	public Label Title;
	public Image DraftsLogo;
	public Label nameLabel;
	
	
	
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
					scoreLabel = new Jlabel("Score: ", score, JLabel.SOUTH_EAST)
					
				}
			});
		} catch(Exception e) {
			System.err.println("Creating the GUI didn't work in DraftsMain.java");
		}
	}
}
