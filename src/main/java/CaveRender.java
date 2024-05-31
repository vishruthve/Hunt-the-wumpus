//aiden
import java.awt.*;
import java.util.Arrays;
import java.io.*;

public class CaveRender extends Canvas{
    private static final boolean debug = false;
    private static final double e = Math.PI/3;
    private static final int size = 60, xOffset = 1, yOffset = -7;
    private static final double rot = 0;//Math.toRadians(-30.0);
    private static final Color background = new Color(0xc1eebe);
    private static final Color hexBG = new Color(0x15acde);
    private static final Color path = new Color(0x129be0);
    private static final Color closed = new Color(0x112121);
    private static final Color open = new Color(0x0a5bb2);
    private static Font tet;
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

    public void paint(Graphics g){ 
        g2 = (Graphics2D) g;
        setBackground(background);
        renderCave();
        renderPlayer();
        //setIgnoreRepaint(true);
        
    }  
    public void renderPlayer(){
        double x = c.getCavern()[c.player.getPosition()].XPOS*1.5*size+size+xOffset;
        double y = c.getCavern()[c.player.getPosition()].YPOS*2*size*Math.sin(e)+(c.getCavern()[c.player.getPosition()].XPOS%2==1?size*Math.sin(e):0)+size+yOffset;
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) p.addPoint(r(x+(size-17)*cosCache[i]),r(y+(size-17)*sinCache[i]));
        g2.setColor(Color.CYAN);
        g2.setStroke(new BasicStroke(12.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawPolygon(p);
        g2.setColor(Color.LIGHT_GRAY);
        g2.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int z=0; z<6;z++){
            if (c.canTraverse(c.player.getPosition(), z)){
            x = c.getCavern()[c.getCavern()[c.player.getPosition()].NEIGHBORIDS[z]].XPOS*1.5*size+size+xOffset;
            y = c.getCavern()[c.getCavern()[c.player.getPosition()].NEIGHBORIDS[z]].YPOS*2*size*Math.sin(e)+(c.getCavern()[c.getCavern()[c.player.getPosition()].NEIGHBORIDS[z]].XPOS%2==1?size*Math.sin(e):0)+size+yOffset;
            p = new Polygon();
            for (int i = 0; i < 6; i++) p.addPoint(r(x+(size-17)*cosCache[i]),r(y+(size-17)*sinCache[i]));
            g2.drawPolygon(p);
            }
        }
    }
    public CaveRender(Cave C){
        c=C;
        try {
            tet = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/font/LEDCalculator.ttf"));
            tet = tet.deriveFont(Font.BOLD, 24.0f);
        } catch(Exception e){
            System.out.println(e);
        }
    }
    public Dimension getPreferredSize() {
        return new Dimension(1111,600);
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
            g2.setFont(tet);
            g2.drawString((C.CELLID<10?"0":"")+C.CELLID, r(x)-15, r(y)+6);
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
        

        
    }
}