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

    public void move(int x,int y){//add a listener that will listen where clicked after a piece was selected to get the x and y
        for(Piece p : pieces){
            if(p.isSelected){
                board.getCell(p).setPiece(null);//moves the piece from pos1 to pos2
                board.getCell(x, y).setPiece(p);
            }
        }
    }//will add the listener in the gameview in android studio which willl be a thread.
    //create inner class for connection handling------------------------------

}
