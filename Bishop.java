import java.awt.*;
import java.lang.Math;
public class Bishop extends Piece{
    public Bishop(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
    }

    public boolean[][] getLegal(int[][] board){
        boolean[][] legal = new boolean[8][8];
        int col = getX() / 100;
        int row = getY() / 100;
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                int rowDisp = Math.abs(row - r);
                int colDisp = Math.abs(col - c);
                if(rowDisp == colDisp && col != c){
                    legal[r][c] = true;
                }
                else{
                    legal[r][c] = false;
                }
            }
        }
        int disp = 1;
        boolean blocked = false;
        while(row + disp < 8 && col + disp < 8){
            if(board[row + disp][col + disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row + disp][col + disp] <= Game.wKING) || (!getColor() && board[row + disp][col + disp] > Game.wKING)){
                    legal[row + disp][col + disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row + disp][col + disp] = false;
                disp++;
            }
            else{
                disp++;
            }
        }
        disp = 1;
        blocked = false;
        while(row - disp >= 0 && col - disp >= 0){
            if(board[row - disp][col - disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row - disp][col - disp] <= Game.wKING) || (!getColor() && board[row - disp][col - disp] > Game.wKING)){
                    legal[row - disp][col - disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row - disp][col - disp] = false;
                disp++;
            }
            else{
                disp++;
            }
        }
        disp = 1;
        blocked = false;
        while(row - disp >= 0 && col + disp < 8){
            if(board[row - disp][col + disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row - disp][col + disp] <= Game.wKING) || (!getColor() && board[row - disp][col + disp] > Game.wKING)){
                    legal[row - disp][col + disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row - disp][col + disp] = false;
                disp++;
            }
            else{
                disp++;
            }
        }
        disp = 1;
        blocked = false;
        while(row + disp < 8 && col - disp >= 0){
            if(board[row + disp][col - disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row + disp][col - disp] <= Game.wKING) || (!getColor() && board[row + disp][col - disp] > Game.wKING)){
                    legal[row + disp][col - disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row + disp][col - disp] = false;
                disp++;
            }
            else{
                disp++;
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
                int colDisp = Math.abs(col - c);
                if(rowDisp == colDisp && col != c){
                    legal[r][c] = true;
                }
                else{
                    legal[r][c] = false;
                }
            }
        }
        int disp = 1;
        boolean blocked = false;
        while(row + disp < 8 && col + disp < 8){
            if(board[row + disp][col + disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row + disp][col + disp] <= Game.wKING) || (!getColor() && board[row + disp][col + disp] > Game.wKING)){
                    legal[row + disp][col + disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row + disp][col + disp] = false;
                disp++;
            }
            else{
                disp++;
            }
        }
        disp = 1;
        blocked = false;
        while(row - disp >= 0 && col - disp >= 0){
            if(board[row - disp][col - disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row - disp][col - disp] <= Game.wKING) || (!getColor() && board[row - disp][col - disp] > Game.wKING)){
                    legal[row - disp][col - disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row - disp][col - disp] = false;
                disp++;
            }
            else{
                disp++;
            }
        }
        disp = 1;
        blocked = false;
        while(row - disp >= 0 && col + disp < 8){
            if(board[row - disp][col + disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row - disp][col + disp] <= Game.wKING) || (!getColor() && board[row - disp][col + disp] > Game.wKING)){
                    legal[row - disp][col + disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row - disp][col + disp] = false;
                disp++;
            }
            else{
                disp++;
            }
        }
        disp = 1;
        blocked = false;
        while(row + disp < 8 && col - disp >= 0){
            if(board[row + disp][col - disp] != Game.EMPTY && !blocked){
                if((getColor() && board[row + disp][col - disp] <= Game.wKING) || (!getColor() && board[row + disp][col - disp] > Game.wKING)){
                    legal[row + disp][col - disp] = false;
                }
                blocked = true;
                disp++;
            }
            else if(blocked){
                legal[row + disp][col - disp] = false;
                disp++;
            }
            else{
                disp++;
            }
        }
        return legal;
    }
 
    public Image getImage() {
        if(getColor()){
            return ImageLoader.loadCompatibleImage("whiteBishop.png");
        }
        return ImageLoader.loadCompatibleImage("blackBishop.png");
    }

    public int getType() {
        if(getColor()){
            return Game.wBISHOP;
        }
        return Game.bBISHOP;
    }
}
