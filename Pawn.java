//imports:
import java.util.*;




public class Pawn extends Piece{
    //variables
    public boolean isFirstMove=true;



    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }
    
    //methods:

    //---
    //---
    //-p-
    public void calcMoves(){//should be added to the board class
        if (isFirstMove){
            super.possibleMoves.add(new int[] {row-2,col});
            isFirstMove = false;
        }
        super.possibleMoves.add(new int[] {row-1,col+1});
        super.possibleMoves.add(new int[] {row-1,col-1});
        super.possibleMoves.add(new int[] {row-1,col});
    }

    public List<int[]> getMoves() {
        calculateMoves();
        return this.possibleMoves;
    }
}
