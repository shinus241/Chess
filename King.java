import java.awt.*;
public class King extends Piece{
    public King(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
     }
 
     public Image getImage() {
         if(getColor()){
             return ImageLoader.loadCompatibleImage("whiteKing.png");
         }
         return ImageLoader.loadCompatibleImage("blackKing.png");
     }
}
