// Aiden Whitlock
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.util.*;
public class Main extends Base{
	public static Scanner s = new Scanner(System.in);
	public static Cave c;
	public static Gui g;
	public static void main(String[] args) throws FileNotFoundException{
		c = new Cave("default");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				g = new Gui(c);
			}
		});
	}
}