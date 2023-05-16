import java.awt.*;
import java.lang.Math;
public class King extends Piece{
    public King(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
    }

    public boolean[][] getLegal(int[][] board){
        boolean[][] legal = new boolean[8][8];
        int col = getX() / 100;
        int row = getY() / 100;
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int rowDisp = Math.abs(row - r);
                int colDisp = Math.abs(col -c);
                if(((rowDisp == 1 && colDisp < 2) || (colDisp == 1 && rowDisp < 2)) && !(row == r && col == c)){
                    legal[r][c] = true;
                }
                else{
                    legal[r][c] = false;
                }
            }
        }
        boolean[][] checks = Game.getEnemySpace(getColor());
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if(legal[r][c]){
                    if(((getColor() && board[r][c] < Game.bPAWN && board[r][c] != Game.EMPTY) || (!getColor() && board[r][c] > Game.wKING && board[r][c] != Game.EMPTY)) || checks[r][c]){
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
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int rowDisp = Math.abs(row - r);
                int colDisp = Math.abs(col -c);
                if(((rowDisp == 1 && colDisp < 2) || (colDisp == 1 && rowDisp < 2)) && !(row == r && col == c)){
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
                    if(((getColor() && board[r][c] < Game.bPAWN) || (!getColor() && board[r][c] > Game.wKING))){
                        legal[r][c] = false;
                    }
                }
            }
        }
        return legal;
    }

    public boolean isChecking(int[][] b){
        return false;
    }
 
    public Image getImage() {
        if(getColor()){
            return ImageLoader.loadCompatibleImage("whiteKing.png");
        }
        return ImageLoader.loadCompatibleImage("blackKing.png");
    }

    public int getType() {
        if(getColor()){
            return Game.wKING;
        }
        return Game.bKING;
    }
}
