//imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.concurrent.*;


public class Server{
    final int port=8888;
    private static ServerSocket ss;
    public static PlayerServer p1;
    public static PlayerServer p2;
    private final int MAX_GAMES=10;
    protected ExecutorService tpl =
        Executors.newFixedThreadPool(2*MAX_GAMES);

    public Server() throws IOException{
        //create the server
        Server.ss=new ServerSocket(port);
    }

    public void close() throws IOException{
        Server.ss.close();
    }

    public Socket accept() throws IOException{
        return Server.ss.accept();
    }

    public static class PlayerServer extends Thread{
        public boolean isWhite;
        public boolean ourTurn;
        public boolean isGameOver=false;
        public Socket s;
        public PlayerServer opp;
        private PrintWriter writer;
        private BufferedReader reader;
        
        public PlayerServer(Socket s,boolean ourTurn){
            this.s=s;
            this.ourTurn=this.isWhite=ourTurn;
            System.out.println("player connected");
            try {
                this.writer=new PrintWriter(s.getOutputStream(),true);
                this.reader=new BufferedReader(new InputStreamReader(s.getInputStream()));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        public void setOpp(PlayerServer opp){
            this.opp=opp;
        }
        
        @Override
        public void run() {
            try {
                this.writer.println(ourTurn ? "true" : "false");
            } catch (Exception e) {
                // TODO: handle exception
            }
            String msg="";
            while(!isGameOver){
                try {
                    if(ourTurn){
                        msg=this.reader.readLine();
                        if(!msg.equals("")){
                            this.opp.writer.println(msg);
                            if(msg.startsWith("GAME OVER"))
                                this.isGameOver=true;
                        }
                    }else{
                        msg=this.opp.reader.readLine();
                        if(!msg.equals("")){
                            this.writer.println(msg);
                            if(msg.startsWith("GAME OVER"))
                                this.isGameOver=true;
                        }
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    try {
                        this.writer.close();
                        this.reader.close();
                        this.s.close();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    
                }
            }
        }
    }
    

    public static void main(String[] args) throws IOException {
        Server server=new Server();
        boolean isWhite=Math.random()>=0.5;
        try {
            while(true){
                p1=new PlayerServer(server.accept(), isWhite);
                p2=new PlayerServer(server.accept(), !isWhite);
                p1.setOpp(p2);
                p2.setOpp(p1);
                server.tpl.execute(p1);
                server.tpl.execute(p2);
                isWhite=Math.random()>=0.5;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            server.close();
        }
    }
}
