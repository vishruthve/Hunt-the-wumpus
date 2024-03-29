//Vishruth V.
//GUI
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Gui extends JFrame {
    private JPanel drawingPanel;
    public Gui() {
        setTitle( "Simple Drawing GUI");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw shapes based on user input here
            }
        };
        getContentPane().add(drawingPanel, BorderLayout.CENTER);
        JTextField userInput = new JTextField(20);
        JButton drawButton = new JButton("Draw");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Parse user input and draw based on input
                drawingPanel.repaint();
            }
        });
        JPanel controls = new JPanel();
        controls.add(new JLabel("Enter shape command: "));
        controls.add(userInput);
        controls.add(drawButton);
        getContentPane().add(controls, BorderLayout.SOUTH);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gui());
    }
}