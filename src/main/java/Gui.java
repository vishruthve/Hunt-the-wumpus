//aiden
import java.awt.*;
import javax.swing.JFrame;

import Cave.*;

import java.util.Arrays;

import java.swing.JFrame;
import java.awt.FlowLayout;

public class Gui extends Canvas {
    private static final double e = Math.PI/3;
    private Cave c;
    private Graphics2D g2;

    private int r(double num){return (int)Math.round(num);}

    public void paint(Graphics g) {  
        g2 = (Graphics2D) g;
        setBackground(Color.WHITE);    
        renderCave(200, 50, 60);
        
        
    }  
    public Gui(Cave C){c=C;}

    public void renderCave(int xOffset, int yOffset, int size){
        for(Cave.Cell C:c.getCavern()){
            double x = C.XPOS*1.5*size+size+xOffset;
            double y = C.YPOS*2*size*Math.sin(e)+(C.XPOS%2==1?size*Math.sin(e):0)+size+yOffset;
            Polygon p = new Polygon();
            for (int i = 0; i < 6; i++) p.addPoint(r(x+(size-1)*Math.cos(i*e)),r(y+(size-1)*Math.sin(i*e)));
            g2.setColor(Color.GREEN);
            g2.fillPolygon(p);
            g2.setColor(Color.BLACK);
            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
            g2.drawPolygon(p);
            for(int j=0;j<6;j++){
                g2.setColor(c.canTraverse(C.CELLID, j)?Color.BLUE:(C.doorState()[j]?Color.GRAY:Color.BLACK));
                g2.setStroke(new BasicStroke(4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
                g2.drawLine(
                    r(x+(size-2)*Math.cos(j*e)),
                    r(y+(size-2)*Math.sin(j*e)), 
                    r(x+(size-2)*Math.cos((j+1)*e)), 
                    r(y+(size-2)*Math.sin((j+1)*e))
                );
            }
            g2.drawString("id: "+C.CELLID, r(x)-10, r(y)-10);
            g2.drawString("shape: "+C.SHAPE, r(x)-10, r(y));
            g2.drawString("rot: "+C.rotation, r(x)-10, r(y)+10);



        }

        
    }
}