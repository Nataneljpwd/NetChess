//---------------------------imports--------------------------------
import java.util.*;
//handle gui later
//------------------------class + methods--------------------------------
public abstract class Piece{

    //---------------------------position handling--------------------------------
    protected int col;
    protected int row;
    protected boolean isWhite;
    protected List<int []> possibleMoves;
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
        this.size=160;
        this.possibleMoves=new ArrayList<int[]>();
    }
    //returns position of the piece assuming 0,0 is the start of the board
    public int[] getPos(){
        return new int[] {col*size,row*size};//returns {x,y}
    }

    public int[] getRawPos(){
        return new int[]{row,col};
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public List<int[]> getMoves() {
        //removed the board and the calc moves because thy happen before every move.
        return this.possibleMoves;
    }

    public void setMoves(List<int[]> possibleMoves){
        this.possibleMoves= possibleMoves;
    }

    public void validateMoves(Board b){
        //checking for pinned pieces
        List<int[]> moves=this.possibleMoves;
        List<int[]> remove=new ArrayList<>();
        int[] currPos;
        //check what moves to remove:
        for(int[] mov:moves){
            //move the piece without drawing
            currPos=this.getRawPos();
            Piece p=b.getCell(mov[0], mov[1]).getPiece();
            b.move(currPos, mov);
            if(b.isCheck(this.isWhite)){
                remove.add(mov);
            }
            //move the piece back
            b.move(mov,currPos);
            b.getCell(mov[0], mov[1]).setPiece(p);
        }
        //remove the invalid moves
        for(int[] mov:remove){
            moves.remove(mov);
        }
        this.setMoves(moves);
    }

    public abstract void calculateMoves(Board b);

    public abstract void draw();
    public abstract char toChar();

    public void drawValidMoves(){
        //foreach loop of moves, draw circle with low opacity
    }
    //in the board class add a listener to all cells in the board that sends move events
    public void move(int row,int col){}//add the eating mechanism in the subclasses



}