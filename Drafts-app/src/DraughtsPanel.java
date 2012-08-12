import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//This class acts as the calling class of Board.class whereby it paints the board and handles the GUI functions.
//takes the Board.class methods to move and corresponds them to a move in the GUI.
public class DraughtsPanel extends JPanel implements MouseListener {
	
	DraftsMain main;
	Board board;
	Color darkBrown = new Color(133,94,66);
	Color lightBrown = new Color(222,184,135);
	public boolean gameInProgess;
	public ArrayList<Board> legalMoves;
	int selectedRow, selectedCol;
	int currentPlayer;
	Computer AI;
	String difficulty;
	public ArrayList<Board> AIlegalMoves;


	public DraughtsPanel() {
		
	}
	public DraughtsPanel(DraftsMain main) {
		
		setBackground(Color.green);
        addMouseListener(this);
        
        board = new Board();
        AI = new Computer(board);
        AI.setBoard(board);
        AI.computerPlay();
        
        this.main = main;
        newGame();
		//repaint();
	}
	
	
	boolean quitGame(){
		int response;
		if(gameInProgess) {
			response = JOptionPane.showConfirmDialog(null, "Do you want to quit? You will forfeit the game.", "QUIT?",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		}
		else {
			return true;
		}
	    if (response == JOptionPane.NO_OPTION) {
	    	main.message.setText("Continue playing...");
	    	repaint();
	    	return false;
	    } else if (response == JOptionPane.YES_OPTION && gameInProgess == true) {
	    	JOptionPane.showMessageDialog(this, "You forfieted the game, so White wins!", "You lose! :(", JOptionPane.INFORMATION_MESSAGE);
	    	gameInProgess = false;
	    	repaint();
	    	return true;
		}else if (response == JOptionPane.CLOSED_OPTION) {
	    	main.message.setText("Continue playing...");
	    	return false;
	    }
		return true;
			
	} //for this make a prompt box saying are they sure, point out the current score if possible.

	public void newGame() {
		board.setPieces(); 

		gameInProgess = true;
		currentPlayer = Board.BLACK;
		legalMoves = board.getLegalMoves(Board.BLACK);
		selectedRow = -1;

		repaint();
	}


	void setDifficulty(String difficulty2) {
		difficulty = difficulty2;
	}
	public void paintComponent(Graphics g) {
		 g.setColor(Color.black);
	     g.drawRect(29,20,getSize().width-79,getSize().height-79);  
		    
        /* Draw the squares of the checkerboard and the checkers. */
	
	     for (int row = 0; row < 8; row++) {
	    	 for (int col = 0; col < 8; col++) {   
	    		 if ( row % 2 == col % 2 ) {
	    			 g.setColor(lightBrown);
	    			 g.fillRect(30+col*80, 21+row*80, 80,80);
	    		 }
	    		 else {
	    			 g.setColor(darkBrown);
	    			 g.fillRect(30+col*80, 21+row*80, 80,80);
	    		 }
	    		 switch(board.pieceIndex(row, col)) {
	    		 	case Board.WHITE:	
	    		 		g.setColor(Color.white);
	    		 		g.fillOval(32 + col*80, 23 + row*80, 75, 75);
	    		 		break;
	    		 	case Board.BLACK:
	    		 		g.setColor(Color.black);
	    		 		g.fillOval(32 + col*80, 23 + row*80, 75, 75);
	    		 		break;	
	    		 	case Board.WHITEKING:
	             		g.setColor(Color.white);
						g.fillOval(32 + col*80, 23 + row*80, 75, 75);
						g.setColor(Color.red);
						g.setFont(new Font("Arial",Font.BOLD,14));
						g.drawString("King", 57 + col*80, 62 + row*80);
						break;
	    		 	case Board.BLACKKING:
	             		g.setColor(Color.black);
						g.fillOval(32 + col*80, 23 + row*80, 75, 75);
						g.setColor(Color.red);
						g.setFont(new Font("Arial",Font.BOLD,14));
						g.drawString("King", 57 + col*80, 62 + row*80);
						break;
					
	    		 }
	    	 }
	     }
        //check to see the difficulty level to show board highlighting or not.
        //Don't need to check if the game is in progress for the highlighting because it always is, and the new game button is disabled.
       if(currentPlayer == Board.BLACK && difficulty !="Expert") {
    	   g.setColor(Color.green);
    	   for(int i = 0; i < legalMoves.size(); i++) {
    		   g.drawRect(30+ legalMoves.get(i).fromCol*80,20+legalMoves.get(i).fromRow*80, 79, 79);
    	   }
    	   if (selectedRow >= 0) {
               g.drawRect(30 + selectedCol*80, 20 + selectedRow*80, 77, 77);
               g.setColor(Color.green);
               for (int i = 0; i < legalMoves.size(); i++) {
                  if (legalMoves.get(i).fromCol == selectedCol && legalMoves.get(i).fromRow == selectedRow) {
                     g.drawRect(30 + legalMoves.get(i).toCol*80, 20 + legalMoves.get(i).toRow*80, 79, 79);
                  }
               }
            }
       }	     
	}//end of paintComponent().
	 

	void move2square(int toRow, int toCol) {
		for (int i = 0; i < legalMoves.size(); i++) {
            if (legalMoves.get(i).fromRow == toRow && legalMoves.get(i).fromCol == toCol) {
               selectedRow = toRow;
               selectedCol = toCol;
               repaint();
               return;
            }
		}
		//it's the human moving.
		if (selectedRow <0) {
	            main.message.setText("Click the piece you want to move.");
	            return;
	    }

		for (int i = 0; i < legalMoves.size(); i++) {
			if (legalMoves.get(i).fromRow == selectedRow && legalMoves.get(i).fromCol == selectedCol
		       && legalMoves.get(i).toRow == toRow && legalMoves.get(i).toCol == toCol) {
				makeMoveBoard(legalMoves.get(i));
				return;
			}
		}
	    repaint();
	}
	 
	
	//make a move on te board.
	void makeMoveBoard(Board move) {
		//has to be a case for teh AI since it calls makeMove itself
        if(currentPlayer == Board.BLACK)
        	board.move(move.fromRow, move.fromCol, move.toRow, move.toCol);

        
        /* Checking whether a move was a jump. If so, keep checking 
         * if there are more available jumps and add them to the legalMoves of the player. */
        /*This applies to both players. */
        if (move.jumpTest()) {
           legalMoves = board.getLegalJumps(currentPlayer,move.toRow,move.toCol);
           if (legalMoves != null) {
              selectedRow = move.toRow;  // Since only one piece can be moved, select it.
              selectedCol = move.toCol;
              //repaint();
              return;
           }
        }      
        /* Switch to the other player now since the current player has taken their move */
        if (currentPlayer == Board.WHITE) {
        	currentPlayer = Board.BLACK;
        	legalMoves = board.getLegalMoves(currentPlayer);
        	if (legalMoves == null) {
        		repaint();
        		JOptionPane.showMessageDialog(this, "Black has no moves, computer wins!", "You lose! :(", JOptionPane.INFORMATION_MESSAGE);
        		gameInProgess = false;
        	}
        }
        else {
        	currentPlayer = Board.WHITE;
        	AI.computerPlay();
        	makeMoveBoard(AI.currentBoard);
        	AIlegalMoves = AI.successors;
        	if(AIlegalMoves == null) {
        		gameInProgess = false;
        	}
        }
        
        /* Set selectedRow = -1 to record that the player has not yet selected
         a piece to move. */
        
        selectedRow = -1;
        
        //Redraw the board with updated events.
        repaint();
        
     }  // end doMakeMove();
     	
	
	public void mousePressed(MouseEvent evt) {
    	int col = (evt.getX() - 20) / 80;
    	int row = (evt.getY() - 20) / 80;
    	if ((col >= 0 && col < 8 && row >= 0 && row < 8)) {
    		move2square(row,col);
    		main.message.setText("Game in progress.");
          //now get the AI to move after the human.
    	}       
	}
		
	 public void mouseReleased(MouseEvent evt) { }
	 public void mouseClicked(MouseEvent evt) { }
	 public void mouseEntered(MouseEvent evt) { }
	 public void mouseExited(MouseEvent evt) { }
        
}//end of DraughtsPanel
