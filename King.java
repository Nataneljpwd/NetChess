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

        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){//the -1 is so that the curr pos wont be a move.
            if(b.getCell(i,j).getPiece()!=null){
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){//if the pieces are the same color we stop.
                    break;
                }
                if(b.getCell(i,j).getPiece() instanceof Queen || b.getCell(i,j).getPiece() instanceof Bishop){
                    return true;
                }
            }
        }
        //then top right moves:
        for(int i=row-1,j=col+1;i>=0 && j<8;i--,j++){
            if(b.getCell(i,j).getPiece()!=null){
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){
                    break;
                }
                if(b.getCell(i,j).getPiece() instanceof Queen || b.getCell(i,j).getPiece() instanceof Bishop){
                    return true;
                }
            }
        }
        
        //Bottom right
        for(int i=row+1,j=col+1;i<8 && j<8;i++,j++){
            if(b.getCell(i,j).getPiece()!=null){
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){
                    break;
                }
                if(b.getCell(i,j).getPiece() instanceof Queen || b.getCell(i,j).getPiece() instanceof Bishop){
                    return true;
                }
            }
        }
        //Bottom left
        for(int i=row+1,j=col-1;i<8 && j>=0;i++,j--){//the -1 is so that the curr pos wont be a move.
            if(b.getCell(i,j).getPiece()!=null){
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){
                    break;
                }
                if(b.getCell(i,j).getPiece() instanceof Queen || b.getCell(i,j).getPiece() instanceof Bishop){
                    return true;
                }   
            }
        }

        //check for knight and pawn checks:

        //knight:

        int[][] moves={{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};//taken from the knight class
        //exact same algorithm, just reusing differently
        for(int[] mov:moves){
            if(this.row+mov[0]>=0 && this.row+mov[0]<8 && this.col+mov[1]>=0 && this.col+mov[1]<8){//checking if inside the board
                if(b.getCell(this.row+mov[0],this.col+mov[1]).getPiece()!=null && //if there is a piece there
                b.getCell(this.row+mov[0],this.col+mov[1]).getPiece() instanceof Knight//is this piece a Knight
                ){
                    return true;
                }
            }
        }

        //pawn:
        if(this.row+1<8 && this.col+1<8 && b.getCell(this.row+1, this.col+1).getPiece()!=null){//checking if there is a pawn infront on the right of the king
            if(b.getCell(this.row+1, this.col+1).getPiece() instanceof Pawn){
                return true;
            }
        }
        if(this.row-1>=0 && this.col-1>=0 && b.getCell(this.row-1, this.col-1).getPiece()!=null){//checking if there is a pawn infront on the left of the king
            if(b.getCell(this.row+1, this.col+1).getPiece() instanceof Pawn){
                return true;
            }
        }

        //if no checks found we return false.
        return false;
    }

    public int[] whereCheck(Board b){
        //TODO:implement this method.
        //so that later we will be able to see where check comes from so that we will be able to determine is it mate. (using dfs)
        //consult with Teacher how to do this, do i add the where to the isCheck or create a seperate function
        //complications: what if the array is empty later?
        return new int[]{};
    }
}
