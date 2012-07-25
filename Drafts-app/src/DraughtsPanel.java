import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;








public class DraughtsPanel extends JPanel{
	
	DraftsMain main;
	Board board;
	
	JLabel message;
	int diffChoice;
	public Image blackPiece;
	Graphics g;
	Color darkBrown = new Color(133,94,66);
	Color lightBrown = new Color(222,184,135);

	/* METHODS. namely reset and newGame, and paint!!
	 * 
	 */
	void resetGame(){
		Object[] options = {"Yes, please",
	                "No, thanks",
	                "No eggs, no ham!"};
		//if(gameInProgess == true)  
			JOptionPane.showOptionDialog(null,"Are you sure, there is a game in progress!!", "Quit?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
			
		message.setText("You forfieted the game, so White wins!");
	    board.gameInProgress = false;
	} //for this make a prompt box saying are they sure, point out the current score if possible.
	
	public void newGame() {
		 for (int row = 0; row < 8; row++) {
	            for (int col = 0; col < 8; col++) {
	               if ( row % 2 == col % 2 ) {
	                  if (row < 3)
	                     board.board[row][col] = board.BLACK;
	                  else if (row > 4)
	                     board.board[row][col] = board.WHITE;
	                  else
	                     board.board[row][col] = board.OFB;
	               }
	               else {
	                  board.board[row][col] = board.OFB;
	               }
	            }
	         }
		repaint();
		board.gameInProgress = true;
		System.out.println("Black pieces move first!");
		
	}
	
	void setDifficulty(int diffChoice){
		diffChoice = this.diffChoice;
	}

	private static class CheckersMove {
	      int fromRow, fromCol;  // Position of piece to be moved.
	      int toRow, toCol;      // Square it is to move to.
	      CheckersMove(int r1, int c1, int r2, int c2) {
	              // Constructor.  Just set the values of the instance variables.
	         fromRow = r1;
	         fromCol = c1;
	         toRow = r2;
	         toCol = c2;
	      }
	      boolean isJump() {
	             // Test whether this move is a jump.  It is assumed that
	             // the move is legal.  In a jump, the piece moves two
	             // rows.  (In a regular move, it only moves one row.)
	         return (fromRow - toRow == 2 || fromRow - toRow == -2);
	      }
	   }  // end class CheckersMove.
	CheckersMove[] legalMoves;
	int selectedRow, selectedCol;
	int currentPlayer;
	void move2square(int toRow, int toCol) {
		for (int i = 0; i < legalMoves.length; i++)
            if (legalMoves[i].fromRow == toRow && legalMoves[i].fromCol == toCol) {
               selectedRow = toRow;
               selectedCol = toCol;
               if (currentPlayer == board.BLACK)
                  message.setText("BLACK:  Make your move.");
               else
                  message.setText("COMPUTER:  Make your move.");
               repaint();
               return;
            }
		
		
		 if (selectedRow < 0) {
	            message.setText("Click the piece you want to move.");
	            return;
	         }
	         
	         /* If the user clicked on a square where the selected piece can be
	          legally moved, then make the move and return. */
	         
	         for (int i = 0; i < legalMoves.length; i++)
	            if (legalMoves[i].fromRow == selectedRow && legalMoves[i].fromCol == selectedCol
	                  && legalMoves[i].toRow == toRow && legalMoves[i].toCol == toCol) {
	               doMakeMove(legalMoves[i]);
	               return;
	            }
	}
	
	
	
	
	/*END OF FIRST SECTION. NOW RULES AND SCORE ARE DEFINED
	 * 
	 */
	   
	/*score
	 * 
	 * 
	 */
	
	
	/*rules
	 * 
	 * 
	 * 
	 */
	/*
	//a boolean to tell if a piece can jump or not.
	//must check whether the piece that wants to jump can land inside the board.
	boolean canJump(int initRow, int initCol, int interRow, int interCol, int destRow, int destCol3) {
		//check to see if the place it wants to jump is a valid position.
		if(destRow <0 || destRow >=8 || destCol3 < 0 || destCol3 >=8)
			return false;
		
		//check to see if the place it wants to move is already occupied.
		if(board[destRow][destCol3] != OFB)
			return false;
		//if not, then it is free. Move there
		
		
		return true;
	}
	
	
	private int currentX = 0,  currentY = 0,  offsetX = 0,  offsetY = 0;
	class CustomMouseAdapter extends MouseAdapter {
		 
	    public void mousePressed(MouseEvent e) {
	    	offsetX = e.getX() - currentX ;
	    	offsetY= e.getY() - currentY ;
	    }
	 
	    public void mouseDragged(MouseEvent e) {
	      currentX = e.getX() - offsetX;
	      currentY = e.getY() - offsetY;
	 
	      //currentX = e.getX();
	      //currentY = e.getY();
	      repaint();
	    }
	  }
	*/
	

		 
		  
	
}

