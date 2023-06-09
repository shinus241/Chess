import java.awt.*;
public abstract class Piece {
    private Rectangle rect;
    private boolean color;
    private int x;
    private int y;
    private boolean selected = false;
    private boolean canBlockCheck = false;
    private boolean hasMoved = false;
    public Piece(int x1, int y1, int w, int h, boolean c){
        rect = new Rectangle(x1, y1, w, h);
        x = x1;
        y = y1;
        color = c;
    }

    public abstract boolean[][] getLegal(int[][] b);

    public abstract boolean[][] getLegal2(int[][] b);

    public abstract boolean isChecking(int[][] b);

    public abstract boolean isProtected();

    public boolean hasMoved(){
        return hasMoved;
    }

    public boolean canBlockCheck(){
        return canBlockCheck;
    }

    public void setIfCanBlockCheck(boolean b){
        canBlockCheck = b;
    }

    public boolean selected(){
        return selected;
    }

    public void set(boolean t){
        selected = t;
    }

    public boolean intersects(int x1, int y1){
        return (x1 > x && x1 < x + 99) && (y1 > y && y1 < y +99);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setPosition(int x1, int y1){
        x = x1;
        y = y1;
        hasMoved = true;
    }

    public boolean getColor(){
        return color;
    }

    public Rectangle getRectangle(){
        return rect;
    }

    public void draw(Graphics g){
        g.drawImage(getImage(), x, y, rect.width, rect.height, null);
    }

    public Image getImage() {
        return ImageLoader.loadCompatibleImage("whitePawn.png");
    }

    public int getType(){
        return -1;
    }
}
