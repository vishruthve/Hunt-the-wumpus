// Aiden Whitlock

import javax.swing.*;
import java.util.Scanner;
//import java.util.*;
public class Main extends Base{
	public static Scanner s = new Scanner(System.in);
	public static Cave c;
	private static void createAndShowGUI() {
        JFrame frame = new JFrame("Wumpus Hunter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.add(new Gui(c)); 
		frame.pack();
        frame.setVisible(true);	  
		  
    }
	public static void main(String[] args){
		c = new Cave();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}
}