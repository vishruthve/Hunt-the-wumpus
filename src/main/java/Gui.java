//Rayhan 
// 3/8/34
import java.awt.*;
import javax.swing.JFrame;
import java.util.Arrays;


public class Gui extends Canvas {
    private Polygon[] p = new Polygon[0];
    private int[][][] l = new int[0][6][2];
    public void paint(Graphics g) {  
        //g.drawString("Hello",40,40);  
        setBackground(Color.WHITE);  
        //g.fillRect(130, 30,100, 80);  
        //g.drawOval(30,130,50, 60);  
        //setForeground(Color.BLUE);  
        //g.fillOval(130,130,50, 60);  
        //g.drawArc(30, 200, 40,50,90,60);  
        //g.fillArc(30, 130, 40,50,180,40);  
        g.setColor(Color.GREEN);
        if (p.length > 0)
        for(int i=0;i<p.length;i++){
            g.fillPolygon(p[i]);
        }
        g.setColor(Color.BLACK);
        for(int i=0;i<l.length;i++){
            for(int j=0;j<6;j++){
                g.drawLine(l[i][j][0], l[i][j][1], l[i][(j+1)%6][0], l[i][(j+1)%6][1]);
            }
        }
        
    }  
    public Gui(){

    }

    public void drawHex(int size, int x, int y){
        p = Arrays.copyOf(p, p.length+1);
        l = Arrays.copyOf(l, l.length+1);
        l[l.length-1] = new int[6][2]; 
        p[p.length-1] = new Polygon();
        for (int i = 0; i < 6; i++){
            p[p.length-1].addPoint((int) (x + size * Math.cos(i * 2 * Math.PI / 6)), (int) (y + size * Math.sin(i * 2 * Math.PI / 6)));
            l[l.length-1][i][0] = (int) (x + size * Math.cos(i * 2 * Math.PI / 6));
            l[l.length-1][i][1] = (int) (y + size * Math.sin(i * 2 * Math.PI / 6));
            System.out.print(Arrays.toString(l[l.length-1][i]));
        }
        System.out.println();
        

    }
}
