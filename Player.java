//imports
import java.net.*;
import java.io.*;
import java.net.Socket;
import java.util.*;


public class Player implements Runnable{
    private Board board;//think about how move mechanism will work (almost done)
    public boolean isWhite;
    public boolean ourTurn;
    private List<Piece> pieces;
    private ConnectionHandler ch;
    String host="localhost";
    int port = 8888;
    private boolean isGameOver=false;

    public Player(){
        this.pieces=new ArrayList<>();
        this.ch=new ConnectionHandler(host,port);
        this.ch.run();
    }

    public ConnectionHandler getConnectionHandler(){
        return this.ch;
    }

    public static void main(String[] args) throws Exception {
        Player p=new Player();
        p.playerSetup(p.getConnectionHandler().getIsWhite());
        p.ch.run();
        p.run();
    }

    public void playerSetup(boolean isWhite){
        this.isWhite=isWhite;
        this.ourTurn=this.isWhite;
        this.board=new Board(this.isWhite, this.pieces);
    }

    //add a check for what pieces there are to know if its draw!.
    public boolean checkDraw(){
        int c=0;
        for(Piece p : pieces){
            if(p instanceof Bishop || p instanceof Knight){c++;}
            
            if(p.possibleMoves.size()>0){
                if(!(p instanceof King )&& !(p instanceof Bishop) && !(p instanceof Knight))c-=100;
                return false;
            }
        }
        //c is equal to 1 if we have only a bishop or only a knight
        if(c==1){
            // only the bishop or the knight is left
            return true;
        }
        return true ;
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
                ourTurn=!ourTurn;
                this.ch.move=curr[0]+","+curr[1]+" "+to[0]+","+to[1];
            }
        }
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
    //it will call the move function


    private class ConnectionHandler implements Runnable{

        private Socket s;
        private BufferedReader reader;
        private PrintWriter writer;
        private boolean ourTurn;
        private boolean isWhite;
        private boolean isGameOver=false;
        private String move;

        public ConnectionHandler(String host,int port){
            try {
                s=new Socket(host,port);
                reader=new BufferedReader(new InputStreamReader(s.getInputStream()));
                writer=new PrintWriter(s.getOutputStream(),true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        public boolean getIsWhite(){
            return isWhite;
        }

        public void setMove(String mv){
            this.move=mv;
        }
        
        public void sendDraw() {}

        public void sendOppWon(){}

        @Override
        public void run() {
            // TODO Auto-generated method stub
            String msg="";
            try {
                //first we send a message wheter the playee is white of=r black
                msg=this.reader.readLine();
                this.isWhite = Integer.parseInt(msg)%2==0;
                this.ourTurn=this.isWhite;    
            } catch (Exception e) {
                // TODO: handle exception
            }
            while(!this.isGameOver){
                try{
                    if(ourTurn){
                        if(!this.move.equals("")){
                            //recieve the move we made
                            
                            writer.println("OPP"+this.move);
                            this.move="";
                            ourTurn=!ourTurn;
                        }
                    }else{
                        msg=reader.readLine();
                        this.move="OPP "+msg;
                        //make the move
                        ourTurn=!ourTurn;
                    }
                }catch(Exception e){
                    //this.isGameOver=true;
                    //TODO handle exception
                }finally{
                    try {
                        this.isGameOver=true;
                        this.reader.close();
                        this.writer.close();
                        this.s.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }               
            }          
        }

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(!isGameOver){
            //we recieve move
            if(!this.ch.move.equals("")){
                if(this.ch.move.startsWith("OPP") && !ourTurn){
                    //we make the move for ourselves
                    String[] conv=this.ch.move.substring(4).split(" ");
                    int[] from={Integer.parseInt(conv[0].split(",")[0]),Integer.parseInt(conv[0].split(",")[1])};
                    int[] to={Integer.parseInt(conv[1].split(",")[0]),Integer.parseInt(conv[1].split(",")[1])};
                    from[0]=7-from[0];
                    from[1]=7-from[1];
                    to[0]=7-to[0];
                    to[1]=7-to[1];
                    if(this.board.remove!=null){
                        this.pieces.remove(this.board.remove);
                        this.board.remove=null;
                    }
                    this.board.move(from, to, isWhite);
                    this.ourTurn=!ourTurn;
                    int a =this.situationCheck();
                    if(a==1){
                        //Makwe a sound or vibration
                    }else if(a==2){
                        isGameOver=true;
                        sendOppWon();
                    }else if(a==3){
                        sendDraw();
                    }
                }else if(ourTurn){
                    String[] conv=this.ch.move.split(" ");
                    int[] from={Integer.parseInt(conv[0].split(",")[0]),Integer.parseInt(conv[0].split(",")[1])};
                    int[] to={Integer.parseInt(conv[1].split(",")[0]),Integer.parseInt(conv[1].split(",")[1])};
                    this.board.move(from, to, isWhite);
                    this.ch.setMove(from[0]+","+from[1]+" "+to[0]+","+to[1]);

                    this.ourTurn=!this.ourTurn;
                    //here we get the move 
                }
            }
        }
    }

    private void sendDraw() {
        this.ch.sendDraw();
    }

    private void sendOppWon() {
        this.ch.sendOppWon();
    }
}
