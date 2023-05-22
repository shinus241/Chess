import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
public class King extends Piece{
    
    private ArrayList<Piece> pieces;

    public King(int x, int y, int w, int h, boolean c, ArrayList<Piece> p){
        super(x, y, w, h, c); 
        pieces = p;
    }

    /*
     * returns:
     * 0 - Unable to castle
     * 1 - Can castle to the right
     * 2 - Can castle to the left
     * 3 - Can castle in either direction
     */
    public int canCastle(int[][] board){ 
        int col = getX() / 100;
        int row = getY() / 100;
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getColor() == getColor()){
                if((pieces.get(i).getType() == Game.wKING || pieces.get(i).getType() == Game.bKING) && pieces.get(i).hasMoved()){
                    return 0;
                }
            }
        }
        if(getColor()){
            boolean right = false;
            boolean left = false;
            for(int i = 0; i < pieces.size(); i++){
                if(pieces.get(i).getType() == Game.wROOK){
                    int tempRow = pieces.get(i).getY() / 100;
                    int tempCol = pieces.get(i).getX() / 100;
                    if(tempRow == 7 && tempCol == 0 && !pieces.get(i).hasMoved()){
                        if(board[7][1] == Game.EMPTY && board[7][2] == Game.EMPTY && board[7][3] == Game.EMPTY && !Game.moveCauseCheck(row, col, 7, 1, getColor()) && !Game.moveCauseCheck(row, col, 7, 2, getColor()) && !Game.moveCauseCheck(row, col, 7, 3, getColor())){
                            left = true;
                        }
                    }
                }
            }
            for(int i = 0; i < pieces.size(); i++){
                if(pieces.get(i).getType() == Game.wROOK){
                    int tempRow = pieces.get(i).getY() / 100;
                    int tempCol = pieces.get(i).getX() / 100;
                    if(tempRow == 7 && tempCol == 7 && !pieces.get(i).hasMoved()){
                        if(board[7][5] == Game.EMPTY && board[7][6] == Game.EMPTY && !Game.moveCauseCheck(row, col, 7, 5, getColor()) && !Game.moveCauseCheck(row, col, 7, 6, getColor())){
                            right = true;
                        }
                    }
                }
            }
            if(left && right){
                return 3;
            }
            else if(left){
                return 2;
            }
            else if(right){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            boolean right = false;
            boolean left = false;
            for(int i = 0; i < pieces.size(); i++){
                if(pieces.get(i).getType() == Game.bROOK){
                    int tempRow = pieces.get(i).getY() / 100;
                    int tempCol = pieces.get(i).getX() / 100;
                    if(tempRow == 0 && tempCol == 0 && !pieces.get(i).hasMoved()){
                        if(board[0][1] == Game.EMPTY && board[0][2] == Game.EMPTY && board[0][3] == Game.EMPTY && !Game.moveCauseCheck(row, col, 0, 1, getColor()) && !Game.moveCauseCheck(row, col, 0, 2, getColor()) && !Game.moveCauseCheck(row, col, 0, 3, getColor())){
                            left = true;
                        }
                    }
                }
            }
            for(int i = 0; i < pieces.size(); i++){
                if(pieces.get(i).getType() == Game.bROOK){
                    int tempRow = pieces.get(i).getY() / 100;
                    int tempCol = pieces.get(i).getX() / 100;
                    if(tempRow == 0 && tempCol == 7 && !pieces.get(i).hasMoved()){
                        if(board[0][5] == Game.EMPTY && board[0][6] == Game.EMPTY && !Game.moveCauseCheck(row, col, 0, 5, getColor()) && !Game.moveCauseCheck(row, col, 0, 6, getColor())){
                            right = true;
                        }
                    }
                }
            }
            if(left && right){
                return 3;
            }
            else if(left){
                return 2;
            }
            else if(right){
                return 1;
            }
            else{
                return 0;
            }
        }
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
        int castle = canCastle(board);
        if(castle == 3){
            legal[row][2] = true;
            legal[row][6] = true; 
        }
        else if(castle == 2){
            legal[row][2] = true;
        }
        else if(castle == 1){
            legal[row][6] = true;
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
