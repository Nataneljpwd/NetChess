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
        bRight=new ArrayList<>();

        List<List<int[]>> lst=new ArrayList<>();
        lst.add(tLeft);
        lst.add(tRight);
        lst.add(bLeft);
        lst.add(bRight);

        int[][] dir={{-1,-1},{-1,1},{1,-1},{-1,-1}};
        //top left, top right, bottom left, bottom right

        for(int i=0;i<dir.length;i++){
            for(int r=this.row,c=this.col;r>=0 && c>=0 && r<8 && c<8;r+=dir[i][0], c+=dir[i][1]){
                //if first time we dont check curr pos,(possible not to add this cond)
                //Delete if needed
                if(r==this.row && c==this.col){
                    r+=dir[i][0];
                    c+=dir[i][1];
                }
                if(i==0){
                    if(b.getCell(r,c).getPiece()==null){
                        tLeft.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            tLeft.add(new int[] {r,c});
                            break;
                        }
                    }
                }

                else if(i==1){
                    if(b.getCell(r,c).getPiece()==null){
                        tLeft.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            tLeft.add(new int[] {r,c});
                            break;
                        }
                    }
                }
                
                else if(i==2){
                    if(b.getCell(r,c).getPiece()==null){
                        tLeft.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            tLeft.add(new int[] {r,c});
                            break;
                        }
                    }
                }
                
                else{
                    if(b.getCell(r,c).getPiece()==null){
                        tLeft.add(new int[]{r,c});
                    }else{
                        if(b.getCell(r,c).getPiece().isWhite==this.isWhite){
                            break;
                        }else{
                            tLeft.add(new int[] {r,c});
                            break;
                        }
                    }
                }
            }
        }
        
        for(int i=0;i<lst.size();i++){
            for(int j=0;j<lst.get(i).size();j++){
                //adding all the possible moves to the arrayList.
                super.possibleMoves.add(lst.get(i).get(j));
            }
        }

        validateMoves(b);
    }

}
