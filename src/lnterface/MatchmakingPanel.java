package lnterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import threads.MatchmakingThread;

public class MatchmakingPanel extends JPanel implements ActionListener {

	public final static Image BACKGROUND = Toolkit.getDefaultToolkit()
			.createImage("./images/backgrounds/lobbyBackground.jpg");

	private LobbyPanel lobby;
	private JPanel aux; // JTable here
	private JPanel auxTwo; // Info Player and loading
	private DefaultTableModel modelPlayers;
	private List info;
	private JTable players;
	private JScrollPane scroll;

	private JButton add;
	private MatchmakingThread adding;

	public MatchmakingPanel(LobbyPanel lobby) {

		setLayout(new BorderLayout());

		this.lobby = lobby;

		add = new JButton("add");
		add.setActionCommand("agregando");
		add.addActionListener(this);

		adding = new MatchmakingThread(this);

		initAux();

		adding.start();

	}

	public void initAux() {

		Border border = BorderFactory.createRaisedBevelBorder();

		JLabel match = new JLabel("Matchmaking");

		match.setHorizontalAlignment(JLabel.CENTER);
		match.setForeground(Color.WHITE);
		match.setFont(new Font("Garamond", 1, 46));

		aux = new JPanel();
		aux.setBorder(border);
		aux.setLayout(new BorderLayout());
		aux.setBackground(new Color(0, 0, 0, 85));

		JLabel widthTwo = new JLabel(
				"                                                                                                        ");
		widthTwo.setSize(500, 1);

		auxTwo = new JPanel();
//		auxTwo.setLayout(null);

		auxTwo.setBorder(border);

		auxTwo.add(widthTwo);
		auxTwo.setBackground(new Color(0, 0, 0, 60));

		auxTwo.add(add);

		playersTable();

		aux.add(scroll);
		aux.add(match, BorderLayout.NORTH);

		add(aux, BorderLayout.CENTER);
		add(auxTwo, BorderLayout.EAST);

	}

	public void playersTable() {

		String[] columns = { "Nickname", "Level", "Ping" };

		modelPlayers = new DefaultTableModel(columns, 0);
		players = new JTable(modelPlayers);
		players.setOpaque(false);
		players.setBackground(new Color(0, 0, 0, 85));
		players.setForeground(Color.WHITE.brighter());
		players.setEnabled(false);
		players.getTableHeader().setReorderingAllowed(false);
		players.setFont(new Font("Garamond", 1, 18));

		scroll = new JScrollPane(players);
		scroll.getViewport().setBackground(new Color(0, 0, 0, 0));
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);

	}

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(BACKGROUND, 0, 0, null);

		repaint();
	}

	public DefaultTableModel getTableModel() {

		return modelPlayers;

	}

	public void justProve() {

		Object[] row = { "hola", "50", "50" };

		modelPlayers.addRow(row);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command = e.getActionCommand();

	}

}
