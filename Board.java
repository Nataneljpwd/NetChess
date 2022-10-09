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
            }
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

    public boolean isCheck(boolean isWhite){//check if it is check, here we input the color of the player
        //check kings implementation of ischeck to see the dir
        if(isWhite){
            return wk.isCheck(this);
        }else{
            return bk.isCheck(this);
        }
    }

    //TODO: implement.(in android studio)
    public void draw(boolean isWhite){}
    //We can just check if its check and we have no moves!!!! if no moves and its check than its mate!

    //Implement isMate func too.
    

}
