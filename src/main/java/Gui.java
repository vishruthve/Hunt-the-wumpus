//aiden
import java.awt.*;
import javax.swing.*;
import java.util.Arrays;

public class Gui extends JPanel{
	private CaveRender cav;

	public Gui(Cave c){
		cav = new CaveRender(c);
		cav.setSize(600,600);
		add(cav);
	}
}