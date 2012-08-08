import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



//This class acts as the calling class of Board.class whereby it paints the board and handles the GUI functions.
//takes the Board.class methods to move and corresponds them to a move in the GUI.
public class DraughtsPanel extends JPanel implements MouseListener{
	
	DraftsMain main;
	Board board;
	
	JLabel message;
	Color darkBrown = new Color(133,94,66);
	Color lightBrown = new Color(222,184,135);
	public boolean gameInProgess;
	public ArrayList<Board> legalMoves;
	public ArrayList<Board> AIlegalMoves;
	int selectedRow, selectedCol;
	int currentPlayer;
	Computer AI;



													
	public DraughtsPanel() {
		
		setBackground(Color.green);
        addMouseListener(this);
        
        board = new Board();
        AI = new Computer(board);
        AI.setBoard(board);
        //AI.computerPlay();
        newGame();
		 System.out.println("new game started");
	}
	
	void quitGame(){
		//JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, "Do you want to quit? You will forfeit the game.", "QUIT?",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      System.out.println("No button clicked");
		    } else if (response == JOptionPane.YES_OPTION) {
		      System.out.println("Yes button clicked");
		      System.out.println("You forfieted the game, so White wins!");
		      gameInProgess = false;
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      System.out.println("JOptionPane closed");
		    }
			
	} //for this make a prompt box saying are they sure, point out the current score if possible.

	public void newGame() {
		board.setPieces(); 

		gameInProgess = true;
		currentPlayer = Board.BLACK;
		legalMoves = board.getLegalMoves(Board.BLACK);
		AIlegalMoves = AI.successors;
		selectedRow = -1;
		System.out.println("Black pieces move first!");
		
		//AI.computerPlay();

	     
		
		
		repaint();
	}

	String difficulty;
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
		     
		             
		            	 switch(board.pieceAt(row, col)) {
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
								g.setFont(new Font("Dialog",Font.BOLD,14));
								g.drawString("King", 57 + col*80, 42 + row*80);
								break;
			             	case Board.BLACKKING:
			             		g.setColor(Color.black);
								g.fillOval(32 + col*80, 23 + row*80, 75, 75);
								g.setColor(Color.red);
								g.setFont(new Font("Dialog",Font.BOLD,14));
								g.drawString("King", 57 + col*80, 42 + row*80);
								break;
								
			             }
		             }
		        }
		        //check to see the difficulty level to show board highlighting or not.
		           if(difficulty !="Expert") {
		        	   g.setColor(Color.green);
		        	   for(int i = 0; i < legalMoves.size(); i++) {
		        		   g.drawRect(legalMoves.get(i).fromRow * 80,legalMoves.get(i).fromCol * 80, 79, 79);
		        	   }
		           }

		        
		     
	}
	 
	
	/*END OF FIRST SECTION. NOW RULES AND SCORE ARE DEFINED
	 * 
	 */


	void move2square(int toRow, int toCol) {
		System.out.println("move2Square");
		for (int i = 0; i < legalMoves.size(); i++)
            if (legalMoves.get(i).fromRow == toRow && legalMoves.get(i).fromCol == toCol) {
               selectedRow = toRow;
               selectedCol = toCol;
               if (currentPlayer == Board.BLACK)
                  System.out.println("BLACK:  Make your move.");
               else {
                 System.out.println("COMPUTER:  Make your move.");
               }
               repaint();
               return;
            }
		
		//it's the human moving.
		 //if (selectedRow <0) {
	     //       System.out.println("Click the piece you want to move.");
	     //       return;
	     //    }
	         /* If the user clicked on a square where the selected piece can be
	          legally moved, then make the move and return. */
	         //if(currentPlayer == Board.BLACK) {
		         for (int i = 0; i < legalMoves.size(); i++)
		            if (legalMoves.get(i).fromRow == selectedRow && legalMoves.get(i).fromCol == selectedCol
		                  && legalMoves.get(i).toRow == toRow && legalMoves.get(i).toCol == toCol) {
		               doMakeMove(legalMoves.get(i));
		               return;
		            }

	         //}
	        
	        // else {
	        	 System.out.println("AI's turn");
	        	// AImove2square();
	        	//Board moves;
	        	//AI.minimax(board);

	        // }

	         

		     
	         //else call the method for the AI to move to a square.
	         repaint();
	}
	
	void AImove2square() {
		System.out.println("AI moving to a square");
		AI.computerPlay();
		doMakeMove(AI.currentBoard);

		/*
		AI.minimax(board);
		for (int i = 0; i < AIlegalMoves.size(); i++)
            if (AIlegalMoves.get(i).fromRow == toRow && AIlegalMoves.get(i).fromCol == toCol) {
               selectedRow = toRow;
               selectedCol = toCol;
               if (currentPlayer == Board.BLACK)
                  System.out.println("BLACK:  Make your move.");
               else {
                 System.out.println("COMPUTER:  Make your move.");
               }
               
               repaint();
               return;
            }
		*/
		//it's the human moving.
		 if (currentPlayer == Board.WHITE) {
	            System.out.println("AI!!!!!!!!!!!!!!!!!1");
	            return;
	         }
	         /* If the user clicked on a square where the selected piece can be
	          legally moved, then make the move and return. */
		 /*
	         //if(currentPlayer == Board.BLACK) {
		         for (int i = 0; i < AIlegalMoves.size(); i++)
		            if (AIlegalMoves.get(i).fromRow == selectedRow && AIlegalMoves.get(i).fromCol == selectedCol
		                  && AIlegalMoves.get(i).toRow == toRow && AIlegalMoves.get(i).toCol == toCol) {
		               doMakeMove(AIlegalMoves.get(i));
		               return;
		            }
		            */
		//board.makeMove(board.fromRow, board.fromCol, board.toRow, board.toCol);
		/*
		for (int i = 0; i < legalMoves.size(); i++)
            if (legalMoves.get(i).fromRow == toRow && legalMoves.get(i).fromCol == toCol) {
               selectedRow = toRow;
               selectedCol = toCol;
               //repaint();
               return;
            }
		for (int i = 0; i < legalMoves.size(); i++)
            if (legalMoves.get(i).fromRow == selectedRow && legalMoves.get(i).fromCol == selectedCol
                  && legalMoves.get(i).toRow == toRow && legalMoves.get(i).toCol == toCol) {
               doMakeMove(legalMoves.get(i));
               return;
            }
            
        */

	}



		 
	void doMakeMove(Board move) {
		//has to be a case for teh AI since it calls makeMove itself

        //if(currentPlayer == Board.BLACK)

        if(currentPlayer == Board.BLACK)

        	board.makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);

        
        /* If the move was a jump, it's possible that the player has another
         jump.  Check for legal jumps starting from the square that the player
         just moved to.  If there are any, the player must jump.  The same
         player continues moving.
         */
        
        if (move.isJump()) {
           legalMoves = board.getLegalJumpsFrom(currentPlayer,move.toRow,move.toCol);
           if (legalMoves != null) {
              if (currentPlayer == Board.WHITE)
           	   System.out.println("RED:  You must continue jumping.");
              else
                 System.out.println("BLACK:  You must continue jumping.");
              selectedRow = move.toRow;  // Since only one piece can be moved, select it.
              selectedCol = move.toCol;
              repaint();
              return;
           }
        }
        
        /* The current player's turn is ended, so change to the other player.
         Get that player's legal moves.  If the player has no legal moves,
         then the game ends. */
        
        if (currentPlayer == Board.WHITE) {
           currentPlayer = Board.BLACK;



           //AI.computerPlay();
          // AI.minimax(board);

           legalMoves = board.getLegalMoves(currentPlayer);
           if (legalMoves == null)
              //gameOver("BLACK has no moves.  RED wins.");
           	System.out.println("Black has no moves, white wins¬¬");
           if (legalMoves.get(0).isJump())
              //message.setText("BLACK:  Make your move.  You must jump.");
           	System.out.println("");
           else
              System.out.println("BLACK:  Make your move.");
        }
        else {
           currentPlayer = Board.WHITE;

           legalMoves = board.getLegalMoves(currentPlayer);
          // AI.computerPlay();
    		//doMakeMove(AI.currentBoard);

           //AIlegalMoves = board.getLegalMoves(currentPlayer);
           AI.computerPlay();
    		doMakeMove(AI.currentBoard);

           if (legalMoves == null)
              //gameOver("RED has no moves.  BLACK wins.");
           	System.out.println("White has no moves, black wins");
           else if (legalMoves.get(0).isJump())
             System.out.println("RED:  Make your move.  You must jump.");
           else
             System.out.println("RED:  Make your move.");
        }
        
        /* Set selectedRow = -1 to record that the player has not yet selected
         a piece to move. */
        
        selectedRow = -1;
        
       
        
        /* Make sure the board is redrawn in its new state. */
        
        repaint();
        
     }  // end doMakeMove();
     	
	
	public void mousePressed(MouseEvent evt) {
        if (gameInProgess == false)
           //message.setText("Click \"New Game\" to start a new game.");
       	 System.out.println("Click new game to start new game");
        else {
           int col = (evt.getX() - 20) / 80;
           int row = (evt.getY() - 20) / 80;
           if ((col >= 0 && col < 8 && row >= 0 && row < 8)) {
              move2square(row,col);
              System.out.println("Test " + row + ", " + col);
              //now get the AI to move after the human.

              
           }
          // else {
        	  // AImove2square(row,col);
         // }
        }
        
	}
		
	 public void mouseReleased(MouseEvent evt) { }
     public void mouseClicked(MouseEvent evt) { }
     public void mouseEntered(MouseEvent evt) { }
     public void mouseExited(MouseEvent evt) { 

     }
   
     
     
}
