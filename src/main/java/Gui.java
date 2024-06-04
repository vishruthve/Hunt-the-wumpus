//aiden
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Gui extends JFrame implements ActionListener{
	private CaveRender cav;  
	private Cave C;
	private JSpinner target;
	private JButton rotate = new JButton("↶");
	private JButton rotate2 = new JButton("↷");
	private JPanel panel = new JPanel();
	private JPanel controls = new JPanel();
	private String[] labs = {"↓","↙","↖","↑","↗","↘"};
	private String[] dirs = {"S","SW","NW","N","NE","SE"};
	private char[] keys = {'s','a','q','w','e','d'};
	private JButton[] hexButtons = new JButton[6];
	private JPanel hexPanel = new JPanel();
	private JLabel rotcells = new JLabel("Rotate Cell");
	private JLabel moveGuide = new JLabel("(Alt+QWEASD)");
	private GridBagConstraints con = new GridBagConstraints();
	private Font tet;
	private JLabel question = new JLabel("trivia");
	private JPanel triviaBox = new JPanel();
	private JButton[] triviaAnswers = new JButton[0];
	Trivia triv;

	public Gui(Cave c){
		super("Wumpus Hunter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		add(panel);
		C = c;
		try {
            tet = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/font/FSEX300.ttf"));
            tet = tet.deriveFont(Font.PLAIN, 18.0f);
        } catch(Exception e){
            System.out.println(e);
        }
		target = new JSpinner(new SpinnerNumberModel(C.player.getPosition(),0,C.CELLCOUNT-1,1));
		target.setFont(tet);
		cav = new CaveRender(C);
		C.linkRender(cav);
		controls.setLayout(new BoxLayout(controls, BoxLayout.LINE_AXIS));
		controls.setBorder(BorderFactory.createBevelBorder(0));
		rotate.setActionCommand("rcw");
		rotate2.setActionCommand("rccw");
		rotcells.setLabelFor(target);
		JPanel rotcont = new JPanel(new GridBagLayout());
		con.gridwidth=3;
		con.fill = 3;
		rotcont.add(rotcells, con);
		con.gridwidth=1;
		con.gridy=1;
		rotcont.add(rotate,con);
		con.gridx=1;
		rotcont.add(target,con);
		con.gridx=2;
		rotcont.add(rotate2,con);
		rotate.setBackground(Color.WHITE);
		rotate2.setBackground(Color.WHITE);
		target.setBackground(Color.WHITE);
		rotcells.setOpaque(true);
		rotcont.setBorder(BorderFactory.createBevelBorder(0));
		controls.add(rotcont);
		controls.add(triviaBox);
		triviaBox.setPreferredSize(new Dimension(860, 50));
		triviaBox.add(question);
		try {
			triv = new Trivia();
		} catch(Exception e) {
			System.out.println(e);
		}
		triv.getNewTrivia();
		triviaAnswers = new JButton[triv.Trivia_Answers.size()];
		for (int i=0;i<triviaAnswers.length;i++){
			triviaAnswers[i] = new JButton(triv.Trivia_Answers.get(i));
			triviaAnswers[i].setActionCommand("a"+i);
			triviaAnswers[i].addActionListener(this);
			triviaAnswers[i].setMargin(new Insets(1, 1, 1, 1));
			triviaBox.add(triviaAnswers[i]);
		}
		//controls.add(Box.createHorizontalStrut(860));
		for (int i=0;i<6;i++){
			hexButtons[i] = new JButton(labs[i]);
			hexButtons[i].setActionCommand(dirs[i]);
			hexButtons[i].addActionListener(this);
			hexButtons[i].setEnabled(C.canTraverse(C.player.getPosition(), i));
			hexButtons[i].setBackground(C.canTraverse(C.player.getPosition(), i)?Color.WHITE:Color.GRAY);
			hexButtons[i].setMnemonic(keys[i]);
			hexButtons[i].setMargin(new Insets(1, 1, 1, 1));
		}
		hexPanel.setLayout(new GridBagLayout());
		con.gridwidth=3;
		con.gridx=0;
		con.gridy=0;
		hexPanel.add(moveGuide,con);
		con.weightx=1;
		con.fill=2; 
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
		hexPanel.setBorder(BorderFactory.createBevelBorder(0));

		Container contentPane = getContentPane();
		contentPane.add(cav, BorderLayout.NORTH);
		contentPane.add(controls, BorderLayout.SOUTH);
		rotate.addActionListener(this);
		rotate2.addActionListener(this);
		rotcells.setFont(tet);
		moveGuide.setFont(tet);
		

		pack();
        setVisible(true);	

	}
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		if (e.getActionCommand()!=null){
			switch (e.getActionCommand()){
				case "N": C.attemptMove(3); target.setValue((Integer) C.player.getPosition()); break;
				case "S": C.attemptMove(0); target.setValue((Integer) C.player.getPosition()); break;
				case "NW": C.attemptMove(2); target.setValue((Integer) C.player.getPosition()); break;
				case "NE": C.attemptMove(4); target.setValue((Integer) C.player.getPosition()); break;
				case "SW": C.attemptMove(1); target.setValue((Integer) C.player.getPosition()); break;
				case "SE": C.attemptMove(5); target.setValue((Integer) C.player.getPosition()); break;
				case "rcw": C.rotateCell((int)target.getValue(),-1); break;
				case "rccw": C.rotateCell((int)target.getValue(), 1); break;
			}
		}
		for(int i=0;i<6;i++){
			hexButtons[i].setEnabled(C.canTraverse(C.player.getPosition(), i));
			hexButtons[i].setBackground(C.canTraverse(C.player.getPosition(), i)?Color.WHITE:Color.GRAY);
		}


    }
}