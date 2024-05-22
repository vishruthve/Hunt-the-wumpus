//aiden
import java.awt.*;
import java.util.Arrays;

public class CaveRender extends Canvas {
    private static final boolean debug = false;
    private static final double e = Math.PI/3;
    private static final int size = 60, xOffset = 5, yOffset = 5;
    private static final double rot = 0;//Math.toRadians(-30.0);
    private static final Color background = new Color(0xf1ffde);
    private static final Color hexBG = new Color(0x15acde);
    private static final Color path = new Color(0x1090e0);
    private static final Color open = new Color(0x3b45bf);
    private static final Color closed = new Color(0x312111);
    private static final BasicStroke hexLine = new BasicStroke(4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
    private static final double[] sinCache = {
            Math.sin(e+rot),
            Math.sin(2*e+rot),
            Math.sin(3*e+rot),
            Math.sin(4*e+rot),
            Math.sin(5*e+rot),
            Math.sin(6*e+rot)
        }, 
        cosCache = {
            Math.cos(e+rot),
            Math.cos(2*e+rot),
            Math.cos(3*e+rot),
            Math.cos(4*e+rot),
            Math.cos(5*e+rot),
            Math.cos(6*e+rot)
        };
    
    
    private Cave c;
    private Graphics2D g2;

    private int r(double num){return (int)Math.round(num);}

    public void paint(Graphics g) { 

        g2 = (Graphics2D) g;    
        setBackground(background);
        renderCave();
        
    }  
    public CaveRender(Cave C){
        c=C;
    }
    public Dimension getPreferredSize() {
        return new Dimension(600,600);
    }

    public void renderCave(){
        for(Cave.Cell C:c.getCavern()){
            double x = C.XPOS*1.5*size+size+xOffset;
            double y = C.YPOS*2*size*Math.sin(e)+(C.XPOS%2==1?size*Math.sin(e):0)+size+yOffset;
            Polygon p = new Polygon();
            for (int i = 0; i < 6; i++) p.addPoint(r(x+(size-1)*cosCache[i]),r(y+(size-1)*sinCache[i]));
            g2.setColor(hexBG);
            g2.fillPolygon(p);
            g2.setStroke(hexLine);
            for(int j=0;j<6;j++){
                g2.setColor(c.canTraverse(C.CELLID, j)?path:(C.doorState()[j]?open:closed));
                g2.drawLine(
                    r(x+(size-2)*cosCache[j]),
                    r(y+(size-2)*sinCache[j]), 
                    r(x+(size-2)*cosCache[(j+1)%6]), 
                    r(y+(size-2)*sinCache[(j+1)%6])
                );
            }
            g2.setColor(Color.BLACK);
            g2.drawString(""+C.CELLID, r(x)-10, r(y)-10);
            if (debug){
                g2.setColor(Color.BLACK);
                g2.drawString("pos:" + C.XPOS + ", " + C.YPOS, r(x)-10, r(y)-20);
                g2.drawString("id: "+C.CELLID, r(x)-10, r(y)-10);
                g2.drawString("shape: "+C.SHAPE, r(x)-10, r(y));
                g2.drawString("rot: "+C.rotation, r(x)-10, r(y)+10);
                g2.drawString(""+Arrays.toString(C.getNeighbors()), r(x)-60,r(y)+20);
                g2.drawString(""+Arrays.toString(C.doorState()).replaceAll("true", "1").replaceAll("false","0"), r(x)-60,r(y)+30);
            }


        }
        double x = c.getCavern()[c.playerPos].XPOS*1.5*size+size+xOffset;
        double y = c.getCavern()[c.playerPos].YPOS*2*size*Math.sin(e)+(c.getCavern()[c.playerPos].XPOS%2==1?size*Math.sin(e):0)+size+yOffset;
        g2.setColor(Color.MAGENTA);
        g2.fillRect(r(x-5),r(y-5),10,10);

        
    }
}