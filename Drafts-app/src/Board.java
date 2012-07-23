import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;




//class that holds the notion of a board. it paints the board and the pieces also.
public class Board extends Component {
	static final int WHITE = 0, BLACK = 1, WHITEKING = 2, BLACKKING = 3, OFB = -1;
	int [][] board;
	Color darkBrown = new Color(133,94,66);
	Color lightBrown = new Color(222,184,135);
	boolean gameInProgress = true;
	boolean newGamePressed = true;
	int row,col;
	Graphics g;
	
	
	
	Board () {
		board = new int[8][8];
		
	}
	int pieceAt(int row, int col) {
        return board[row][col];
     }
	public void paint(Graphics g) {
		 g.setColor(Color.black);
        g.drawRect(29,0,getSize().width-79,getSize().height-79);

        
        
        /* Draw the squares of the checkerboard and the checkers. */
        for (row = 0; row < 8; row++) {
           for (col = 0; col < 8; col++) {
              if ( row % 2 == col % 2 ) {
                 g.setColor(lightBrown);
                 g.fillRect(30+col*80, 1+row*80, 80,80);
              }
              else {
                 g.setColor(darkBrown);
                 g.fillRect(30+col*80, 1+row*80, 80,80);
              }
              
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
            	 switch(pieceAt(row, col)) {
	             	case WHITE:	
	             		g.setColor(Color.white);
	             		g.drawOval(32 + col*80, 3 + row*80, 75, 75);
					    g.fillOval(32 + col*80, 3 + row*80, 75, 75);
					    break;
	             	case BLACK:
	             		g.setColor(Color.black);
						g.fillOval(32 + col*80, 3 + row*80, 75, 75);
						break;
						
	             	case WHITEKING:
	             		g.setColor(Color.white);
						g.fillOval(32 + col*80, 3 + row*80, 75, 75);
						g.setColor(Color.red);
						g.drawString("King", 57 + col*80, 42 + row*80);
						break;
	             	case BLACKKING:
	             		g.setColor(Color.black);
						g.fillOval(32 + col*80, 3 + row*80, 75, 75);
						g.setColor(Color.red);
						g.drawString("King", 57 + col*80, 42 + row*80);
						break;
						
	             }
             }
             
           }
        }

	}// end of paint.

	
	public void mousePressed(MouseEvent e) {
		row = e.getX();
		col = g.getY();
	}
	BoardGrid [][] boardGrid = new BoardGrid[8][8];
	
	protected boolean pieceIsInside(int x, int y) {
		  return ((x >= 0 && x < 8) && (y >=0 && y<8));
	}
	int mainBoard[][] = new int[8][8];
	protected int carryPiece;
	protected int userColor = BLACK;

	protected boolean movingPiece=false;
	protected boolean didjump=false;
	protected int carryPlace;
	protected int carryXpos, carryYpos;
	protected int tough=0;
	protected int defTough=2;
	protected boolean waiting=false;

	
	protected int plainType(int type) {
		  return (int)((type>=0) ? (type % 2) : type);
		}
	
	//public Shape pieceImage(int colorfor) {
	//	  switch(colorfor) {
	//	  case 0: /*redType:*/  return ;
	//	  case 1: /*blackType:*/        return Color.black;
	//	  case 3:/*blackKingType:*/     return Color.black;
	//	  case 2:/*redKingType:*/       return Color.red;
	//	  default:      return Color.green;
	//	  }
		  //return null;
	//	}
	/*
	public boolean mouseDown(Event evt, int x, int y) {
		  int loop;
		  Graphics g = getGraphics();

		  //myErr("\nmouseDown");

		  for(loop=0;loop<8;loop++) {
			  for(int loop2=0; loop2<8;loop2++){
			    if(pieceIsInside(x,y) 
			       //&& (mainBoard[loop] != emptyType) ) {
			       && (plainType(mainBoard[loop][loop2]) == userColor) ) {
			      //myErr("mouse is inside piece " + loop);
			      //couldjump=could_jump(plainType(mainBoard[loop]), mainBoard);
			      carryPiece=mainBoard[loop][loop2];
			      carryPlace=loop;
			      carryXpos=boardGrid[loop][loop2].xpos;
			      carryYpos=boardGrid[loop][loop2].ypos;
			      mainBoard[loop][loop2]=OFB;
			     // g.drawImage(pieceImage(mainBoard[loop]), 
			     //             boardGrid[loop].xpos, boardGrid[loop].ypos,
			     //             boardGrid[loop].xwidth, boardGrid[loop].ywidth,
			     //             this);
			      
			     // g.setColor(Color.white);
			      g.fillOval(boardGrid[loop][loop2].xpos,boardGrid[loop][loop2].ypos,boardGrid[loop][loop2].xwidth,boardGrid[loop][loop2].ywidth);
			      
			      movingPiece=true;
			     // g.drawImage(pieceImage(carryPiece),
			     //             carryXpos, carryYpos,
			     //             boardGrid[carryPlace].xwidth, boardGrid[carryPlace].ywidth,
			     //             this);
			      g.fillOval(carryXpos,carryYpos,boardGrid[loop][loop2].xwidth,boardGrid[loop][loop2].ywidth);                          
			      return true;
			    }
			  }
		  }
		                
		  return true;
		}
		*/
	
		
}
class BoardGrid {
    public int xpos;
    public int ypos;
    public int xwidth;
    public int ywidth;
}