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
    public Component(){
        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e){
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Component component = new Component();
        frame.add(component);
        frame.setSize(800,800);
        frame.setVisible(true);
        for(int i = 0; i < 800; i += 100){
            component.pieces.add(new Pawn(i, 600, 100, 100, true));
            component.pieces.add(new Pawn(i, 100, 100, 100, false));
            if(i == 0 || i == 700){
                component.pieces.add(new Rook(i, 700, 100, 100, true));
                component.pieces.add(new Rook(i, 0, 100, 100, false));
            }
            else if(i == 100 || i == 600){
                component.pieces.add(new Knight(i, 700, 100, 100, true));
                component.pieces.add(new Knight(i, 0, 100, 100, false));
            }
            else if(i == 200 || i == 500){
                component.pieces.add(new Bishop(i, 700, 100, 100, true));
                component.pieces.add(new Bishop(i, 0, 100, 100, false));
            }
        }
        component.pieces.add(new King(300, 700, 100, 100, true));
        component.pieces.add(new King(400, 0, 100, 100, false));
        component.pieces.add(new Queen(400, 700, 100, 100, true));
        component.pieces.add(new Queen(300, 0, 100, 100, false));
        while(true){
            frame.repaint();
        }
    }
    public void paintComponent(Graphics g){
        g.setColor(new Color(76, 153, 0));
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                g.drawRect(i * 100, j * 100, 100, 100);
                if((i+j) % 2 == 0){
                    g.fillRect(i * 100, j * 100, 100, 100);
                }
            }
        }
        for(Piece p : pieces){
            p.draw(g);
        }
    }
}