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

}
