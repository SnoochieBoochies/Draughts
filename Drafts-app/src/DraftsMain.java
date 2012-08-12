//not an actual main, it just calls the actual logic classes and sets up the applet GUI.
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class DraftsMain extends JApplet implements ActionListener, ItemListener {
	
	public DraughtsPanel draughtsPanel;
	public JButton newGameButton;
	private Choice choice;
	private JLabel difficultyLabel;
	public JButton quitButton;
	public JLabel Title;
	public JLabel playerLabel;
	public JLabel AILabel;
	public JLabel message;
	DraftsMain main;

	//Call for when applet is called in the browser.
	//Main setup calls in here.
	public void init() {
		try {
			DraftsMain main;
			main = this;
			draughtsPanel = new DraughtsPanel(main);
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        	public void run() {
					setLayout(null);
			        newGameButton = new JButton("New Game");
			        newGameButton.setFont(new Font("Dialog",Font.BOLD,14));
			        newGameButton.setEnabled(false);
			        newGameButton.addActionListener(new ActionListener() {
			        	@Override
			            public void actionPerformed(ActionEvent e)
			            {
			                //Execute when button is pressed
			            	draughtsPanel.newGame();
			            	newGameButton.setEnabled(false);
			            	quitButton.setEnabled(true);
			            	//repaint();
			            }
			        });
			        
			        quitButton = new JButton("Quit Game");
			        quitButton.setFont(new Font("Dialog",Font.BOLD,12));
			        quitButton.addActionListener(new ActionListener() {
			        	@Override
			            public void actionPerformed(ActionEvent e)
			            {
			                //Execute when button is pressed
			            	if(draughtsPanel.quitGame()) {
			            		newGameButton.setEnabled(true);
			            		quitButton.setEnabled(false);
			            	}
			            	else {
			            		return;
			            	}
			            }
			        });
			        
			        Title = new JLabel("<html><u>Draughts in Java!</u></html>",JLabel.LEFT);
			        Title.setFont(new Font("Dialog",Font.BOLD,12));
			        playerLabel = new JLabel("Player Colour = BLACK", JLabel.RIGHT);
			        AILabel = new JLabel("Computer Colour = WHITE", JLabel.RIGHT);
			        choice = new Choice();
			        choice.addItem("Beginner");
			        choice.addItem("Expert");
			        choice.select(0);
			        choice.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent e) {
							// TODO Auto-generated method stub
							String result = choice.getSelectedItem();
							if(result == "Expert")
								draughtsPanel.setDifficulty(result);
							else
								draughtsPanel.setDifficulty(result);
						}
					});

			        difficultyLabel = new JLabel("Difficulty level: ", JLabel.RIGHT);
			        message = new JLabel("Black pieces move first!",JLabel.RIGHT);
			        
			        add(Title);
			        add(draughtsPanel);
			        add(quitButton);
			        add(newGameButton);
			        add(playerLabel);
			        add(AILabel);
			        add(difficultyLabel);
			        add(choice);
			        add(message);

			        initialPositionSet();
			        repaint();
			
	        	}
	        });
		}
		catch(Exception e) {
			System.out.println("Exception");
		}
		
	}
	//sort the GUI initialisation
	public void initialPositionSet()
    {
        setSize(730,805);
        draughtsPanel.setBounds(0,0,720,720);
        newGameButton.setBounds(280,720,116,54);
        quitButton.setBounds(406,720,116,54);
        //player + AI labels to show the user who's who.
        playerLabel.setBounds(0, 720, 136, 29);
        AILabel.setBounds(-1, 760, 156, 29);
        //pieces for this are made in paint();
        Title.setBounds(350,-5,119,19);
        choice.setBounds(620,720,106,27);
        difficultyLabel.setBounds(530,720,88,19);
        message.setBounds(306, 660, 136, 19);
        // End of InitialPositionSet()   
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	//END OF INIT STUFF AND BUTTON DECLARATIONS.

}
