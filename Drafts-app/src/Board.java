import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;


//class that holds the notion of a board and draughts pieces(as integers).
//also does the moves/rules.
public class Board extends Component {
	static final int WHITE = 1, BLACK = 3, WHITEKING = 2, BLACKKING = 4, OFB = -1;
	int [][] board;
	
	Color darkBrown = new Color(133,94,66);
	Color lightBrown = new Color(222,184,135);
	public int row =0,col =0;
	DraughtsPanel derp;
	Board () {
		board = new int[8][8];
		
		setPieces();
	}
	Board(int r1, int c1, int r2, int c2) {
        // Constructor.  Just set the values of the instance variables.
	   fromRow = r1;
	   fromCol = c1;
	   toRow = r2;
	   toCol = c2;
	}
	
	
	void setPieces() {
		for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
               if ( row % 2 != col % 2 ) {
                  if (row < 3) {
                     board[row][col] = WHITE;
                  //System.out.println(board[row][col]);
                  }
                  else if (row > 4) {
                     board[row][col] = BLACK;
                 // System.out.println(board[row][col]);
                  }
                  else
                     board[row][col] = OFB;
               }
               else {
                  board[row][col] = OFB;
                 // System.out.println(board[row][col]);
               }
            }
         }
		
	}
	int pieceAt(int row, int col) {
        return board[row][col];
     }

  int fromRow, fromCol;  // Position of piece to be moved.
  int toRow, toCol;      // Square it is to move to.
  boolean isJump() {
         // Test whether this move is a jump.  It is assumed that
         // the move is legal.  In a jump, the piece moves two
         // rows.  (In a regular move, it only moves one row.)
     return (fromRow - toRow == 2 || fromRow - toRow == -2);
  }
  /**
   * Make the move from (fromRow,fromCol) to (toRow,toCol).  It is
   * assumed that this move is legal.  If the move is a jump, the
   * jumped piece is removed from the board.  If a piece moves
   * the last row on the opponent's side of the board, the 
   * piece becomes a king.
   */
  void makeMove(int fromRow, int fromCol, int toRow, int toCol) {
     board[toRow][toCol] = board[fromRow][fromCol];
     board[fromRow][fromCol] = OFB;
     if (fromRow - toRow == 2 || fromRow - toRow == -2) {
        // The move is a jump.  Remove the jumped piece from the board.
        int jumpRow = (fromRow + toRow) / 2;  // Row of the jumped piece.
        int jumpCol = (fromCol + toCol) / 2;  // Column of the jumped piece.
        board[jumpRow][jumpCol] = OFB;
     }
     if (toRow == 0 && board[toRow][toCol] == BLACK)
        board[toRow][toCol] = BLACKKING;
     if (toRow == 7 && board[toRow][toCol] == WHITE)
        board[toRow][toCol] = WHITEKING;
  }
  
  ArrayList<Board> getLegalMoves(int player) {
      
      if (player != WHITE && player != BLACK)
         return null;
      
      int playerKing;  // The constant representing a King belonging to player.
      if (player == WHITE)
         playerKing = WHITEKING;
      else
         playerKing = BLACKKING;
      
      ArrayList<Board> moves = new ArrayList<Board>();  // Moves will be stored in this list.
      
      /*  First, check for any possible jumps.  Look at each square on the board.
       If that square contains one of the player's pieces, look at a possible
       jump in each of the four directions from that square.  If there is 
       a legal jump in that direction, put it in the moves ArrayList.
       */
      
      for (int row = 0; row < 8; row++) {
         for (int col = 0; col < 8; col++) {
            if (board[row][col] == player || board[row][col] == playerKing) {
               if (canJump(player, row, col, row+1, col+1, row+2, col+2))
                  moves.add(new Board(row, col, row+2, col+2));
               if (canJump(player, row, col, row-1, col+1, row-2, col+2))
                  moves.add(new Board(row, col, row-2, col+2));
               if (canJump(player, row, col, row+1, col-1, row+2, col-2))
                  moves.add(new Board(row, col, row+2, col-2));
               if (canJump(player, row, col, row-1, col-1, row-2, col-2))
                  moves.add(new Board(row, col, row-2, col-2));
            }
         }
      }
      
      /* check for
       any legal regular moves.  Look at each square on the board.
       If that square contains one of the player's pieces, look at a possible
       move in each of the four directions from that square.  If there is 
       a legal move in that direction, put it in the moves ArrayList.
       */
      if(moves.size() == 0) {
         for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
               if (board[row][col] == player || board[row][col] == playerKing) {
                  if (canMove(player,row,col,row+1,col+1))
                     moves.add(new Board(row, col, row+1,col+1));
                  if (canMove(player,row,col,row-1,col+1))
                     moves.add(new Board(row, col, row-1,col+1));
                  if (canMove(player,row,col,row+1,col-1))
                     moves.add(new Board(row, col, row+1,col-1));
                  if (canMove(player,row,col,row-1,col-1))
                     moves.add(new Board(row, col, row-1,col-1));
               }
            }
         }
      }
      
      /* If no legal moves have been found, return null.  Otherwise, create
       an array just big enough to hold all the legal moves, copy the
       legal moves from the ArrayList into the array, and return the array. */

      if (moves.size() == 0)
         return null;
      else {

         return moves;
      }
      
   }  // end getLegalMoves
  
  
  ArrayList<Board> getLegalJumpsFrom(int player, int row, int col) {
      if (player != WHITE && player != BLACK)
         return null;
      int playerKing;  // The constant representing a King belonging to player.
      if (player == WHITE)
         playerKing = WHITEKING;
      else
         playerKing = BLACKKING;
      ArrayList<Board> moves = new ArrayList<Board>();  // The legal jumps will be stored in this list.
      if (board[row][col] == player || board[row][col] == playerKing) {
         if (canJump(player, row, col, row+1, col+1, row+2, col+2))
            moves.add(new Board(row,col,row+2, col+2));
         if (canJump(player, row, col, row-1, col+1, row-2, col+2))
            moves.add(new Board(row, col, row-2, col+2));
         if (canJump(player, row, col, row+1, col-1, row+2, col-2))
            moves.add(new Board(row, col, row+2, col-2));
         if (canJump(player, row, col, row-1, col-1, row-2, col-2))
            moves.add(new Board(row, col, row-2, col-2));
      }
      if (moves.size() == 0)
         return null;
      else {
         return moves;
      }
   }  // end getLegalMovesFrom()
  
  
  private boolean canJump(int player, int r1, int c1, int r2, int c2, int r3, int c3) {
      
      if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
         return false;  // (r3,c3) is off the board.
      
      if (board[r3][c3] != OFB)
         return false;  // (r3,c3) already contains a piece.
      
      if (player == BLACK) {
         if (board[r1][c1] == BLACK && r3 > r1)
            return false;  // Regular red piece can only move  up.
         if (board[r2][c2] != WHITE && board[r2][c2] != WHITEKING)
            return false;  // There is no black piece to jump.
         return true;  // The jump is legal.
      }
      else {
         if (board[r1][c1] == WHITE && r3 < r1)
            return false;  // Regular black piece can only move downn.
         if (board[r2][c2] != BLACK && board[r2][c2] != BLACKKING)
            return false;  // There is no red piece to jump.
         return true;  // The jump is legal.
      }
      
   }  // end canJump()
  
  public boolean canMove(int player, int r1, int c1, int r2, int c2) {
      
      if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
         return false;  // (r2,c2) is off the board.
      
      if (board[r2][c2] != OFB)
         return false;  // (r2,c2) already contains a piece.
      
      if (player == BLACK) {
         if (board[r1][c1] == BLACK && r2 > r1)
            return false;  // Regular red piece can only move down.
         return true;  // The move is legal.
      }
      else {
         if (board[r1][c1] == WHITE && r2 < r1)
            return false;  // Regular black piece can only move up.
         return true;  // The move is legal.
      }
      
   }  // end canMove()

    

	
	 


}
