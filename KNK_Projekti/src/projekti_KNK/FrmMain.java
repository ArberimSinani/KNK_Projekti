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
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import java.awt.event.KeyAdapter;

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
		
		String team ;
		private int id;
		
		private JTable tblUpcoming;
		private JTextField txtSearch;
		
		//Objekti i klases methods :
		Methods objMethods = new Methods();
		private JTextField txtHteam;
		private JTextField txtAteam;
		private JTextField txtHS;
		private JTextField txtAS;
		private JTextField txtTime;
		private JTextField txtDate;
		private JTable tblResults;
		private JTable tblPrishtina;
		private JTable tblTrepca;
		private JTable tblPeja;
		private JTable tblBashkimi;
		private JTable tblRahoveci;
		private JTable tblBorea;
		private JTable tblGolden;
		private JTable tblKerasan;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try { 
					try {
					    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					        if ("Nimbus".equals(info.getName())) {
					            UIManager.setLookAndFeel(info.getClassName());
					            break;
					        }
					    }
					} catch (Exception e) {
					    // If Nimbus is not available, you can set the GUI to another look and feel.
					}
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
	//====================================================================================================================================================
		//Funksioni per update te tabeles tblUpcoming..............mos harro me thirr funksionin n'fund
		//====================================================================================================================================================
		public void updateTblUpcoming()
		{
			try 
			{
				String sql="select ht_name as 'HT', at_name as 'AT'," 
										+"game_time as 'Time',game_date as 'Date' from tblResults where ht_score is null order by game_date asc;";
				pst=conn.prepareStatement(sql);			
				res=pst.executeQuery();
				tblUpcoming.setModel(DbUtils.resultSetToTableModel(res));
				
				
				pst.close();
			} 
			catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null, "Something went wrong while updating table."+e.getMessage());
			}
		}
	public void updateTblTeams(JTable table, String teamName)
	{
		try 
		{
			String sql="select ht_name as 'HT', at_name as 'AT',ht_score as 'HT Score', at_score as 'AT Score'," 
									+"game_time as 'Time',game_date as 'Date' from tblResults where ht_name ='"+teamName+"' or at_name ='"+teamName+"' order by game_date desc;";
			pst=conn.prepareStatement(sql);
			//objekti qe mundeson ekzekutimin e querit dhe vendosjen e rezultatit ne objektin res.
			res=pst.executeQuery();
			//duhet te behet import rs2xml libraria.
			table.setModel(DbUtils.resultSetToTableModel(res));
			pst.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Something went wrong while updating table."+e.getMessage());
		}
	}
	public void updateTblResults()
	{
		try 
		{
			String sql="select ht_name as 'HT', at_name as 'AT',ht_score as 'HT Score', at_score as 'AT Score'," 
									+"game_time as 'Time',game_date as 'Date' from tblResults order by game_date desc;";
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
		
		JMenu mnLang = new JMenu("Lang");
		menuBar.add(mnLang);
		
		JMenuItem mntmEng = new JMenuItem("Eng");
		mnLang.add(mntmEng);
		
		JMenuItem mntmAlb = new JMenuItem("Alb");
		mnLang.add(mntmAlb);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
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
		
		//=====================================================================================
				//Scroll pane per upcoming games table-----------------
				//=====================================================================================
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(128, 201, 816, 175);
				homePanel.add(scrollPane_1);
				
				tblUpcoming = new JTable();
				
				scrollPane_1.setViewportView(tblUpcoming);
				//====================================================================================================================================================		
				//============================================Search fieldi=======================================================================================================
				//====================================================================================================================================================
				txtSearch = new JTextField();
				txtSearch.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						try {
							
							//String sql = "SELECT  * FROM tblresults where ht_name like '"+txtSearch.getText()+"%' or at_name like '"+txtSearch.getText()+"%'; ";
							
							String sql = "SELECT ht_name as HT,at_name as AT,game_time as Time,game_date as Date from tblresults where ht_score is null and (ht_name like '"+txtSearch.getText()+"%' or at_name like "
									+ "'"+txtSearch.getText()+"%');";
							pst=conn.prepareStatement(sql);
							res=pst.executeQuery();
							tblUpcoming.setModel(DbUtils.resultSetToTableModel(res));
							pst.close();
						}
						catch(Exception e) {
							
							JOptionPane.showMessageDialog(null, "Something went wrong with the search : " + e.getMessage() );
							
						}
					}
				});
				txtSearch.setBounds(350, 156, 238, 38);
				homePanel.add(txtSearch);
				txtSearch.setColumns(10);
				//====================================================================================================================================================
				//label per search field
				//====================================================================================================================================================
				JLabel lblSearchForUpcoming = new JLabel("Search for upcoming games:");
				lblSearchForUpcoming.setBounds(128, 154, 238, 36);
				homePanel.add(lblSearchForUpcoming);
				lblSearchForUpcoming.setForeground(Color.WHITE);
				lblSearchForUpcoming.setFont(new Font("Tahoma", Font.PLAIN, 17));
				//===========================================================================================================================================
		
		objMethods.setupTabTraversalKeys(tabbedPane);//Thirrja per metoden qe mundeson nderrimin e tabave me TAB dhe shift+TAB
		
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBackground(new Color(51, 102, 102));
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.BLACK);
		resultsPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 536, 522);
		panel_1.setBackground(Color.BLACK);
		resultsPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblResults = new JLabel("Insert results");
		lblResults.setBounds(119, 12, 292, 28);
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults.setForeground(Color.WHITE);
		lblResults.setFont(new Font("Calibri", Font.PLAIN, 23));
		panel_1.add(lblResults);
		
		txtHteam = new JTextField();
		txtHteam.setBounds(57, 92, 183, 33);
		panel_1.add(txtHteam);
		txtHteam.setColumns(10);
		
		txtAteam = new JTextField();
		txtAteam.setBounds(287, 92, 183, 33);
		txtAteam.setColumns(10);
		panel_1.add(txtAteam);
		
		txtHS = new JTextField();
		txtHS.setBounds(57, 198, 183, 33);
		txtHS.setColumns(10);
		panel_1.add(txtHS);
		
		txtAS = new JTextField();
		txtAS.setBounds(287, 198, 183, 33);
		txtAS.setColumns(10);
		panel_1.add(txtAS);
		
		txtTime = new JTextField();
		txtTime.setBounds(57, 298, 183, 33);
		txtTime.setColumns(10);
		panel_1.add(txtTime);
		
		txtDate = new JTextField();
		txtDate.setHorizontalAlignment(SwingConstants.LEFT);
		txtDate.setToolTipText("Date format: yyyy-MM-dd");
		txtDate.setBounds(287, 299, 183, 33);
		txtDate.setColumns(10);
		panel_1.add(txtDate);
