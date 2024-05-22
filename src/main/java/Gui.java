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
	private String[] dirs = {"Down","DLeft","ULeft","Up","URight","DRight"};
	private JButton[] hexButtons = new JButton[6];
	private JPanel hexPanel = new JPanel();
	private JLabel rotcells = new JLabel("Cell rotation");

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
		rotate.setActionCommand("rotate");
		rotcells.setLabelFor(rotate);
		JPanel rotcont = new JPanel(new BorderLayout());
		rotcont.add(rotcells, BorderLayout.NORTH);
		rotcont.add(rotate, BorderLayout.EAST);
		rotcont.add(target, BorderLayout.WEST);
		rotcells.setOpaque(true);
		controls.add(rotcont);
		controls.add(Box.createRigidArea(new Dimension(400, 40)));
		for (int i=0;i<6;i++){
			hexButtons[i] = new JButton(dirs[i]);
			hexButtons[i].setActionCommand(dirs[i]);
			hexButtons[i].addActionListener(this);
		}
		hexPanel.setLayout(new GridLayout(2,3));
		hexPanel.add(hexButtons[2]);
		hexPanel.add(hexButtons[3]);
		hexPanel.add(hexButtons[4]);
		hexPanel.add(hexButtons[1]);
		hexPanel.add(hexButtons[0]);
		hexPanel.add(hexButtons[5]);
		controls.add(Box.createHorizontalGlue());
		controls.add(hexPanel);

		Container contentPane = getContentPane();
		contentPane.add(cav, BorderLayout.NORTH);
		contentPane.add(Box.createVerticalStrut(20));
		contentPane.add(controls, BorderLayout.SOUTH);
		rotate.addActionListener(this);
		

		pack();
        setVisible(true);	

	}
	public void actionPerformed(ActionEvent e) {
        if ("rotate".equals(e.getActionCommand())) {
            C.rotateCell((int)target.getValue());
        }

		switch (e.getActionCommand()){
			case "Up": C.attemptMove(3); break;
			case "Down": C.attemptMove(0); break;
			case "ULeft": C.attemptMove(2); break;
			case "URight": C.attemptMove(4); break;
			case "DLeft": C.attemptMove(1); break;
			case "DRight": C.attemptMove(5); break;
		}


    }
}