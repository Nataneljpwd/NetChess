//imports
import java.util.*;



public class Rook extends Piece{

    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }

    //methods

    public void calculateMoves(){
        //adds all vertical moves
        for(int i=0;i<8;i++){
            if(i!=row)
                super.possibleMoves.add(new int[] {i,col});
        }
        //adds all horizontal moves
        for(int i=0;i<8;i++){
            if(i!=col)super.possibleMoves.add(new int[] {row,i});
        }
    }
    
}
