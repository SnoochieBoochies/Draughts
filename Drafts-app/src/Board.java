import java.util.ArrayList;

//class that holds the notion of a board and draughts pieces(as integers).
//also does the moves/rules.
public class Board {
	static final int WHITE = 1, BLACK = 3, WHITEKING = 2, BLACKKING = 4, OFB = -1;
	int [][] board;

	int fromRow, fromCol;  // Position of piece to be moved.
	int toRow, toCol;      // Square it is to move to.
	Board () {
		board = new int[8][8];
		
		setPieces();
	}
	Board(int rowA, int colA, int rowB, int colB) {
        // Constructor.  Just set the values of the instance variables.
	   fromRow = rowA;
	   fromCol = colA;
	   toRow = rowB;
	   toCol = colB;
	}
	
	/* This method places the integers denoting pieces into the 2D array.
	 * It also fills the rest of the spaces with the OFB variable to represent 
	 * the checkered board.
	 */
	void setPieces() {
		for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
               if ( row % 2 != col % 2 ) {
                  if (row < 3) {
                     board[row][col] = WHITE;
                  }
                  else if (row > 4) {
                     board[row][col] = BLACK;
                  }
                  else
                     board[row][col] = OFB;
               }
               else {
                  board[row][col] = OFB;
               }
            }
         }
		
	}
	
	//simple method that returns the position of a piece in the array.
	int pieceIndex(int row, int col) {
        return board[row][col];
     }


	/*Method that tests whether a move on the board was a jump.
	 *  Jumps occur from moving 2 rows up or down the board.
	 */
	boolean jumpTest() {
		return (fromRow - toRow == 2 || fromRow - toRow == -2);
	}
 
	
	/* The main method that makes moves on the 2D array.
	 * It takes a set of original x and y coordinates and 
	 * the destination x and y coordinates.
	 * This method assumes that the move taking place is legal,
	 * and does not concern itself with checking.
	 * makeMoveBoard() does the checking of this.
	 */
	void move(int fromRow, int fromCol, int toRow, int toCol) {
		board[toRow][toCol] = board[fromRow][fromCol];
		board[fromRow][fromCol] = OFB;
		if (fromRow - toRow == 2 || fromRow - toRow == -2) {
			// For some reason calling jumpTest() doesn't work.
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
	
	
	/* This method returns an arraylist of Board instances for each player.
	 * It checks whether pieces are able to jump, and if so, it adds them to the arraylist.
	 * Note: Moves occur by diagonally going +/-2 rows and +/-1 column.
	 * Next it checks for pieces able to move and adds them to the arraylist.
	 * Note: on checking moveable pieces, the if statement enforces the rule,
	 * that if you can jump, you must.
	 */
	ArrayList<Board> getLegalMoves(int player) {
      
		if (player != WHITE && player != BLACK)
			return null;
      
		int playerKing;  // The constant representing a King belonging to player.
		if (player == WHITE)
			playerKing = WHITEKING;
		else
			playerKing = BLACKKING;
      
		ArrayList<Board> moves = new ArrayList<Board>();  // Moves will be stored in this list.
      
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col] == player || board[row][col] == playerKing) {
					if (ableToJump(player, row, col, row+1, col+1, row+2, col+2))
						moves.add(new Board(row, col, row+2, col+2));
					if (ableToJump(player, row, col, row-1, col+1, row-2, col+2))
						moves.add(new Board(row, col, row-2, col+2));
					if (ableToJump(player, row, col, row+1, col-1, row+2, col-2))
						moves.add(new Board(row, col, row+2, col-2));
					if (ableToJump(player, row, col, row-1, col-1, row-2, col-2))
						moves.add(new Board(row, col, row-2, col-2));
				}
			}
		}
		
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
      
      /* If no legal moves have been found, return null.  */

		if (moves.size() == 0)
			return null;
		else {
			return moves;
		}
	}  // end getLegalMoves
  
  
	
	/* Method that is called by makeMoveBoard()
	 * when there is a possible jump.
	 * Returns an arraylist with the possible jumps for a player
	 *  as they may have more than one jump in a row.
	 */
	ArrayList<Board> getLegalJumps(int player, int row, int col) {
		if (player != WHITE && player != BLACK)
			return null;
		int playerKing;  // The constant representing a King belonging to player.
		if (player == WHITE)
			playerKing = WHITEKING;
		else
			playerKing = BLACKKING;
		ArrayList<Board> moves = new ArrayList<Board>();
		
		if (board[row][col] == player || board[row][col] == playerKing) {
			if (ableToJump(player, row, col, row+1, col+1, row+2, col+2))
				moves.add(new Board(row,col,row+2, col+2));
			if (ableToJump(player, row, col, row-1, col+1, row-2, col+2))
				moves.add(new Board(row, col, row-2, col+2));
			if (ableToJump(player, row, col, row+1, col-1, row+2, col-2))
				moves.add(new Board(row, col, row+2, col-2));
			if (ableToJump(player, row, col, row-1, col-1, row-2, col-2))
				moves.add(new Board(row, col, row-2, col-2));
		}
		if (moves.size() == 0)
			return null;
		else {
			return moves;
		}
   }  // end getLegalMovesFrom()
  
	
	/* Method that returns a boolean stating whether a piece is able to jump.
	 * rowA,colA act as the current points, and rowC,colC act as the destination points.
	 * rowB,colB act as the piece's coordinates that will be jumped.
	 * The first if statement checks whether a piece is off the board.
	 * Next the method checks the player and then checks if the current coords are that player's piece
	 * and the direction that the piece is trying to move.
	 */
	private boolean ableToJump(int player, int rowA, int colA, int rowB, int colB, int rowC, int colC) {  
		if (rowC < 0 || rowC >= 8 || colC < 0 || colC >= 8)
			return false; 

		if (board[rowC][colC] != OFB)
			return false;
		
		if (player == BLACK) {
			if (board[rowA][colA] == BLACK && rowC > rowA)
				return false; 
			if (board[rowB][colB] != WHITE && board[rowB][colB] != WHITEKING)
				return false;  
			return true;  // The jump is legal.
		}
		else {
			if (board[rowA][colA] == WHITE && rowC < rowA)
				return false;  
			if (board[rowB][colB] != BLACK && board[rowB][colB] != BLACKKING)
				return false;  
			return true;  // The jump is legal.
		}
  
      
	}  // end canJump()
  
	
	/* This method, like ableToJump checks whether a piece is able to go
	 * from rowA,colA to rowB,colB in a manner of a move.
	 * 
	 */
	public boolean canMove(int player, int rowA, int colA, int rowB, int colB) {
      
		if (rowB < 0 || rowB >= 8 || colB < 0 || colB >= 8)
			return false;  // (r2,c2) is off the board.
      
		if (board[rowB][colB] != OFB)
			return false;  // (r2,c2) already contains a piece.
      
		if (player == BLACK) {
			if (board[rowA][colA] == BLACK && rowB > rowA)
				return false;  // Regular red piece can only move down.
			return true;  // The move is legal.
		}
		else {
			if (board[rowA][colA] == WHITE && rowB < rowA)
				return false;  // Regular black piece can only move up.
			return true;  // The move is legal.
		}
      
   }  // end canMove()

}
