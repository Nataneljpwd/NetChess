//imports-------
import java.util.*;

public class King extends Piece {
    public King(int row,int col,boolean isWhite){
        super(row, col, isWhite);
    }

    public void calculateMoves(Board b){
        int[][] dir={{-1,-1},{-1,1},{1,-1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};
        for(int[] d:dir){
            if(this.row+d[0]<8 && this.row+d[0]>=0 && this.col+d[1]<8 && this.col+d[1]>=0){
                super.possibleMoves.add(new int[] {this.row+d[0], this.col+d[1]});
            }
        }
        validateMoves(b);
    }

    public boolean isCheck(Board b){
        //run BFS to check if its check+ 8 moves for Knight and 2 for pawns.
        //checking for top checks

        int[][] dir={{-1,-1},{-1,1},{1,-1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};
        //first diagonal then vertical
        Piece p;
        for(int i=0;i<dir.length;i++) {
            for(int r=this.row,c=this.col;r<8 && r>=0 && c<8 && c>=0;r+=dir[i][0],c+=dir[i][1]){
                if((p=b.getCell(r,c).getPiece())!=null){
                    if(p.isWhite!=this.isWhite){//check if not our color
                        if(i<=3){
                            if(p instanceof Queen || p instanceof Bishop){
                                return true;
                            }
                        }else{
                            if(p instanceof Queen || p instanceof Rook){
                                return true;
                            }
                        }
                    }else{
                        //we can break cuz it blocks
                        break;
                    }
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
