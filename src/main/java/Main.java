// Aiden Whitlock

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;
//import java.util.*;
public class Main extends Base{
	public static Cave c;
	private static void createAndShowGUI() {
        JFrame frame = new JFrame("HelloWorldSwing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	
		Gui m=new Gui();  
        frame.add(m);  
		c.render(m);
		//m.drawHex(50,100,100);
		//m.drawHex(50,200,100);
		//m.drawHex(30,280,100);
		///m.drawHex(10,310,100);
		//m.drawHex(20,100,200);
		/*for(int x=0,y=0;y<6;y+=++x/5,x%=5){
			m.drawHex(50, x*75+50, y*88+(x%2==1?44:0)+50);
		}
		m.drawHex(50,50,50);
		m.drawHex(50,125,94);
		m.drawHex(50,200,50);
		m.drawHex(50,50,138);
		m.drawHex(50,125,182);
		m.drawHex(50,200,138);
		*/
		//m.drawHex(50,225,94);
		//m.drawHex(50,250,50);
		//m.drawHex(100,175,145);
		//m.drawHex(50,100,200);
		frame.setSize(1600,800);  
    }
	public static void main(String[] args){
		log("Main");
		c = new Cave(new int[][]{ 
			{0 ,  0,0,   1, 5, 9, 4,29,25,  7,0},
			{1 ,  1,0,   2, 6, 5, 0,25,26,  7,1},
			{2 ,  2,0,   3, 7, 6, 1,26,27,  7,0},
			{3 ,  3,0,   4, 8, 7, 2,27,28,  7,0},
			{4 ,  4,0,   0, 9, 8, 3,28,29,  7,0},
			{5 ,  0,1,   6,11,10, 9, 0, 1,  7,0},
			{6 ,  1,1,   7,12,11, 5, 1, 2,  7,0},
			{7 ,  2,1,   8,13,12, 6, 2, 3,  7,0},
			{8 ,  3,1,   9,14,13, 7, 3, 4,  7,0},
			{9 ,  4,1,   5,10,14, 8, 4, 0,  7,0},
			{10,  0,2,  11,15,19,14, 9, 5,  7,0},
			{11,  1,2,  12,16,15,10, 5, 6,  7,0},
			{12,  2,2,  13,17,16,11, 6, 7,  7,0},
			{13,  3,2,  14,18,17,12, 7, 8,  7,0},
			{14,  4,2,  10,19,18,13, 8, 9,  7,0},
			{15,  0,3,  16,21,20,19,10,11,  7,0},
			{16,  1,3,  17,22,21,15,11,12,  7,0},
			{17,  2,3,  18,23,22,16,12,13,  7,0},
			{18,  3,3,  19,24,23,17,13,14,  7,0},
			{19,  4,3,  15,20,24,18,14,10,  7,0},
			{20,  0,4,  21,25,29,24,19,19,  7,0},
			{21,  1,4,  22,26,25,20,15,16,  7,0},
			{22,  2,4,  23,27,26,21,16,17,  7,0},
			{23,  3,4,  24,28,27,22,17,18,  7,0},
			{24,  4,4,  20,29,28,23,18,19,  7,0},
			{25,  0,5,  26, 1, 0,29,20,21,  7,0},
			{26,  1,5,  27, 2, 1,25,21,22,  7,0},
			{27,  2,5,  28, 3, 2,26,22,23,  7,0},
			{28,  3,5,  29, 4, 3,27,23,24,  7,0},
			{29,  4,5,  25, 0, 4,28,24,20,  7,0}
		});
	System.out.println(c.canTraverse(0, 0));
	System.out.println(c.canTraverse(1, 3));
	System.out.println(c.canTraverse(1, 0));
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });		

	}
}