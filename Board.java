import java.util.*;

//---------------imports-------------------



public class Board{//add the tap listener later in android studio
    //variables 
    private BoardCell[][] board;
    private King wk;
    private King bk;
    public Piece remove;
    public boolean isFirstClick;
    int[] from;
    private Player player;

    public Board(boolean isWhite,Player p){
        this.player=p;
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
                        p.pieces.add(board[i][j].getPiece());
                    }
                    if(i==7){
                        if(j==0 || j==7){
                            //ad our colored rook
                            board[i][j].setPiece(new Rook(i, j, isWhite));
                            p.pieces.add(board[i][j].getPiece());

                        }

                        if(j==1 || j==6){
                            //add our colored horse
                            board[i][j].setPiece(new Knight(i, j, isWhite));
                            p.pieces.add(board[i][j].getPiece());
                        }

                        if(j==2 || j==5){
                            //add our colored bishop
                            board[i][j].setPiece(new Bishop(i, j, isWhite));
                            p.pieces.add(board[i][j].getPiece());
                        }

                        if(j==3){
                            //add our colored queen
                            board[i][j].setPiece(new Queen(i, j, isWhite));
                            p.pieces.add(board[i][j].getPiece());
                        }
                        if(isWhite){
                            if(j==3){
                                //add our colored queen
                                board[i][j].setPiece(new Queen(i, j, isWhite));
                                p.pieces.add(board[i][j].getPiece());
                            }
                            if(j==4){
                                if(!isWhite){
                                bk=new King(i, j, isWhite);
                                board[i][j].setPiece(bk);
                                }
                                else{
                                wk=new King(i, j, isWhite);
                                board[i][j].setPiece(wk);
                                }
                                p.pieces.add(board[i][j].getPiece());
                            } 
                        }else{
                            if(j==3){
                                if(!isWhite){
                                bk=new King(i, j, isWhite);
                                board[i][j].setPiece(bk);
                                }
                                else{
                                wk=new King(i, j, isWhite);
                                board[i][j].setPiece(wk);
                                }
                                p.pieces.add(board[i][j].getPiece());
                            }
                            if(j==4){
                                board[i][j].setPiece(new Queen(i, j, isWhite));
                                p.pieces.add(board[i][j].getPiece());
                            }
                        }
                    }
            }
        }
    }

    public void psuedoClickListener(int rawX,int rawY){
        int row=rawY%BoardCell.size;
        int col=rawX%BoardCell.size;
        Piece curr=this.getCell(row, col).getPiece();
        if(isFirstClick){
            if(curr!=null && curr.isWhite==player.isWhite){
                for(int[] mov:curr.getMoves()){
                    //if the move is ion moves, we do the logic
                    if(row==mov[0] && col==mov[1]){
                        from=new int[]{row,col};
                        isFirstClick=!isFirstClick;
                    }
                }
            }
        }else{
            row=rawY%BoardCell.size;
            col=rawX%BoardCell.size;
            for(int[] mov: curr.getMoves()){
                if(mov[0]==row && mov[1]==col){
                    player.move(from, new int[]{row,col});       
                    isFirstClick=!isFirstClick;
                    this.move(from , mov);
                }
            }
        }
    }

    public void move(int[] from,int[] to) {
        Piece p=this.getCell(to[0], to[1]).getPiece();
        if(p!=null){
            player.remove(p);
            this.board[to[0]][to[1]].setPiece(this.board[from[0]][from[1]].getPiece());
            this.board[from[0]][from[1]].setPiece(null);
        }else{
            this.board[to[0]][to[1]].setPiece(this.board[from[0]][from[1]].getPiece());
            this.board[from[0]][from[1]].setPiece(null);
        }
    }
    public void playerMove(int[] from, int[] to){

    }

    public void printBoard(){
        for(int i=0;i<this.board.length;i++){
            for(int j=0;j<this.board[i].length;j++){
                board[i][j].print();
            }
            System.out.println();
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

    public boolean isCheck(boolean isWhite){//check if it is check, here we input the color of the player
        //check kings implementation of ischeck to see the dir
        if(isWhite){
            return wk.isCheck(this);
        }else{
            return bk.isCheck(this);
        }
    }


}
