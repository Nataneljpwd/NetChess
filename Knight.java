//imports------
import java.util.*;


public class Knight extends Piece{
    
    public Knight(int row, int col,boolean isWhite){
        super(row, col, isWhite);
    }

    public void calculateMoves(Board b){
        int[][] moves={{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};//the possible 8 moves.
        for(int[] mov:moves){
            if(this.row+mov[0]<8 && this.row+mov[0]>=0 && this.col+mov[1]<8 && this.col+mov[1]>=0){
                super.possibleMoves.add(mov);
            }
        }
        validateMoves(b);
    }
}
