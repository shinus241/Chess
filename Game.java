import java.util.ArrayList;
public class Game{
    private static int[][] board;
    private static ArrayList<Piece> pieces = new ArrayList<Piece>();
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
    private static boolean stop = false;
    
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

    public void move(int origX, int origY, int x1, int y1){
        int temp = board[origY][origX];
        if(((temp == wPAWN) && (y1 == 0) && (origY == 1)) || ((temp == bPAWN) && (y1 == 7) && (origY == 6))){ // CHECK FOR PROMOTION CONDITIONS
            board[origY][origX] = EMPTY;
            if(temp == wPAWN){
                board[y1][x1] = wQUEEN;
                for(int i = 0; i < pieces.size(); i++){
                    int tempx = pieces.get(i).getX() / 100;
                    int tempy = pieces.get(i).getY() / 100;
                    if(tempx == x1 && tempy == y1 && pieces.get(i).selected()){
                        pieces.add(new Queen(tempx * 100, tempy * 100, 100, 100, true));
                        pieces.remove(i);
                        break;
                    }
                }
                for(int i = 0; i < pieces.size(); i++){
                    int tempx = pieces.get(i).getX() / 100;
                    int tempy = pieces.get(i).getY() / 100;
                    if(tempx == x1 && tempy == y1 && !pieces.get(i).selected() && pieces.get(i).getType() != wQUEEN){
                        pieces.remove(i);
                        break;
                    }
                }
            }
            else{
                board[y1][x1] = bQUEEN;
                for(int i = 0; i < pieces.size(); i++){
                    int tempx = pieces.get(i).getX() / 100;
                    int tempy = pieces.get(i).getY() / 100;
                    if(tempx == x1 && tempy == y1 && pieces.get(i).selected()){
                        pieces.add(new Queen(tempx * 100, tempy * 100, 100, 100, false));
                        pieces.remove(i);
                        break;
                    }
                }
                for(int i = 0; i < pieces.size(); i++){
                    int tempx = pieces.get(i).getX() / 100;
                    int tempy = pieces.get(i).getY() / 100;
                    if(tempx == x1 && tempy == y1 && !pieces.get(i).selected() && pieces.get(i).getType() != bQUEEN){
                        pieces.remove(i);
                        break;
                    }
                }
            }
        }
        else if(temp == wKING || temp == bKING){
            board[origY][origX] = EMPTY;
            board[y1][x1] = temp;
            if(temp == wKING && origY == 7 && origX == 4){
                if(y1 == 7 && x1 == 6){
                    for(int i = 0; i < pieces.size(); i++){
                        int tempx = pieces.get(i).getX() / 100;
                        int tempy = pieces.get(i).getY() / 100;
                        if(tempx == 7 && tempy == 7){
                            board[7][7] = EMPTY;
                            board[7][5] = wROOK;
                            pieces.get(i).setPosition(500, 700);
                        }
                    }
                }
                if(y1 == 7 && x1 == 2){
                    for(int i = 0; i < pieces.size(); i++){
                        int tempx = pieces.get(i).getX() / 100;
                        int tempy = pieces.get(i).getY() / 100;
                        if(tempx == 0 && tempy == 7){
                            board[7][0] = EMPTY;
                            board[7][3] = wROOK;
                            pieces.get(i).setPosition(300, 700);
                        }
                    }
                }
                else{
                    for(int i = 0; i < pieces.size(); i++){
                        int tempx = pieces.get(i).getX() / 100;
                        int tempy = pieces.get(i).getY() / 100;
                        if(tempx == x1 && tempy == y1 && !pieces.get(i).selected()){
                            pieces.remove(i);
                            break;
                        }
                    }
                }
            }
            else if(temp == bKING && origY == 0 && origX == 4){
                if(y1 == 0 && x1 == 6){
                    for(int i = 0; i < pieces.size(); i++){
                        int tempx = pieces.get(i).getX() / 100;
                        int tempy = pieces.get(i).getY() / 100;
                        if(tempx == 7 && tempy == 0){
                            board[0][7] = EMPTY;
                            board[0][5] = bROOK;
                            pieces.get(i).setPosition(500, 0);
                        }
                    }
                }
                if(y1 == 0 && x1 == 2){
                    for(int i = 0; i < pieces.size(); i++){
                        int tempx = pieces.get(i).getX() / 100;
                        int tempy = pieces.get(i).getY() / 100;
                        if(tempx == 0 && tempy == 0){
                            board[0][0] = EMPTY;
                            board[0][3] = bROOK;
                            pieces.get(i).setPosition(300, 0);
                        }
                    }
                }
                else{
                    for(int i = 0; i < pieces.size(); i++){
                        int tempx = pieces.get(i).getX() / 100;
                        int tempy = pieces.get(i).getY() / 100;
                        if(tempx == x1 && tempy == y1 && !pieces.get(i).selected()){
                            pieces.remove(i);
                            break;
                        }
                    }
                }
            }
        }
        else{
            board[origY][origX] = EMPTY;
            board[y1][x1] = temp;
            for(int i = 0; i < pieces.size(); i++){
                int tempx = pieces.get(i).getX() / 100;
                int tempy = pieces.get(i).getY() / 100;
                if(tempx == x1 && tempy == y1 && !pieces.get(i).selected()){
                    pieces.remove(i);
                    break;
                }
            }
        }
    }

