//---------------imports------------------
import java.util.*;
//handle gui later

//will probably be changed to buttons
public class BoardCell {
    //position of the board cell
    private int col;
    private int row;
    public static final int size=64;
    private Piece p;
    public boolean isWhite;

    //TODO: add color chosing in the board class

    //-------------------------methods+ctor--------------------------------
    public BoardCell(int row, int col) {
        this.col = col;
        this.row = row;
        
        this.p=null;//so that later we can check if has piece
    }

    public void setPiece(Piece p) {
        this.p = p;
    }
    public int[] getPosition() {
        return new int[]{this.col*size, this.row*size};//returns {x,y}
    }

    public Piece getPiece(){
        return this.p;
    }

}
