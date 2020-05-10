package kdtreeGUI;

import kdtree.KDNode;
import kdtree.KDTree;
import kdtree.Point;
import kdtree.Region;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

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
    private String printToSerial = "";

    //Window Size
    int widthWindow = 1000;
    int heightWindow = 1000;

    // KDtree instantiation
    KDTree kdTree = new KDTree();

    public KDViewer(){

        // Create the main frame to place components on
        mainFrame = new JFrame();
        mainFrame.add(rootPanel);
        mainFrame.setSize(widthWindow,heightWindow);
        mainFrame.setResizable(false);

        // Button Events
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kdTree = new KDTree();
                mainFrame.repaint(); //erase contents of panel

                printToSerial = " ";
                debugPrintPanel.setText(printToSerial);
            }
        });
        debugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement search or propagate to view
                printToSerial = " ";
                debugPrintPanel.setText(printToSerial);
            }
        });

        // Mouse Events
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Point p = new Point(e.getX(), e.getY());
                kdTree.insert(p);
                graphics = mainPanel.getGraphics();

                LinkedList<KDNode> kdNodes = kdTree.graphicIterate();
                for(KDNode n: kdNodes) {
                    drawGraphics(graphics, (int) n.getPoint().getX(), (int) n.getPoint().getY(), n.getRegion(), n.getOrient());
                }
            }
        });

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    private int constrainYAxis(int y){

        if(y <= Integer.MIN_VALUE){ return 0; }
        else if(y >= Integer.MAX_VALUE){ return heightWindow-200; }

        return y;
    }

    private int constrainXAxis(int x){

        if(x <= Integer.MIN_VALUE){ return 0; }
        else if(x >= Integer.MAX_VALUE) { return 715;}

        return x;
    }
    private void drawGraphics(Graphics g, Integer x, Integer y, Region r, KDNode.Orientation o){
        Graphics2D gr2 = (Graphics2D) g;

        gr2.setColor(new Color(192,89,219));
        int sideLength = 1000; //TODO: Convert from the doubles to visual integers

        if(o == KDNode.Orientation.VERTICAL){
            Integer y1 = constrainYAxis((int) r.getYmin()*sideLength);
            Integer y2 = constrainYAxis((int) r.getYmax()*sideLength);
            gr2.drawLine(x, y1, x, y2);

            printToSerial += "Vertical Line\n  x1: " + x.toString() + " y1: " + y1.toString() + "\n  x2: " + x + " y2: " + y2.toString() + "\n";
        }else{
            Integer x1 = constrainXAxis((int) r.getXmin()*sideLength);
            Integer y1 = constrainYAxis(y);
            Integer x2 = constrainXAxis((int) r.getXmax()*sideLength);
            Integer y2 = constrainYAxis(y);
            gr2.drawLine(x1, y1, x2, y2);

            printToSerial += "Horzontal Line\n  x1: " + x1.toString() + " y1: " + y1.toString() + "\n  x2: " + x2.toString() + " y2: " + y2.toString() + "\n";
        }

        printToSerial += "----------------------------\n";
        debugPrintPanel.setText(printToSerial);

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
