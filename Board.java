import java.util.List;

//---------------imports-------------------



public class Board{//add the tap listener later in android studio
    //variables 
    private BoardCell[][] board;

    public Board(){
        board=new BoardCell[8][8];
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board.length;j++){
                board[i][j]=new BoardCell(i,j);
                //color handling
                if(j%2==0){
                    board[i][j].isWhite=true;
                }
                else{
                    board[i][j].isWhite=false;
                }//if(p.isWhite()){
                    if(i==0){//add black backrank pieces
                        if(j==0 || j==7){
                            //ad a black rook
                        }

                        if(j==1 || j==6){
                            //add a black horse
                        }

                        if(j==2 || j==5){
                            //add a black bishop
                        }

                        if(j==3){
                            //add a black qween
                        }

                        if(j==4){
                            //add a black king
                        }
                    }
                    if(i==1){
                        //add black pawn
                    }
                    if(i==6){
                        //add white pawn
                    }
                    if(i==7){
                        if(j==0 || j==7){
                            //ad a white rook
                        }

                        if(j==1 || j==6){
                            //add a white horse
                        }

                        if(j==2 || j==5){
                            //add a white bishop
                        }

                        if(j==3){
                            //add a white qween
                        }
                        if(j==4){
                            //add a white king
                        }
                    }
                    
            }//}else{}//add rendering and board building for black player
        }
    }

    public BoardCell getCell(int x,int y){
        return this.board[x][y];
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
    public void pieceSelected(int row,int col){
        this.board[row][col].getPiece().isSelected=true;
        List<int[]> moves=calcMoves(this.board[row][col].getPiece());//then draw all the moves
        this.board[row][col].getPiece().setMoves(moves);

    }

    public List<int[]> calcMoves(Piece p){
        List<int[]> mvs=p.getMoves();
        validate(mvs,p);
        return mvs;
    }

    public void validate(List<int[]> mvs,Piece p){
        //check if moves are possible for pawn
        if(p instanceof Pawn){
            if(((Pawn)p).isFirstMove){
                //check if nothing blocking
                try {
                    if(board[p.getRow()-2][p.getCol()]!=null){//case: if ther is a piece 2 infrong of the piece
                        mvs.remove(new int[] {p.getRow()-2,p.getCol()});
                    }
                } catch (Exception e) {
                    mvs.remove(new int[] {p.getRow()-2,p.getCol()});
                }
                validatePawnMoves(mvs, p);
                
            }else{//if is is not the first move
                validatePawnMoves(mvs, p);
            }
            
        }
        //check possible moves for rook
        if(p instanceof Rook){
            
        }

        //at the end always check if piece is not pinned using the king
    }

    //move validations

    public void validatePawnMoves(List<int[]> mvs,Piece p){
        try{
            if(board[p.getRow()-1][p.getCol()]!=null){//case: if there is a piece directly infront
                mvs.remove(new int[] {p.getRow()-2,p.getCol()});//removes the double first move
                mvs.remove(new int[] {p.getRow()-1,p.getCol()});//removes the 1 move
            }
        }catch(Exception e){
                //if outOfBounds then we remove the move
                mvs.remove(new int[] {p.getRow()-2,p.getCol()});//removes the double first move
                mvs.remove(new int[] {p.getRow()-1,p.getCol()});//removes the 1 move
        }
            //removes the taking moves if not possible
        try{
            if(board[p.getRow()-1][p.getCol()+1]==null){//case: if there isnt a piece on the right to eat
                mvs.remove(new int[] {p.getRow()-1,p.getCol()+1});
            }
        }catch(Exception e){
                //if outOfBounds then we remove the move
                mvs.remove(new int[] {p.getRow()-1,p.getCol()+1});
        }
        try{
            if(board[p.getRow()-1][p.getCol()-1]==null){//case: if there isnt a piece on the left to eat
                mvs.remove(new int[] {p.getRow()-1,p.getCol()-1});
            }
        }catch(Exception e){
                //if outOfBounds then we remove the move
                mvs.remove(new int[] {p.getRow()-1,p.getCol()-1});
        }
    }

    public void validateRookMoves(List<int[]> mvs,Piece p) {

    }
    

}
