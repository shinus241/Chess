import javax.swing.JFrame;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
public class Component extends JComponent{
    private int mouseX;
    private int mouseY;
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Game game;
    private boolean pieceCurrentlySelected = false;
    private int turn = 0;
    public Component(){
        for(int i = 0; i < 800; i += 100){
            pieces.add(new Pawn(i, 600, 100, 100, true));
            pieces.add(new Pawn(i, 100, 100, 100, false));
            if(i == 0 || i == 700){
                pieces.add(new Rook(i, 700, 100, 100, true));
                pieces.add(new Rook(i, 0, 100, 100, false));
            }
            else if(i == 100 || i == 600){
                pieces.add(new Knight(i, 700, 100, 100, true));
                pieces.add(new Knight(i, 0, 100, 100, false));
            }
            else if(i == 200 || i == 500){
                pieces.add(new Bishop(i, 700, 100, 100, true));
                pieces.add(new Bishop(i, 0, 100, 100, false));
            }
        }
        pieces.add(new King(400, 700, 100, 100, true));
        pieces.add(new King(400, 0, 100, 100, false));
        pieces.add(new Queen(300, 700, 100, 100, true));
        pieces.add(new Queen(300, 0, 100, 100, false));
        game = new Game(pieces);
        addMouseListener(new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
                if(mouseX < 800 && mouseY < 800){
                    if(pieceCurrentlySelected){
                        for(Piece p : pieces){
                            if(p.selected()){
                                int x = mouseX / 100;
                                int y = mouseY / 100;
                                if(p.getLegal(game.getBoard())[y][x]){ // UPDATING POSITION OF A PIECE HERE
                                    int origX = p.getX();
                                    int origY = p.getY();
                                    p.setPosition(x * 100, y * 100);
                                    System.out.println(x + " " + y);
                                    pieceCurrentlySelected = false;
                                    game.move(origX / 100, origY / 100, x, y);
                                    p.set(false);
                                    turn++;
                                    break;
                                }
                                else{
                                    p.set(false);
                                    pieceCurrentlySelected = false;
                                }
                            }
                        }
                    }
                    else{ //WHAT HAPPENS WHEN A PIECE IS SELECTED
                        if(game.checked(turn % 2 == 0)){
                            System.out.println("this color is in check");
                            Piece pieceThatIsChecking = game.getEnemyChecking(turn % 2 == 0);
                            //find if you can block the check or if you have to move the king
                            //write a getPiecesThatCanBlockCheck method
                            ArrayList<Piece> piecesThatCanBlock = game.getPiecesThatCanBlockCheck(pieceThatIsChecking);
                            Piece selected = game.selected(mouseX, mouseY);
                            if(selected != null && ((selected.getColor() && turn % 2 == 0 )||(!selected.getColor() && turn % 2 != 0))){
                                if(selected.canBlockCheck()){
                                    selected.set(true);
                                    pieceCurrentlySelected = true;
                                }
                            }
                        }
                        else if(!game.checked(turn % 2 == 0)){
                            Piece selected = game.selected(mouseX, mouseY);
                            if(selected != null && ((selected.getColor() && turn % 2 == 0)||(!selected.getColor() && turn % 2 != 0))){
                                selected.set(true);
                                pieceCurrentlySelected = true;
                            }
                        }
                    }
                }
            }
        });
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Component component = new Component();
        frame.add(component);
        frame.setSize(800,800);
        frame.setVisible(true);
        while(true){
            frame.repaint();
        }
    }
    public void paintComponent(Graphics g){
        g.setColor(new Color(76, 153, 0));
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                g.drawRect(i * 100, j * 100, 100, 100);
                if((i+j) % 2 != 0){
                    g.fillRect(i * 100, j * 100, 100, 100);
                }
            }
        }
        for(Piece p : pieces){
            p.draw(g);
            if(p.selected()){
                boolean[][] temp = p.getLegal(game.getBoard());
                for(int r = 0; r < 8; r++){
                    for(int c = 0; c < 8; c++){
                        if(temp[r][c]){
                            g.setColor(new Color(0, 0, 255));
                            g.drawOval(c * 100, r * 100, 100, 100);
                        }
                    }
                }
            }
        }
    }
}