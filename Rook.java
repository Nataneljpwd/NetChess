//imports
import java.util.*;



public class Rook extends Piece{

    private List<int[]> up;
    private List<int[]> down;
    private List<int[]> left;
    private List<int[]> right;

    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
        this.up=new ArrayList<int[]>();
        this.down=new ArrayList<int[]>();
        this.left=new ArrayList<int[]>();
        this.right=new ArrayList<int[]>();
    }

    //methods

    public void calculateMoves(Board b){
        List<List<int[]>>lst=new ArrayList();
        lst.add(left);
        lst.add(right);
        lst.add(up);
        lst.add(down);

        //adds all vertical moves
        //first the up moves:
        for(int i=this.row-1;i>=0;i--){
            if(b.getCell(i, this.col).getPiece()==null){
                this.up.add(new int[] {i,this.col});
            }else {
                if(b.getCell(i, this.col).getPiece().isWhite==super.isWhite){
                    //if the same color then we stop because we cand continue
                    break;
                }else{
                    //if different we add the eating and then we stop
                    this.up.add(new int[] {i,this.col});
                    break;
                }
            }
        }

        //down moves:
        for(int i=1+this.row;i<8;i++){
            if(b.getCell(i, this.col).getPiece()==null){
                this.down.add(new int[] {i,this.col});
            }else{
                //check the same thing for the down as the up:
                if(b.getCell(i, this.col).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    this.down.add(new int[] {i,this.col});
                    break;
                }
            }
        }

        //right moves:
        for(int i=this.col+1;i<8;i++){
            if(b.getCell(i, this.col).getPiece()==null){
                this.right.add(new int[] {this.row,i});
            }else{
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    this.right.add(new int[] {this.row,i});
                    break;
                }
            }
        }
        //left moves:
        for(int i=this.col-1;i>=0;i--){
            if(b.getCell(this.row,i).getPiece()==null){
                this.left.add(new int[] {row,i});
            }else{
                if(b.getCell(this.row,i).getPiece().isWhite==this.isWhite){
                    break;
                }else{
                    this.left.add(new int[] {this.row,i});
                    break;
                }
            }
        }
        //add moves to Moves arrayList so that we will be able to validate later.

        for(int i=0;i<lst.size();i++){
            for(int j=0;j<lst.get(i).size();j++){
                //adding all the possible moves to the arrayList.
                super.possibleMoves.add(lst.get(i).get(j));
            }
        }
        validateMoves(b);
    }

    public void validateMoves(Board b){
        //checking for pinned pieces
        List<int[]> moves=this.possibleMoves;
        List<int[]> remove=new ArrayList();
        int[] currPos;
        //check what moves to remove:
        for(int[] mov:moves){
            //move the piece without drawing
            currPos=this.getRawPos();
            this.move(mov[0],mov[1]);
            if(b.isCheck(this.isWhite)){
                remove.add(mov);
            }
            //move the piece back
            this.move(currPos[0],currPos[1]);
        }
        //remove the invalid moves
        for(int[] mov:remove){
            moves.remove(mov);
        }
        this.setMoves(moves);
    }
    
}
