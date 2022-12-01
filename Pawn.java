//imports:
import java.util.*;




public class Pawn extends Piece{
    //variables
    public boolean isFirstMove=true;



    public Pawn(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
    }
    
    //methods:
    public void calculateMoves(Board b){
        //change to check the moves better - we dont have a check checker
        if (row-1>=0 && b.getCell(row-1,col).getPiece()==null){
            if(isFirstMove && row-2>=0 && b.getCell(row-2,col).getPiece()==null)
                super.possibleMoves.add(new int[] {row-2,col});
            isFirstMove = false;
            super.possibleMoves.add(new int[] {row-1,col});
        }
        if(row-1>=0 && col+1<8 && b.getCell(row-1, col+1).getPiece()!=null)
            super.possibleMoves.add(new int[] {row-1,col+1});
        if(row-1>=0 && col-1>=0 && b.getCell(row-1, col-1).getPiece()!=null)
            super.possibleMoves.add(new int[] {row-1,col-1});
        
        validateMoves(b);
    }

    public List<int[]> getMoves(Board b) {
        calculateMoves(b);
        return this.possibleMoves;
    }

    @Override
    public char toChar() {
        return this.isWhite ? 'P':'p';
    }

    @Override
    public void draw() {}
}
