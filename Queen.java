import java.awt.*;
public class Queen extends Piece{
    public Queen(int x, int y, int w, int h, boolean c){
        super(x, y, w, h, c); 
     }
 
     public Image getImage() {
         if(getColor()){
             return ImageLoader.loadCompatibleImage("whiteQueen.png");
         }
         return ImageLoader.loadCompatibleImage("blackQueen.png");
     }
}
