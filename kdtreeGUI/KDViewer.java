package kdtreeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KDViewer extends JFrame{
    private JFrame mainFrame;
    private JButton clearButton;
    private JButton debugButton;
    private JPanel rootPanel;
    private JPanel debugPanel;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private JTextPane debugPrintPanel;
    private Graphics graphics;
    private String debugPrint = "YOOOOOO\n";

    public KDViewer(){

        mainFrame = new JFrame();

        mainFrame.add(rootPanel);
        mainFrame.setSize(300,400);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.repaint(); //erase contents of panel
            }
        });
        debugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debugPrint += debugPrint;
                debugPrintPanel.setText(debugPrint);
            }
        });
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                graphics = mainPanel.getGraphics();
                doLineGraphics(graphics,e.getX(),e.getY());
            }
        });

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private void doLineGraphics(Graphics g, int x, int y){
        Graphics2D gr2 = (Graphics2D) g;

        gr2.setColor(new Color(192,89,219));

        gr2.drawLine(0,0,x,y);

        gr2.setColor(new Color(109,219,56));

        drawCircleMouse(gr2,x,y,10);
    }

    private void drawCircleMouse(Graphics2D gr2, int x, int y, int radius){
        gr2.fillOval(x-radius,y-radius,radius*3,radius*3);
    }

    public static void main(String[] args){
        KDViewer window = new KDViewer();
    }

}
