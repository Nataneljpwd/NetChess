//imports
import java.util.*;



public class Rook extends Piece{

    /*private List<int[]> up;
    private List<int[]> down;
    private List<int[]> left;
    private List<int[]> right;*/

    public Rook(int row, int col, boolean isWhite) {
        super(row, col, isWhite);
        /*this.up=new ArrayList<int[]>();
        this.down=new ArrayList<int[]>();
        this.left=new ArrayList<int[]>();
        this.right=new ArrayList<int[]>();*/
    }

    //methods

    public void calculateMoves(Board b){

        int[][] dir={{1,0},{0,1},{-1,0},{0,-1}};
        //down right up left

        for(int i=0;i<dir.length;i++){
            for(int r=this.row,c=this.col;r<8 && c<8 && c>=0 && r>=0;r+=dir[i][0],c+=dir[i][1]){
                if(r==this.row && c==this.col){
                    continue;
                }
                if(b.getCell(r,c).getPiece()==null){
                    super.possibleMoves.add(new int[]{r, c});
                }else{
                    if(b.getCell(r,c).getPiece().isWhite==this.isWhite){//if our color
                        break;
                    }else{//if not our color
                        super.possibleMoves.add(new int[]{r, c});
                        break;
                    }
                }
                    
            }
        }
        validateMoves(b);
    }

    @Override
    public char toChar() {
        return this.isWhite ? 'R':'r';
    }

    @Override
    public void draw() {}

    
    
}
