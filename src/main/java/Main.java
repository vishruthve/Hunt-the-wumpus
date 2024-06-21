// Aiden Whitlock
//import java.util.*;
public class Main {
	public static Cave c;
	public static Gui g;
	public static void main(String[] args) {
		c = new Cave("default");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				g = new Gui(c);
			}
		});
	}
}