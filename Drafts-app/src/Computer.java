import java.awt.List;
import java.util.ArrayList;


public class Computer {

	private Board currentBoard;
	//convert board into 1d.

	private int newBoard[] = new int[32];
	int copy;
	int durr =1;
	int [] convert(Board board) {
		for(int i =0; i <8; i++) {
			for(int j = 0; j <8; j++) {
			copy = board.board[i][j];
				if(board.board[i][j] == 0) continue;
				newBoard[durr] = copy;
			}
			durr++;
		}
		return newBoard;
	}

	
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
		color = Board.BLACK;
	}
	/*
	public void ComputerMove() {
		try {
			Board moves;
			DraughtsPanel panel;
			moves = minimax(currentBoard);
			
			if(!moves.) {
				panel.doMakeMove(moves);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
*/
	//try and call this in the board.java 
	


	public void setBoard(Board board) {
		currentBoard = board;
	}
	
	public List minimax(Board board) {
		ArrayList<Board> successors;
		Board move;
		List bestMove = null;
		Board nextBoard;
		int value, maxValue = Integer.MIN_VALUE;
		
		successors = board.getLegalMoves(color);
		
		while(successors.size() > 0) {
			move = successors.remove(0);
			nextBoard = board;
			
			System.out.println("******************************************************");
			nextBoard.makeMove(nextBoard.fromRow, nextBoard.fromCol, nextBoard.toRow, nextBoard.toCol);
			value = minMove(nextBoard, 1, maxValue, Integer.MAX_VALUE);
			
			if(value > maxValue) {
				System.out.println("Max value: "+value+ "at depth:0");
				maxValue =  value;
				bestMove =move;
			}
		}
		return convert(bestMove);
	}
	
	private int maxMove(Board board, int depth, int alpha, int beta) {
		if(cutOffTest(board, depth))
			return eval(board);
		
		
		Board [] successors;
		Board move;
		Board nextBoard;
		int value;
		
		System.out.println("Max node depth: " + depth + "with alpha "+alpha+" beta "+beta);
		
		successors = board.getLegalMoves(color);
		while(successors.length > 0) {
			move = successors[0];
			nextBoard = board;
			value =  minMove(nextBoard, depth+1,alpha,beta);
			
			if(value >alpha) {
				alpha = value;
				System.out.println("MAx value: " +value + " at depth "+depth);

			}
			if(alpha > beta) {
				System.out.println("MAx value with pruning: "+beta+ " at depth "+depth);
				System.out.println(successors.length + " successors left!");
				return beta;
			}
			
		}
		System.out.println("Max value selected: "+alpha+ " at depth "+depth);
		return alpha;	
		
	}
	
	
	private int minMove(Board board, int depth, int alpha, int beta) {
		if(cutOffTest(board, depth))
			return eval(board);
		
		
		Board [] successors;
		Board move;
		Board nextBoard;
		int value;
		
		System.out.println("Min node depth: " + depth + "with alpha "+alpha+" beta "+beta);
		
		successors = board.getLegalMoves(color);
		while(successors.length > 0) {
			move = successors[0];
			nextBoard = board;
			value =  maxMove(nextBoard, depth+1,alpha,beta);
			
			if(value < beta) {
				beta = value;
				System.out.println("Min value: " +value + " at depth "+depth);

			}
			if(beta < alpha) {
				System.out.println("Min value with pruning: "+alpha+ " at depth "+depth);
				System.out.println(successors.length + " successors left!");
				return alpha;
			}
			
		}
		System.out.println("Min value selected: "+beta+ " at depth "+depth);
		return beta;	
	}

	
	private int eval(Board board) {
		int colorKing;
		int enemy,  enemyKing;
		int convertedBoard[] = new int[32];
		convertedBoard = convert(board);
		
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
			for(int i = 0; i < 32; i++) {
				piece = convertedBoard[i];
				
				if(piece != Board.OFB) {
					if(piece == color || piece == colorKing) {
						colorForce +=calculateValue(piece, i);
					}
					else
						enemyForce += calculateValue(piece, i);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return colorForce - enemyForce;
	}

	
	
	private int calculateValue(int piece, int pos) {
		int value;
		if(piece == Board.BLACK) {
			if(pos >=4 && pos <= 7) {
				value = 7;
			}
			else value = 5;
		}
		else if(piece != Board.WHITE) {
			if(pos >=24 && pos <=27) {
				value = 7;
			}
			else value = 5;
		}
		else value = 10;
		
		return value * tableWeight[pos];
	}
	
	
	private boolean  cutOffTest(Board board, int depth) {
		return depth > maxDepth || board.canMove(color, board.fromRow, board.fromCol, board.toRow, board.toCol);
	}

}