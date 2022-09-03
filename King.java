//imports-------
import java.util.*;

public class King extends Piece {
    public King(int row,int col,boolean isWhite){
        super(row, col, isWhite);
    }

    public void calculateMoves(Board b){}

    public boolean isCheck(Board b){
        //run BFS to check if its check+ 8 moves for Knight and 2 for pawns.
        //checking for top checks
        for(int i=row-1;i>=0;i--){
            if(b.getCell(i,this.col).getPiece()!=null){
                //if there is a piece check the color,if same as King, break.
                if(b.getCell(i,this.col).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    if(b.getCell(i,this.col).getPiece() instanceof Rook || b.getCell(i,this.col).getPiece() instanceof Queen){
                        return true;
                    }
                }
            }
        }
        //check bottom checks
        for(int i=row+1;i<8;i++){
            if(b.getCell(i,this.col).getPiece()!=null){
                //if there is a piece check the color,if same as King, break.
                if(b.getCell(i,this.col).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    if(b.getCell(i,this.col).getPiece() instanceof Rook || b.getCell(i,this.col).getPiece() instanceof Queen){
                        return true;
                    }
                }
            }
        }
        //check for left and right:
        //right:
        for(int i=col+1;i<8;i++){
            if(b.getCell(this.row,i).getPiece()!=null){
                //if there is a piece check the color,if same as King, break.
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    if(b.getCell(this.row,i).getPiece() instanceof Rook || b.getCell(i,this.col).getPiece() instanceof Queen){
                        return true;
                    }
                }
            }
        }
        //left:
        for(int i=col-1;i>=0;i--){
            if(b.getCell(this.row,i).getPiece()!=null){
                //if there is a piece check the color,if same as King, break.
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    if(b.getCell(this.row,i).getPiece() instanceof Rook || b.getCell(i,this.col).getPiece() instanceof Queen){
                        return true;
                    }
                }
            }
        }

        //check for diagonal checks

        //TODO: implement diagonal checks for checks.
        return false;
    }
}
