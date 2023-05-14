import java.awt.*;
public class Pawn extends Piece{
    public Pawn(int x, int y, int w, int h, boolean c){
       super(x, y, w, h, c); 
    }

    public boolean[][] getLegal(int[][] board){
        boolean[][] legal = new boolean[8][8];
        int col = getX() / 100;
        int row = getY() / 100;
        System.out.println(row + " " + col);
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                legal[r][c] = false;
            }
        }
        if(getColor()){ // white
            if(row == 6){
                if(col == 0){
                    if(board[row - 2][col] == Game.EMPTY){
                        legal[row - 2][col] = true;
                    }
                    if(board[row - 1][col] == Game.EMPTY){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING){
                        legal[row - 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row - 2][col] == Game.EMPTY){
                        legal[row - 2][col] = true;
                    }
                    if(board[row - 1][col] == Game.EMPTY){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING){
                        legal[row - 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row - 2][col] == Game.EMPTY && board[row - 1][col] == Game.EMPTY){
                        legal[row - 2][col] = true;
                        legal[row - 1][col] = true;
                    }
                    else if(board[row - 1][col] == Game.EMPTY){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING){
                        legal[row - 1][col + 1] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING){
                        legal[row - 1][col - 1] = true;
                    }
                }
            }
            else if(row > 1){
                if(col == 0){
                    if(board[row - 1][col] == Game.EMPTY){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING){
                        legal[row - 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row - 1][col] == Game.EMPTY){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING){
                        legal[row - 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row - 1][col] == Game.EMPTY){
                        legal[row - 1][col] = true;
                    }
                    if(board[row - 1][col + 1] > Game.wKING){
                        legal[row - 1][col + 1] = true;
                    }
                    if(board[row - 1][col - 1] > Game.wKING){
                        legal[row - 1][col - 1] = true;
                    }
                }
            }
            else{
                //promotion
            }
        }
        else{ // black
            if(row == 1){
                if(col == 0){
                    if(board[row + 2][col] == Game.EMPTY){
                        legal[row + 2][col] = true;
                    }
                    if(board[row + 1][col] == Game.EMPTY){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY){
                        legal[row + 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row + 2][col] == Game.EMPTY){
                        legal[row + 2][col] = true;
                    }
                    if(board[row + 1][col] == Game.EMPTY){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY){
                        legal[row + 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row + 2][col] == Game.EMPTY){
                        legal[row + 2][col] = true;
                    }
                    if(board[row + 1][col] == Game.EMPTY){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY){
                        legal[row + 1][col + 1] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY){
                        legal[row + 1][col - 1] = true;
                    }
                }
            }
            else if(row < 6){
                if(col == 0){
                    if(board[row + 1][col] == Game.EMPTY){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY){
                        legal[row + 1][col + 1] = true;
                    }
                }
                else if(col == 7){
                    if(board[row + 1][col] == Game.EMPTY){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY){
                        legal[row + 1][col - 1] = true;
                    }
                }
                else{
                    if(board[row + 1][col] == Game.EMPTY){
                        legal[row + 1][col] = true;
                    }
                    if(board[row + 1][col + 1] < Game.bPAWN && board[row + 1][col + 1] != Game.EMPTY){
                        legal[row + 1][col + 1] = true;
                    }
                    if(board[row + 1][col - 1] < Game.bPAWN && board[row + 1][col - 1] != Game.EMPTY){
                        legal[row + 1][col - 1] = true;
                    }
                }
            }
            else{
                //promotion
            }
        }
        return legal;
    }

    public Image getImage() {
        if(getColor()){
            return ImageLoader.loadCompatibleImage("whitePawn.png");
        }
        return ImageLoader.loadCompatibleImage("blackPawn.png");
    }
}
