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

        int[][] dir={{1,0},{0,1},{-1,0},{0,-1}};
        //down right up left

        for(int i=0;i<dir.length;i++){
            for(int r=this.row,c=this.col;r<8 && c<8 && c>=0 && r>=0;r+=dir[i][0],c+=dir[i][1]){
                if(r==this.row && c==this.col){
                    r+=dir[i][0];
                    c+=dir[i][1];
                }
                if(i==0){
                    if(b.getCell(r,c).getPiece()==null){
                        down.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            down.add(new int[] {r,c});
                            break;
                        }
                    }
                }else if(i==1){
                    if(b.getCell(r,c).getPiece()==null){
                        right.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            right.add(new int[] {r,c});
                            break;
                        }
                    }
                }else if(i==2){
                    if(b.getCell(r,c).getPiece()==null){
                        up.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            up.add(new int[] {r,c});
                            break;
                        }
                    }
                }else{
                    if(b.getCell(r,c).getPiece()==null){
                        left.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            left.add(new int[] {r,c});
                            break;
                        }
                    }
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

    
    
}
