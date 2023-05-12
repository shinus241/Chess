import java.awt.*;
public class Bishop extends Piece{
    public Bishop(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
     }
 
     public Image getImage() {
         if(getColor()){
             return ImageLoader.loadCompatibleImage("whiteBishop.png");
         }
         return ImageLoader.loadCompatibleImage("blackBishop.png");
     }
}