    public static boolean[][] getEnemySpace(boolean color){ // color passed in is the piece's color
        boolean[][] enemySpace = new boolean[8][8];
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getColor() != color){
                boolean[][] enemyLegal = pieces.get(i).getLegal2(board);
                for(int r = 0; r < 8; r++){
                    for(int c = 0; c < 8; c++){
                        if(enemyLegal[r][c]){
                            enemySpace[r][c] = true;
                        }
                    }
                } 
            }
        }
        return enemySpace;
    }

    public static boolean checked(boolean color){ //color passed in is the piece's color
        boolean[][] enemySpace = getEnemySpace(color);
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if((color && board[r][c] == wKING && enemySpace[r][c]) || (!color && board[r][c] == bKING && enemySpace[r][c])){
                    return true;
                }
            }
        }
        return false;
    }

    public static Piece getEnemyChecking(boolean color){ // Color that the piece is
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).isChecking(board) && (pieces.get(i).getColor() != color)){
                return pieces.get(i);
            }
        }
        return null;
    }

    public static boolean[][] setPiecesThatCanBlockCheck(Piece pieceChecking){
        boolean[][] attackedSquares = new boolean[8][8];
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                attackedSquares[r][c] = false;
            }
        }
        int row = pieceChecking.getY() / 100;
        int col = pieceChecking.getX() / 100;
        boolean color = pieceChecking.getColor();
        for(int i = 0; i < pieces.size(); i++){
            pieces.get(i).setIfCanBlockCheck(false);
        }
        if(pieceChecking.getType() == wPAWN || pieceChecking.getType() == bPAWN){
            for(int i = 0; i < pieces.size(); i++){
                boolean[][] legal = pieces.get(i).getLegal(board);
                if(legal[row][col]){
                    pieces.get(i).setIfCanBlockCheck(true); 
                }
            }
            attackedSquares[row][col] = true;
        }
        else if(pieceChecking.getType() == wKNIGHT || pieceChecking.getType() == bKNIGHT){
            for(int i = 0; i < pieces.size(); i++){
                boolean[][] legal = pieces.get(i).getLegal(board);
                if(legal[row][col]){
                    pieces.get(i).setIfCanBlockCheck(true); 
                }
            }
            attackedSquares[row][col] = true;
        }
        else if(pieceChecking.getType() == wBISHOP || pieceChecking.getType() == bBISHOP){
            //first chunk to get position of king
            int kingRow = 0;
            int kingCol = 0;
            for(int i = 0; i < pieces.size(); i++){
                if((pieces.get(i).getColor() != color) && (pieces.get(i).getType() == wKING || pieces.get(i).getType() == bKING)){
                    kingCol = pieces.get(i).getX() / 100;
                    kingRow = pieces.get(i).getY() / 100;
                }
            }
            int rowDisp = (kingRow - row) / Math.abs(kingRow - row);
            int colDisp = (kingCol - col) / Math.abs(kingCol - col);
            for(int i = 0; i < Math.abs(kingRow - row); i++){
                for(int j = 0; j < pieces.size(); j++){ // JJJJJJJJJJJJJJJJJJJJJJJJJJJJJ
                    if(pieces.get(j).getColor() != color){
                        boolean[][] legal = pieces.get(j).getLegal(board);
                        if(legal[row + rowDisp * i][col + colDisp * i] && (pieces.get(j).getType() != wKING && pieces.get(j).getType() != bKING)){
                            pieces.get(j).setIfCanBlockCheck(true);
                        }
                    }
                }
                attackedSquares[row + rowDisp * i][col + colDisp * i] = true;
            }
        }
        else if(pieceChecking.getType() == wROOK || pieceChecking.getType() == bROOK){
            int kingRow = 0;
            int kingCol = 0;
            for(int i = 0; i < pieces.size(); i++){
                if((pieces.get(i).getColor() != color) && (pieces.get(i).getType() == wKING || pieces.get(i).getType() == bKING)){
                    kingCol = pieces.get(i).getX() / 100;
                    kingRow = pieces.get(i).getY() / 100;
                }
            }
            if(col == kingCol){
                int rowDisp = (kingRow - row) / Math.abs(kingRow - row);
                for(int i = 0; i < Math.abs(kingRow - row); i++){
                    for(int j = 0; j < pieces.size(); j++){ //JJJJJJJJJJJJJJJJJJJJJJJJJ
                        if(pieces.get(j).getColor() != color){
                            boolean[][] legal = pieces.get(j).getLegal(board);
                            if(legal[row + rowDisp * i][col] && (pieces.get(j).getType() != wKING && pieces.get(j).getType() != bKING)){
                                pieces.get(j).setIfCanBlockCheck(true);
                            }
                        }
                    }
                    attackedSquares[row + rowDisp * i][col] = true;
                }
            }
            else{
                int colDisp = (kingCol - col) / Math.abs(kingCol - col);
                for(int i = 0; i < Math.abs(kingCol - col); i++){
                    for(int j = 0; j < pieces.size(); j++){ //JJJJJJJJJJJJJJJJJJJJJJJJJJ
                        if(pieces.get(j).getColor() != color){
                            boolean[][] legal = pieces.get(j).getLegal(board);
                            if(legal[row][col + colDisp * i] && (pieces.get(j).getType() != wKING && pieces.get(j).getType() != bKING)){
                                pieces.get(j).setIfCanBlockCheck(true);
                            }
                        }
                    }
                    attackedSquares[row][col + colDisp * i] = true;
                }
            }
        }
        else if(pieceChecking.getType() == wQUEEN || pieceChecking.getType() == bQUEEN){
            int kingRow = 0;
            int kingCol = 0;
            for(int i = 0; i < pieces.size(); i++){
                if((pieces.get(i).getColor() != color) && (pieces.get(i).getType() == wKING || pieces.get(i).getType() == bKING)){
                    kingCol = pieces.get(i).getX() / 100;
                    kingRow = pieces.get(i).getY() / 100;
                }
            }
            if(col == kingCol){
                int rowDisp = (kingRow - row) / Math.abs(kingRow - row);
                for(int i = 0; i < Math.abs(kingRow - row); i++){
                    for(int j = 0; j < pieces.size(); j++){ //JJJJJJJJJJJJJJJJJJJJJJJJJJ
                        if(pieces.get(j).getColor() != color){
                            boolean[][] legal = pieces.get(j).getLegal(board);
                            if(legal[row + rowDisp * i][col] && (pieces.get(j).getType() != wKING && pieces.get(j).getType() != bKING)){
                                pieces.get(j).setIfCanBlockCheck(true);
                            }
                        }
                    }
                    attackedSquares[row + rowDisp * i][col] = true;
                }
            }
            else if(row == kingRow){
                int colDisp = (kingCol - col) / Math.abs(kingCol - col);
                for(int i = 0; i < Math.abs(kingCol - col); i++){
                    for(int j = 0; j < pieces.size(); j++){ //JJJJJJJJJJJJJJJJJJJJJJJJJJJJ
                        if(pieces.get(j).getColor() != color){
                            boolean[][] legal = pieces.get(j).getLegal(board);
                            if(legal[row][col + colDisp * i] && (pieces.get(j).getType() != wKING && pieces.get(j).getType() != bKING)){
                                pieces.get(j).setIfCanBlockCheck(true);
                            }
                        }
                    }
                    attackedSquares[row][col + colDisp * i] = true;
                }
            }
            else{
                int rowDisp = (kingRow - row) / Math.abs(kingRow - row);
                int colDisp = (kingCol - col) / Math.abs(kingCol - col);
                for(int i = 0; i < Math.abs(kingRow - row); i++){
                    for(int j = 0; j < pieces.size(); j++){ // JJJJJJJJJJJJJJJJJJJJJJ
                        if(pieces.get(j).getColor() != color){
                            boolean[][] legal = pieces.get(j).getLegal(board);
                            if(legal[row + rowDisp * i][col + colDisp * i] && (pieces.get(j).getType() != wKING && pieces.get(j).getType() != bKING)){
                                pieces.get(j).setIfCanBlockCheck(true);
                            }
                        }
                    }
                    attackedSquares[row + rowDisp * i][col + colDisp * i] = true;
                }
            }
        }
        return attackedSquares;
    }

    public static boolean moveCauseCheck(int origY, int origX, int y1, int x1, boolean color){
        int[][] tempBoard = new int[8][8];
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                tempBoard[r][c] = board[r][c];
            }
        }
        ArrayList<Piece> tempPieces = new ArrayList<Piece>();
        for(int i = 0; i < pieces.size(); i++){
            tempPieces.add(pieces.get(i));
        }
        int temp = tempBoard[origY][origX];
        tempBoard[origY][origX] = EMPTY;
        tempBoard[y1][x1] = temp;
        for(int i = 0; i < tempPieces.size(); i++){
            int tempx = tempPieces.get(i).getX() / 100;
            int tempy = tempPieces.get(i).getY() / 100;
            if(tempx == x1 && tempy == y1 && !pieces.get(i).selected()){
                tempPieces.remove(i);
                break;
            }
        }
        return checked2(color, tempBoard, tempPieces);
    }

    public static boolean checked2(boolean color , int[][] tempBoard, ArrayList<Piece> tempPieces){ //color passed in is the piece's color
        boolean[][] enemySpace = getEnemySpace2(color, tempBoard, tempPieces);
        for(int r = 0; r < 8; r++){
            for(int c = 0; c < 8; c++){
                if((color && tempBoard[r][c] == wKING && enemySpace[r][c]) || (!color && tempBoard[r][c] == bKING && enemySpace[r][c])){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean[][] getEnemySpace2(boolean color , int[][]tempBoard, ArrayList<Piece> tempPieces){ // color passed in is the piece's color
        boolean[][] enemySpace = new boolean[8][8];
        for(Piece p : tempPieces){
            if(p.getColor() != color){
                boolean[][] enemyLegal = p.getLegal2(tempBoard);
                for(int r = 0; r < 8; r++){
                    for(int c = 0; c < 8; c++){
                        if(enemyLegal[r][c]){
                            enemySpace[r][c] = true;
                        }
                    }
                } 
            }
        }
        return enemySpace;
    }

    public static boolean[][] getSpace(boolean color){
        boolean[][] space = new boolean[8][8];
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getColor() == color){
                boolean[][] legal = pieces.get(i).getLegal2(board);
                for(int r = 0; r < 8; r++){
                    for(int c = 0; c < 8; c++){
                        if(legal[r][c]){
                            space[r][c] = true;
                        }
                    }
                }
            }
        }
        return space;
    }

    public static boolean[][] getSpace2(boolean color){
        boolean[][] space = new boolean[8][8];
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getColor() == color && (pieces.get(i).getType() != wKING && pieces.get(i).getType() != bKING)){
                boolean[][] legal = pieces.get(i).getLegal2(board);
                for(int r = 0; r < 8; r++){
                    for(int c = 0; c < 8; c++){
                        if(legal[r][c]){
                            space[r][c] = true;
                        }
                    }
                }
            }
        }
        return space;
    }

    public static boolean checkmate(boolean color1){
        for(int i = 0; i < pieces.size(); i++){
            if(((pieces.get(i).getType() == wKING) && color1) || ((pieces.get(i).getType() == bKING)) && !color1){
                boolean[][] legal = pieces.get(i).getLegal(board);
                boolean color = pieces.get(i).getColor();
                if(checked(color)){
                    Piece p = getEnemyChecking(color);
                    boolean[][] attackedSquares = Game.setPiecesThatCanBlockCheck(p);
                    int row = p.getY() / 100;
                    int col = p.getX() / 100;
                    boolean[][] space = getSpace2(color);
                    int numLegalMoves = 0;
                    for(int r = 0; r < 8; r++){
                        for(int c = 0; c < 8; c++){
                            if(legal[r][c]){
                                numLegalMoves++;
                            }
                        }
                    }
                    for(int j = 0; j < pieces.size(); j++){
                        if(pieces.get(j).canBlockCheck() && pieces.get(j).getColor() == color){
                            return false;
                        }
                    }
                    if(numLegalMoves == 0 && !space[row][col] && checked(color)){
                        stop = true;
                    }
                    return numLegalMoves == 0 && !space[row][col] && checked(color);
                }
            }
        }
        return false;
    }

    public static boolean isStalemate(boolean color){
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getColor() == color){
                boolean[][] legal = pieces.get(i).getLegal(board);
                for(int r = 0; r < 8; r++){
                    for(int c = 0; c < 8; c++){
                        if(legal[r][c]){
                            return false;
                        }
                    }
                }
            }
        }
        if(!checked(color)){
            stop = true;
        }
        return !checked(color);
    }

    public static boolean stop(){
        return stop;
    }

    public Piece selected(int x, int y){
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).intersects(x, y)){
                return pieces.get(i);
            }
        }
        return null;
    }

    public int[][] getBoard(){
        return board;
    }
}
