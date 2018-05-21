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
import java.awt.event.InputEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain extends JFrame {

	private JPanel contentPane;
	private JTable tblStandings;
	//objekti per lidhje
		Connection conn=null;
		//objekti per vendosje te rezultatit
		ResultSet res=null;
		//objekti per query
		PreparedStatement pst=null;
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
					{"Bashkimi", new Integer(28), new Integer(23), new Integer(5), new Integer(2552), new Integer(2011), new Integer(56)},
					{"Prishtina", new Integer(28), new Integer(27), new Integer(1), new Integer(2778), new Integer(1939), new Integer(51)},
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
			) {
				Class[] columnTypes = new Class[] {
					String.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			tblStandings.getColumnModel().getColumn(0).setPreferredWidth(90);
			tblStandings.getColumnModel().getColumn(0).setMinWidth(90);
			tblStandings.getColumnModel().getColumn(1).setPreferredWidth(80);
			
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
		tabbedPane.setBackgroundAt(0, Color.ORANGE);
		tabbedPane.setForegroundAt(0, Color.WHITE);
		homePanel.setBackground(Color.WHITE);
		homePanel.setLayout(null);
		
		JPanel resultsPanel = new JPanel();
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.setBackgroundAt(1, Color.ORANGE);
		
		JPanel standingsPanel = new JPanel();
		tabbedPane.addTab("Standings", null, standingsPanel, null);
		standingsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(106, 52, 869, 155);
		standingsPanel.add(scrollPane);
		
		tblStandings = new JTable();
		tblStandings.setBackground(Color.WHITE);
		tblStandings.setForeground(Color.BLACK);
		tblStandings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblStandings);
		tblStandings.setColumnSelectionAllowed(true);
		tblStandings.setFont(new Font("Calibri", Font.PLAIN, 16));
		tabbedPane.setBackgroundAt(2, Color.ORANGE);
		
		JPanel teamsPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamsPanel, null);
		tabbedPane.setBackgroundAt(3, Color.ORANGE);
		updateTable();
	}
}
