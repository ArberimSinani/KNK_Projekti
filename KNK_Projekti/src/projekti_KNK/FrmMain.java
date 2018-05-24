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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.border.LineBorder;

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
		
		String team = "";
	
		//Objekti i klases methods :
		Methods objMethods = new Methods();
		private JTextField txtHteam;
		private JTextField txtAteam;
		private JTextField txtHS;
		private JTextField txtAS;
		private JTextField txtTime;
		private JTextField txtDate;
		private JTable tblResults;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UIManager.put("TabbedPane.selected", new Color(51, 102, 102));
					FrmMain frame = new FrmMain();
					//frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void updateTblResults()
	{
		try 
		{
			String sql="select ht_name as 'HT', at_name as 'AT',at_score as 'HT Score', at_score as 'AT Score'," 
									+"game_time as 'Time',game_date as 'Date' from tblResults;";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rezultatit ne objektin res.
			res=pst.executeQuery();
			//duhet te behet import rs2xml libraria.
			tblResults.setModel(DbUtils.resultSetToTableModel(res));
			tblResults.getColumnModel().getColumn(0).setPreferredWidth(125);
			tblResults.getColumnModel().getColumn(0).setMinWidth(125);
			
			pst.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Something went wrong while updating table."+e.getMessage());
		}
	}

	
	//Metoda qe e ben update tabelen me te dhena nga databaza
	public void updateTable()
	{
		try 
		{
			String sql="select team as 'Team', games as 'GP',wins as 'W', losses as 'L',"
						+"scored as 'Scored',taken as 'Taken',points as 'Points' from tblStandings order by points desc;";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rezultatit ne objektin res.
			res=pst.executeQuery();
			//duhet te behet import rs2xml libraria.
			tblStandings.setModel(DbUtils.resultSetToTableModel(res));
			tblStandings.getColumnModel().getColumn(0).setPreferredWidth(155);
			tblStandings.getColumnModel().getColumn(0).setMinWidth(155);
			tblStandings.getColumnModel().getColumn(1).setPreferredWidth(35);
			tblStandings.getColumnModel().getColumn(1).setMaxWidth(2147483612);
			tblStandings.getColumnModel().getColumn(2).setPreferredWidth(35);
			tblStandings.getColumnModel().getColumn(2).setMinWidth(34);
			tblStandings.getColumnModel().getColumn(4).setPreferredWidth(35);
			tblStandings.getColumnModel().getColumn(5).setPreferredWidth(35);
			tblStandings.getColumnModel().getColumn(6).setPreferredWidth(35);
			
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
		setTitle("Basketball");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMain.class.getResource("/images/15983-200.png")));
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
		homePanel.setBackground(new Color(51, 102, 102));
		homePanel.setLayout(null);
		
		
		JPanel teamsPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamsPanel, null);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.BLACK);
	
		
		objMethods.setupTabTraversalKeys(tabbedPane);//Thirrja per metoden qe mundeson nderrimin e tabave me TAB dhe shift+TAB
		
		JPanel standingsPanel = new JPanel();
		standingsPanel.setBackground(new Color(51, 102, 102));
		tabbedPane.addTab("Standings", null, standingsPanel, null);
		tabbedPane.setForegroundAt(2, Color.WHITE);
		tabbedPane.setBackgroundAt(2, Color.BLACK);
		standingsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(307, 82, 676, 153);
		scrollPane.setBorder(null); 
		scrollPane.setBackground(Color.BLACK);
		
		standingsPanel.add(scrollPane);
		
		tblStandings = new JTable();
		
		
		
		//Refresh table when mouse pointer enters the table space.
		tblStandings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				updateTable();
			}
			
			//Kur klikon ne rresht, te mbushen textFieldat
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel model=(DefaultTableModel)tblStandings.getModel();
				team=(String)model.getValueAt(tblStandings.getSelectedRow(),0);
				
				try 
				{
					String sql="select * from tblstandings where team='"+team+"'";
					pst=conn.prepareStatement(sql);
					res=pst.executeQuery();
					while(res.next()) 
					{
						txtTeam.setText(res.getString("team"));
						txtGamesplayed.setText(res.getString("games"));
						txtWins.setText(res.getString("wins"));
						txtLosses.setText(res.getString("losses"));
						txtScored.setText(res.getString("scored"));
						txtTaken.setText(res.getString("taken"));
						txtPoints.setText(res.getString("points"));
					}
					pst.close();
					
				}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, "Gabim gjate mbushjes se textbox me te dhena"+e.getMessage());
				}
				
				
			}
		});
		tblStandings.setBackground(new Color(204, 204, 255));
		tblStandings.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tblStandings.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
		tblStandings.getTableHeader().setBackground(Color.BLACK);
		tblStandings.getTableHeader().setForeground(Color.WHITE);
		tblStandings.getTableHeader().setBorder(null); 
		tblStandings.setBorder(new LineBorder(new Color(0, 0, 0), 1, true)); 
		
		
		scrollPane.setViewportView(tblStandings);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(tblStandings, popupMenu);
		
		//POPUP DELETE ==========================================================================================
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Delete from tblstandings where team='"+txtTeam.getText()+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTable();
					
					txtTeam.setText("");
					txtGamesplayed.setText("");
					txtWins.setText("");
					txtLosses.setText("");
					txtScored.setText("");
					txtTaken.setText("");
					txtPoints.setText("");
					
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null,"Gabim gjate fshirjes "+e2.getMessage());
				}
				
			}
		});
		popupMenu.add(mntmDelete);
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(260, 0, 819, 49);
		standingsPanel.add(titlePanel);
		titlePanel.setLayout(null);
		titlePanel.setBackground(new Color(0, 0, 0));
		
		JLabel lblStandingsTable = new JLabel("Standings ");
		lblStandingsTable.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblStandingsTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblStandingsTable.setForeground(Color.WHITE);
		lblStandingsTable.setBounds(294, 11, 223, 28);
		titlePanel.add(lblStandingsTable);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 263, 524);
		standingsPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setBounds(33, 4, 189, 20);
		panel.add(lblTeam);
		lblTeam.setForeground(Color.LIGHT_GRAY);
		
		txtTeam = new JTextField();
		txtTeam.setBackground(Color.LIGHT_GRAY);
		txtTeam.setBounds(34, 25, 188, 33);
		panel.add(txtTeam);
		txtTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtTeam.setColumns(10);
		
		JLabel lblGamesPlayed = new JLabel("Games played");
		lblGamesPlayed.setBounds(33, 64, 188, 20);
		panel.add(lblGamesPlayed);
		lblGamesPlayed.setForeground(Color.LIGHT_GRAY);
		
		txtGamesplayed = new JTextField();
		txtGamesplayed.setBackground(Color.LIGHT_GRAY);
		txtGamesplayed.setBounds(33, 85, 188, 33);
		panel.add(txtGamesplayed);
		txtGamesplayed.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtGamesplayed.setColumns(10);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setBounds(35, 129, 188, 20);
		panel.add(lblWins);
		lblWins.setForeground(Color.LIGHT_GRAY);
		
		txtWins = new JTextField();
		txtWins.setBackground(Color.LIGHT_GRAY);
		txtWins.setBounds(33, 150, 188, 33);
		panel.add(txtWins);
		txtWins.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtWins.setColumns(10);
		
		JLabel lblLosses = new JLabel("Losses");
		lblLosses.setBounds(34, 194, 188, 20);
		panel.add(lblLosses);
		lblLosses.setForeground(Color.LIGHT_GRAY);
		
		txtLosses = new JTextField();
		txtLosses.setBackground(Color.LIGHT_GRAY);
		txtLosses.setBounds(34, 215, 188, 33);
		panel.add(txtLosses);
		txtLosses.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtLosses.setColumns(10);
		
		JLabel lblScored = new JLabel("Scored");
		lblScored.setBounds(34, 259, 188, 20);
		panel.add(lblScored);
		lblScored.setForeground(Color.LIGHT_GRAY);
		
		txtScored = new JTextField();
		txtScored.setBackground(Color.LIGHT_GRAY);
		txtScored.setBounds(34, 277, 188, 33);
		panel.add(txtScored);
		txtScored.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtScored.setColumns(10);
		
		JLabel lblTaken = new JLabel("Taken");
		lblTaken.setBounds(36, 321, 188, 20);
		panel.add(lblTaken);
		lblTaken.setForeground(Color.LIGHT_GRAY);
		
		txtTaken = new JTextField();
		txtTaken.setBackground(Color.LIGHT_GRAY);
		txtTaken.setBounds(34, 339, 188, 33);
		panel.add(txtTaken);
		txtTaken.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtTaken.setColumns(10);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setBounds(34, 383, 185, 20);
		panel.add(lblPoints);
		lblPoints.setForeground(Color.LIGHT_GRAY);
		
		txtPoints = new JTextField();
		txtPoints.setBackground(Color.LIGHT_GRAY);
		txtPoints.setBounds(34, 402, 188, 33);
		panel.add(txtPoints);
		txtPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtPoints.setColumns(10);
		
		//Butoni RESET ===================================================================================
		JButton btnReset = new JButton("Reset");
		btnReset.setBounds(33, 479, 95, 33);
		panel.add(btnReset);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtTeam.setText("");
				txtGamesplayed.setText("");
				txtWins.setText("");
				txtLosses.setText("");
				txtScored.setText("");
				txtTaken.setText("");
				txtPoints.setText("");
			}
		});
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnReset.setBackground(Color.BLACK);
		
		//BUTONI DELETE ======================================================================================================
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(130, 479, 92, 33);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql = "Delete from tblstandings where team='"+txtTeam.getText()+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTable();
					
					txtTeam.setText("");
					txtGamesplayed.setText("");
					txtWins.setText("");
					txtLosses.setText("");
					txtScored.setText("");
					txtTaken.setText("");
					txtPoints.setText("");
					
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null,"Gabim gjate fshirjes "+e2.getMessage());
				}
				
				
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnDelete.setBackground(Color.BLACK);
		
		//Butoni ADD ============================================================================================================
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(33, 446, 95, 33);
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				String sql = "insert into tblstandings values "+"('"+txtTeam.getText()+"',"+txtGamesplayed.getText()+","+txtWins.getText()
				+","+txtLosses.getText()+","+txtScored.getText()+","+txtTaken.getText()+","+txtPoints.getText()+")";
				
				pst=conn.prepareStatement(sql);
				pst.execute();
				pst.close();
				updateTable();
				
				txtTeam.setText("");
				txtGamesplayed.setText("");
				txtWins.setText("");
				txtLosses.setText("");
				txtScored.setText("");
				txtTaken.setText("");
				txtPoints.setText("");
				
				
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Gabim gjate insertimit "+e.getMessage());
				}
				
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnAdd.setBackground(Color.BLACK);
		
		//Butoni Edit ===================================================================================
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(130, 446, 92, 33);
		panel.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql = "update tblstandings set Team='"+txtTeam.getText()+"', games="+txtGamesplayed.getText()+
							",wins="+txtWins.getText()+",losses="+txtLosses.getText()+",scored="+txtScored.getText()+",taken="
							+txtTaken.getText()+", points="+txtPoints.getText()+" where team='"+txtTeam.getText()+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTable();
					
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Gabim gjate editimit "+e1.getMessage());
					
				}
				
			}
		});
		btnEdit.setForeground(Color.WHITE);
		btnEdit.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnEdit.setBackground(Color.BLACK);
		
		JLabel label = new JLabel("");
		label.setBounds(868, 422, 84, 58);
		standingsPanel.add(label);
		label.setIcon(new ImageIcon(FrmMain.class.getResource("/images/basketball-logo-with-flames.png")));
		
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBackground(new Color(51, 102, 102));
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.setForegroundAt(3, Color.WHITE);
		tabbedPane.setBackgroundAt(3, Color.BLACK);
		resultsPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 536, 522);
		panel_1.setBackground(Color.BLACK);
		resultsPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblResults = new JLabel("Insert results");
		lblResults.setBounds(119, 53, 292, 28);
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults.setForeground(Color.WHITE);
		lblResults.setFont(new Font("Calibri", Font.PLAIN, 23));
		panel_1.add(lblResults);
		
		txtHteam = new JTextField();
		txtHteam.setBounds(58, 133, 178, 28);
		panel_1.add(txtHteam);
		txtHteam.setColumns(10);
		
		txtAteam = new JTextField();
		txtAteam.setBounds(288, 133, 183, 28);
		txtAteam.setColumns(10);
		panel_1.add(txtAteam);
		
		txtHS = new JTextField();
		txtHS.setBounds(288, 225, 178, 28);
		txtHS.setColumns(10);
		panel_1.add(txtHS);
		
		txtAS = new JTextField();
		txtAS.setBounds(58, 226, 178, 28);
		txtAS.setColumns(10);
		panel_1.add(txtAS);
		
		txtTime = new JTextField();
		txtTime.setBounds(58, 313, 183, 28);
		txtTime.setColumns(10);
		panel_1.add(txtTime);
		
		txtDate = new JTextField();
		txtDate.setBounds(288, 314, 178, 28);
		txtDate.setColumns(10);
		panel_1.add(txtDate);
		
		JButton btnAdd_1 = new JButton("ADD");
		btnAdd_1.setBounds(72, 396, 89, 36);
		btnAdd_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdd_1.setBackground(Color.BLACK);
		btnAdd_1.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnAdd_1);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBounds(171, 396, 89, 36);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnNewButton);
		
		JButton btnEdit_1 = new JButton("Edit");
		btnEdit_1.setBounds(270, 396, 89, 36);
		btnEdit_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnEdit_1.setBackground(Color.BLACK);
		btnEdit_1.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnEdit_1);
		
		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.setBounds(369, 396, 89, 36);
		btnReset_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset_1.setBackground(Color.BLACK);
		btnReset_1.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnReset_1);
		
		JLabel lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setBounds(58, 108, 104, 14);
		lblHomeTeam.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblHomeTeam);
		
		JLabel lblAwayTeam = new JLabel("Away Team");
		lblAwayTeam.setBounds(289, 108, 104, 14);
		lblAwayTeam.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblAwayTeam);
		
		JLabel lblHomeTeamScores = new JLabel("Home Team Scores");
		lblHomeTeamScores.setBounds(290, 200, 160, 14);
		lblHomeTeamScores.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblHomeTeamScores);
		
		JLabel lblAwayTeamScores = new JLabel("Away Team Scores");
		lblAwayTeamScores.setBounds(58, 200, 178, 15);
		lblAwayTeamScores.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblAwayTeamScores);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(58, 288, 104, 14);
		lblTime.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblTime);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(290, 288, 104, 14);
		lblDate.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblDate);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBorder(null);
		scrollPane2.setBackground(Color.BLACK);
		scrollPane2.setBounds(535, 49, 676, 153);
		resultsPanel.add(scrollPane2);
		
		tblResults = new JTable();
		scrollPane2.setViewportView(tblResults);
		updateTable();
		updateTblResults();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
