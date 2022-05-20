package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import main.Player;

/**
 * Class for a custom swing panel that displays a selection of monster battles.
 * 
 * @author Jackie Jone
 * @version 1.0 Mar, 2022
 */
public class BattleSelectionPanel extends EntityViewer implements Updatable {

    private static final long serialVersionUID = 1L;

	private JButton btnBattle;

	private ButtonGroup battleButtons;

    private static final int RADIOBUTTONHEIGHT = 120;
    private static final int PNLBATTLESHEIGHT = 475;
    private static final int PNLBATTLESWIDTH = 535;
    
    private int buttonGap; // Not constant, changes at runtime
    private JPanel pnlBattles;

    /**
     * Init the panel ready to be populated with battles
     */
    public BattleSelectionPanel() {
        super(true, true, true);
        setName("BattleSelection");

        JLabel lblBattleSelectionTitle = new JLabel("Battle Selection");
        lblBattleSelectionTitle.setBounds(385, 6, 246, 37);
        lblBattleSelectionTitle.setFont(new Font("Lucida Grande", Font.BOLD, 30));
        add(lblBattleSelectionTitle);

        btnBattle = new JButton("Battle");
        btnBattle.addActionListener(battle -> {
            goToBattle();
        });
        btnBattle.setBounds(690, 487, 295, 47);
        add(btnBattle);

        // Init battles panel
        pnlBattles = new JPanel();
        pnlBattles.setBounds(16, 54, PNLBATTLESWIDTH, PNLBATTLESHEIGHT);
        pnlBattles.setBackground(this.getBackground());
        add(pnlBattles);

        FlowLayout pnlBattlesLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        pnlBattles.setLayout(pnlBattlesLayout);
    }
    
    /**
     * Update the contents of the panel by populating it with all the available battles
     */
    @Override
    public void update() {
        btnBattle.setEnabled(false);
        btnBattle.setText("Battle");
        pnlBattles.removeAll();

        // Clear battleButtons group
        battleButtons = new ButtonGroup();
        ArrayList<Player> opponents = MainContainer.game.getBattleState().getOpponents();

        buttonGap = (PNLBATTLESHEIGHT - (opponents.size() * RADIOBUTTONHEIGHT)) / (opponents.size() + 1);
        ((FlowLayout) pnlBattles.getLayout()).setVgap(buttonGap);

        for (Player opponent : opponents) {
            JRadioButton oppButton = new JRadioButton(getOpponentDescription(opponent));
            oppButton.setActionCommand(String.valueOf(opponents.indexOf(opponent)));
            oppButton.addActionListener(selected -> {
                super.updatePreview(battleButtons, opponents.toArray());
            });

            oppButton.setPreferredSize(new Dimension(PNLBATTLESWIDTH, RADIOBUTTONHEIGHT));
            oppButton.setOpaque(false);
            oppButton.setFocusable(false);
            oppButton.setFont(oppButton.getFont().deriveFont(25.0f));
            pnlBattles.add(oppButton);

            battleButtons.add(oppButton);
            btnBattle.setEnabled(true);
        }

        if (MainContainer.game.getPlayer().getTeam().getAliveMonsters().isEmpty()) {
            btnBattle.setEnabled(false);
            btnBattle.setText("All Monsters Fainted");
        }

        super.updatePlayerInfo();
        super.selectFirstAvailableButton(battleButtons);
        
        
        
        super.updatePreview(battleButtons, opponents.toArray());
    }
    
    /**
     * Get the information of the oppoent to be viewed on the GUI
     * 
     * @param  opponent The opponent to view the information of
     * @return The information of the opponent as a string
     */
    private String getOpponentDescription(Player opponent) {
        String outputString = opponent.getName() + ":  ";
        outputString += opponent.getTeam().toString();

        return outputString;
    }
    
    /**
     * Start the battle that was selected by the player
     */
    private void goToBattle() {
        int battleIndex = Integer.parseInt(battleButtons.getSelection().getActionCommand());
        Player opponent = MainContainer.game.getBattleState().getOpponents().get(battleIndex);
        MainContainer.game.getBattleState().setOpponent(opponent);
        MainContainer.showScreen("BattleSimulation");
    }
}
