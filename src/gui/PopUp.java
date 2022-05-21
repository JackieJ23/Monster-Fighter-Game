package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * A class for creating new warning/error/information popups on the screen
 * 
 * @author Harrison Tyson
 * @version 1.0 May 2022
 */
public class PopUp extends JFrame {

    /**
     * Default serial version ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Panel for popup content
     */
    private JPanel contentPane;

    /**
     * Default width
     */
    private final int POPUPWIDTH = 350;

    /**
     * Default height
     */
    private final int POPUPHEIGHT = 150;

    /**
     * Create the PopUp Window
     * 
     * @param title    title of the window
     * @param message  message to be displayed
     * @param location location of parent window
     */
    public PopUp(String title, String message, Point location) {
        setType(Type.POPUP);
        setAlwaysOnTop(true);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, POPUPWIDTH, 150);
        contentPane = new JPanel();
        // contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBounds(0, 0, POPUPWIDTH, POPUPHEIGHT);
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setContentPane(contentPane);

        JLabel lblmessage = new JLabel(message);
        lblmessage.setVerticalAlignment(SwingConstants.TOP);
        lblmessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblmessage.setPreferredSize(new Dimension(POPUPWIDTH, ((POPUPHEIGHT - 34) * 3) / 4));
        contentPane.add(lblmessage);

        JButton btnOK = new JButton("OK");
        btnOK.addActionListener(close -> {
            this.dispose();
        });
        btnOK.setPreferredSize(new Dimension(POPUPWIDTH, (POPUPHEIGHT - 34) / 4));
        btnOK.setFocusable(false);
        contentPane.add(btnOK);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocation(location.x +
                (gui.MainContainer.SCREENWIDTH / 2) -
                this.getWidth() / 2,
                location.y +
                        (gui.MainContainer.SCREENHEIGHT / 2) -
                        this.getHeight() / 2);

        setVisible(true);

        // Closes when loses focus
        this.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowLostFocus(WindowEvent e) {
                e.getWindow().dispose();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Do Nothing

            }

        });

        this.getRootPane().setDefaultButton(btnOK);
    }

}