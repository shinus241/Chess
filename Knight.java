import java.awt.*;
import java.lang.Math;
public class Knight extends Piece{
    public Knight(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
    }

    public boolean[][] getLegal(int[][] board){
        boolean[][] legal = new boolean[8][8];
        int col = getX() / 100;
        int row = getY() / 100;
        double hyp = Math.sqrt(5);
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int rowDisp = Math.abs(row - r);
                int colDisp = Math.abs(col - c);
                if(Math.sqrt(Math.pow(rowDisp, 2) + Math.pow(colDisp, 2)) == hyp){
                    legal[r][c] = true; 
                }
                else{
                    legal[r][c] = false;
                }
            }
        }
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if(legal[r][c] && board[r][c] != Game.EMPTY){
                    if((getColor() && board[r][c] < Game.bPAWN) || (!getColor() && board[r][c] > Game.wKING)){
                        legal[r][c] = false;
                    }
                }
            }
        }
        return legal;
    }

    public boolean[][] getLegal2(int[][] board){
        boolean[][] legal = new boolean[8][8];
        int col = getX() / 100;
        int row = getY() / 100;
        double hyp = Math.sqrt(5);
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int rowDisp = Math.abs(row - r);
                int colDisp = Math.abs(col - c);
                if(Math.sqrt(Math.pow(rowDisp, 2) + Math.pow(colDisp, 2)) == hyp){
                    legal[r][c] = true; 
                }
                else{
                    legal[r][c] = false;
                }
            }
        }
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if(legal[r][c] && board[r][c] != Game.EMPTY){
                    if((getColor() && board[r][c] < Game.bPAWN) || (!getColor() && board[r][c] > Game.wKING)){
                        legal[r][c] = false;
                    }
                }
            }
        }
        return legal;
    }
 
    public Image getImage() {
        if(getColor()){
            return ImageLoader.loadCompatibleImage("whiteKnight.png");
        }
        return ImageLoader.loadCompatibleImage("blackKnight.png");
    }

    public int getType() {
        if(getColor()){
            return Game.wKNIGHT;
        }
        return Game.bKNIGHT;
    }
}
