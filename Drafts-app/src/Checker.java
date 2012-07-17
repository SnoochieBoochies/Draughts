//Notion of a checker(piece) for OO consistency.

public class Checker {
	//there are two types of checker, red/black. Then red and black kings, which extend this class.
	// red=0,redKing=1,black=2,blackKing=3, out of bounds = OFB = -1.
	static final int RED = 1, REDKING = 2, BLACK = 3, BLACKKING = 4, OFB = -1;
	int draftsBoard [][];
	Checker () {
		draftsBoard = new int[8][8];
		placeCheckers();
	}
	
	void placeCheckers() {
		//place all the pieces on the board. Starting at the bottom left on white squares.
		//nested for loops for the grid.
		for(int row = 0; row < 8; row++) {
			for(int col = 0; col < 8; col++) {
				//pieces can only go in white squares, so row%2 and col%2 both have to be true.
				if(col % 2 == row % 2) {
					//place the two sets of pieces at top and bottom of board.
					//pieces occupy the first and last 3 rows from 0 -> 8.
					if(row < 3) {
						draftsBoard[row][col] = BLACK;
					}
					else if(row > 4) {
						draftsBoard[row][col] = RED;
					}
					else { draftsBoard[row][col] = OFB; }
				}
			}
		}
	}
	
	int pieceAt(int row, int col){
		return draftsBoard[row][col];
	}
}
