package projekti_KNK;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
//********Objektet e nevojshme per lidhje me databaze *****
		Connection conn=null;
		ResultSet res=null;
		PreparedStatement pst=null;
//*********************************************************		
		
		private JTable tblStandings;
		private JTextField txtTeam;
		private JTextField txtGamesplayed;
		private JTextField txtWins;
		private JTextField txtLosses;
		private JTextField txtScored;
		private JTextField txtTaken;
		private JTextField txtPoints;
		//Objekti i klases methods :
		Methods objMethods = new Methods();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.put("TabbedPane.selected", new Color(254,144,59));
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
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rezultatit ne objektin res.
			res=pst.executeQuery();
			//duhet te behet import rs2xml libraria.
			tblStandings.setModel(DbUtils.resultSetToTableModel(res));
			tblStandings.getColumnModel().getColumn(0).setPreferredWidth(111);
			
			pst.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Something went wrong while updating table."+e.getMessage());
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
		tabbedPane.setForegroundAt(0, Color.WHITE);
		tabbedPane.setBackgroundAt(0, Color.BLACK);
		homePanel.setBackground(Color.WHITE);
		homePanel.setLayout(null);
		
		JPanel resultsPanel = new JPanel();
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.BLACK);
		
		JPanel standingsPanel = new JPanel();
		standingsPanel.setBackground(Color.WHITE);
		tabbedPane.addTab("Standings", null, standingsPanel, null);
		tabbedPane.setForegroundAt(2, Color.WHITE);
		standingsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(238, 50, 841, 154);
		standingsPanel.add(scrollPane);
		
		tblStandings = new JTable();
		//Refresh table when mouse pointer enters the table space.
		tblStandings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				updateTable();
			}
		});
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
		tabbedPane.setBackgroundAt(2, Color.BLACK);
		
		
		JPanel teamsPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamsPanel, null);
		tabbedPane.setForegroundAt(3, Color.WHITE);
		tabbedPane.setBackgroundAt(3, Color.BLACK);
	
		
		objMethods.setupTabTraversalKeys(tabbedPane);//Thirrja per metoden qe mundeson nderrimin e tabave me TAB dhe shift+TAB
		updateTable();
	}
}
