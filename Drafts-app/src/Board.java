import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;


//class that holds the notion of a board. it paints the board and the pieces also.
public class Board extends Component{
	static final int WHITE = 0, BLACK = 1, WHITEKING = 2, BLACKKING = 3, OFB = -1;
	int [][] board;
	Color darkBrown = new Color(133,94,66);
	Color lightBrown = new Color(222,184,135);
	boolean gameInProgress = true;
	boolean newGamePressed = true;
	
	
	
	public void paint(Graphics g) {
		 g.setColor(Color.black);
        g.drawRect(29,0,getSize().width-79,getSize().height-79);
        
        //g.drawRect(1,1,getSize().width-3,getSize().height-3);
        
        /* Draw the squares of the checkerboard and the checkers. */
        for (int row = 0; row < 8; row++) {
           for (int col = 0; col < 8; col++) {
              if ( row % 2 == col % 2 )
                 g.setColor(lightBrown);
              else
                 g.setColor(darkBrown);
              g.fillRect(30+col*80, 1+row*80, 80,80);
              
             if(newGamePressed || gameInProgress) {
           	  //Do new game board setup, else a game is in progress. move the pieces as needed.
           	  //applet starts up with a game in progress. hitting reset forfeits, the user has to press newgame after this.
				//pieces can only go in white squares, so row%2 and col%2 both have to be true.
				if(col % 2 != row % 2) {
					//place the two sets of pieces at top and bottom of board.
					//pieces occupy the first and last 3 rows from 0 -> 8.
					if(row < 3) {
						g.setColor(Color.white);
						g.fillOval(32 + col*80, 3 + row*80, 75, 75);
					}
					else if(row > 4) {
						g.setColor(Color.black);
						g.fillOval(32 + col*80, 3 + row*80, 75, 75);
				       
					}
				}
             }
             else {
           	  if(board[row][col] == WHITE) {
           		  g.setColor(Color.white);
					  g.fillOval(32 + col*80, 3 + row*80, 75, 75);
					  
           	  }
           	  else if(board[row][col] == BLACK) {
           		  g.setColor(Color.black);
					  g.fillOval(32 + col*80, 3 + row*80, 75, 75);
           	  }
           	  else if(board[row][col] == WHITEKING) {
           		  g.setColor(Color.white);
					  g.fillOval(32 + col*80, 3 + row*80, 75, 75);
					  g.setColor(Color.red);
					  g.drawString("King", 57 + col*80, 42 + row*80);
           	  }
           	  else if(board[row][col] == BLACKKING) {
           		  g.setColor(Color.black);
					  g.fillOval(32 + col*80, 3 + row*80, 75, 75);
					  g.setColor(Color.red);
					  g.drawString("King", 57 + col*80, 42 + row*80);
           	  }
             }
              
           }
        }

	}// end of paint.
}
