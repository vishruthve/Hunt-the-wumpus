//aiden
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JFrame implements ActionListener{
	private CaveRender cav;  
	private Cave C;
	private JSpinner target = new JSpinner(new SpinnerNumberModel(15,0,29,1));
	private JButton rotate = new JButton("Rotate");
	private JPanel panel = new JPanel();
	private JPanel controls = new JPanel();

	public Gui(Cave c){
		super("Wumpus Hunter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		add(panel);
		C = c;
		cav = new CaveRender(C);
		c.linkRender(cav);
		cav.setSize(600,600);
		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));
		controls.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		controls.add(Box.createHorizontalStrut(500) );
		rotate.setActionCommand("rotate");
		controls.add(target);
		controls.add(Box.createRigidArea(new Dimension(10, 0)));
		controls.add(rotate);
		Container contentPane = getContentPane();
		contentPane.add(cav, BorderLayout.WEST);
		contentPane.add(controls, BorderLayout.PAGE_END);
		rotate.addActionListener(this);
		

		pack();
        setVisible(true);	

	}
	public void actionPerformed(ActionEvent e) {
        if ("rotate".equals(e.getActionCommand())) {
            C.rotateCell((int)target.getValue());
        }
    }
}