//imports
import java.net.*;
import java.io.*;
import java.net.Socket;
import java.util.*;


public class Player {
    private Board board;//think about how move mechanism will work (almost done)
    public boolean isWhite;
    public boolean ourTurn;
    private List<Piece> pieces;

    public Player(boolean isWhite){
        this.pieces=new ArrayList<>();
        this.isWhite = isWhite;
        this.ourTurn=this.isWhite;
        this.board=new Board(isWhite,pieces);
    }

    public boolean checkDraw(){
        for(Piece p : pieces){
            if(p.possibleMoves.size()>0){
                return false;
            }
        }
        return true;
    }

    public void calculateMoves(){
        for(Piece p : pieces){
            p.calculateMoves(board);
        }
    }

    public void move(int[] curr,int[] to){//add a listener that will listen where clicked after a piece was selected to get the x and y
        Piece p=board.getCell(curr[0], curr[1]).getPiece();
        for(int[] mov:p.possibleMoves){
            if(mov[0]==to[0] && mov[1]==to[1]){
                //send the move and change ourTurn=!ourTurn.
                //Handle some of the logic for networking
                //before our turn we calculate moves for all the pieces
            }
        }
    }

    public boolean isMate(){
        return true;
    }

    //after we recieve the move:
    public int situationCheck(){
        //return 0 if nothing, 1 if check 2 if mate 3 if draw.
        if(board.isCheck(isWhite)){
            calculateMoves();
            //here if we get true then its mate because its check and there are no possible moves.
            if(checkDraw()){
                return 2;
            }else{
                return 1;
            }
        }else{
            //check for draw
            if(checkDraw()){
                return 3;
            }
        }
        return 0;
    }
    
    //will add the listener in the gameview in android studio which willl be a thread.
    //create inner class for connection handling------------------------------
    //will calculate moves for all Pieces before every move and check for draw mate and check.

}
