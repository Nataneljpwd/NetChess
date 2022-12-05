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

        for(int[] mov:dir){
            for(int r=this.row+mov[0],c=this.col+mov[1]; r<8 && c<8 && r>=0 && c>=0; r+=mov[0],c+=mov[1]){
                if(b.getCell(r,c).getPiece()==null){
                    this.possibleMoves.add(new int[]{r,c});
                }else{
                    if(b.getCell(r,c).getPiece().getIsWhite() != b.getCell(this.row, this.col).getPiece().getIsWhite()){
                        this.possibleMoves.add(new int[]{r,c});
                        break;
                    }else{
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
