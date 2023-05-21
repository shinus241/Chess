import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
public class King extends Piece{
    
    private ArrayList<Piece> pieces; 

    public King(int x, int y, int w, int h, boolean c, ArrayList<Piece> p){
        super(x, y, w, h, c); 
        pieces = p;
    }

    public boolean[][] getLegal(int[][] board){
        boolean[][] legal = new boolean[8][8];
        int col = getX() / 100;
        int row = getY() / 100;
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int rowDisp = Math.abs(row - r);
                int colDisp = Math.abs(col -c);
                if(((rowDisp == 1 && colDisp < 2) || (colDisp == 1 && rowDisp < 2)) && !(row == r && col == c) && !Game.moveCauseCheck(row, col, r, c, getColor())){
                    legal[r][c] = true;
                }
                else{
                    legal[r][c] = false;
                }
            }
        }
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if(legal[r][c]){
                    for(int i = 0; i < pieces.size(); i++){
                        int otherRow = pieces.get(i).getY() / 100;
                        int otherCol = pieces.get(i).getX() / 100;
                        if(otherRow == r && otherCol == c && pieces.get(i).isProtected()){
                            legal[r][c] = false;
                        }
                    }
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
        return legal;
    }

    public boolean isChecking(int[][] b){
        return false;
    }

    public boolean isProtected(){
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
