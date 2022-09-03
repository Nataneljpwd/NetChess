//imports-------
import java.util.*;



public class Bishop extends Piece{
    
    public Bishop(int row,int col,boolean isWhite) {
        super(row, col, isWhite);
    }

    //calculateing the moves using bfs

    public void calculateMoves(Board b){
        //the 4 directions of the piece movements
        List<int[]> tLeft= new ArrayList<>()
        ,tRight=new ArrayList<>(),
        bLeft=new ArrayList<>(),
        bRight=new ArrayList<>(),
        remove=new ArrayList<>();
        
        //start with top left moves:
        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){//the -1 is so that the curr pos wont be a move.
            if(b.getCell(i,j).getPiece()==null){
                tLeft.add(new int[] {i,j});
            }else{
                if(b.getCell(i,j).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    tLeft.add(new int[] {i,j});
                    break;
                }
            }
        }
        //then top right moves:
        for(int i=row-1,j=col+1;i>=0 && j<8;i--,j++){
            if(b.getCell(i,j).getPiece()==null){
                tRight.add(new int[] {i,j});
            }else{
                if(b.getCell(i,j).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    tRight.add(new int[] {i,j});
                    break;
                }
            }
        }
        
        //Bottom right
        for(int i=row+1,j=col+1;i<8 && j<8;i++,j++){
            if(b.getCell(i,j).getPiece()==null){
                bRight.add(new int[] {i,j});
            }else{
                if(b.getCell(i,j).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    bRight.add(new int[] {i,j});
                    break;
                }
            }
        }
        //Bottom left
        for(int i=row+1,j=col-1;i<8 && j>=0;i++,j--){//the -1 is so that the curr pos wont be a move.
            if(b.getCell(i,j).getPiece()==null){
                bLeft.add(new int[] {i,j});
            }else{
                if(b.getCell(i,j).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    bLeft.add(new int[] {i,j});
                    break;
                }
            }
        }
        validateMoves(b);
    }

}
