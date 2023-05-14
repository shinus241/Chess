import java.util.ArrayList;
public class Game{
    private int[][] board;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    public static final int EMPTY = 0;
    public static final int wPAWN = 1;
    public static final int wBISHOP = 2;
    public static final int wKNIGHT = 3;
    public static final int wROOK = 4;
    public static final int wQUEEN = 5;
    public static final int wKING = 6;
    public static final int bPAWN = 7;
    public static final int bBISHOP = 8;
    public static final int bKNIGHT = 9;
    public static final int bROOK = 10;
    public static final int bQUEEN = 11;
    public static final int bKING = 12;
    
    public Game(ArrayList<Piece> p){
        pieces = p;
        board = new int[8][8];
        for(int r = 0; r < board.length; r++){
            for(int c = 0; c < board.length; c++){
                if(r > 1 && r < 6){
                    board[r][c] = EMPTY;
                }
                else if(r == 1){
                    board[r][c] = bPAWN;
                }
                else if(r == 6){
                    board[r][c] = wPAWN;
                }
                else if(r == 0){
                    if(c == 0 || c == 7){
                        board[r][c] = bROOK;
                    }
                    else if(c == 1 || c == 6){
                        board[r][c] = bKNIGHT;
                    }
                    else if(c == 2 || c == 5){
                        board[r][c] = bBISHOP;
                    }
                    else if(c == 3){
                        board[r][c] = bQUEEN;
                    }
                    else{
                        board[r][c] = bKING;
                    }
                }
                else{
                    if(c == 0 || c == 7){
                        board[r][c] = wROOK;
                    }
                    else if(c == 1 || c == 6){
                        board[r][c] = wKNIGHT;
                    }
                    else if(c == 2 || c == 5){
                        board[r][c] = wBISHOP;
                    }
                    else if(c == 3){
                        board[r][c] = wQUEEN;
                    }
                    else{
                        board[r][c] = wKING;
                    }
                }
            }
        }
    }

    public Piece selected(int x, int y){
        for(Piece p : pieces){
            if(p.intersects(x, y)){
                return p;
            }
        }
        return null;
    }

    public int[][] getBoard(){
        return board;
    }
}
