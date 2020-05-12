package kdtreeGUI;

import kdtree.KDNode;
import kdtree.KDTree;
import kdtree.Point;

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
    private JTextArea singleDebugPrint;
    private JPanel drawPanel;
    private Graphics graphics;
    private String printToSerial = "";

    // Main Window Size
    static int widthWindow = 1400;
    static int heightWindow = 800;

    // Draw Window Size
    int drawWindowWidth;
    int drawWindowHeight;

    // KDtree instantiation
    KDTree kdTree = new KDTree();
    Integer nodeNumber = 0;

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
                singleDebugPrint.setText(printToSerial);
                debugPrintPanel.setText(printToSerial);
            }
        });
        debugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: implement search or propagate to view
                debugPrintPanel.setText(printToSerial);
            }
        });

        // Mouse Events
        drawPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Integer x = e.getX();
                Integer y = e.getY();

                System.out.println("Mouse Values\n  x:" + x.toString() + " y: " + y.toString());

                // Insert mouse click values in kd tree
                Point p = new Point(e.getX(), e.getY());
                kdTree.insert(p);
                KDNode iNode = kdTree.get(p);
                if(iNode.getOrient() == KDNode.Orientation.HORIZONTAL){
                    singleDebugPrint.setText("Horizontal Node Added\n  Mouse x: " + x.toString() + " y: " + y.toString() + iNode.toString());
                }else{
                    singleDebugPrint.setText("Vertical Node Added\n  x: " + x.toString() + " y: " + y.toString() + iNode.toString());
                }

                graphics = drawPanel.getGraphics();

                LinkedList<KDNode> kdNodes = kdTree.graphicIterate();
                printToSerial = " ";
                nodeNumber = 0;
                for(KDNode n: kdNodes) {
                    nodeNumber++;
                    drawGraphics(graphics, n);
                }
            }
        });

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        // Draw Window Size. Has to be done after the GUI is visible
        drawWindowWidth = drawPanel.getWidth();
        drawWindowHeight = drawPanel.getHeight();
        System.out.println("Draw Window Dimensions\n  h:"+ drawWindowHeight + " w:" + drawWindowWidth);
    }

    private int constrainYAxis(int y){

        if(y <= Integer.MIN_VALUE){ return 0; }
        else if(y >= Integer.MAX_VALUE){ return drawWindowHeight; }

        return y;
    }

    private int constrainXAxis(int x){

        if(x <= Integer.MIN_VALUE){ return 0; }
        else if(x >= Integer.MAX_VALUE) { return drawWindowWidth;}

        return x;
    }

    private void drawCircleMouse(Graphics2D gr2, int x, int y, int radius){
        int scale = 3;
        gr2.fillOval(x-radius+1,y-radius+1,radius*scale,radius*scale);
    }

    private void drawGraphics(Graphics g, KDNode kd){
        Graphics2D gr2 = (Graphics2D) g;
        Integer x = (int)kd.getPoint().getX();
        Integer y = (int)kd.getPoint().getY();

        gr2.setColor(new Color(192,89,219));

        if(kd.getOrient() == KDNode.Orientation.VERTICAL){
            Integer y1 = constrainYAxis((int)kd.getRegion().getYmin());
            Integer y2 = constrainYAxis((int)kd.getRegion().getYmax());
            gr2.drawLine(x, y2, x, y1);

            printToSerial += nodeNumber.toString() + "." + kd.getPoint().toString() + "\n Vertical Line\n  same x: " + x.toString() + "\n  y1: " + y1.toString() + " y2: " + y2.toString() + "\n";
        }else{
            Integer x1 = constrainXAxis((int)kd.getRegion().getXmin());
            Integer x2 = constrainXAxis((int)kd.getRegion().getXmax());
            gr2.drawLine(x2, y, x1, y);

            printToSerial += nodeNumber.toString() + "." + kd.getPoint().toString() + "\n Horizontal Line\n  same y: " + y.toString() + "\n  x1: " + x1.toString() + " x2: " + x2.toString() + "\n";
        }

        printToSerial += "----------------------------\n";

        gr2.setColor(new Color(109,219,56));

        drawCircleMouse(gr2,x,y,8);
    }

    public static void main(String[] args){
        KDViewer window = new KDViewer();
    }

}
