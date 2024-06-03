// Aiden Whitlock
import java.io.FileNotFoundException;
//import java.util.*;
public class Main extends Base{
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