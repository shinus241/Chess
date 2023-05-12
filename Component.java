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
public class Component extends JComponent{
    private int mouseX;
    private int mouseY;
    static BufferedImage pawnImage;
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
        try{
            pawnImage = ImageIO.read(new File("whitePawn.png"));
        }
        catch(Exception e){
            System.out.println("Error loading image");
        }
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
                if((i+j) % 2 == 0){
                    g.fillRect(i * 100, j * 100, 100, 100);
                }
            }
        }
        g.drawImage(pawnImage, mouseX, mouseY, null);
    }
}