//****************ADD TO TABLE RESULTS*************************************************************************		
		JButton btnAddResults = new JButton("ADD");
		btnAddResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "Insert into tblResults (ht_name, at_name, ht_score, at_score,game_time, game_date) values ('"+txtHteam.getText()+"','"
						+txtAteam.getText()+"',"+txtHS.getText()+","+txtAS.getText()+",'"+txtTime.getText()+"','"+txtDate.getText()+"');";
					pst= conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTblResults();
					txtHteam.setText("");
					txtAteam.setText("");
					txtHS.setText("");
					txtAS.setText("");
					txtTime.setText("");
					txtDate.setText("");
				}
				catch(Exception e){
					
				}
				
			}
		});
		btnAddResults.setBounds(72, 396, 89, 36);
		btnAddResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddResults.setBackground(Color.BLACK);
		btnAddResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnAddResults);
//************DELETE FROM TABLE RESULTS**************************
		JButton btnDeleteResults = new JButton("Delete");
		btnDeleteResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "Delete from tblresults where ht_name='"+txtHteam.getText()+"' and game_date = '"+txtDate.getText()+"';";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					
					txtHteam.setText("");
					txtAteam.setText("");
					txtHS.setText("");
					txtAS.setText("");
					txtTime.setText("");
					txtDate.setText("");
					updateTblResults();
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(null,"Gabim gjate fshirjes "+e2.getMessage());
				}
				
				
			}
			
			}
		);
		btnDeleteResults.setBounds(171, 396, 89, 36);
		btnDeleteResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteResults.setBackground(Color.BLACK);
		btnDeleteResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnDeleteResults);
		
