import java.awt.List;
import java.util.ArrayList;


public class Computer {

	private Board currentBoard;
	
	
	private static final int maxDepth = 2;
	int color;
	
	/*
	private static final int tableWeight[] = {-1, 4, -1, 4,-1, 4,-1, 4,
        4,-1, 3,-1, 3,-1, 3,-1,
        -1, 3,-1, 2,-1, 2,-1, 4,
        4,-1, 2,-1, 1,-1, 3,-1,
        -1, 3,-1, 1,-1, 2,-1, 4,
        4,-1, 2,-1, 2,-1, 3,
        3, 3, 3, 4,
        4, 4, 4, 4};
	*/
	/*
	private static final int [][] tableWeight = {{-1, 4, -1,4, -1, 4, -1, 4},
		   										    {4, -1, 3, -1,3, -1, 3, -1},
		   											{-1, 3, -1, 2, -1, 2, -1, 4},
		   											{4, -1, 2, -1, 1, -1, 3, -1},
		   											{-1, 3, -1, 1, -1,2, -1, 4},
		   											{4, -1, 2, -1, 2, -1, 3, -1},
		   											{-1, 3, -1, 3, -1, 3, -1, 4},
		   											{4, -1, 4, -1, 4, -1, 4, -1}};
*/
	private static final int [] tableWeight =  {4, 4, 4, 4,
        4, 3, 3, 3,
        3, 2, 2, 4,
        4, 2, 1, 3,
        3, 1, 2, 4,
        4, 2, 2, 3,
        3, 3, 3, 4,
        4, 4, 4, 4};
	public Computer(Board gameBoard) {
		currentBoard = gameBoard;
		color = Board.WHITE;

	}
	
	public void computerPlay() {
		try {
			Board moves;
			
			moves = minimax(currentBoard);
			
			if(!moves.equals(null)) {
				moves.makeMove(currentBoard.fromRow, currentBoard.fromCol, currentBoard.toRow, currentBoard.toCol);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	//try and call this in the board.java 
	


	public void setBoard(Board board) {
		currentBoard = board;
	}
	
	public Board minimax(Board board) {
		ArrayList<Board> successors;
		Board move;
		Board bestMove = new Board();
		Board nextBoard;
		int value, maxValue = Integer.MIN_VALUE;
		
		successors = board.getLegalMoves(color);

		while(successors.size() > 0) {
			move = successors.get(0);
			nextBoard = board;
			
			System.out.println("******************************************************");
			nextBoard.makeMove(nextBoard.fromRow, nextBoard.fromCol, nextBoard.toRow, nextBoard.toCol);
			value = minMove(nextBoard, 1, maxValue, Integer.MAX_VALUE);
			
			if(value > maxValue) {
				System.out.println("Max value: "+value+ "at depth:0");
				maxValue =  value;
				bestMove = move;
			}
		}
		System.out.println("Move value selected: " + maxValue + " at depth: 0");
		return bestMove;
	}
	
	private int maxMove(Board board, int depth, int alpha, int beta) {
		if(cutOffTest(board, depth))
			return eval(board);
		
		
		ArrayList<Board> successors;
		Board move;
		Board nextBoard;
		int value;
		
		System.out.println("Max node depth: " + depth + "with alpha "+alpha+" beta "+beta);
		
		successors = board.getLegalMoves(color);
		while(successors.size() > 0) {
			move = successors.get(0);
			nextBoard = board;
			//nextBoard.makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
			value =  minMove(nextBoard, depth+1,alpha,beta);
			
			if(value >alpha) {
				alpha = value;
				System.out.println("MAx value: " +value + " at depth "+depth);

			}
			if(alpha > beta) {
				System.out.println("MAx value with pruning: "+beta+ " at depth "+depth);
				System.out.println(successors.size() + " successors left!");
				return beta;
			}
			
		}
		System.out.println("Max value selected: "+alpha+ " at depth "+depth);
		return alpha;	
		
	}
	
	
	private int minMove(Board board, int depth, int alpha, int beta) {
		if(cutOffTest(board, depth))
			return eval(board);
		
		
		ArrayList<Board> successors;
		Board move;
		Board nextBoard;
		int value;
		
		System.out.println("Min node depth: " + depth + "with alpha "+alpha+" beta "+beta);
		
		successors = board.getLegalMoves(color);
		while(successors.size() > 0) {
			move = successors.remove(0);
			nextBoard = board;
			//nextBoard.makeMove(move.fromRow, move.fromCol, move.toRow, move.toCol);
			value =  maxMove(nextBoard, depth+1,alpha,beta);
			
			if(value < beta) {
				beta = value;
				System.out.println("Min value: " +value + " at depth "+depth);

			}
			if(beta < alpha) {
				System.out.println("Min value with pruning: "+alpha+ " at depth "+depth);
				System.out.println(successors.size() + " successors left!");
				return alpha;
			}
			
		}
		System.out.println("Min value selected: "+beta+ " at depth "+depth);
		return beta;	
	}

	
	private int eval(Board board) {
		int colorKing;
		int enemy,  enemyKing;
		
		//it's the AI.
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
		int piece;// off the board.
			
		try {
			for(int row = 0; row < 8; row++) {
				for(int col =0; col<8; col++) {
					piece = board.pieceAt(row, col);
					if(piece != Board.OFB) {
						if(piece == color || piece == colorKing) {
							colorForce +=calculateValue(piece, row, col);
						}
						else
							enemyForce += calculateValue(piece, row, col);
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

	
	
	private int calculateValue(int piece, int xPos, int yPos) {
		int value;
		if(piece == Board.BLACK) {
			if((xPos >=4 && xPos <= 7)) {
				value = 7;
			}
			else value = 5;
		}
		else if(piece != Board.WHITE) {
			if((xPos >=4 && xPos <=7)) {
				value = 7;
			}
			else value = 5;
		}
		else value = 10;
		
		return value * tableWeight[xPos];
	}
	
	
	private boolean  cutOffTest(Board board, int depth) {
		return depth > maxDepth || !board.canMove(color, board.fromRow, board.fromCol, board.toRow, board.toCol);
	}

}