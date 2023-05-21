import java.awt.*;
public class Pawn extends Piece{
    public Pawn(int x, int y, int w, int h, boolean c){
       super(x, y, w, h, c); 
    }

    public boolean[][] getLegal(int[][] board){
        boolean[][] legal = new boolean[8][8];
        int col = getX() / 100;
        int row = getY() / 100;
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                legal[r][c] = false;
            }
        }
        if(getColor()){ // white
            if(row == 6){
                if(col == 0){
                    if(board[row - 2][col] == Game.EMPTY && board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 2, col, getColor())){
                        legal[row - 2][col] = true;
                        legal[row - 1][col] = true;
                    }
                    else if(board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 1, col, getColor())){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col + 1, getColor())){
                        legal[row - 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row - 2][col] == Game.EMPTY && board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 2, col, getColor())){
                        legal[row - 2][col] = true;
                        legal[row - 1][col] = true;
                    }
                    else if(board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 1, col, getColor())){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col - 1, getColor())){
                        legal[row - 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row - 2][col] == Game.EMPTY && board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 2, col, getColor())){
                        legal[row - 2][col] = true;
                        legal[row - 1][col] = true;
                    }
                    else if(board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 1, col, getColor())){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col + 1, getColor())){
                        legal[row - 1][col + 1] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col - 1, getColor())){
                        legal[row - 1][col - 1] = true;
                    }
                }
            }
            else if(row > 0){
                if(col == 0){
                    if(board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 1, col, getColor())){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col + 1, getColor())){
                        legal[row - 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 1, col, getColor())){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col - 1, getColor())){
                        legal[row - 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row - 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row - 1, col, getColor())){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col + 1, getColor())){
                        legal[row - 1][col + 1] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING && !Game.moveCauseCheck(row, col, row - 1, col - 1, getColor())){
                        legal[row - 1][col - 1] = true;
                    }
                }
            }
        }
        else{ // black
            if(row == 1){
                if(col == 0){
                    if(board[row + 2][col] == Game.EMPTY && board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 2, col, getColor())){
                        legal[row + 2][col] = true;
                        legal[row + 1][col] = true;
                    }
                    else if(board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col, getColor())){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col + 1, getColor())){
                        legal[row + 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row + 2][col] == Game.EMPTY && board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 2, col, getColor())){
                        legal[row + 2][col] = true;
                        legal[row + 1][col] = true;
                    }
                    else if(board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col, getColor())){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col - 1, getColor())){
                        legal[row + 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row + 2][col] == Game.EMPTY && board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 2, col, getColor())){
                        legal[row + 2][col] = true;
                        legal[row + 1][col] = true;
                    }
                    else if(board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col, getColor())){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col + 1, getColor())){
                        legal[row + 1][col + 1] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col - 1, getColor())){
                        legal[row + 1][col - 1] = true;
                    }
                }
            }
            else if(row < 7){
                if(col == 0){
                    if(board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col, getColor())){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col + 1, getColor())){
                        legal[row + 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col, getColor())){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col - 1, getColor())){
                        legal[row + 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row + 1][col] == Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col, getColor())){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col + 1, getColor())){
                        legal[row + 1][col + 1] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY && !Game.moveCauseCheck(row, col, row + 1, col - 1, getColor())){
                        legal[row + 1][col - 1] = true;
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
                legal[r][c] = false;
            }
        }
        if(getColor()){ // white
            if(row > 0){
                if(col == 0){
                    legal[row - 1][col + 1] = true;
                }
                else if(col == 7){
                    legal[row - 1][col - 1] = true;
                }
                else{
                    legal[row - 1][col + 1] = true;
                    legal[row - 1][col - 1] = true;
                }
            }
        }
        else{ // black
            if(row < 7){
                if(col == 0){
                    legal[row + 1][col + 1] = true;
                }
                else if(col == 7){
                    legal[row + 1][col - 1] = true;
                }
                else{
                    legal[row + 1][col + 1] = true;
                    legal[row + 1][col - 1] = true;
                }
            }
        }
        return legal;
    }

    public boolean isChecking(int[][] b){
        boolean[][] legal = getLegal2(b);
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if(legal[r][c] && ((getColor() && b[r][c] == Game.bKING) || (!getColor() && b[r][c] == Game.wKING))){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isProtected(){
        int row = getY() / 100;
        int col = getX() / 100;
        boolean[][] space = Game.getSpace(getColor());
        return space[row][col];
    }

    public Image getImage() {
        if(getColor()){
            return ImageLoader.loadCompatibleImage("whitePawn.png");
        }
        return ImageLoader.loadCompatibleImage("blackPawn.png");
    }

    public int getType() {
        if(getColor()){
            return Game.wPAWN;
        }
        return Game.bPAWN;
    }
}
