package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;

import javax.swing.JButton;

public class InfoPopUp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public InfoPopUp(String message) {
		setType(Type.POPUP);
		setAlwaysOnTop(true);
		setTitle("Info");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(gui.MainContainer.SCREENWIDTH / 2, gui.MainContainer.SCREENHEIGHT / 2, 350, 150);
		setSize(new Dimension(350, 150));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInfoMessage = new JLabel(message);
		lblInfoMessage.setVerticalAlignment(SwingConstants.TOP);
		lblInfoMessage.setBounds(6, 6, 338, 72);
		contentPane.add(lblInfoMessage);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(close -> {this.dispose();});
		btnOK.setBounds(16, 87, 328, 29);
		contentPane.add(btnOK);
	}

}
