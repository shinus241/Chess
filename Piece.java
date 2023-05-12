import java.awt.*;
import java.util.ArrayList;
public abstract class Piece {
    private Rectangle rect;
    private boolean color;
    public Piece(int x, int y, int w, int h, boolean c){
        rect = new Rectangle(x, y, w, h);
        color = c;
    }

    public boolean getColor(){
        return color;
    }

    public Rectangle getRectangle(){
        return rect;
    }

    public void draw(Graphics g){
        g.drawImage(getImage(), rect.x, rect.y, rect.width, rect.height, null);
    }

    public Image getImage() {
        return ImageLoader.loadCompatibleImage("whitePawn.png");
    }
}
