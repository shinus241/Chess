import java.awt.*;
public abstract class Piece {
    private Rectangle rect;
    private boolean color;
    private int x;
    private int y;
    private boolean selected = false;
    public Piece(int x1, int y1, int w, int h, boolean c){
        rect = new Rectangle(x1, y1, w, h);
        x = x1;
        y = y1;
        color = c;
    }

    public abstract boolean[][] getLegal(int[][] b);

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

    public void setPosition(int newX, int newY){
        x = newX;
        y = newY;
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
}
