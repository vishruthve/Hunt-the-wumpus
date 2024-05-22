//aiden
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Gui extends JFrame implements ActionListener{
	private CaveRender cav;  
	private Cave C;
	private JSpinner target;
	private JButton rotate = new JButton("Rotate");
	private JPanel panel = new JPanel();
	private JPanel controls = new JPanel();
	private String[] dirs = {"S","SW","NW","N","NE","SE"};
	private char[] keys = {'s','a','q','w','e','d'};
	private JButton[] hexButtons = new JButton[6];
	private JPanel hexPanel = new JPanel();
	private JLabel rotcells = new JLabel("Cell rotation");
	private JLabel moveGuide = new JLabel("(Hold ALT to move)");
	private GridBagConstraints con = new GridBagConstraints();

	public Gui(Cave c){
		super("Wumpus Hunter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		add(panel);
		C = c;
		target = new JSpinner(new SpinnerNumberModel(15,0,C.CELLCOUNT-1,1));
		cav = new CaveRender(C);
		c.linkRender(cav);
		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));
		controls.setBorder(BorderFactory.createBevelBorder(0));
		rotate.setActionCommand("rotate");
		rotcells.setLabelFor(rotate);
		JPanel rotcont = new JPanel(new GridBagLayout());
		con.gridwidth=2;
		rotcont.add(rotcells, con);
		con.gridwidth=1;
		con.gridy=1;
		rotcont.add(rotate,con);
		con.gridx=1;
		rotcont.add(target,con);
		rotcells.setOpaque(true);
		controls.add(rotcont);
		for (int i=0;i<6;i++){
			hexButtons[i] = new JButton(dirs[i]);
			hexButtons[i].setActionCommand(dirs[i]);
			hexButtons[i].addActionListener(this);
			hexButtons[i].setEnabled(C.canTraverse(C.playerPos, i));
			hexButtons[i].setMnemonic(keys[i]);
		}
		hexPanel.setLayout(new GridBagLayout());
		con.gridwidth=3;
		con.gridx=0;
		con.gridy=0;
		hexPanel.add(moveGuide,con);
		con.gridwidth=1;
		con.gridx=0;
		con.gridy=1;
		hexPanel.add(hexButtons[2],con);
		con.gridx=1;
		con.gridy=1;
		hexPanel.add(hexButtons[3],con);
		con.gridx=2;
		con.gridy=1;
		hexPanel.add(hexButtons[4],con);
		con.gridx=0;
		con.gridy=2;
		hexPanel.add(hexButtons[1],con);
		con.gridx=1;
		con.gridy=2;
		hexPanel.add(hexButtons[0],con);
		con.gridx=2;
		con.gridy=2;
		hexPanel.add(hexButtons[5],con);
		controls.add(hexPanel);

		Container contentPane = getContentPane();
		contentPane.add(cav, BorderLayout.NORTH);
		contentPane.add(controls, BorderLayout.SOUTH);
		rotate.addActionListener(this);
		

		pack();
        setVisible(true);	

	}
	public void actionPerformed(ActionEvent e) {
        if ("rotate".equals(e.getActionCommand())) {
            C.rotateCell((int)target.getValue());
        }
		if (e.getActionCommand()!=null){
			switch (e.getActionCommand()){
				case "N": C.attemptMove(3); break;
				case "S": C.attemptMove(0); break;
				case "NW": C.attemptMove(2); break;
				case "NE": C.attemptMove(4); break;
				case "SW": C.attemptMove(1); break;
				case "SE": C.attemptMove(5); break;
			}
		}
		for(int i=0;i<6;i++){
			hexButtons[i].setEnabled(C.canTraverse(C.playerPos, i));
		}


    }
}