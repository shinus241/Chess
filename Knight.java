import java.awt.*;
public class Knight extends Piece{
    public Knight(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
     }
 
     public Image getImage() {
         if(getColor()){
             return ImageLoader.loadCompatibleImage("whiteKnight.png");
         }
         return ImageLoader.loadCompatibleImage("blackKnight.png");
     }
}
