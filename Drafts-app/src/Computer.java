import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;

//This class represents the AI component.
//It implements the minimax algorithm with alpha-beta pruning to determine which moves to make.
//Computer must extend "Component" to give it the ability to make a dialog box.
public class Computer extends Component{

	Board currentBoard;
	//arraylist that will hold the legalmoves for the AI.
	public ArrayList<Board> successors;
	/*this is as far as the algorithm will search in depth.
	*If it were to be increased more, the time it would take to decide on a move would increase
	* to 3^n. */
	private static final int maxDepth = 4;
	int color; //color of the player.
	//2d array that represents the board, and the values of each position.
	private static final int [][] tableWeight = {{-1, 4, -1,4, -1, 4, -1, 4},
		    {4, -1, 3, -1,3, -1, 3, -1},
			{-1, 3, -1, 2, -1, 2, -1, 4},
			{4, -1, 2, -1, 1, -1, 3, -1},
			{-1, 3, -1, 1, -1,2, -1, 4},
			{4, -1, 2, -1, 2, -1, 3, -1},
			{-1, 3, -1, 3, -1, 3, -1, 4},
			{4, -1, 4, -1, 4, -1, 4, -1}};

	public Computer(Board gameBoard) {
		currentBoard = gameBoard;
		color = Board.WHITE;
	}
	
	
	//applies the minimax implementation on the board and makes a move based on it.
	public void computerPlay() {
		try {
			Board moves;

			moves = minimax(currentBoard);

			if(!moves.equals(null)) {
				currentBoard.move(moves.fromRow, moves.fromCol, moves.toRow, moves.toCol);

			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public void setBoard(Board board) {
		currentBoard = board;
	}

	/*implementation of the minimax algorithm
	 * Gets the legalmoves available to it and makes a move on the coords.
	 * It next assigns an int called "value" and applies "maxMove()" which gets the best
	 * move for that particular player. Note: it could call "minMove()" aswell, it doesn't matter.
	 * After this it assigns the best move available to a board instance and returns this.
	 */
	public Board minimax(Board board) {
		Board move;
		Board bestMove = new Board();
		Board nextBoard;
		int value, maxValue = Integer.MIN_VALUE;
		
		successors = board.getLegalMoves(Board.WHITE);
		if(successors == null){
			 JOptionPane.showMessageDialog(this, "Computer has no moves, Black wins!", "You win! :)", JOptionPane.INFORMATION_MESSAGE);
		}
		while(successors.size() > 0) {
			move = successors.remove(0);
			nextBoard = board;
			
			nextBoard.move(nextBoard.fromRow, nextBoard.fromCol, nextBoard.toRow, nextBoard.toCol);
			value = maxMove(nextBoard, 1, maxValue, Integer.MAX_VALUE);
			if(value > maxValue) {
				maxValue =  value;
				bestMove = move;
			}
		}
		return bestMove;
	}
	
	
	/* maxMove() returns an integer that represents the highest value move for itself.
	 * It also checks if it has reached its maximum depth, and if it has it evaluates 
	 * an instance of the board to an integer to determine the value of moving to a square.
	 */
	private int maxMove(Board board, int depth, int alpha, int beta) {
		if(cutOffTest(board, depth))
			return evaluate(board);

		ArrayList<Board> successors;
		Board move;
		Board nextBoard;
		int value;

		successors = board.getLegalMoves(color);
		while(successors.size() > 0) {
			move = successors.remove(0);
			nextBoard = board;
			nextBoard.move(nextBoard.fromRow, nextBoard.fromCol, nextBoard.toRow, nextBoard.toCol);
			value =  minMove(nextBoard, depth+1,alpha,beta);

			if(value >alpha) {
				alpha = value;
			}
			if(alpha > beta) {
				return beta;
			}

		}
		return alpha;	
	}

	/* Similarly minMove() returns an integer that represents the lowest value move 
	 * for the opposing player.
	 * It checks for the maximum depth also and applies an evaluation function 
	 * on it for determining square values.
	 */
	private int minMove(Board board, int depth, int alpha, int beta) {
		if(cutOffTest(board, depth))
			return evaluate(board);

		ArrayList<Board> successors;
		Board move;
		Board nextBoard;
		int value;

		successors = board.getLegalMoves(color);
		while(successors.size() > 0) {
			move = successors.remove(0);
			nextBoard = board;
			nextBoard.move(nextBoard.fromRow, nextBoard.fromCol, nextBoard.toRow, nextBoard.toCol);
			value =  maxMove(nextBoard, depth+1,alpha,beta);

			if(value < beta) {
				beta = value;
			}
			if(beta < alpha) {
				return alpha;
			}

		}
		return beta;	
	}

	
	/* This is the evaluation function used in maxMove() and minMove()
	 * It cycles through the board and calls calculateValue() for a piece at a certain square,
	 * for both players.
	 */
	private int evaluate(Board board) {
		int colorKing;
		int enemy,  enemyKing;

		if(color == Board.WHITE) {
			colorKing = Board.WHITEKING;
			enemy = Board.BLACK;
			enemyKing = Board.BLACKKING;
		}
		else {
			colorKing = Board.BLACKKING;
			enemy = Board.WHITE;
			enemyKing = Board.WHITEKING;
		}

		int colorForce = 0;
		int enemyForce = 0;
		int piece;

		try {
			for(int row = 0; row < 8; row++) {
				for(int col =0; col<8; col++) {
					piece = board.pieceIndex(row, col);
					if(piece != Board.OFB) {
						if(piece == color || piece == colorKing) {
							enemyForce +=calculateValue(piece, row, col);
						}
						else
							colorForce += calculateValue(piece, row, col);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return colorForce - enemyForce;
	}


	/* Method that returns an integer value based on board positions of pieces,
	 * multiplied by the board positions themselves.
	 * This method is called by the evaluation function.
	 */
	private int calculateValue(int piece, int xPos, int yPos) {
		int value;
		if(piece == Board.WHITE) {
			if((xPos >=4 && xPos <= 7) && (yPos >=4 && yPos <=7)) {
				value = 7;
			}
			else value = 5;
		}
		else if(piece != Board.BLACK) {
			if((xPos >=4 && xPos <=7)&& (yPos >=4 && yPos <=7)) {
				value = 7;
			}
			else value = 5;
		}
		else value = 10;
		int pos = xPos+yPos;
		return value * tableWeight[xPos][yPos];
	}
	
	/* Small method that returns a boolean describing 
	 * whether the depth of the search has exceeded the maximum depth allowed,
	 * or whether a piece is unable to move.
	 * maxMove() and minMove() call this function and call the evaluation function in return, 
	 * depending on the outcome of this method.
	 */
	private boolean  cutOffTest(Board board, int depth) {
		return depth > maxDepth || !board.canMove(color, board.fromRow, board.fromCol, board.toRow, board.toCol);
	}

}
