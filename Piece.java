//---------------------------imports--------------------------------
import java.util.*;
//handle gui later
//------------------------class + methods--------------------------------
public abstract class Piece{

    //---------------------------position handling--------------------------------
    protected int col;
    protected int row;
    protected boolean isWhite;
    protected List<Integer[]> moves;
    public boolean isSelected;//if piece is tapped we set it to true and calc moves and display validated moves
    //if 1 piece is selected change all other are to not selected

    //------------------------GUI handling--------------------------------
    protected int size;


    //------------------------methods--------------------------------

    public Piece(int row, int col, boolean isWhite){
        this.row = row;
        this.col = col;
        this.isWhite = isWhite;
        this.isSelected=false;
    }
    //returns position of the piece assuming 0,0 is the start of the board
    public int[] getPos(){
        return new int[] {col*size,row*size};//returns {x,y}
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public boolean isIsWhite() {
        return this.isWhite;
    }

    public List<Integer[]> getMoves() {
        calculateMoves();
        validateMove();
        return this.moves;
    }

    public void calculateMoves(){}

    public void validateMove(){}

    public void draw(){}

    public void drawValidMoves(){
        //foreach loop of moves, draw circle with low opacity
    }
    //in the board class add a listener to all cells in the board that sends move events
    public void move(int row,int col){}//add the eating mechanism in the subclasses



}