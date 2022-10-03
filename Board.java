import java.nio.BufferOverflowException;
import java.util.*;

//---------------imports-------------------



public class Board{//add the tap listener later in android studio
    //variables 
    private BoardCell[][] board;
    private King wk;
    private King bk;
    

    public Board(boolean isWhite,List<Piece> pieces){
        
        board=new BoardCell[8][8];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                board[i][j]=new BoardCell(i,j);
                //color handling
                if((i+j)%2==0){
                    board[i][j].isWhite=true;
                }
                else{
                    board[i][j].isWhite=false;
                }
                    if(i==0){//add black backrank pieces
                        if(j==0 || j==7){
                            board[i][j].setPiece(new Rook(i,j,!isWhite));
                        }

                        if(j==1 || j==6){
                            //add a black horse
                            board[i][j].setPiece(new Knight(i, j,!isWhite));
                        }

                        if(j==2 || j==5){
                            //add a black bishop
                            board[i][j].setPiece(new Bishop(i, j, !isWhite));
                        }
                        if(isWhite){
                            if(j==3){
                                //add a black qween
                                board[i][j].setPiece(new Queen(i, j, !isWhite));
                            }
                            if(j==4){
                                if(!isWhite){
                                wk=new King(i, j, !isWhite);
                                }
                                else{
                                bk=new King(i, j, !isWhite);
                                }
                            } 
                        }else{
                            if(j==3){
                                if(!isWhite){
                                wk=new King(i, j, !isWhite);
                                }
                                else{
                                bk=new King(i, j, !isWhite);
                                }
                            }
                            if(j==4){
                                //add a black qween
                                board[i][j].setPiece(new Queen(i, j, !isWhite));
                            }
                        }

                    }
                    if(i==1){
                        //add not our color pawn
                        board[i][j].setPiece(new Pawn(i, j, !isWhite));
                    }
                    if(i==6){
                        //add our colored pawn
                        board[i][j].setPiece(new Pawn(i, j, isWhite));
                        pieces.add(board[i][j].getPiece());
                    }
                    if(i==7){
                        if(j==0 || j==7){
                            //ad our colored rook
                            board[i][j].setPiece(new Rook(i, j, isWhite));
                            pieces.add(board[i][j].getPiece());

                        }

                        if(j==1 || j==6){
                            //add our colored horse
                            board[i][j].setPiece(new Knight(i, j, isWhite));
                            pieces.add(board[i][j].getPiece());
                        }

                        if(j==2 || j==5){
                            //add our colored bishop
                            board[i][j].setPiece(new Bishop(i, j, isWhite));
                            pieces.add(board[i][j].getPiece());
                        }

                        if(j==3){
                            //add our colored queen
                            board[i][j].setPiece(new Queen(i, j, isWhite));
                            pieces.add(board[i][j].getPiece());
                        }
                        if(isWhite){
                            if(j==3){
                                //add our colored queen
                                board[i][j].setPiece(new Queen(i, j, isWhite));
                                pieces.add(board[i][j].getPiece());
                            }
                            if(j==4){
                                if(!isWhite){
                                bk=new King(i, j, isWhite);
                                }
                                else{
                                wk=new King(i, j, isWhite);
                                }
                                pieces.add(board[i][j].getPiece());
                            } 
                        }else{
                            if(j==3){
                                if(!isWhite){
                                bk=new King(i, j, isWhite);
                                }
                                else{
                                wk=new King(i, j, isWhite);
                                }
                                pieces.add(board[i][j].getPiece());
                            }
                            if(j==4){
                                //add a black queen
                                board[i][j].setPiece(new Queen(i, j, isWhite));
                                pieces.add(board[i][j].getPiece());
                            }
                        }
                    }
            }//add rendering and board building for black player ----possible to do without duping code----
        }
    }

    public BoardCell getCell(int row,int col){
        return this.board[row][col];
    }

    public BoardCell getCell(Piece p){
        for(int i=0;i<this.board.length;i++){
            for(int j=0;j<this.board.length;j++){
                if(this.board[i][j].getPiece()==p){
                    return this.board[i][j];
                }
            }
        }
        return null;
    }
    //add if clicked somewhere after piece is selected to get the row and col and check if is valid move
    //move the func to the player class.
    /*public void pieceSelected(int row,int col){
        this.board[row][col].getPiece().isSelected=true;
        List<int[]> moves=calcMoves(this.board[row][col].getPiece());//then draw all the moves
        this.board[row][col].getPiece().setMoves(moves);

    }*/

    public boolean isCheck(boolean isWhite){//check if it is check, here we input the color of the player
        if(isWhite){
            return wk.isCheck(this);
        }else{
            return bk.isCheck(this);
        }
    }

    public int[] whereCheck(boolean isWhite){
        if(isWhite){
            return wk.whereCheck(this);
        }else{
            return bk.whereCheck(this);
        }
    }//IMPORTANT! talk with teacher and consult , need to implement this method in king or integrate with whereCheck method.
    //TODO: implement.(in android studio)
    public void draw(){}

    //TODO:implement dfs function for each Square to check if it is mate
    public boolean isMate(boolean isWhite,int[] fromWhere){//gets the color of the player and from where check comes from
        //check if diagonal check,vertical/horizontal or horse.
        if(isWhite){
            int a=dirCheck(new int[] {wk.row,wk.col},fromWhere);
            if(a==0){
                //down and then up
                for(int r=wk.row,c=wk.col;r<8;r++){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
                for(int r=wk.row,c=wk.col;r>=0;r--){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
            }

            else if(a==1){
                //right then left
                for(int r=wk.row,c=wk.col;c<8;c++){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
                for(int r=wk.row,c=wk.col;c>=0;c--){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
            }

            else if(a==2){
                //bRight then bLeft
                for(int r=wk.row+1,c=wk.col+1;c<8 && r<8;c++,r++){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
                for(int r=wk.row+1,c=wk.col-1;c>=0 && r<8;c--,r++){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
                //tRight then tLeft
                for(int r=wk.row-1,c=wk.col+1;c<8 && r>=0;c++,r--){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
                for(int r=wk.row-1,c=wk.col-1;c>=0 && r>=0;c--,r--){
                    if(canBlockOrTake(r,c,isWhite)){
                        return false;
                    }
                }
            }
            else if(a==3){
                int[][] moves={{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{-1,2},{1,-2},{-1,-2}};
                for(int[] mov:moves){
                    if(canBlockOrTake(wk.row+mov[0], wk.col+mov[1], isWhite)){
                        return false;
                    }
                }
            }
        }else{
            //TODO:implement for black, try to thinl of way without useing else so no repreating.
        }
        return true;
    }
    //checks if we can block
    public boolean DFS(int row,int col,boolean isWhite){
        return false;
    }

    public int dirCheck(int[] posKing,int[]fromWhere){//checks if check is diagonal horizontal/vertical or horse
        //return 0 if vertical
        //return 1 if horizontal
        //return 2 if diagonal check
        //return 3 if Horse check
        return -1;
    }

    //canBlock-same as DFS so might delete
    public boolean canBlockOrTake(int row,int col,boolean isWhite){
        
        return false;
    }
    //Implement isMate func too.
    

}
