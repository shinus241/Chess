import java.awt.*;
public class Pawn extends Piece{
    public Pawn(int x, int y, int w, int h, boolean c){
       super(x, y, w, h, c); 
    }

    public Image getImage() {
        if(getColor()){
            return ImageLoader.loadCompatibleImage("whitePawn.png");
        }
        return ImageLoader.loadCompatibleImage("blackPawn.png");
    }
}
