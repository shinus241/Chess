import java.awt.*;
public class Rook extends Piece{
    public Rook(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
     }
 
     public Image getImage() {
         if(getColor()){
             return ImageLoader.loadCompatibleImage("whiteRook.png");
         }
         return ImageLoader.loadCompatibleImage("blackRook.png");
     }
}
