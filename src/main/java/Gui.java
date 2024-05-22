//aiden
//import java.awt.*;
import javax.swing.*;

public class Gui extends JFrame{
	private CaveRender cav;  

	public Gui(Cave c){
		super("Wumpus Hunter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		cav = new CaveRender(c);
		c.linkRender(cav);
		cav.setSize(600,600);
		add(cav);
		pack();
        setVisible(true);	
	}
}