//*******************EDIT TABLE RESULTS*******************************************
		
		JButton btnEditResults = new JButton("Edit");
		btnEditResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "update tblresults set ht_name='"+txtHteam.getText()+"', at_name='"+txtAteam.getText()+
							"',ht_score='"+txtHS.getText()+"',at_score='"+txtAS.getText()+"',game_time='"+txtTime.getText()+"',game_date='"
							+txtDate.getText()+"'  where ht_name='"+txtHteam.getText()+"' and game_date = '"+txtDate.getText()+"';";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					txtHteam.setText("");
					txtAteam.setText("");
					txtHS.setText("");
					txtAS.setText("");
					txtTime.setText("");
					txtDate.setText("");
					updateTblResults();
					
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Gabim gjate editimit "+e1.getMessage());
					
				}
				
				
			}
		});
		btnEditResults.setBounds(270, 396, 89, 36);
		btnEditResults.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnEditResults.setBackground(Color.BLACK);
		btnEditResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnEditResults);
		
		JButton btnResetResults = new JButton("Reset");
		btnResetResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtHteam.setText("");
				txtAteam.setText("");
				txtHS.setText("");
				txtAS.setText("");
				txtTime.setText("");
				txtDate.setText("");
				updateTblResults();
			}
		});
		btnResetResults.setBounds(369, 396, 89, 36);
		btnResetResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResetResults.setBackground(Color.BLACK);
		btnResetResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnResetResults);
		
		JLabel lblHomeTeam = new JLabel("Home Team");
		lblHomeTeam.setBounds(57, 67, 183, 14);
		lblHomeTeam.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblHomeTeam);
		
		JLabel lblAwayTeam = new JLabel("Away Team");
		lblAwayTeam.setBounds(288, 67, 182, 14);
		lblAwayTeam.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblAwayTeam);
		
		JLabel lblHomeTeamScores = new JLabel("Home Team Scores");
		lblHomeTeamScores.setBounds(57, 174, 183, 14);
		lblHomeTeamScores.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblHomeTeamScores);
		
		JLabel lblAwayTeamScores = new JLabel("Away Team Scores");
		lblAwayTeamScores.setBounds(287, 171, 178, 15);
		lblAwayTeamScores.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblAwayTeamScores);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(57, 273, 183, 14);
		lblTime.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblTime);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(289, 273, 181, 14);
		lblDate.setForeground(Color.LIGHT_GRAY);
		panel_1.add(lblDate);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				updateTblResults();
			}
		});
		scrollPane2.setBorder(null);
		scrollPane2.setBackground(Color.BLACK);
		scrollPane2.setBounds(535, 49, 544, 153);
		resultsPanel.add(scrollPane2);
		
		tblResults = new JTable();
		tblResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model=(DefaultTableModel)tblResults.getModel();
				team=(String)model.getValueAt(tblResults.getSelectedRow(),0);
				
				try 
				{
					String sql="select * from tblresults where ht_name='"+team+"'";
					pst=conn.prepareStatement(sql);
					res=pst.executeQuery();
					while(res.next()) 
					{
						txtHteam.setText(res.getString("ht_name"));
						txtAteam.setText(res.getString("at_name"));
						txtHS.setText(String.valueOf(res.getInt("ht_score")));
						txtAS.setText(String.valueOf(res.getInt("at_score")));
						txtTime.setText(res.getString("game_time"));
						txtDate.setText(res.getString("game_date"));
				
					}
					pst.close();
					
				}
				catch (Exception e) 
				{
					JOptionPane.showMessageDialog(null, "Gabim gjate mbushjes se textbox me te dhena"+e.getMessage());
				}
			}
		});
		scrollPane2.setViewportView(tblResults);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(535, 0, 544, 49);
		resultsPanel.add(panel_2);
		
		JLabel lblResults_1 = new JLabel("Results");
		lblResults_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults_1.setForeground(Color.WHITE);
		lblResults_1.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblResults_1.setBounds(139, 12, 223, 28);
		panel_2.add(lblResults_1);
		
		JPanel standingsPanel = new JPanel();
		standingsPanel.setBackground(new Color(51, 102, 102));
		tabbedPane.addTab("Standings", null, standingsPanel, null);
		tabbedPane.setForegroundAt(2, Color.WHITE);
		tabbedPane.setBackgroundAt(2, Color.BLACK);
		standingsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(369, 84, 676, 153);
		scrollPane.setBorder(null); 
		scrollPane.setBackground(Color.BLACK);
		
		standingsPanel.add(scrollPane);
		
		tblStandings = new JTable();
		tblStandings.setLocation(369, 0);
		
		
		
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
		titlePanel.setBounds(331, 0, 753, 49);
		standingsPanel.add(titlePanel);
		titlePanel.setLayout(null);
		titlePanel.setBackground(new Color(0, 0, 0));
		
		JLabel lblStandingsTable = new JLabel("Standings ");
		lblStandingsTable.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblStandingsTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblStandingsTable.setForeground(Color.WHITE);
		lblStandingsTable.setBounds(265, 11, 223, 28);
		titlePanel.add(lblStandingsTable);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 332, 524);
		standingsPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setBounds(33, 38, 189, 20);
		panel.add(lblTeam);
		lblTeam.setForeground(Color.LIGHT_GRAY);
		
		txtTeam = new JTextField();
		txtTeam.setBackground(Color.WHITE);
		txtTeam.setBounds(34, 59, 188, 33);
		panel.add(txtTeam);
		txtTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtTeam.setColumns(10);
		
		JLabel lblGamesPlayed = new JLabel("Games played");
		lblGamesPlayed.setBounds(33, 101, 188, 20);
		panel.add(lblGamesPlayed);
		lblGamesPlayed.setForeground(Color.LIGHT_GRAY);
		
		txtGamesplayed = new JTextField();
		txtGamesplayed.setBackground(Color.WHITE);
		txtGamesplayed.setBounds(33, 122, 188, 33);
		panel.add(txtGamesplayed);
		txtGamesplayed.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtGamesplayed.setColumns(10);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setBounds(35, 166, 188, 20);
		panel.add(lblWins);
		lblWins.setForeground(Color.LIGHT_GRAY);
		
		txtWins = new JTextField();
		txtWins.setBackground(Color.WHITE);
		txtWins.setBounds(33, 187, 188, 33);
		panel.add(txtWins);
		txtWins.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtWins.setColumns(10);
		
		JLabel lblLosses = new JLabel("Losses");
		lblLosses.setBounds(34, 231, 188, 20);
		panel.add(lblLosses);
		lblLosses.setForeground(Color.LIGHT_GRAY);
		
		txtLosses = new JTextField();
		txtLosses.setBackground(Color.WHITE);
		txtLosses.setBounds(34, 252, 188, 33);
		panel.add(txtLosses);
		txtLosses.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtLosses.setColumns(10);
		
		JLabel lblScored = new JLabel("Scored");
		lblScored.setBounds(34, 296, 188, 20);
		panel.add(lblScored);
		lblScored.setForeground(Color.LIGHT_GRAY);
		
		txtScored = new JTextField();
		txtScored.setBackground(Color.WHITE);
		txtScored.setBounds(34, 314, 188, 33);
		panel.add(txtScored);
		txtScored.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtScored.setColumns(10);
		
		JLabel lblTaken = new JLabel("Taken");
		lblTaken.setBounds(36, 358, 188, 20);
		panel.add(lblTaken);
		lblTaken.setForeground(Color.LIGHT_GRAY);
		
		txtTaken = new JTextField();
		txtTaken.setBackground(Color.WHITE);
		txtTaken.setBounds(34, 376, 188, 33);
		panel.add(txtTaken);
		txtTaken.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtTaken.setColumns(10);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setBounds(34, 420, 185, 20);
		panel.add(lblPoints);
		lblPoints.setForeground(Color.LIGHT_GRAY);
		
		txtPoints = new JTextField();
		txtPoints.setBackground(Color.WHITE);
		txtPoints.setBounds(34, 439, 188, 33);
		panel.add(txtPoints);
		txtPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtPoints.setColumns(10);
		
		//Butoni ADD ============================================================================================================
		JButton btnAddStandings = new JButton("Add");
		btnAddStandings.setBounds(230, 252, 95, 33);
		panel.add(btnAddStandings);
		btnAddStandings.addActionListener(new ActionListener() {
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
		btnAddStandings.setForeground(Color.WHITE);
		btnAddStandings.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnAddStandings.setBackground(Color.BLACK);
		
		//Butoni Edit ===================================================================================
		JButton btnEditStandings = new JButton("Edit");
		btnEditStandings.setBounds(231, 314, 92, 33);
		panel.add(btnEditStandings);
		btnEditStandings.addActionListener(new ActionListener() {
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
		btnEditStandings.setForeground(Color.WHITE);
		btnEditStandings.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnEditStandings.setBackground(Color.BLACK);
		
		//BUTONI DELETE ======================================================================================================
		JButton btnDeleteStandings = new JButton("Delete");
		btnDeleteStandings.setBounds(231, 376, 92, 33);
		panel.add(btnDeleteStandings);
		btnDeleteStandings.addActionListener(new ActionListener() {
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
		btnDeleteStandings.setForeground(Color.WHITE);
		btnDeleteStandings.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnDeleteStandings.setBackground(Color.BLACK);
		
		//Butoni RESET ===================================================================================
		JButton btnResetStandings = new JButton("Reset");
		btnResetStandings.setBounds(230, 439, 95, 33);
		panel.add(btnResetStandings);
		btnResetStandings.addActionListener(new ActionListener() {
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
		btnResetStandings.setForeground(Color.WHITE);
		btnResetStandings.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnResetStandings.setBackground(Color.BLACK);
		
		JLabel label = new JLabel("1.");
		label.setBounds(331, 108, 46, 14);
		standingsPanel.add(label);
		
		
		JPanel teamsPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamsPanel, null);
		teamsPanel.setLayout(null);
		
		JPanel panelBorea = new JPanel();
		panelBorea.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelBorea);
		panelBorea.setLayout(null);
		
		JLabel lblBorea = new JLabel("BOREA");
		lblBorea.setToolTipText("");
		lblBorea.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblBorea.setBounds(73, 8, 219, 30);
		panelBorea.add(lblBorea);
		
		JPanel panel_25 = new JPanel();
		panel_25.setLayout(null);
		panel_25.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_25.setBounds(72, 47, 207, 272);
		panelBorea.add(panel_25);
		
		JLabel label_21 = new JLabel("");
		label_21.setBounds(103, 6, 0, 0);
		panel_25.add(label_21);
		
		JLabel label_31 = new JLabel("");
		label_31.setIcon(new ImageIcon(FrmMain.class.getResource("/images/borea_large.png")));
		label_31.setBounds(28, 31, 146, 214);
		panel_25.add(label_31);
		
		JPanel panel_26 = new JPanel();
		panel_26.setLayout(null);
		panel_26.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_26.setBackground(new Color(0, 102, 153));
		panel_26.setBounds(255, 47, 278, 272);
		panelBorea.add(panel_26);
		
		JLabel lblBorea_1 = new JLabel("Borea");
		lblBorea_1.setForeground(Color.WHITE);
		lblBorea_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBorea_1.setBounds(32, 46, 142, 21);
		panel_26.add(lblBorea_1);
		
		JLabel lblEmailKbboreagmailcom = new JLabel("Email: kb.borea@gmail.com");
		lblEmailKbboreagmailcom.setForeground(Color.WHITE);
		lblEmailKbboreagmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKbboreagmailcom.setBounds(32, 78, 216, 21);
		panel_26.add(lblEmailKbboreagmailcom);
		
		JLabel lblTel_2 = new JLabel("Tel: 049 131 414");
		lblTel_2.setForeground(Color.WHITE);
		lblTel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTel_2.setBounds(32, 110, 142, 21);
		panel_26.add(lblTel_2);
		
		JLabel lblPersoniKontaktuesKastriot = new JLabel("Personi Kontaktues: Kastriot Blakaj");
		lblPersoniKontaktuesKastriot.setForeground(Color.WHITE);
		lblPersoniKontaktuesKastriot.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesKastriot.setBounds(32, 141, 240, 21);
		panel_26.add(lblPersoniKontaktuesKastriot);
		
		JLabel label_36 = new JLabel("Ngjyra e pare:");
		label_36.setForeground(Color.WHITE);
		label_36.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_36.setBounds(32, 178, 91, 21);
		panel_26.add(label_36);
		
		JLabel label_37 = new JLabel("Ngjyra e dyte:");
		label_37.setForeground(Color.WHITE);
		label_37.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_37.setBounds(32, 210, 91, 21);
		panel_26.add(label_37);
		
		JPanel panel_27 = new JPanel();
		panel_27.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_27.setBackground(new Color(0, 153, 255));
		panel_27.setBounds(120, 179, 20, 20);
		panel_26.add(panel_27);
		
		JPanel panel_28 = new JPanel();
		panel_28.setBorder(null);
		panel_28.setBackground(new Color(153, 0, 0));
		panel_28.setBounds(120, 211, 20, 20);
		panel_26.add(panel_28);
		
		JLabel label_38 = new JLabel("Pershkrimi");
		label_38.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_38.setBounds(564, 47, 219, 30);
		panelBorea.add(label_38);
		
		JLabel lblTrajneriKastriotBlakaj = new JLabel("Trajneri: Kastriot Blakaj");
		lblTrajneriKastriotBlakaj.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriKastriotBlakaj.setBounds(564, 85, 168, 19);
		panelBorea.add(lblTrajneriKastriotBlakaj);
		
		JLabel label_40 = new JLabel("Ndeshjet - 2018");
		label_40.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_40.setBounds(71, 331, 137, 19);
		panelBorea.add(label_40);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(72, 365, 595, 120);
		panelBorea.add(scrollPane_6);
		
		tblBorea = new JTable();
		scrollPane_6.setViewportView(tblBorea);
		panelBorea.setVisible(false);
		
		JPanel panelKPrishtina = new JPanel();
		panelKPrishtina.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelKPrishtina);
		panelKPrishtina.setLayout(null);
		
		JLabel lblKerasanPrishtina = new JLabel("KERASAN PRISHTINA");
		lblKerasanPrishtina.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblKerasanPrishtina.setBounds(71, 11, 304, 30);
		panelKPrishtina.add(lblKerasanPrishtina);
		
		JPanel panel_33 = new JPanel();
		panel_33.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_33.setBounds(72, 47, 207, 272);
		panelKPrishtina.add(panel_33);
		panel_33.setLayout(null);
		
		JLabel label_13 = new JLabel("");
		label_13.setBounds(103, 6, 0, 0);
		panel_33.add(label_13);
		
		JLabel label_29 = new JLabel("");
		label_29.setIcon(new ImageIcon(FrmMain.class.getResource("/images/kerprishtina_large.png")));
		label_29.setBounds(27, 21, 149, 225);
		panel_33.add(label_29);
		
		JPanel panel_34 = new JPanel();
		panel_34.setLayout(null);
		panel_34.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_34.setBackground(new Color(0, 102, 153));
		panel_34.setBounds(255, 47, 278, 272);
		panelKPrishtina.add(panel_34);
		
		JLabel lblKerasanPrishtina_1 = new JLabel("Kerasan Prishtina");
		lblKerasanPrishtina_1.setForeground(Color.WHITE);
		lblKerasanPrishtina_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKerasanPrishtina_1.setBounds(32, 46, 142, 21);
		panel_34.add(lblKerasanPrishtina_1);
		
		JLabel lblEmailKbkerasangmailcom = new JLabel("Email: kb.kerasan@gmail.com");
		lblEmailKbkerasangmailcom.setForeground(Color.WHITE);
		lblEmailKbkerasangmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKbkerasangmailcom.setBounds(32, 78, 216, 21);
		panel_34.add(lblEmailKbkerasangmailcom);
		
		JLabel lblTel_4 = new JLabel("Tel: 044 141 055");
		lblTel_4.setForeground(Color.WHITE);
		lblTel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTel_4.setBounds(32, 110, 142, 21);
		panel_34.add(lblTel_4);
		
		JLabel lblPersoniKontaktuesDritero = new JLabel("Personi Kontaktues: Dritero Sefaja");
		lblPersoniKontaktuesDritero.setForeground(Color.WHITE);
		lblPersoniKontaktuesDritero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesDritero.setBounds(32, 141, 240, 21);
		panel_34.add(lblPersoniKontaktuesDritero);
		
		JLabel label_44 = new JLabel("Ngjyra e pare:");
		label_44.setForeground(Color.WHITE);
		label_44.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_44.setBounds(32, 178, 91, 21);
		panel_34.add(label_44);
		
		JLabel label_46 = new JLabel("Ngjyra e dyte:");
		label_46.setForeground(Color.WHITE);
		label_46.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_46.setBounds(32, 210, 91, 21);
		panel_34.add(label_46);
		
		JPanel panel_35 = new JPanel();
		panel_35.setBackground(new Color(102, 0, 0));
		panel_35.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_35.setBounds(120, 179, 20, 20);
		panel_34.add(panel_35);
		
		JPanel panel_36 = new JPanel();
		panel_36.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_36.setBackground(Color.BLUE);
		panel_36.setBounds(120, 211, 20, 20);
		panel_34.add(panel_36);
		
		JLabel label_47 = new JLabel("Pershkrimi");
		label_47.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_47.setBounds(564, 47, 219, 30);
		panelKPrishtina.add(label_47);
		
		JLabel lblTrajneriDriteroSefaja = new JLabel("Trajneri: Dritero Sefaja");
		lblTrajneriDriteroSefaja.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriDriteroSefaja.setBounds(564, 89, 168, 19);
		panelKPrishtina.add(lblTrajneriDriteroSefaja);
		
		JLabel label_49 = new JLabel("Ndeshjet - 2018");
		label_49.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_49.setBounds(72, 332, 137, 19);
		panelKPrishtina.add(label_49);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(72, 365, 595, 120);
		panelKPrishtina.add(scrollPane_8);
		
		tblKerasan = new JTable();
		scrollPane_8.setViewportView(tblKerasan);
		panelKPrishtina.setVisible(false);
		
		
		JPanel panelGEYlli = new JPanel();
		panelGEYlli.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelGEYlli);
		panelGEYlli.setLayout(null);
		
		JLabel lblGoldenEagleYlli = new JLabel("GOLDEN EAGLE YLLI");
		lblGoldenEagleYlli.setToolTipText("");
		lblGoldenEagleYlli.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblGoldenEagleYlli.setBounds(73, 8, 302, 30);
		panelGEYlli.add(lblGoldenEagleYlli);
		
		JPanel panel_29 = new JPanel();
		panel_29.setLayout(null);
		panel_29.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_29.setBounds(72, 47, 207, 272);
		panelGEYlli.add(panel_29);
		
		JLabel label_24 = new JLabel("");
		label_24.setBounds(103, 6, 0, 0);
		panel_29.add(label_24);
		
		JLabel label_28 = new JLabel("");
		label_28.setIcon(new ImageIcon(FrmMain.class.getResource("/images/gey_large.png")));
		label_28.setBounds(32, 18, 158, 236);
		panel_29.add(label_28);
		
		JPanel panel_30 = new JPanel();
		panel_30.setLayout(null);
		panel_30.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_30.setBackground(new Color(0, 102, 153));
		panel_30.setBounds(255, 47, 278, 272);
		panelGEYlli.add(panel_30);
		
		JLabel lblGoldenEagleYlli_1 = new JLabel("Golden Eagle Ylli");
		lblGoldenEagleYlli_1.setForeground(Color.WHITE);
		lblGoldenEagleYlli_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGoldenEagleYlli_1.setBounds(32, 46, 142, 21);
		panel_30.add(lblGoldenEagleYlli_1);
		
		JLabel lblEmailKbylligmailcom = new JLabel("Email: k.b-ylli@gmail.com");
		lblEmailKbylligmailcom.setForeground(Color.WHITE);
		lblEmailKbylligmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKbylligmailcom.setBounds(32, 78, 216, 21);
		panel_30.add(lblEmailKbylligmailcom);
		
		JLabel lblTel_3 = new JLabel("Tel: 049 401 907");
		lblTel_3.setForeground(Color.WHITE);
		lblTel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTel_3.setBounds(32, 110, 142, 21);
		panel_30.add(lblTel_3);
		
		JLabel lblPersoniKontaktuesEgzon = new JLabel("Personi Kontaktues: Egzon Fetiu");
		lblPersoniKontaktuesEgzon.setForeground(Color.WHITE);
		lblPersoniKontaktuesEgzon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesEgzon.setBounds(32, 141, 240, 21);
		panel_30.add(lblPersoniKontaktuesEgzon);
		
		JLabel label_41 = new JLabel("Ngjyra e pare:");
		label_41.setForeground(Color.WHITE);
		label_41.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_41.setBounds(32, 178, 91, 21);
		panel_30.add(label_41);
		
		JLabel label_42 = new JLabel("Ngjyra e dyte:");
		label_42.setForeground(Color.WHITE);
		label_42.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_42.setBounds(32, 210, 91, 21);
		panel_30.add(label_42);
		
		JPanel panel_31 = new JPanel();
		panel_31.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_31.setBackground(new Color(0, 0, 255));
		panel_31.setBounds(120, 179, 20, 20);
		panel_30.add(panel_31);
		
		JPanel panel_32 = new JPanel();
		panel_32.setBorder(null);
		panel_32.setBackground(new Color(255, 255, 255));
		panel_32.setBounds(120, 211, 20, 20);
		panel_30.add(panel_32);
		
		JLabel label_43 = new JLabel("Pershkrimi");
		label_43.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_43.setBounds(564, 47, 219, 30);
		panelGEYlli.add(label_43);
		
		JLabel lblTrajneriDenizBrajmovci = new JLabel("Trajneri: Deniz Brajmovci");
		lblTrajneriDenizBrajmovci.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriDenizBrajmovci.setBounds(564, 85, 168, 19);
		panelGEYlli.add(lblTrajneriDenizBrajmovci);
		
		JLabel label_45 = new JLabel("Ndeshjet - 2018");
		label_45.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_45.setBounds(72, 331, 137, 19);
		panelGEYlli.add(label_45);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(72, 363, 595, 120);
		panelGEYlli.add(scrollPane_7);
		
		tblGolden = new JTable();
		scrollPane_7.setViewportView(tblGolden);
		
		JLabel lblTrajneriAsistuesHasan = new JLabel("Trajneri Asistues: Hasan Bytyqi");
		lblTrajneriAsistuesHasan.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriAsistuesHasan.setBounds(564, 119, 168, 19);
		panelGEYlli.add(lblTrajneriAsistuesHasan);
		panelGEYlli.setVisible(false);
		
		
		JPanel panelRahoveci = new JPanel();
		panelRahoveci.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelRahoveci);
		panelRahoveci.setLayout(null);
		
		JLabel lblRahoveci = new JLabel("RAHOVECI");
		lblRahoveci.setToolTipText("");
		lblRahoveci.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblRahoveci.setBounds(73, 8, 219, 30);
		panelRahoveci.add(lblRahoveci);
		
		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_21.setBounds(72, 47, 207, 272);
		panelRahoveci.add(panel_21);
		panel_21.setLayout(null);
		
		JLabel label_17 = new JLabel("");
		label_17.setBounds(103, 6, 0, 0);
		panel_21.add(label_17);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(FrmMain.class.getResource("/images/rahoveci_large.png")));
		label_1.setBounds(28, 16, 150, 233);
		panel_21.add(label_1);
		
		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_22.setBackground(new Color(0, 102, 153));
		panel_22.setBounds(255, 47, 278, 272);
		panelRahoveci.add(panel_22);
		
		JLabel lblRahoveci_1 = new JLabel("Rahoveci");
		lblRahoveci_1.setForeground(Color.WHITE);
		lblRahoveci_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRahoveci_1.setBounds(32, 46, 142, 21);
		panel_22.add(lblRahoveci_1);
		
		JLabel lblEmailKbrahovecigmailcom = new JLabel("Email: kb.rahoveci@gmail.com");
		lblEmailKbrahovecigmailcom.setForeground(Color.WHITE);
		lblEmailKbrahovecigmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKbrahovecigmailcom.setBounds(32, 78, 216, 21);
		panel_22.add(lblEmailKbrahovecigmailcom);
		
		JLabel lblTel_1 = new JLabel("Tel: 045 208 091");
		lblTel_1.setForeground(Color.WHITE);
		lblTel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTel_1.setBounds(32, 110, 142, 21);
		panel_22.add(lblTel_1);
		
		JLabel lblPersoniKontaktuesAlban = new JLabel("Personi Kontaktues: Alban Rama");
		lblPersoniKontaktuesAlban.setForeground(Color.WHITE);
		lblPersoniKontaktuesAlban.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesAlban.setBounds(32, 141, 240, 21);
		panel_22.add(lblPersoniKontaktuesAlban);
		
		JLabel label_32 = new JLabel("Ngjyra e pare:");
		label_32.setForeground(Color.WHITE);
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_32.setBounds(32, 178, 91, 21);
		panel_22.add(label_32);
		
		JLabel label_33 = new JLabel("Ngjyra e dyte:");
		label_33.setForeground(Color.WHITE);
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_33.setBounds(32, 210, 91, 21);
		panel_22.add(label_33);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_23.setBackground(new Color(0, 153, 255));
		panel_23.setBounds(120, 179, 20, 20);
		panel_22.add(panel_23);
		
		JPanel panel_24 = new JPanel();
		panel_24.setBorder(null);
		panel_24.setBackground(new Color(255, 102, 0));
		panel_24.setBounds(120, 211, 20, 20);
		panel_22.add(panel_24);
		
		JLabel label_34 = new JLabel("Ndeshjet - 2018");
		label_34.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_34.setBounds(71, 331, 137, 19);
		panelRahoveci.add(label_34);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(72, 365, 595, 120);
		panelRahoveci.add(scrollPane_5);
		
		tblRahoveci = new JTable();
		scrollPane_5.setViewportView(tblRahoveci);
		
		JLabel label_35 = new JLabel("Pershkrimi");
		label_35.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_35.setBounds(564, 47, 219, 30);
		panelRahoveci.add(label_35);
		
		JLabel lblTrajneriMarkRodiqi = new JLabel("Trajneri: Mark Rodiqi");
		lblTrajneriMarkRodiqi.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriMarkRodiqi.setBounds(564, 85, 168, 19);
		panelRahoveci.add(lblTrajneriMarkRodiqi);
		panelRahoveci.setVisible(false);
		
		JPanel panelPrishtina = new JPanel();
		panelPrishtina.setBackground(new Color(153, 204, 204));
		panelPrishtina.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelPrishtina);
		panelPrishtina.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_3.setBounds(72, 47, 207, 272);
		panelPrishtina.add(panel_3);
		
		JLabel label_2 = new JLabel("");
		panel_3.add(label_2);
		label_2.setIcon(new ImageIcon(FrmMain.class.getResource("/images/PRISHTINAAA.png")));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(0, 102, 153));
		panel_4.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_4.setBounds(255, 47, 278, 272);
		panelPrishtina.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblSigalPrishtina = new JLabel("Sigal Prishtina");
		lblSigalPrishtina.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSigalPrishtina.setForeground(new Color(255, 255, 255));
		lblSigalPrishtina.setBounds(32, 46, 142, 21);
		panel_4.add(lblSigalPrishtina);
		
		JLabel lblEmailKbsigalprishtinagmailcom = new JLabel("Email: kb.sigalprishtina@gmail.com");
		lblEmailKbsigalprishtinagmailcom.setForeground(Color.WHITE);
		lblEmailKbsigalprishtinagmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKbsigalprishtinagmailcom.setBounds(32, 78, 216, 21);
		panel_4.add(lblEmailKbsigalprishtinagmailcom);
		
		JLabel lblTel = new JLabel("Tel: 049 149 222");
		lblTel.setForeground(Color.WHITE);
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTel.setBounds(32, 110, 142, 21);
		panel_4.add(lblTel);
		
		JLabel lblPersoniKontaktuesAgon = new JLabel("Personi Kontaktues: Agon Abazi");
		lblPersoniKontaktuesAgon.setForeground(Color.WHITE);
		lblPersoniKontaktuesAgon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesAgon.setBounds(32, 141, 240, 21);
		panel_4.add(lblPersoniKontaktuesAgon);
		
		JLabel lblNgjyraEPare = new JLabel("Ngjyra e pare:");
		lblNgjyraEPare.setForeground(Color.WHITE);
		lblNgjyraEPare.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgjyraEPare.setBounds(32, 178, 91, 21);
		panel_4.add(lblNgjyraEPare);
		
		JLabel lblNgjyraEDyte = new JLabel("Ngjyra e dyte:");
		lblNgjyraEDyte.setForeground(Color.WHITE);
		lblNgjyraEDyte.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNgjyraEDyte.setBounds(32, 210, 91, 21);
		panel_4.add(lblNgjyraEDyte);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(120, 179, 20, 20);
		panel_4.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 0, 255));
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(120, 211, 20, 20);
		panel_4.add(panel_6);
		
		JLabel lblSigalPrishtina_1 = new JLabel("SIGAL PRISHTINA");
		lblSigalPrishtina_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblSigalPrishtina_1.setBounds(72, 11, 219, 30);
		panelPrishtina.add(lblSigalPrishtina_1);
		
		JLabel lblP = new JLabel("Pershkrimi");
		lblP.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblP.setBounds(564, 47, 219, 30);
		panelPrishtina.add(lblP);
		
		JLabel lblTrajneriAndinRashica = new JLabel("Trajneri: Andin Rashica");
		lblTrajneriAndinRashica.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriAndinRashica.setBounds(564, 89, 168, 19);
		panelPrishtina.add(lblTrajneriAndinRashica);
		
		JLabel lblNdeshjet = new JLabel("Ndeshjet - 2018");
		lblNdeshjet.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblNdeshjet.setBounds(72, 332, 137, 19);
		panelPrishtina.add(lblNdeshjet);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(72, 365, 595, 120);
		panelPrishtina.add(scrollPane_11);
		
		tblPrishtina = new JTable();
		scrollPane_11.setViewportView(tblPrishtina);
		
		JLabel label_3 = new JLabel("");
		label_3.setBackground(new Color(102, 153, 153));
		label_3.setBounds(0, 0, 823, 506);
		panelPrishtina.add(label_3);
		
		panelPrishtina.setVisible(false);
		
		JPanel panelBashkimi = new JPanel();
		panelBashkimi.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelBashkimi);
		panelBashkimi.setLayout(null);
		
		JLabel lblBashkimi = new JLabel("BASHKIMI");
		lblBashkimi.setToolTipText("");
		lblBashkimi.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblBashkimi.setBounds(73, 8, 219, 30);
		panelBashkimi.add(lblBashkimi);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_17.setBounds(72, 47, 207, 272);
		panelBashkimi.add(panel_17);
		panel_17.setLayout(null);
		
		JLabel label_9 = new JLabel("");
		label_9.setBounds(103, 6, 0, 0);
		panel_17.add(label_9);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon(FrmMain.class.getResource("/images/bashkimi - Copy.png")));
		label_7.setBounds(25, 32, 159, 218);
		panel_17.add(label_7);
		
		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_18.setBackground(new Color(0, 102, 153));
		panel_18.setBounds(255, 47, 278, 272);
		panelBashkimi.add(panel_18);
		
		JLabel lblBashkimi_1 = new JLabel("Bashkimi");
		lblBashkimi_1.setForeground(Color.WHITE);
		lblBashkimi_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblBashkimi_1.setBounds(32, 46, 142, 21);
		panel_18.add(lblBashkimi_1);
		
		JLabel lblEmailGazigmailcom = new JLabel("Email: arlindi.k@gmail.com");
		lblEmailGazigmailcom.setForeground(Color.WHITE);
		lblEmailGazigmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailGazigmailcom.setBounds(32, 78, 216, 21);
		panel_18.add(lblEmailGazigmailcom);
		
		JLabel label_22 = new JLabel("Tel: 049 149 222");
		label_22.setForeground(Color.WHITE);
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_22.setBounds(32, 110, 142, 21);
		panel_18.add(label_22);
		
		JLabel lblPersoniKontaktuesArlind = new JLabel("Personi Kontaktues: Arlind Kacaniku");
		lblPersoniKontaktuesArlind.setForeground(Color.WHITE);
		lblPersoniKontaktuesArlind.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesArlind.setBounds(32, 141, 240, 21);
		panel_18.add(lblPersoniKontaktuesArlind);
		
		JLabel label_25 = new JLabel("Ngjyra e pare:");
		label_25.setForeground(Color.WHITE);
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_25.setBounds(32, 178, 91, 21);
		panel_18.add(label_25);
		
		JLabel label_26 = new JLabel("Ngjyra e dyte:");
		label_26.setForeground(Color.WHITE);
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_26.setBounds(32, 210, 91, 21);
		panel_18.add(label_26);
		
		JPanel panel_19 = new JPanel();
		panel_19.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_19.setBackground(new Color(255, 102, 0));
		panel_19.setBounds(120, 179, 20, 20);
		panel_18.add(panel_19);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBorder(null);
		panel_20.setBackground(new Color(255, 255, 255));
		panel_20.setBounds(120, 211, 20, 20);
		panel_18.add(panel_20);
		
		JLabel label_27 = new JLabel("Pershkrimi");
		label_27.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_27.setBounds(564, 47, 219, 30);
		panelBashkimi.add(label_27);
		
		JLabel lblTrajneriTakianosFotis = new JLabel("Trajneri: Takianos Fotis");
		lblTrajneriTakianosFotis.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriTakianosFotis.setBounds(564, 85, 168, 19);
		panelBashkimi.add(lblTrajneriTakianosFotis);
		
		JLabel lblTrajneriAsistuesIlmen = new JLabel("Trajneri Asistues: Ilmen Bajra");
		lblTrajneriAsistuesIlmen.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriAsistuesIlmen.setBounds(565, 103, 168, 19);
		panelBashkimi.add(lblTrajneriAsistuesIlmen);
		
		JLabel label_30 = new JLabel("Ndeshjet - 2018");
		label_30.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_30.setBounds(71, 331, 137, 19);
		panelBashkimi.add(label_30);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(72, 365, 595, 120);
		panelBashkimi.add(scrollPane_4);
		
		tblBashkimi = new JTable();
		scrollPane_4.setViewportView(tblBashkimi);
		panelBashkimi.setVisible(false);
		
		JPanel panelPeja = new JPanel();
		panelPeja.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelPeja);
		panelPeja.setLayout(null);
		
		JLabel lblPeja = new JLabel("PEJA");
		lblPeja.setToolTipText("");
		lblPeja.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblPeja.setBounds(73, 8, 219, 30);
		panelPeja.add(lblPeja);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_13.setBounds(72, 47, 207, 272);
		panelPeja.add(panel_13);
		panel_13.setLayout(null);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(103, 6, 0, 0);
		panel_13.add(label_6);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(FrmMain.class.getResource("/images/Peja.png")));
		label_5.setBounds(6, 20, 205, 235);
		panel_13.add(label_5);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_14.setBackground(new Color(0, 102, 153));
		panel_14.setBounds(255, 47, 278, 272);
		panelPeja.add(panel_14);
		
		JLabel lblPeja_1 = new JLabel("Peja");
		lblPeja_1.setForeground(Color.WHITE);
		lblPeja_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPeja_1.setBounds(32, 46, 142, 21);
		panel_14.add(lblPeja_1);
		
		JLabel lblEmailGazipejagmailcom = new JLabel("Email: gazi_peja1@gmail.com");
		lblEmailGazipejagmailcom.setForeground(Color.WHITE);
		lblEmailGazipejagmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailGazipejagmailcom.setBounds(32, 78, 216, 21);
		panel_14.add(lblEmailGazipejagmailcom);
		
		JLabel label_16 = new JLabel("Tel: 049 149 222");
		label_16.setForeground(Color.WHITE);
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_16.setBounds(32, 110, 142, 21);
		panel_14.add(label_16);
		
		JLabel lblPersoniKontaktuesGazmend = new JLabel("Personi Kontaktues: Gazmend Popovci");
		lblPersoniKontaktuesGazmend.setForeground(Color.WHITE);
		lblPersoniKontaktuesGazmend.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesGazmend.setBounds(32, 141, 240, 21);
		panel_14.add(lblPersoniKontaktuesGazmend);
		
		JLabel label_18 = new JLabel("Ngjyra e pare:");
		label_18.setForeground(Color.WHITE);
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_18.setBounds(32, 178, 91, 21);
		panel_14.add(label_18);
		
		JLabel label_19 = new JLabel("Ngjyra e dyte:");
		label_19.setForeground(Color.WHITE);
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_19.setBounds(32, 210, 91, 21);
		panel_14.add(label_19);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_15.setBackground(new Color(255, 204, 0));
		panel_15.setBounds(120, 179, 20, 20);
		panel_14.add(panel_15);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBorder(null);
		panel_16.setBackground(new Color(0, 0, 0));
		panel_16.setBounds(120, 211, 20, 20);
		panel_14.add(panel_16);
		
		JLabel label_20 = new JLabel("Pershkrimi");
		label_20.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_20.setBounds(564, 47, 219, 30);
		panelPeja.add(label_20);
		
		JLabel lblTrajneriBujarLoci = new JLabel("Trajneri: Bujar Loci");
		lblTrajneriBujarLoci.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriBujarLoci.setBounds(564, 85, 168, 19);
		panelPeja.add(lblTrajneriBujarLoci);
		
		JLabel lblTrajneriAsistuesPetrit = new JLabel("Trajneri Asistues: Petrit Zeqiri");
		lblTrajneriAsistuesPetrit.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriAsistuesPetrit.setBounds(564, 105, 168, 19);
		panelPeja.add(lblTrajneriAsistuesPetrit);
		
		JLabel label_23 = new JLabel("Ndeshjet - 2018");
		label_23.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_23.setBounds(71, 331, 137, 19);
		panelPeja.add(label_23);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(72, 365, 595, 120);
		panelPeja.add(scrollPane_3);
		
		tblPeja = new JTable();
		scrollPane_3.setViewportView(tblPeja);
		panelPeja.setVisible(false);
		
		JPanel panelTrepca = new JPanel();
		panelTrepca.setBounds(261, 0, 823, 512);
		teamsPanel.add(panelTrepca);
		panelTrepca.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_7.setBounds(72, 47, 207, 272);
		panelTrepca.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(103, 6, 0, 0);
		panel_7.add(label_4);
		
		JLabel label_15 = new JLabel("");
		label_15.setIcon(new ImageIcon(FrmMain.class.getResource("/images/TREPCAAAA.png")));
		label_15.setBounds(0, 18, 206, 230);
		panel_7.add(label_15);
		
		JLabel lblTrepca = new JLabel("TREP\u00C7A");
		lblTrepca.setToolTipText("");
		lblTrepca.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblTrepca.setBounds(73, 8, 219, 30);
		panelTrepca.add(lblTrepca);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 102, 153), 1, true));
		panel_8.setBackground(new Color(0, 102, 153));
		panel_8.setBounds(255, 47, 278, 272);
		panelTrepca.add(panel_8);
		
		JLabel lblTrepa = new JLabel("Trep\u00E7a");
		lblTrepa.setForeground(Color.WHITE);
		lblTrepa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTrepa.setBounds(32, 46, 142, 21);
		panel_8.add(lblTrepa);
		
		JLabel lblEmailKbtrepcagmailcom = new JLabel("Email: kb.trepca@gmail.com");
		lblEmailKbtrepcagmailcom.setForeground(Color.WHITE);
		lblEmailKbtrepcagmailcom.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmailKbtrepcagmailcom.setBounds(32, 78, 216, 21);
		panel_8.add(lblEmailKbtrepcagmailcom);
		
		JLabel label_8 = new JLabel("Tel: 049 149 222");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_8.setBounds(32, 110, 142, 21);
		panel_8.add(label_8);
		
		JLabel lblPersoniKontaktuesNaim = new JLabel("Personi Kontaktues: Naim Hajrizi");
		lblPersoniKontaktuesNaim.setForeground(Color.WHITE);
		lblPersoniKontaktuesNaim.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPersoniKontaktuesNaim.setBounds(32, 141, 240, 21);
		panel_8.add(lblPersoniKontaktuesNaim);
		
		JLabel label_10 = new JLabel("Ngjyra e pare:");
		label_10.setForeground(Color.WHITE);
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_10.setBounds(32, 178, 91, 21);
		panel_8.add(label_10);
		
		JLabel label_11 = new JLabel("Ngjyra e dyte:");
		label_11.setForeground(Color.WHITE);
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_11.setBounds(32, 210, 91, 21);
		panel_8.add(label_11);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(0, 153, 51));
		panel_9.setBorder(null);
		panel_9.setBounds(120, 179, 10, 20);
		panel_8.add(panel_9);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBorder(null);
		panel_10.setBackground(new Color(0, 153, 51));
		panel_10.setBounds(120, 211, 10, 20);
		panel_8.add(panel_10);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(0, 0, 0));
		panel_11.setBorder(null);
		panel_11.setBounds(130, 179, 10, 20);
		panel_8.add(panel_11);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(null);
		panel_12.setBounds(130, 211, 10, 20);
		panel_8.add(panel_12);
		
		JLabel label_12 = new JLabel("Pershkrimi");
		label_12.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_12.setBounds(564, 47, 219, 30);
		panelTrepca.add(label_12);
		
		JLabel lblTrajneriGazmentAsllani = new JLabel("Trajneri: Gazment Asllani");
		lblTrajneriGazmentAsllani.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriGazmentAsllani.setBounds(564, 85, 168, 19);
		panelTrepca.add(lblTrajneriGazmentAsllani);
		
		JLabel label_14 = new JLabel("Ndeshjet - 2018");
		label_14.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_14.setBounds(71, 331, 137, 19);
		panelTrepca.add(label_14);
		
		JLabel lblTrajneriAsistuesIlir = new JLabel("Trajneri Asistues: Ilir Selmani");
		lblTrajneriAsistuesIlir.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTrajneriAsistuesIlir.setBounds(564, 105, 168, 19);
		panelTrepca.add(lblTrajneriAsistuesIlir);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(72, 365, 595, 120);
		panelTrepca.add(scrollPane_2);
		
		tblTrepca = new JTable();
		scrollPane_2.setViewportView(tblTrepca);
		panelTrepca.setVisible(false);
		
		JPanel panelTeams = new JPanel();
		panelTeams.setBackground(Color.BLACK);
		panelTeams.setBounds(0, 0, 263, 512);
		teamsPanel.add(panelTeams);
		panelTeams.setLayout(null);
		
		JButton btnTeamPrishtina = new JButton("Sigal Prishtina");
		btnTeamPrishtina.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamPrishtina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelPrishtina.setVisible(true);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				updateTblTeams(tblPrishtina,"Prishtina");
			}
		});
		btnTeamPrishtina.setIcon(new ImageIcon(FrmMain.class.getResource("/images/prishtina_logo.png")));
		btnTeamPrishtina.setForeground(Color.WHITE);
		btnTeamPrishtina.setBackground(Color.BLACK);
		btnTeamPrishtina.setBounds(0, 0, 261, 44);
		panelTeams.add(btnTeamPrishtina);
		
		JButton btnTeamTrepca = new JButton("Trepca");
		btnTeamTrepca.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamTrepca.setIcon(new ImageIcon(FrmMain.class.getResource("/images/Trepca.png")));
		btnTeamTrepca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(true);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				updateTblTeams(tblTrepca,"Trepca");
				
			}
		});
		btnTeamTrepca.setForeground(Color.WHITE);
		btnTeamTrepca.setBackground(Color.BLACK);
		btnTeamTrepca.setBounds(0, 44, 261, 44);
		panelTeams.add(btnTeamTrepca);
		
		JButton btnTeamPeja = new JButton("Peja");
		btnTeamPeja.setSelectedIcon(new ImageIcon(FrmMain.class.getResource("/images/peja_vogel.png")));
		btnTeamPeja.setIcon(new ImageIcon(FrmMain.class.getResource("/images/peja_vogel.png")));
		btnTeamPeja.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamPeja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(true);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				updateTblTeams(tblPeja,"Peja");
			}
		});
		btnTeamPeja.setForeground(Color.WHITE);
		btnTeamPeja.setBackground(Color.BLACK);
		btnTeamPeja.setBounds(0, 88, 261, 44);
		panelTeams.add(btnTeamPeja);
		
		JButton btnTeamBashkimi = new JButton("Bashkimi");
		btnTeamBashkimi.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamBashkimi.setIcon(new ImageIcon(FrmMain.class.getResource("/images/bashkimi.png")));
		btnTeamBashkimi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(true);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				updateTblTeams(tblBashkimi,"Bashkimi");
			}
		});
		btnTeamBashkimi.setForeground(Color.WHITE);
		btnTeamBashkimi.setBackground(Color.BLACK);
		btnTeamBashkimi.setBounds(0, 132, 261, 44);
		panelTeams.add(btnTeamBashkimi);
		
		JButton btnTeamRahoveci = new JButton("Rahoveci");
		btnTeamRahoveci.setIcon(new ImageIcon(FrmMain.class.getResource("/images/rahoveci_small.png")));
		btnTeamRahoveci.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamRahoveci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(true);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				updateTblTeams(tblRahoveci,"Rahoveci");
			}
		});
		btnTeamRahoveci.setForeground(Color.WHITE);
		btnTeamRahoveci.setBackground(Color.BLACK);
		btnTeamRahoveci.setBounds(0, 176, 261, 44);
		panelTeams.add(btnTeamRahoveci);
		
		JButton btnTeamGEYlli = new JButton("Golden Eagle Ylli");
		btnTeamGEYlli.setIcon(new ImageIcon(FrmMain.class.getResource("/images/gey_small.png")));
		btnTeamGEYlli.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamGEYlli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(true);
				panelKPrishtina.setVisible(false);
				updateTblTeams(tblGolden,"Golden Eagle Ylli");
			}
		});
		btnTeamGEYlli.setForeground(Color.WHITE);
		btnTeamGEYlli.setBackground(Color.BLACK);
		btnTeamGEYlli.setBounds(0, 264, 261, 44);
		panelTeams.add(btnTeamGEYlli);
		
		JButton btnTeamKPrishtina = new JButton("Kerasan Prishtina");
		btnTeamKPrishtina.setIcon(new ImageIcon(FrmMain.class.getResource("/images/kerprishtina_small.png")));
		btnTeamKPrishtina.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamKPrishtina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(true);
				updateTblTeams(tblKerasan,"Kerasan Prishtina");
			}
		});
		btnTeamKPrishtina.setForeground(Color.WHITE);
		btnTeamKPrishtina.setBackground(Color.BLACK);
		btnTeamKPrishtina.setBounds(0, 308, 261, 44);
		panelTeams.add(btnTeamKPrishtina);
		
		JButton btnTeamBorea = new JButton("Borea");
		btnTeamBorea.setIcon(new ImageIcon(FrmMain.class.getResource("/images/borea_small.png")));
		btnTeamBorea.setHorizontalAlignment(SwingConstants.LEFT);
		btnTeamBorea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(true);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				updateTblTeams(tblBorea,"Borea");
			}
		});
		btnTeamBorea.setForeground(Color.WHITE);
		btnTeamBorea.setBackground(Color.BLACK);
		btnTeamBorea.setBounds(0, 220, 261, 44);
		panelTeams.add(btnTeamBorea);
		tabbedPane.setForegroundAt(3, Color.WHITE);
		tabbedPane.setBackgroundAt(3, Color.BLACK);
		
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
