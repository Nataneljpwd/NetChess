//---------------imports------------------
import java.util.*;
//handle gui later

//will probably be changed to buttons
public class BoardCell {
    //position of the board cell
    private int col;
    private int row;
    private int size=100;
    private Piece p;
    public boolean isWhite;

    //TODO: add color chosing in the board class

    //-------------------------methods+ctor--------------------------------
    public BoardCell(int row, int col) {
        this.col = col;
        this.row = row;
        
        this.p=null;//so that later we can check if has piece
    }

    public void setPieces(Piece p) {
        this.p = p;
    }
    public int[] getPosition() {
        return new int[]{this.col*size, this.row*size};//returns {x,y}
    }
}
