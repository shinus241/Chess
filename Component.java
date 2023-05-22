import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

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
    private boolean[][] attackedSquares = new boolean[8][8];
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
        pieces.add(new King(400, 700, 100, 100, true, pieces));
        pieces.add(new King(400, 0, 100, 100, false, pieces));
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
                            Piece pieceThatIsChecking = game.getEnemyChecking(turn % 2 == 0);
                            //find if you can block the check or if you have to move the king
                            //write a getPiecesThatCanBlockCheck method
                            attackedSquares = game.setPiecesThatCanBlockCheck(pieceThatIsChecking);
                            Piece selected = game.selected(mouseX, mouseY);
                            if(selected != null && ((selected.getColor() && turn % 2 == 0 )||(!selected.getColor() && turn % 2 != 0))){
                                if(selected.canBlockCheck() || ((selected.getType() == Game.wKING && selected.getColor()) || (selected.getType() == Game.bKING && !selected.getColor()))){
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
                                for(int r = 0; r < 8; r++){
                                    for(int c = 0; c < 8; c++){
                                        attackedSquares[r][c] = true;
                                    }
                                }
                            }
                        }
                    }
                    if(Game.checkmate(turn % 2 == 0)){
                        if(turn % 2 != 0){
                            System.out.println("Checkmate, white wins!");
                        }
                        else{
                            System.out.println("Checkmate, black wins!");
                        }
                    }
                    else if(Game.isStalemate(turn % 2 == 0)){
                        System.out.println("Stalemate, this game ends in a draw.");
                    }
                }
            }
        });
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Component component = new Component();
        frame.add(component);
        frame.setSize(900,900);
        frame.setVisible(true);
        while(true){
            frame.repaint();
            if(Game.stop()){
                if(component.turn % 2 != 0){
                    JFrame frame2 = new JFrame();
                    JLabel label = new JLabel();
                    ImageIcon image = new ImageIcon("whiteWins.png");
                    label.setText("Checkmate, white wins!");
                    label.setHorizontalTextPosition(JLabel.CENTER);
                    label.setVerticalTextPosition(JLabel.TOP);
                    label.setForeground(new Color(0, 255, 255));
                    label.setFont(new Font("VCR OSD Mono", Font.PLAIN, 40));
                    label.setIcon(image);
                    label.setBackground(Color.black);
                    label.setOpaque(true);
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    frame2.add(label);
                    frame2.setSize(image.getIconWidth(),image.getIconHeight() + 100);
                    frame2.setVisible(true);
                }
                else{
                    JFrame frame2 = new JFrame();
                    JLabel label = new JLabel();
                    ImageIcon image = new ImageIcon("redWins.png");
                    label.setText("Checkmate, red wins!");
                    label.setHorizontalTextPosition(JLabel.CENTER);
                    label.setVerticalTextPosition(JLabel.TOP);
                    label.setForeground(new Color(0, 255, 255));
                    label.setFont(new Font("VCR OSD Mono", Font.PLAIN, 40));
                    label.setIcon(image);
                    label.setBackground(Color.black);
                    label.setOpaque(true);
                    label.setVerticalAlignment(JLabel.BOTTOM);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    frame2.add(label);
                    frame2.setSize(image.getIconWidth(),image.getIconHeight() + 100);
                    frame2.setVisible(true);
                }
                break;
            }
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
                        if(temp[r][c] && (p.getType() == Game.wKING || p.getType() == Game.bKING)){
                            g.setColor(new Color(0, 0, 255));
                            g.drawOval(c * 100, r * 100, 100, 100);
                        }
                        else if(temp[r][c] && attackedSquares[r][c]){
                            g.setColor(new Color(0, 0, 255));
                            g.drawOval(c * 100, r * 100, 100, 100);
                        }
                    }
                }
            }
        }
    }
}