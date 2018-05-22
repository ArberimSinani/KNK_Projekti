package projekti_KNK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class FrmMain extends JFrame {

	private JPanel contentPane;
	//objekti per lidhje
		Connection conn=null;
		//objekti per vendosje te rezultatit
		ResultSet res=null;
		//objekti per query
		PreparedStatement pst=null;
		private JTable tblStandings;
		private JTextField txtTeam;
		private JTextField txtGamesplayed;
		private JTextField txtWins;
		private JTextField txtLosses;
		private JTextField txtScored;
		private JTextField txtTaken;
		private JTextField txtPoints;
		Methods objMethods = new Methods();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FrmMain frame = new FrmMain();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Metoda qe e ben update tabelen me te dhena nga databaza
	public void updateTable()
	{
		try 
		{
			String sql="select team as 'Team', games as 'Games played',wins as 'Wins', losses as 'Losses',"
						+"scored as 'Scored',taken as 'Taken',points as 'Points' from tblStandings order by points desc;";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rez ne objektin res.
			res=pst.executeQuery();
			//duhet te behet import rs2xml libraria.
			tblStandings.setModel(new DefaultTableModel(
				new Object[][] {
					{"Prishtina", new Integer(28), new Integer(27), new Integer(1), new Integer(2778), new Integer(1939), new Integer(55)},
					{"Bashkimi", new Integer(28), new Integer(23), new Integer(5), new Integer(2552), new Integer(2011), new Integer(51)},
					{"Rahoveci", new Integer(28), new Integer(16), new Integer(12), new Integer(2378), new Integer(2289), new Integer(44)},
					{"Golden Eagle Ylli", new Integer(28), new Integer(15), new Integer(13), new Integer(2553), new Integer(2402), new Integer(43)},
					{"Trepca", new Integer(28), new Integer(12), new Integer(16), new Integer(2176), new Integer(2342), new Integer(40)},
					{"Peja", new Integer(28), new Integer(8), new Integer(20), new Integer(2118), new Integer(2536), new Integer(36)},
					{"Borea", new Integer(28), new Integer(7), new Integer(21), new Integer(2226), new Integer(2711), new Integer(35)},
					{"Kerasan Prishtina", new Integer(28), new Integer(4), new Integer(24), new Integer(2115), new Integer(2666), new Integer(32)},
				},
				new String[] {
					"Team", "Games played", "Wins", "Losses", "Scored", "Taken", "Points"
				}
			));
			tblStandings.getColumnModel().getColumn(0).setPreferredWidth(111);
			pst.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Gabim gjate update te table."+e.getMessage());
		}
	}

	/**
	 * Create the frame.
	 */
	public FrmMain() {
		setTitle("Kucat Score");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMain.class.getResource("/images/icon.png")));
		conn=SQLConn.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		//Using exit from menu bar to exit the frame
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnFile.add(mntmExit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1084, 540);
		contentPane.add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		tabbedPane.setBackgroundAt(0, new Color(246, 144, 59));
		homePanel.setBackground(Color.WHITE);
		homePanel.setLayout(null);
		
		JPanel resultsPanel = new JPanel();
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.setBackgroundAt(1, new Color(246, 144, 59));
		
		JPanel standingsPanel = new JPanel();
		standingsPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Standings", null, standingsPanel, null);
		standingsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 50, 841, 154);
		standingsPanel.add(scrollPane);
		
		tblStandings = new JTable();
		tblStandings.setBackground(new Color(236,235,235));
		tblStandings.setFont(new Font("Calibri", Font.PLAIN, 18));
		scrollPane.setViewportView(tblStandings);
		
		JPanel dunkPanel = new JPanel();
		dunkPanel.setBounds(0, 0, 238, 512);
		dunkPanel.setBackground(new Color(246, 144, 59));
		standingsPanel.add(dunkPanel);
		dunkPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Lenovo\\Desktop\\knk-projekti\\dunk.png"));
		label.setBounds(10, 93, 218, 304);
		dunkPanel.add(label);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(238, 0, 841, 50);
		standingsPanel.add(titlePanel);
		titlePanel.setLayout(null);
		titlePanel.setBackground(new Color(246, 144, 59));
		
		JLabel lblStandingsTable = new JLabel("Standings Table");
		lblStandingsTable.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblStandingsTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblStandingsTable.setForeground(Color.WHITE);
		lblStandingsTable.setBounds(309, 11, 223, 28);
		titlePanel.add(lblStandingsTable);
		
		txtTeam = new JTextField();
		txtTeam.setBounds(327, 258, 188, 33);
		standingsPanel.add(txtTeam);
		txtTeam.setColumns(10);
		
		txtGamesplayed = new JTextField();
		txtGamesplayed.setColumns(10);
		txtGamesplayed.setBounds(564, 258, 188, 33);
		standingsPanel.add(txtGamesplayed);
		
		txtWins = new JTextField();
		txtWins.setColumns(10);
		txtWins.setBounds(801, 258, 188, 33);
		standingsPanel.add(txtWins);
		
		txtLosses = new JTextField();
		txtLosses.setColumns(10);
		txtLosses.setBounds(327, 328, 188, 33);
		standingsPanel.add(txtLosses);
		
		txtScored = new JTextField();
		txtScored.setColumns(10);
		txtScored.setBounds(564, 328, 188, 33);
		standingsPanel.add(txtScored);
		
		txtTaken = new JTextField();
		txtTaken.setColumns(10);
		txtTaken.setBounds(801, 328, 188, 33);
		standingsPanel.add(txtTaken);
		
		txtPoints = new JTextField();
		txtPoints.setColumns(10);
		txtPoints.setBounds(327, 398, 188, 33);
		standingsPanel.add(txtPoints);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setBounds(326, 237, 189, 20);
		standingsPanel.add(lblTeam);
		
		JLabel lblGamesPlayed = new JLabel("Games played");
		lblGamesPlayed.setBounds(564, 237, 188, 20);
		standingsPanel.add(lblGamesPlayed);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setBounds(803, 237, 188, 20);
		standingsPanel.add(lblWins);
		
		JLabel lblLosses = new JLabel("Losses");
		lblLosses.setBounds(327, 307, 188, 20);
		standingsPanel.add(lblLosses);
		
		JLabel lblScored = new JLabel("Scored");
		lblScored.setBounds(564, 310, 188, 20);
		standingsPanel.add(lblScored);
		
		JLabel lblTaken = new JLabel("Taken");
		lblTaken.setBounds(803, 310, 188, 20);
		standingsPanel.add(lblTaken);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setBounds(327, 379, 185, 20);
		standingsPanel.add(lblPoints);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnAdd.setBounds(564, 401, 111, 33);
		btnAdd.setBackground(new Color(246, 144, 59));
		standingsPanel.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnEdit.setBounds(726, 401, 111, 33);
		btnEdit.setBackground(new Color(246, 144, 59));
		standingsPanel.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnDelete.setBounds(878, 401, 111, 33);
		btnDelete.setBackground(new Color(246, 144, 59));
		standingsPanel.add(btnDelete);
		tabbedPane.setBackgroundAt(2, new Color(246, 144, 59));
		
		JPanel teamsPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamsPanel, null);
		tabbedPane.setBackgroundAt(3, new Color(246, 144, 59));
		updateTable();
		objMethods.setupTabTraversalKeys(tabbedPane);
		 
	}
}
