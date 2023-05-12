import java.awt.*;
import java.util.ArrayList;
public class Piece {
    private Rectangle rect;

    public Piece(int x, int y, int w, int h){
        rect = new Rectangle(x, y, w, h);
    }

    public Rectangle getRectangle(){
        return rect;
    }

    public void draw(Graphics g){
        g.drawImage(getImage(), rect.x, rect.y, rect.width, rect.getHeight(), null);
    }
}
