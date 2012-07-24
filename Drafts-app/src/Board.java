import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;




//class that holds the notion of a board. it paints the board and the pieces also.
public class Board extends Component {
	static final int WHITE = 0, BLACK = 1, WHITEKING = 2, BLACKKING = 3, OFB = -1;
	int [][] board;
	Color darkBrown = new Color(133,94,66);
	Color lightBrown = new Color(222,184,135);
	boolean gameInProgress = true;
	boolean newGamePressed = true;
	public int row =0,col =0;
	Graphics g;
	Shape piece = new Ellipse2D.Float(32 + col*80, 3 + row*80, 75, 75);
	Rectangle rect = new Rectangle(30+col*80, 1+row*80, 80,80);
	
	//CREATE A NEW CLASS FOR A PIECE. MAKE ANOTHER PAINT METHOD FOR IT TO DRAW THE PIECES, ADD THE MOUSE STUFF IN THERE.
	Board () {
		board = new int[8][8];
		//addMouseMotionListener(this);
	    //addMouseListener(this);
	}
	int pieceAt(int row, int col) {
        return board[row][col];
     }
	
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		for(row=0;row<8;row++) {
			rect.setLocation(col*80, row*80);
			for(col=0;col<=8;col++) {
				 if ( row % 2 == col % 2 ) {
	                 g.setColor(darkBrown);
	                 g.drawRect(rect.x,rect.y,rect.width,rect.height);
	                 g.fillRect(rect.x, rect.y, rect.width, rect.height);
	                // rect.setLocation(row*90, col*90);
	                // g.setColor(lightBrown);
	                 if(row <3) {
	                 g.setColor(Color.white);
	                 g.fillOval(rect.x, rect.y, rect.width-5, rect.height-5);
	                 }
	                 else if(row >4){
	                	 g.setColor(Color.black);
	 	                 g.fillOval(rect.x, rect.y, rect.width-5, rect.height-5);
	                 }
	              }
	              else {
	                 g.setColor(lightBrown);
	                 g.drawRect(rect.x, rect.y, rect.width, rect.height);
	                 g.fillRect(rect.x, rect.y, rect.width, rect.height);
	                // rect.setLocation(row*90, col*90);
	                // g.setColor(darkBrown);

	              }
				rect.setLocation(col*80, row*80);	
			}
			
			 //if(newGamePressed || gameInProgress) {
	           	  //Do new game board setup, else a game is in progress. move the pieces as needed.
	           	  //applet starts up with a game in progress. hitting reset forfeits, the user has to press newgame after this.
					//pieces can only go in white squares, so row%2 and col%2 both have to be true.
			//		if(col % 2 != row % 2) {
						//place the two sets of pieces at top and bottom of board.
						//pieces occupy the first and last 3 rows from 0 -> 7.
			//			if(row < 3) {
			//				g.setColor(Color.white);
			//				g.drawOval(32 + col*80, 3 + row*80, 75, 75);
			//				g.fillOval(32 + col*80, 3 + row*80, 75, 75);
			//			}
			//			else if(row > 4) {
			//				g.setColor(Color.black);
			//				g.drawOval(32 + col*80, 3 + row*80, 75, 75);
			//				g.fillOval(32 + col*80, 3 + row*80, 75, 75);
			//		       
			//			}
			//		}
	         //    }
	             
	         //    else {
	            	 /*
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
		             */
	            // }
		
		}
		repaint();
	}
	//public void paint(Graphics g) {
	//	 g.setColor(Color.black);
    //    g.drawRect(29,0,getSize().width-79,getSize().height-79);

       
        
        /* Draw the squares of the checkerboard and the checkers. */
	/*
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
     */
             /* 
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
             */
          // }
       // }
      //  repaint();
//	}// end of paint.
	/*
	public void update(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		
		if(newGamePressed || gameInProgress) {
       	  //Do new game board setup, else a game is in progress. move the pieces as needed.
       	  //applet starts up with a game in progress. hitting reset forfeits, the user has to press newgame after this.
				//pieces can only go in white squares, so row%2 and col%2 both have to be true.
				if(col % 2 != row % 2) {
					//place the two sets of pieces at top and bottom of board.
					//pieces occupy the first and last 3 rows from 0 -> 8.
					if(row < 3) {
						//g2.drawOval(32 + boardMain.col*80, 3 + boardMain.row*80, 75, 75);
						g2.setColor(Color.white);
						g2.draw(piece);
					}
					else if(row > 4) {
						g2.setColor(Color.black);
						g2.draw(piece);
				       
					}
				}
         }
         else {
        	 switch(pieceAt(row, col)) {
	             	case WHITE:	
	             		g2.setColor(Color.white);
	             		//g.drawOval(32 + boardMain.col*80, 3 + boardMain.row*80, 75, 75);
					    g2.draw(piece);
					    break;
	             	case BLACK:
	             		g2.setColor(Color.black);
	             		g2.draw(piece);
						break;
						
	             	case WHITEKING:
	             		g2.setColor(Color.white);
	             		g2.draw(piece);
						g2.setColor(Color.red);
						g2.drawString("King", 57 + col*80, 42 + row*80);
						break;
	             	case BLACKKING:
	             		g2.setColor(Color.black);
	             		g2.draw(piece);
						g2.setColor(Color.red);
						g2.drawString("King", 57 + col*80, 42 + row*80);
						break;
						
	             }
         }
	}
	*/
	int mainBoard[][] = new int[8][8];
	int preBoard [][] = new int[8][8];
	protected int carryPiece;
	//protected int userColor = BLACK;

	protected boolean movingPiece=false;
	protected boolean didjump=false;
	protected int carryPlaceX, carryPlaceY;
	protected int carryXpos, carryYpos;
	protected int tough=0;
	protected int defTough=2;
	protected boolean waiting=false;


	
	
}
class BoardGrid {
    public int xpos;
    public int ypos;
    public int xwidth;
    public int ywidth;
}