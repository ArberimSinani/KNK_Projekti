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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.InputEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
		FrmHelpAl helpFrameAl = new FrmHelpAl();
		FrmHelpEn helpFrameEn = new FrmHelpEn();
		
		private JTable tblStandings;
		private JTextField txtTeam;
		private JTextField txtGamesplayed;
		private JTextField txtWins;
		private JTextField txtLosses;
		private JTextField txtScored;
		private JTextField txtTaken;
		private JTextField txtPoints;
		
		String team ;
		Date gDate;
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
		private JTable tblUpcoming;
		//#####################################################################
		//###############   Variablat globale  ################################
		//#####################################################################
		
		JLabel lblHomeTeam = new JLabel("Home Team");
		JLabel lblSearchForUpcoming = new JLabel("Search for upcoming games:");
		JLabel lblUpcomingGames = new JLabel("UPCOMING GAMES!");
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		JLabel lblAwayTeam = new JLabel("Away Team");
		JLabel lblHomeTeamScores = new JLabel("Home Team Scores");
		JLabel lblAwayTeamScores = new JLabel("Away Team Scores");
		JLabel lblTime = new JLabel("Time");
		JLabel lblDate = new JLabel("Date");
		JLabel lblResults_1 = new JLabel("Results");
		JLabel lblResults = new JLabel("Insert results");
		JMenuItem mntmDelete = new JMenuItem("Delete");
		JLabel lblStandingsTable = new JLabel("Standings ");
		JLabel lblTeam = new JLabel("Team");
		JLabel lblGamesPlayed = new JLabel("Games played");
		JLabel lblWins = new JLabel("Wins");
		JLabel lblLosses = new JLabel("Losses");
		JLabel lblScored = new JLabel("Scored");
		JLabel lblTaken = new JLabel("Taken");
		JLabel lblPoints = new JLabel("Points");
		JButton btnAddStandings = new JButton("Add");
		JButton btnEditStandings = new JButton("Edit");
		JButton btnDeleteStandings = new JButton("Delete");
		JButton btnResetStandings = new JButton("Reset");
		JLabel lblTeamsOfSuperleague = new JLabel("TEAMS OF SUPERLEAGUE");
		JLabel lblFirstColor1 = new JLabel("First color:");
		JLabel lblSecondColor1 = new JLabel("Second color:");
		JLabel lblContact1 = new JLabel("Contact:");
		JLabel lblDescription1 = new JLabel("Description");
		JLabel lblCoach1 = new JLabel("Coach: ");
		JLabel lblMatches1 = new JLabel("Matches ");
		JLabel lblFirstColor2 = new JLabel("First color:");
		JLabel lblSecondColor2 = new JLabel("Second color:");
		JLabel lblContact2 = new JLabel("Contact:");
		JLabel lblDescription2 = new JLabel("Description");
		JLabel lblCoach2 = new JLabel("Coach: ");
		JLabel lblMatches2 = new JLabel("Matches ");
		JLabel lblFirstColor3 = new JLabel("First color:");
		JLabel lblSecondColor3 = new JLabel("Second color:");
		JLabel lblContact3 = new JLabel("Contact:");
		JLabel lblDescription3 = new JLabel("Description");
		JLabel lblCoach3 = new JLabel("Coach: ");
		JLabel lblAssCoach3 = new JLabel("Assistant Coach: ");
		JLabel lblMatches3 = new JLabel("Matches ");
		JLabel lblFirstColor4 = new JLabel("First color:");
		JLabel lblSecondColor4 = new JLabel("Second color:");
		JLabel lblContact4 = new JLabel("Contact:");
		JLabel lblDescription4 = new JLabel("Description");
		JLabel lblCoach4 = new JLabel("Coach: ");
		JLabel lblMatches4 = new JLabel("Matches ");
		JLabel lblFirstColor5 = new JLabel("First color:");
		JLabel lblSecondColor5 = new JLabel("Second color:");
		JLabel lblContact5 = new JLabel("Contact:");
		JLabel lblDescription5 = new JLabel("Description");
		JLabel lblCoach5 = new JLabel("Coach: ");
		JLabel lblAssCoach5 = new JLabel("Assistant Coach: ");
		JLabel lblMatches5 = new JLabel("Matches ");
		JLabel lblFirstColor6 = new JLabel("First color:");
		JLabel lblSecondColor6 = new JLabel("Second color:");
		JLabel lblContact6 = new JLabel("Contact:");
		JLabel lblDescription6 = new JLabel("Description");
		JLabel lblCoach6 = new JLabel("Coach: ");
		JLabel lblAssCoach6 = new JLabel("Assistant Coach: ");
		JLabel lblMatches6 = new JLabel("Matches ");
		JLabel lblContact7 = new JLabel("Contact:");
		JLabel lblFirstColor7 = new JLabel("First color:");
		JLabel lblSecondColor7 = new JLabel("Second color:");
		JLabel lblDescription7 = new JLabel("Description");
		JLabel lblCoach7 = new JLabel("Coach: ");
		JLabel lblAssCoach7 = new JLabel("Assistant Coach: ");
		JLabel lblMatches7 = new JLabel("Matches ");
		JLabel lblContact = new JLabel("Contact:");
		JLabel lblFirstColor = new JLabel("First color:");
		JLabel lblSecondColor = new JLabel("Second color:");
		JLabel lblDescription = new JLabel("Description");
		JLabel lblCoach = new JLabel("Coach: ");
		JLabel lblMatches = new JLabel("Matches ");
		JButton btnAddResults = new JButton("ADD");
		JButton btnDeleteResults = new JButton("Delete");
		JButton btnEditResults = new JButton("Edit");
		JButton btnResetResults = new JButton("Reset");
		
		String language;
		
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
					FrmMain frame = new FrmMain(null);
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
		//Funksioni per update te tabeles tblUpcoming............
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
		//#####################################################################
		//###############   Funksioni per update te tabelave te ekipeve  ################################
		//#####################################################################
	public void updateTblTeams(JTable table, String teamName)
	{
		try 
		{
			String sql="select ht_name as 'HT', at_name as 'AT',ht_score as 'HT Score', at_score as 'AT Score'," 
									+"game_time as 'Time',game_date as 'Date' from tblResults where ht_name ='"+teamName+"' or at_name ='"+teamName+"' order by game_date desc;";
			pst=conn.prepareStatement(sql);
			res=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(res));
			pst.close();
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Something went wrong while updating table."+e.getMessage());
		}
	}
	//#####################################################################
	//###############   Funksioni per update te tblResults  ################################
	//#####################################################################
	public void updateTblResults()
	{
		try 
		{
			String sql="select ht_name as 'HT', at_name as 'AT',ht_score as 'HT Score', at_score as 'AT Score'," 
									+"game_time as 'Time',game_date as 'Date' from tblResults order by game_date desc;";
			pst=conn.prepareStatement(sql);
			res=pst.executeQuery();
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

	
	public void updateTblStandings()
	{
		try 
		{
			String sql="select team as 'Team', games as 'GP',wins as 'W', losses as 'L',"
						+"scored as 'Scored',taken as 'Taken',points as 'Points' from tblStandings order by points desc;";
			pst=conn.prepareStatement(sql);
			res=pst.executeQuery();
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
	 * @param lang 
	 */
	public FrmMain(String lang) {// Konstruktori me parameter i cili merr parametrin nga FrmLogin
		setTitle("Basketball");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMain.class.getResource("/images/icon.png")));
		
		conn=SQLConn.connectDB();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		language=lang;
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		//Using exit from menu bar to exit the frame
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnFile.add(mntmExit);
		
		JMenu mnLang = new JMenu("Lang");
		menuBar.add(mnLang);

		//#################################################################################################################################################
		//###############  Ndryshimi i gjuhes nga JMenu ##########################################################################################
		//#################################################################################################################################################
		JMenuItem mntmEng = new JMenuItem("Eng");
		mntmEng.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mntmEng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				language = "EN";
				setLanguage(language);
				
			}
		});
		mnLang.add(mntmEng);
		
		JMenuItem mntmAlb = new JMenuItem("Alb");
		mntmAlb.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnLang.add(mntmAlb);
		
		mntmAlb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				language = "AL";
				setLanguage(language);
				
			}
		});
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(language == "AL") {
					helpFrameAl.setVisible(true);
					helpFrameAl.setLocationRelativeTo(null);	
				}
				else {
					helpFrameEn.setVisible(true);
					helpFrameEn.setLocationRelativeTo(null);
				}
			}
		});
		mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnHelp.add(mntmAbout);
		
		JMenu mnUser = new JMenu("User");
		menuBar.add(mnUser);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FrmLogin frmLogin = new FrmLogin(language);
				frmLogin.setVisible(true);
				frmLogin.setLocationRelativeTo(null);
			}
		});
		mntmLogOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		mnUser.add(mntmLogOut);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		tabbedPane.setBounds(0, 0, 1084, 540);
		contentPane.add(tabbedPane);
		
		JPanel homePanel = new JPanel();
		tabbedPane.addTab("Home", null, homePanel, null);
		tabbedPane.setForegroundAt(0, Color.WHITE);
		tabbedPane.setBackgroundAt(0, Color.BLACK);
		homePanel.setBackground(new Color(255, 255, 255));
		homePanel.setLayout(null);
		
		
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(72, 259, 696, 175);
				homePanel.add(scrollPane_1);
				
				tblUpcoming = new JTable();
				scrollPane_1.setViewportView(tblUpcoming);

				//#################################################################################################################################################
				//###############  Search field ##################################################################################################################
				//#################################################################################################################################################
				txtSearch = new JTextField();
				txtSearch.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						try {
							String sql = "SELECT ht_name as HT,at_name as AT,game_time as Time,game_date as Date from tblresults where ht_score is null and (ht_name like '"+txtSearch.getText()+"%' or at_name like "
									+ "'"+txtSearch.getText()+"%') order by game_date desc;";
							pst=conn.prepareStatement(sql);
							res=pst.executeQuery();
							tblUpcoming.setModel(DbUtils.resultSetToTableModel(res));
							pst.close();
						}
						catch(Exception e) {
							
							JOptionPane.showMessageDialog(null, "Something went wrong with the search : " + e.getMessage() );
							
						}
					}
					@Override
					public void keyTyped(KeyEvent e) {
						char vchar=e.getKeyChar();
						if(!(Character.isLetter(vchar)) && vchar != (char)8)
						{
							getToolkit().beep();
							e.consume();
						}
					}
				});
				txtSearch.setBounds(327, 207, 238, 38);
				homePanel.add(txtSearch);
				txtSearch.setColumns(10);
				//====================================================================================================================================================
				//label per search field
				//====================================================================================================================================================
				
				lblSearchForUpcoming.setBounds(73, 208, 253, 36);
				homePanel.add(lblSearchForUpcoming);
				lblSearchForUpcoming.setForeground(new Color(0, 0, 0));
				lblSearchForUpcoming.setFont(new Font("Tahoma", Font.PLAIN, 17));
				
				
				lblUpcomingGames.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 30));
				lblUpcomingGames.setHorizontalAlignment(SwingConstants.CENTER);
				lblUpcomingGames.setBounds(57, 51, 587, 88);
				homePanel.add(lblUpcomingGames);
				
				JLabel label_78 = new JLabel("");
				label_78.setIcon(new ImageIcon(FrmMain.class.getResource("/images/dunk2.png")));
				label_78.setBounds(684, 6, 342, 463);
				homePanel.add(label_78);
				
				JLabel label_39 = new JLabel("");
				label_39.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3home.jpg")));
				label_39.setBounds(0, 0, 1084, 510);
				homePanel.add(label_39);
				//===========================================================================================================================================
				

		
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBackground(new Color(51, 102, 102));
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.BLACK);
		resultsPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 470, 522);
		panel_1.setBackground(Color.BLACK);
		resultsPanel.add(panel_1);
		panel_1.setLayout(null);
		
		
		lblResults.setBounds(89, 12, 292, 28);
		lblResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults.setForeground(Color.BLACK);
		lblResults.setFont(new Font("Calibri", Font.PLAIN, 27));
		panel_1.add(lblResults);
		
		txtHteam = new JTextField();
		txtHteam.setBounds(28, 90, 183, 33);
		txtHteam.setMargin(new Insets(3,3,3,3));
		
		panel_1.add(txtHteam);
		txtHteam.setColumns(10);
		
		txtAteam = new JTextField();
		txtAteam.setBounds(258, 90, 183, 33);
		txtAteam.setMargin(new Insets(3,3,3,3)); 
		txtAteam.setColumns(10);
		panel_1.add(txtAteam);
		

		//#################################################################################################################################################
		//###############  TEKST KONTROLLAT   #############################################################################################################
		//#################################################################################################################################################
		txtHS = new JTextField();
		txtHS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtHS.setBounds(28, 196, 183, 33);
		txtHS.setMargin(new Insets(3,3,3,3)); 
		txtHS.setColumns(10);
		panel_1.add(txtHS);
		
		txtAS = new JTextField();
		txtAS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtAS.setBounds(258, 196, 183, 33);
		txtAS.setMargin(new Insets(3,3,3,3)); 
		txtAS.setColumns(10);
		panel_1.add(txtAS);
		
		txtTime = new JTextField();
		txtTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar)) && vchar != ':' && vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtTime.setBounds(28, 296, 183, 33);
		txtTime.setMargin(new Insets(3,3,3,3)); 
		txtTime.setColumns(10);
		panel_1.add(txtTime);
		
		txtDate = new JTextField();
		txtDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar)) && vchar != '-' && vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				btnAddResults.getRootPane().setDefaultButton(btnAddResults);
			}
		});
		txtDate.setHorizontalAlignment(SwingConstants.LEFT);
		txtDate.setToolTipText("Date format: yyyy-MM-dd");
		txtDate.setBounds(258, 297, 183, 33);
		txtDate.setMargin(new Insets(3,3,3,3)); 
		txtDate.setColumns(10);
		panel_1.add(txtDate);
		btnAddResults.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnAddResults.getRootPane().setDefaultButton(btnAddResults);
			}
		});

		//#################################################################################################################################################
		//###############  Add to tblResults  #############################################################################################################
		//#################################################################################################################################################		
		
		btnAddResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					//String sql = "Insert into tblResults (ht_name, at_name, ht_score, at_score,game_time, game_date) values ('"+txtHteam.getText()+"','"
					//			+txtAteam.getText()+"',"+txtHS.getText()+","+txtAS.getText()+",'"+txtTime.getText()+"','"+txtDate.getText()+"');";
					
					String sql;
					
					if(txtHS.getText().isEmpty() && txtAS.getText().isEmpty()) {
						sql = "Insert into tblResults (ht_name, at_name, game_time, game_date) values ('"+txtHteam.getText()+"','"+txtAteam.getText()+"','"
								+txtTime.getText()+"','"+txtDate.getText()+"');";
					}
					else {
						sql = "Insert into tblResults values (default,'"+txtHteam.getText()+"','"+txtAteam.getText()+"'"
							+ ","+txtHS.getText()+","+txtAS.getText()+",'"+txtTime.getText()+"','"+txtDate.getText()+"');";
					}
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTblResults();
					

					if(Integer.parseInt(txtHS.getText()) > Integer.parseInt(txtAS.getText())) {
						try {
							
							String sqlWin="UPDATE tblstandings SET games = games + 1, points = points + 2,wins = wins + 1, scored = scored + "+txtHS.getText()+","
									+ " taken = taken + "+txtAS.getText()+" WHERE Team = '"+txtHteam.getText()+"';";
							pst=conn.prepareStatement(sqlWin);
							pst.execute();
							
							String sqlLose="UPDATE tblstandings SET games = games + 1, losses = losses + 1, scored = scored + "+txtAS.getText()+","
									+ " taken = taken + "+txtHS.getText()+" WHERE Team = '"+txtAteam.getText()+"';";
							pst=conn.prepareStatement(sqlLose);
							pst.execute();
							
							
							updateTblStandings();
							pst.close();
							
							
						}
						catch(Exception e) {
							
						}
					}
					else if(Integer.parseInt(txtHS.getText()) < Integer.parseInt(txtAS.getText())) {
						try {
							
							String sqlWin="UPDATE tblstandings SET games = games + 1, points = points + 2, wins = wins + 1, scored = scored + "+txtAS.getText()+","
									+ " taken = taken + "+txtHS.getText()+" WHERE Team = '"+txtAteam.getText()+"';";
							pst=conn.prepareStatement(sqlWin);
							pst.execute();
							
							String sqlLose="UPDATE tblstandings SET games = games + 1, losses = losses + 1, scored = scored + "+txtHS.getText()+","
									+ " taken = taken + "+txtAS.getText()+" WHERE Team = '"+txtHteam.getText()+"';";
							pst=conn.prepareStatement(sqlLose);
							pst.execute();
							
							
							updateTblStandings();
							pst.close();
							
							
						}
						catch(Exception e) {
							
						}
					}
					
					
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
		btnAddResults.setBounds(43, 394, 89, 36);
		btnAddResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddResults.setBackground(Color.BLACK);
		btnAddResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnAddResults);
		

		//#################################################################################################################################################
		//###############  Delete to tblResults  #############################################################################################################
		//#################################################################################################################################################	
		
		btnDeleteResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String sql = "Delete from tblresults where ht_name='"+txtHteam.getText()+"' and game_date = '"+txtDate.getText()+"';";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					
					
					
					if(Integer.parseInt(txtHS.getText()) > Integer.parseInt(txtAS.getText())) {
						try {
							
							String sqlWin="UPDATE tblstandings SET games = games - 1, points = points - 2,wins = wins - 1, scored = scored - "+txtHS.getText()+","
									+ " taken = taken - "+txtAS.getText()+" WHERE Team = '"+txtHteam.getText()+"';";
							pst=conn.prepareStatement(sqlWin);
							pst.execute();
							
							String sqlLose="UPDATE tblstandings SET games = games - 1, losses = losses - 1, scored = scored - "+txtAS.getText()+","
									+ " taken = taken - "+txtHS.getText()+" WHERE Team = '"+txtAteam.getText()+"';";
							pst=conn.prepareStatement(sqlLose);
							pst.execute();
							
							
							updateTblStandings();
							pst.close();
							
							
						}
						catch(Exception e) {
							
						}
					}
					else if(Integer.parseInt(txtHS.getText()) < Integer.parseInt(txtAS.getText())) {
						try {
							
							String sqlWin="UPDATE tblstandings SET games = games - 1, points = points - 2,wins = wins - 1, scored = scored - "+txtAS.getText()+","
									+ " taken = taken - "+txtHS.getText()+" WHERE Team = '"+txtAteam.getText()+"';";
							pst=conn.prepareStatement(sqlWin);
							pst.execute();
							
							String sqlLose="UPDATE tblstandings SET games = games - 1, losses = losses - 1, scored = scored - "+txtHS.getText()+","
									+ " taken = taken - "+txtAS.getText()+" WHERE Team = '"+txtHteam.getText()+"';";
							pst=conn.prepareStatement(sqlLose);
							pst.execute();
							
							
							updateTblStandings();
							pst.close();
							
							
						}
						catch(Exception e) {
							
						}
					}
					
					
					
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
		btnDeleteResults.setBounds(142, 394, 89, 36);
		btnDeleteResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDeleteResults.setBackground(Color.BLACK);
		btnDeleteResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnDeleteResults);

		//#################################################################################################################################################
		//###############  Edit to tblResults  #############################################################################################################
		//#################################################################################################################################################	
		
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
		btnEditResults.setBounds(241, 394, 89, 36);
		btnEditResults.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnEditResults.setBackground(Color.BLACK);
		btnEditResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnEditResults);
		
		
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
		btnResetResults.setBounds(340, 394, 89, 36);
		btnResetResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResetResults.setBackground(Color.BLACK);
		btnResetResults.setForeground(Color.LIGHT_GRAY);
		panel_1.add(btnResetResults);
		
		 
		lblHomeTeam.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblHomeTeam.setBounds(28, 76, 183, 14);
		lblHomeTeam.setForeground(Color.BLACK);
		panel_1.add(lblHomeTeam);
		
		
		lblAwayTeam.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblAwayTeam.setBounds(259, 76, 182, 14);
		lblAwayTeam.setForeground(Color.BLACK);
		panel_1.add(lblAwayTeam);
		
		
		lblHomeTeamScores.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblHomeTeamScores.setBounds(28, 181, 183, 14);
		lblHomeTeamScores.setForeground(Color.BLACK);
		panel_1.add(lblHomeTeamScores);
		
		
		lblAwayTeamScores.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblAwayTeamScores.setBounds(258, 181, 178, 15);
		lblAwayTeamScores.setForeground(Color.BLACK);
		panel_1.add(lblAwayTeamScores);
		
		
		lblTime.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTime.setBounds(28, 282, 183, 14);
		lblTime.setForeground(Color.BLACK);
		panel_1.add(lblTime);
		
		
		lblDate.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblDate.setBounds(260, 282, 181, 14);
		lblDate.setForeground(Color.BLACK);
		panel_1.add(lblDate);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(FrmMain.class.getResource("/images/wpkatror.png")));
		lblNewLabel_1.setBounds(0, 0, 470, 522);
		panel_1.add(lblNewLabel_1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				updateTblResults();
			}
		});
		scrollPane2.setBorder(null);
		scrollPane2.setBackground(Color.BLACK);
		scrollPane2.setBounds(509, 105, 544, 153);
		resultsPanel.add(scrollPane2);
		
		tblResults = new JTable();

		//#################################################################################################################################################
		//###############  Mbushja e textFields duke klikuar ne te dhenat e tabeles tblResults  ###########################################################
		//#################################################################################################################################################	
		tblResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model=(DefaultTableModel)tblResults.getModel();
				team = (String)model.getValueAt(tblResults.getSelectedRow(),0);
				gDate = (Date)model.getValueAt(tblResults.getSelectedRow(),5);
				try 
				{
					String sql="select * from tblresults where ht_name='"+team+"' and game_date = '"+gDate.toString()+"';";
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
		panel_2.setBackground(new Color(51,102,102));
		panel_2.setBounds(471, 0, 613, 49);
		resultsPanel.add(panel_2);
		
		lblResults_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblResults_1.setForeground(Color.WHITE);
		lblResults_1.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblResults_1.setBounds(199, 12, 223, 28);
		panel_2.add(lblResults_1);
		
		JLabel label_10 = new JLabel("");
		label_10.setIcon(new ImageIcon(FrmMain.class.getResource("/images/abs.png")));
		label_10.setBounds(779, 288, 255, 216);
		resultsPanel.add(label_10);
		
		JLabel label_75 = new JLabel("");
		label_75.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_75.setBounds(471, 0, 623, 522);
		resultsPanel.add(label_75);
		
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

		//#################################################################################################################################################
		//###############  Mbushja e textFields duke kaluar ne te dhenat e tabeles tblStandings me tastiere ###############################################
		//#################################################################################################################################################
		tblStandings.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tblStandings.getRootPane();
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
				catch (Exception ex) 
				{
					JOptionPane.showMessageDialog(null, "Error! "+ex.getMessage());
				}
			}
			
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		tblStandings.setLocation(369, 0);
		
		
		
		//Refresh table when mouse pointer enters the table space.
		tblStandings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				updateTblStandings();
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
		

		//#################################################################################################################################################
		//###############  POPUP Delete ###################################################################################################################
		//#################################################################################################################################################
		
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql = "Delete from tblstandings where team='"+txtTeam.getText()+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTblStandings();
					
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
		titlePanel.setBounds(332, 0, 753, 49);
		standingsPanel.add(titlePanel);
		titlePanel.setLayout(null);
		titlePanel.setBackground(new Color(102, 0, 0));
		
		
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
		
		
		lblTeam.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTeam.setBounds(33, 38, 189, 20);
		panel.add(lblTeam);
		lblTeam.setForeground(Color.BLACK);
		
		txtTeam = new JTextField();
		txtTeam.setBackground(Color.WHITE);
		txtTeam.setBounds(34, 59, 188, 33);
		panel.add(txtTeam);
		txtTeam.setMargin(new Insets(3,3,3,3));
		txtTeam.setColumns(10);
		
		
		lblGamesPlayed.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblGamesPlayed.setBounds(33, 101, 188, 20);
		panel.add(lblGamesPlayed);
		lblGamesPlayed.setForeground(Color.BLACK);
		
		txtGamesplayed = new JTextField();
		txtGamesplayed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar=arg0.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					arg0.consume();
				}
			}
		});
		txtGamesplayed.setBackground(Color.WHITE);
		txtGamesplayed.setBounds(33, 122, 188, 33);
		panel.add(txtGamesplayed);
		txtGamesplayed.setMargin(new Insets(3,3,3,3));
		txtGamesplayed.setColumns(10);
		
		
		lblWins.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblWins.setBounds(35, 166, 188, 20);
		panel.add(lblWins);
		lblWins.setForeground(Color.BLACK);
		
		txtWins = new JTextField();
		txtWins.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtWins.setBackground(Color.WHITE);
		txtWins.setBounds(33, 187, 188, 33);
		panel.add(txtWins);
		txtWins.setMargin(new Insets(3,3,3,3));
		txtWins.setColumns(10);
		
		
		lblLosses.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblLosses.setBounds(34, 231, 188, 20);
		panel.add(lblLosses);
		lblLosses.setForeground(Color.BLACK);
		
		txtLosses = new JTextField();
		txtLosses.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtLosses.setBackground(Color.WHITE);
		txtLosses.setBounds(34, 252, 188, 33);
		panel.add(txtLosses);
		txtLosses.setMargin(new Insets(3,3,3,3));
		txtLosses.setColumns(10);
		
		
		lblScored.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblScored.setBounds(34, 296, 188, 20);
		panel.add(lblScored);
		lblScored.setForeground(Color.BLACK);
		
		txtScored = new JTextField();
		txtScored.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtScored.setBackground(Color.WHITE);
		txtScored.setBounds(34, 314, 188, 33);
		panel.add(txtScored);
		txtScored.setMargin(new Insets(3,3,3,3));
		txtScored.setColumns(10);
		
		
		lblTaken.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblTaken.setBounds(36, 358, 188, 20);
		panel.add(lblTaken);
		lblTaken.setForeground(Color.BLACK);
		
		txtTaken = new JTextField();
		txtTaken.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txtTaken.setBackground(Color.WHITE);
		txtTaken.setBounds(34, 376, 188, 33);
		panel.add(txtTaken);
		txtTaken.setMargin(new Insets(3,3,3,3));
		txtTaken.setColumns(10);
		
		
		lblPoints.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblPoints.setBounds(34, 420, 185, 20);
		panel.add(lblPoints);
		lblPoints.setForeground(Color.BLACK);
		
		txtPoints = new JTextField();
		txtPoints.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar=e.getKeyChar();
				if(!(Character.isDigit(vchar))&& vchar != (char) 8)
				{
					getToolkit().beep();
					e.consume();
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {
				btnAddStandings.getRootPane().setDefaultButton(btnAddStandings);
			}
		});
		txtPoints.setBackground(Color.WHITE);
		txtPoints.setBounds(34, 439, 188, 33);
		panel.add(txtPoints);
		txtPoints.setMargin(new Insets(3,3,3,3));
		txtPoints.setColumns(10);
		btnAddStandings.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				btnAddStandings.getRootPane().setDefaultButton(btnAddStandings);
			}
		});
		

		//#################################################################################################################################################
		//###############  Butoni add per tblStandings  ###################################################################################################
		//#################################################################################################################################################
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
				updateTblStandings();
				
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
		
		//#################################################################################################################################################
		//###############  Butoni edit per tblStandings  ###################################################################################################
		//#################################################################################################################################################
		
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
					updateTblStandings();
					
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Gabim gjate editimit "+e1.getMessage());
					
				}
				
			}
		});
		btnEditStandings.setForeground(Color.WHITE);
		btnEditStandings.setFont(new Font("Calibri", Font.PLAIN, 17));
		btnEditStandings.setBackground(Color.BLACK);
		
		//#################################################################################################################################################
		//###############  Butoni Delete per tblStandings  ###################################################################################################
		//#################################################################################################################################################
		
		btnDeleteStandings.setBounds(231, 376, 92, 33);
		panel.add(btnDeleteStandings);
		btnDeleteStandings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql = "Delete from tblstandings where team='"+txtTeam.getText()+"'";
					pst=conn.prepareStatement(sql);
					pst.execute();
					pst.close();
					updateTblStandings();
					
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
		//#################################################################################################################################################
		//###############  Butoni Reset per tblStandings  ###################################################################################################
		//#################################################################################################################################################
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
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FrmMain.class.getResource("/images/wpdrejt.png")));
		lblNewLabel_2.setBounds(0, 0, 332, 524);
		panel.add(lblNewLabel_2);
		
		JLabel label_11 = new JLabel("");
		label_11.setIcon(new ImageIcon(FrmMain.class.getResource("/images/abs.png")));
		label_11.setBounds(779, 288, 255, 216);
		standingsPanel.add(label_11);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label.setBounds(332, 0, 753, 524);
		standingsPanel.add(label);
		
		
		JPanel teamsPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamsPanel, null);
		teamsPanel.setLayout(null);
		
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
		
		lblContact7.setForeground(Color.WHITE);
		lblContact7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact7.setBounds(32, 141, 65, 21);
		panel_8.add(lblContact7);
		
		JLabel lblNaimHajrizi = new JLabel("Naim Hajrizi");
		lblNaimHajrizi.setForeground(Color.WHITE);
		lblNaimHajrizi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNaimHajrizi.setBounds(98, 141, 108, 21);
		panel_8.add(lblNaimHajrizi);
		
		
		lblFirstColor7.setForeground(Color.WHITE);
		lblFirstColor7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor7.setBounds(32, 178, 91, 21);
		panel_8.add(lblFirstColor7);
		
		
		lblSecondColor7.setForeground(Color.WHITE);
		lblSecondColor7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor7.setBounds(32, 210, 91, 21);
		panel_8.add(lblSecondColor7);
		
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
		
		
		lblDescription7.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblDescription7.setBounds(564, 47, 219, 30);
		panelTrepca.add(lblDescription7);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(72, 365, 595, 120);
		panelTrepca.add(scrollPane_2);
		
		tblTrepca = new JTable();
		scrollPane_2.setViewportView(tblTrepca);
		
		
		lblCoach7.setBounds(564, 89, 49, 19);
		panelTrepca.add(lblCoach7);
		lblCoach7.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		
		JLabel lblGazmentAsllani = new JLabel("Gazment Asllani");
		lblGazmentAsllani.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblGazmentAsllani.setBounds(612, 89, 114, 19);
		panelTrepca.add(lblGazmentAsllani);
		
		
		lblAssCoach7.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblAssCoach7.setBounds(564, 112, 94, 19);
		panelTrepca.add(lblAssCoach7);
		
		JLabel lblIlirSelmani = new JLabel("Ilir Selmani");
		lblIlirSelmani.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblIlirSelmani.setBounds(660, 112, 94, 19);
		panelTrepca.add(lblIlirSelmani);
		
		
		lblMatches7.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches7.setBounds(72, 331, 71, 19);
		panelTrepca.add(lblMatches7);
		
		JLabel label_59 = new JLabel("-2018");
		label_59.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_59.setBounds(146, 331, 71, 19);
		panelTrepca.add(label_59);
		
		JLabel label_55 = new JLabel("");
		label_55.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_55.setBackground(new Color(102, 153, 153));
		label_55.setBounds(0, 0, 823, 512);
		panelTrepca.add(label_55);
		panelTrepca.setVisible(false);
		
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
		
		
		lblContact.setForeground(Color.WHITE);
		lblContact.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact.setBounds(32, 141, 65, 21);
		panel_4.add(lblContact);
		
		
		lblFirstColor.setForeground(Color.WHITE);
		lblFirstColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor.setBounds(32, 178, 91, 21);
		panel_4.add(lblFirstColor);
		
		
		lblSecondColor.setForeground(Color.WHITE);
		lblSecondColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor.setBounds(32, 210, 91, 21);
		panel_4.add(lblSecondColor);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_5.setBounds(120, 179, 20, 20);
		panel_4.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(0, 0, 255));
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_6.setBounds(120, 211, 20, 20);
		panel_4.add(panel_6);
		
		JLabel lblAgonAbazi = new JLabel("Agon Abazi");
		lblAgonAbazi.setForeground(Color.WHITE);
		lblAgonAbazi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAgonAbazi.setBounds(98, 141, 108, 21);
		panel_4.add(lblAgonAbazi);
		
		JLabel lblSigalPrishtina_1 = new JLabel("SIGAL PRISHTINA");
		lblSigalPrishtina_1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblSigalPrishtina_1.setBounds(72, 11, 219, 30);
		panelPrishtina.add(lblSigalPrishtina_1);
		
		
		lblDescription.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblDescription.setBounds(564, 47, 219, 30);
		panelPrishtina.add(lblDescription);
		
		
		lblCoach.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblCoach.setBounds(564, 89, 49, 19);
		panelPrishtina.add(lblCoach);
		
		
		lblMatches.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches.setBounds(72, 331, 71, 19);
		panelPrishtina.add(lblMatches);
		
		JLabel lblAndinRashica = new JLabel("Andin Rashica");
		lblAndinRashica.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblAndinRashica.setBounds(612, 89, 114, 19);
		panelPrishtina.add(lblAndinRashica);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(72, 365, 595, 120);
		panelPrishtina.add(scrollPane_11);
		
		tblPrishtina = new JTable();
		scrollPane_11.setViewportView(tblPrishtina);
		
		JLabel label_56 = new JLabel("-2018");
		label_56.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_56.setBounds(146, 331, 71, 19);
		panelPrishtina.add(label_56);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_3.setBackground(new Color(102, 153, 153));
		label_3.setBounds(0, 0, 823, 512);
		panelPrishtina.add(label_3);
		
		panelPrishtina.setVisible(false);
		
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
		
		
		lblFirstColor1.setForeground(Color.WHITE);
		lblFirstColor1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor1.setBounds(32, 178, 91, 21);
		panel_26.add(lblFirstColor1);
		
		
		lblSecondColor1.setForeground(Color.WHITE);
		lblSecondColor1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor1.setBounds(32, 210, 91, 21);
		panel_26.add(lblSecondColor1);
		
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
		
		
		lblContact1.setForeground(Color.WHITE);
		lblContact1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact1.setBounds(32, 141, 65, 21);
		panel_26.add(lblContact1);
		
		JLabel lblKastriotBlakaj = new JLabel("Kastriot Blakaj");
		lblKastriotBlakaj.setForeground(Color.WHITE);
		lblKastriotBlakaj.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblKastriotBlakaj.setBounds(98, 141, 108, 21);
		panel_26.add(lblKastriotBlakaj);
		
		
		lblDescription1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblDescription1.setBounds(564, 47, 219, 30);
		panelBorea.add(lblDescription1);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(72, 365, 595, 120);
		panelBorea.add(scrollPane_6);
		
		tblBorea = new JTable();
		scrollPane_6.setViewportView(tblBorea);
		
		
		lblCoach1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblCoach1.setBounds(564, 89, 49, 19);
		panelBorea.add(lblCoach1);
		
		JLabel lblKastriotBlakaj_1 = new JLabel("Kastriot Blakaj");
		lblKastriotBlakaj_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblKastriotBlakaj_1.setBounds(612, 89, 114, 19);
		panelBorea.add(lblKastriotBlakaj_1);
		
		
		lblMatches1.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches1.setBounds(72, 331, 71, 19);
		panelBorea.add(lblMatches1);
		
		JLabel label_81 = new JLabel("-2018");
		label_81.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_81.setBounds(146, 331, 71, 19);
		panelBorea.add(label_81);
		
		JLabel label_48 = new JLabel("");
		label_48.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_48.setBackground(new Color(102, 153, 153));
		label_48.setBounds(0, 0, 823, 512);
		panelBorea.add(label_48);
		panelBorea.setVisible(false);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(261, 0, 823, 512);
		teamsPanel.add(mainPanel);
		mainPanel.setLayout(null);
		
		
		lblTeamsOfSuperleague.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamsOfSuperleague.setForeground(Color.WHITE);
		lblTeamsOfSuperleague.setFont(new Font("Stencil", Font.BOLD, 44));
		lblTeamsOfSuperleague.setBounds(42, 79, 595, 109);
		mainPanel.add(lblTeamsOfSuperleague);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrmMain.class.getResource("/images/basketballwp.png")));
		lblNewLabel.setBounds(0, 0, 823, 512);
		mainPanel.add(lblNewLabel);
		
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
		
		
		lblFirstColor2.setForeground(Color.WHITE);
		lblFirstColor2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor2.setBounds(32, 178, 91, 21);
		panel_34.add(lblFirstColor2);
		
		
		lblSecondColor2.setForeground(Color.WHITE);
		lblSecondColor2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor2.setBounds(32, 210, 91, 21);
		panel_34.add(lblSecondColor2);
		
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
		
		
		lblContact2.setForeground(Color.WHITE);
		lblContact2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact2.setBounds(32, 141, 65, 21);
		panel_34.add(lblContact2);
		
		JLabel lblDriteroSefaja_1 = new JLabel("Dritero Sefaja");
		lblDriteroSefaja_1.setForeground(Color.WHITE);
		lblDriteroSefaja_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDriteroSefaja_1.setBounds(98, 141, 108, 21);
		panel_34.add(lblDriteroSefaja_1);
		
		
		lblDescription2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblDescription2.setBounds(564, 47, 219, 30);
		panelKPrishtina.add(lblDescription2);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(72, 365, 595, 120);
		panelKPrishtina.add(scrollPane_8);
		
		tblKerasan = new JTable();
		scrollPane_8.setViewportView(tblKerasan);
		
		
		lblCoach2.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblCoach2.setBounds(564, 89, 49, 19);
		panelKPrishtina.add(lblCoach2);
		
		JLabel lblDriteroSefaja = new JLabel("Dritero Sefaja");
		lblDriteroSefaja.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblDriteroSefaja.setBounds(612, 89, 114, 19);
		panelKPrishtina.add(lblDriteroSefaja);
		
		
		lblMatches2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches2.setBounds(72, 331, 71, 19);
		panelKPrishtina.add(lblMatches2);
		
		JLabel label_77 = new JLabel("-2018");
		label_77.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_77.setBounds(146, 331, 71, 19);
		panelKPrishtina.add(label_77);
		
		JLabel label_50 = new JLabel("");
		label_50.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_50.setBackground(new Color(102, 153, 153));
		label_50.setBounds(0, 0, 823, 512);
		panelKPrishtina.add(label_50);
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
		
		
		lblFirstColor3.setForeground(Color.WHITE);
		lblFirstColor3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor3.setBounds(32, 178, 91, 21);
		panel_30.add(lblFirstColor3);
		
		
		lblSecondColor3.setForeground(Color.WHITE);
		lblSecondColor3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor3.setBounds(32, 210, 91, 21);
		panel_30.add(lblSecondColor3);
		
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
		
		
		lblContact3.setForeground(Color.WHITE);
		lblContact3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact3.setBounds(32, 141, 65, 21);
		panel_30.add(lblContact3);
		
		JLabel lblEgzonFetiu = new JLabel("Egzon Fetiu");
		lblEgzonFetiu.setForeground(Color.WHITE);
		lblEgzonFetiu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEgzonFetiu.setBounds(98, 141, 108, 21);
		panel_30.add(lblEgzonFetiu);
		
		
		lblDescription3.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblDescription3.setBounds(564, 47, 219, 30);
		panelGEYlli.add(lblDescription3);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(72, 365, 595, 120);
		panelGEYlli.add(scrollPane_7);
		
		tblGolden = new JTable();
		scrollPane_7.setViewportView(tblGolden);
		
		
		lblCoach3.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblCoach3.setBounds(564, 89, 49, 19);
		panelGEYlli.add(lblCoach3);
		
		JLabel lblDenizBrajmovci = new JLabel("Deniz Brajmovci");
		lblDenizBrajmovci.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblDenizBrajmovci.setBounds(612, 89, 114, 19);
		panelGEYlli.add(lblDenizBrajmovci);
		
		
		lblAssCoach3.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblAssCoach3.setBounds(564, 112, 94, 19);
		panelGEYlli.add(lblAssCoach3);
		
		JLabel lblHasanBytyci = new JLabel("Hasan Bytyci");
		lblHasanBytyci.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblHasanBytyci.setBounds(660, 112, 94, 19);
		panelGEYlli.add(lblHasanBytyci);
		
		
		lblMatches3.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches3.setBounds(72, 331, 71, 19);
		panelGEYlli.add(lblMatches3);
		
		JLabel label_74 = new JLabel("-2018");
		label_74.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_74.setBounds(146, 331, 71, 19);
		panelGEYlli.add(label_74);
		
		JLabel label_51 = new JLabel("");
		label_51.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_51.setBackground(new Color(102, 153, 153));
		label_51.setBounds(0, 0, 823, 512);
		panelGEYlli.add(label_51);
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
		
		
		lblFirstColor4.setForeground(Color.WHITE);
		lblFirstColor4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor4.setBounds(32, 178, 91, 21);
		panel_22.add(lblFirstColor4);
		
		
		lblSecondColor4.setForeground(Color.WHITE);
		lblSecondColor4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor4.setBounds(32, 210, 91, 21);
		panel_22.add(lblSecondColor4);
		
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
		
		
		lblContact4.setForeground(Color.WHITE);
		lblContact4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact4.setBounds(32, 141, 65, 21);
		panel_22.add(lblContact4);
		
		JLabel lblAlbanRama = new JLabel("Alban Rama");
		lblAlbanRama.setForeground(Color.WHITE);
		lblAlbanRama.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblAlbanRama.setBounds(98, 141, 108, 21);
		panel_22.add(lblAlbanRama);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(72, 365, 595, 120);
		panelRahoveci.add(scrollPane_5);
		
		tblRahoveci = new JTable();
		scrollPane_5.setViewportView(tblRahoveci);
		
		
		lblDescription4.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblDescription4.setBounds(564, 47, 219, 30);
		panelRahoveci.add(lblDescription4);
		
		
		lblCoach4.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblCoach4.setBounds(564, 89, 49, 19);
		panelRahoveci.add(lblCoach4);
		
		JLabel lblMarkRodiqi = new JLabel("Mark Rodiqi");
		lblMarkRodiqi.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblMarkRodiqi.setBounds(612, 89, 114, 19);
		panelRahoveci.add(lblMarkRodiqi);
		
		
		lblMatches4.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches4.setBounds(72, 331, 71, 19);
		panelRahoveci.add(lblMatches4);
		
		JLabel label_71 = new JLabel("-2018");
		label_71.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_71.setBounds(146, 331, 71, 19);
		panelRahoveci.add(label_71);
		
		JLabel label_40 = new JLabel("");
		label_40.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_40.setBounds(0, 0, 823, 512);
		panelRahoveci.add(label_40);
		panelRahoveci.setVisible(false);
		
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
		
		
		lblFirstColor5.setForeground(Color.WHITE);
		lblFirstColor5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor5.setBounds(32, 178, 91, 21);
		panel_18.add(lblFirstColor5);
		
		
		lblSecondColor5.setForeground(Color.WHITE);
		lblSecondColor5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor5.setBounds(32, 210, 91, 21);
		panel_18.add(lblSecondColor5);
		
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
		
		
		lblContact5.setForeground(Color.WHITE);
		lblContact5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact5.setBounds(32, 141, 65, 21);
		panel_18.add(lblContact5);
		
		JLabel lblArlindKacaniku = new JLabel("Arlind Kacaniku");
		lblArlindKacaniku.setForeground(Color.WHITE);
		lblArlindKacaniku.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblArlindKacaniku.setBounds(98, 141, 108, 21);
		panel_18.add(lblArlindKacaniku);
		
		
		lblDescription5.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		lblDescription5.setBounds(564, 47, 219, 30);
		panelBashkimi.add(lblDescription5);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(72, 365, 595, 120);
		panelBashkimi.add(scrollPane_4);
		
		tblBashkimi = new JTable();
		scrollPane_4.setViewportView(tblBashkimi);
		
		
		lblCoach5.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblCoach5.setBounds(564, 89, 49, 19);
		panelBashkimi.add(lblCoach5);
		
		JLabel lblTakianosFotis = new JLabel("Takianos Fotis");
		lblTakianosFotis.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblTakianosFotis.setBounds(612, 89, 114, 19);
		panelBashkimi.add(lblTakianosFotis);
		
		
		lblAssCoach5.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblAssCoach5.setBounds(564, 112, 94, 19);
		panelBashkimi.add(lblAssCoach5);
		
		JLabel lblIlmenBajra = new JLabel("Ilmen Bajra");
		lblIlmenBajra.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblIlmenBajra.setBounds(660, 112, 94, 19);
		panelBashkimi.add(lblIlmenBajra);
		
		
		lblMatches5.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches5.setBounds(72, 331, 71, 19);
		panelBashkimi.add(lblMatches5);
		
		JLabel label_68 = new JLabel("-2018");
		label_68.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_68.setBounds(146, 331, 71, 19);
		panelBashkimi.add(label_68);
		
		JLabel label_53 = new JLabel("");
		label_53.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_53.setBackground(new Color(102, 153, 153));
		label_53.setBounds(0, 0, 823, 512);
		panelBashkimi.add(label_53);
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
		
		
		lblFirstColor6.setForeground(Color.WHITE);
		lblFirstColor6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFirstColor6.setBounds(32, 178, 91, 21);
		panel_14.add(lblFirstColor6);
		
		
		lblSecondColor6.setForeground(Color.WHITE);
		lblSecondColor6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSecondColor6.setBounds(32, 210, 91, 21);
		panel_14.add(lblSecondColor6);
		
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
		
		
		lblContact6.setForeground(Color.WHITE);
		lblContact6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblContact6.setBounds(32, 141, 65, 21);
		panel_14.add(lblContact6);
		
		JLabel lblGazmendPopovci = new JLabel("Gazmend Popovci");
		lblGazmendPopovci.setForeground(Color.WHITE);
		lblGazmendPopovci.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGazmendPopovci.setBounds(92, 141, 108, 21);
		panel_14.add(lblGazmendPopovci);
		
		JLabel label_20 = new JLabel("Description");
		label_20.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 24));
		label_20.setBounds(564, 47, 219, 30);
		panelPeja.add(label_20);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(72, 365, 595, 120);
		panelPeja.add(scrollPane_3);
		
		tblPeja = new JTable();
		scrollPane_3.setViewportView(tblPeja);
		
		
		lblCoach6.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblCoach6.setBounds(564, 89, 49, 19);
		panelPeja.add(lblCoach6);
		
		JLabel lblPetritZeqiri = new JLabel("Petrit Zeqiri");
		lblPetritZeqiri.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblPetritZeqiri.setBounds(612, 89, 114, 19);
		panelPeja.add(lblPetritZeqiri);
		
		
		lblAssCoach6.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblAssCoach6.setBounds(564, 112, 94, 19);
		panelPeja.add(lblAssCoach6);
		
		JLabel lblBujarLoci = new JLabel("Bujar Loci");
		lblBujarLoci.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 13));
		lblBujarLoci.setBounds(660, 112, 94, 19);
		panelPeja.add(lblBujarLoci);
		
		
		lblMatches6.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		lblMatches6.setBounds(72, 331, 71, 19);
		panelPeja.add(lblMatches6);
		
		JLabel label_63 = new JLabel("-2018");
		label_63.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 15));
		label_63.setBounds(146, 331, 71, 19);
		panelPeja.add(label_63);
		
		JLabel label_54 = new JLabel("");
		label_54.setIcon(new ImageIcon(FrmMain.class.getResource("/images/background_3.jpg")));
		label_54.setBackground(new Color(102, 153, 153));
		label_54.setBounds(0, 0, 823, 512);
		panelPeja.add(label_54);
		panelPeja.setVisible(false);
		
		JPanel panelTeams = new JPanel();
		panelTeams.setBackground(Color.BLACK);
		panelTeams.setBounds(0, 0, 263, 512);
		teamsPanel.add(panelTeams);
		panelTeams.setLayout(null);
		//#################################################################################################################################################
		//###############  Eventet qe shfaqin panelet e ekipeve  ##########################################################################################
		//#################################################################################################################################################
		
		JButton btnTeamPrishtina = new JButton("Sigal Prishtina");
		btnTeamPrishtina.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				panelPrishtina.setVisible(true);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(false);
				updateTblTeams(tblPrishtina,"Prishtina");
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblPrishtina,"Prishtina");
			}
		});
		btnTeamPrishtina.setIcon(new ImageIcon(FrmMain.class.getResource("/images/prishtina_logo.png")));
		btnTeamPrishtina.setForeground(Color.WHITE);
		btnTeamPrishtina.setBackground(Color.BLACK);
		btnTeamPrishtina.setBounds(0, 0, 261, 44);
		panelTeams.add(btnTeamPrishtina);
		
		JButton btnTeamTrepca = new JButton("Trepca");
		btnTeamTrepca.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(true);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(false);
				updateTblTeams(tblTrepca,"Trepca");
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblTrepca,"Trepca");
				
			}
		});
		btnTeamTrepca.setForeground(Color.WHITE);
		btnTeamTrepca.setBackground(Color.BLACK);
		btnTeamTrepca.setBounds(0, 44, 261, 44);
		panelTeams.add(btnTeamTrepca);
		
		JButton btnTeamPeja = new JButton("Peja");
		btnTeamPeja.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(true);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(false);
				updateTblTeams(tblPeja,"Peja");
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblPeja,"Peja");
			}
		});
		btnTeamPeja.setForeground(Color.WHITE);
		btnTeamPeja.setBackground(Color.BLACK);
		btnTeamPeja.setBounds(0, 88, 261, 44);
		panelTeams.add(btnTeamPeja);
		
		JButton btnTeamBashkimi = new JButton("Bashkimi");
		btnTeamBashkimi.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(true);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(false);
				updateTblTeams(tblBashkimi,"Bashkimi");
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblBashkimi,"Bashkimi");
			}
		});
		btnTeamBashkimi.setForeground(Color.WHITE);
		btnTeamBashkimi.setBackground(Color.BLACK);
		btnTeamBashkimi.setBounds(0, 132, 261, 44);
		panelTeams.add(btnTeamBashkimi);
		
		JButton btnTeamRahoveci = new JButton("Rahoveci");
		btnTeamRahoveci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(true);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(false);
				updateTblTeams(tblRahoveci,"Rahoveci");
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblRahoveci,"Rahoveci");
			}
		});
		btnTeamRahoveci.setForeground(Color.WHITE);
		btnTeamRahoveci.setBackground(Color.BLACK);
		btnTeamRahoveci.setBounds(0, 176, 261, 44);
		panelTeams.add(btnTeamRahoveci);
		
		JButton btnTeamGEYlli = new JButton("Golden Eagle Ylli");
		btnTeamGEYlli.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(true);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(false);
				updateTblTeams(tblGolden,"Golden Eagle Ylli");
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblGolden,"Golden Eagle Ylli");
			}
		});
		btnTeamGEYlli.setForeground(Color.WHITE);
		btnTeamGEYlli.setBackground(Color.BLACK);
		btnTeamGEYlli.setBounds(0, 264, 261, 44);
		panelTeams.add(btnTeamGEYlli);
		
		JButton btnTeamKPrishtina = new JButton("Kerasan Prishtina");
		btnTeamKPrishtina.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(true);
				mainPanel.setVisible(false);
				updateTblTeams(tblKerasan,"Kerasan Prishtina");
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblKerasan,"Kerasan Prishtina");
			}
		});
		btnTeamKPrishtina.setForeground(Color.WHITE);
		btnTeamKPrishtina.setBackground(Color.BLACK);
		btnTeamKPrishtina.setBounds(0, 308, 261, 44);
		panelTeams.add(btnTeamKPrishtina);
		
		JButton btnTeamBorea = new JButton("Borea");
		btnTeamBorea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(true);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(false);
				updateTblTeams(tblBorea,"Borea");
				
			}
		});
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
				mainPanel.setVisible(false);
				updateTblTeams(tblBorea,"Borea");
			}
		});
		btnTeamBorea.setForeground(Color.WHITE);
		btnTeamBorea.setBackground(Color.BLACK);
		btnTeamBorea.setBounds(0, 220, 261, 44);
		panelTeams.add(btnTeamBorea);
		
		JButton btnKthev = new JButton("");
		btnKthev.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		btnKthev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelPrishtina.setVisible(false);
				panelTrepca.setVisible(false);
				panelPeja.setVisible(false);
				panelBashkimi.setVisible(false);
				panelBorea.setVisible(false);
				panelRahoveci.setVisible(false);
				panelGEYlli.setVisible(false);
				panelKPrishtina.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		btnKthev.setIcon(new ImageIcon(FrmMain.class.getResource("/images/arrow-left-hi.png")));
		btnKthev.setHorizontalAlignment(SwingConstants.LEFT);
		btnKthev.setForeground(Color.WHITE);
		btnKthev.setBackground(Color.BLACK);
		btnKthev.setBounds(0, 356, 60, 44);
		panelTeams.add(btnKthev);
		tabbedPane.setForegroundAt(3, Color.WHITE);
		tabbedPane.setBackgroundAt(3, Color.BLACK);
		
		updateTblStandings();
		updateTblResults();
		updateTblUpcoming();
		setLanguage(language);
		//#################################################################################################################################################
		//###############  Thirrja per metoden qe mundeson nderrimin e tabave me TAB dhe shift+TAB  #######################################################
		//#################################################################################################################################################
		objMethods.setupTabTraversalKeys(tabbedPane);
				
		
	}
	//#####################################################################################################################################################################
	//#########  METODA QE BEN PERKTHIMIN NE BAZE TW PARAMETRIT LANG QE MERR ##############################################################################################
	//#####################################################################################################################################################################
	public void setLanguage(String lang) {
		if(lang != null) {
			if(lang == "EN") {
				Locale.setDefault(new Locale("en"));
			}
			else if(lang == "AL") {
				Locale.setDefault(new Locale("al"));
			}
		ResourceBundle r = ResourceBundle.getBundle("log");
		lblSearchForUpcoming.setText(r.getString("search"));
		lblUpcomingGames.setText(r.getString("upcoming"));
		lblHomeTeam.setText(r.getString("hometeam"));
		lblAwayTeam.setText(r.getString("awayteam"));
		lblHomeTeamScores.setText(r.getString("htscore"));
		lblAwayTeamScores.setText(r.getString("atscore"));
		lblTime.setText(r.getString("time"));
		lblDate.setText(r.getString("date"));
		lblResults_1.setText(r.getString("results"));
		lblResults.setText(r.getString("insertresults"));
		mntmDelete.setText(r.getString("delete"));
		lblStandingsTable.setText(r.getString("standings"));
		lblTeam.setText(r.getString("team"));
		lblGamesPlayed.setText(r.getString("gamesplayed"));
		lblWins.setText(r.getString("wins"));
		lblLosses.setText(r.getString("losses"));
		lblScored.setText(r.getString("scored"));
		lblTaken.setText(r.getString("taken"));
		lblPoints.setText(r.getString("points"));
		btnAddStandings.setText(r.getString("add"));
		btnEditStandings.setText(r.getString("edit"));
		btnDeleteStandings.setText(r.getString("delete"));
		btnResetStandings.setText(r.getString("reset"));
		lblTeamsOfSuperleague.setText(r.getString("teamsofsuperleague"));
		lblFirstColor1.setText(r.getString("firstcolor"));
		lblSecondColor1.setText(r.getString("secondcolor"));
		lblContact1.setText(r.getString("contact"));
		lblDescription1.setText(r.getString("description"));
		lblCoach1.setText(r.getString("coach"));
		lblMatches1.setText(r.getString("matches"));
		lblFirstColor2.setText(r.getString("firstcolor"));
		lblSecondColor2.setText(r.getString("secondcolor"));
		lblContact2.setText(r.getString("contact"));
		lblDescription2.setText(r.getString("description"));
		lblCoach2.setText(r.getString("coach"));
		lblMatches2.setText(r.getString("matches"));
		lblFirstColor3.setText(r.getString("firstcolor"));
		lblSecondColor3.setText(r.getString("secondcolor"));
		lblContact3.setText(r.getString("contact"));
		lblDescription3.setText(r.getString("description"));
		lblCoach3.setText(r.getString("coach"));
		lblAssCoach3.setText(r.getString("assistantcoach"));
		lblMatches3.setText(r.getString("matches"));
		lblFirstColor4.setText(r.getString("firstcolor"));
		lblSecondColor4.setText(r.getString("secondcolor"));
		lblContact4.setText(r.getString("contact"));
		lblDescription4.setText(r.getString("description"));
		lblCoach4.setText(r.getString("coach"));
		lblMatches4.setText(r.getString("matches"));
		lblFirstColor5.setText(r.getString("firstcolor"));
		lblSecondColor5.setText(r.getString("secondcolor"));
		lblContact5.setText(r.getString("contact"));
		lblDescription5.setText(r.getString("description"));
		lblCoach5.setText(r.getString("coach"));
		lblAssCoach5.setText(r.getString("assistantcoach"));
		lblMatches5.setText(r.getString("matches"));
		lblFirstColor6.setText(r.getString("firstcolor"));
		lblSecondColor6.setText(r.getString("secondcolor"));
		lblContact6.setText(r.getString("contact"));
		lblDescription6.setText(r.getString("description"));
		lblCoach6.setText(r.getString("coach"));
		lblAssCoach6.setText(r.getString("assistantcoach"));
		lblMatches6.setText(r.getString("matches"));
		lblMatches7.setText(r.getString("matches"));
		lblContact7.setText(r.getString("contact"));
		lblFirstColor7.setText(r.getString("firstcolor"));
		lblSecondColor7.setText(r.getString("secondcolor"));
		lblDescription7.setText(r.getString("description"));
		lblCoach7.setText(r.getString("coach"));
		lblAssCoach7.setText(r.getString("assistantcoach"));
		lblFirstColor.setText(r.getString("firstcolor"));
		lblSecondColor.setText(r.getString("secondcolor"));
		lblContact.setText(r.getString("contact"));
		lblDescription.setText(r.getString("description"));
		lblCoach.setText(r.getString("coach"));
		lblMatches.setText(r.getString("matches"));
		btnAddResults.setText(r.getString("add"));
		btnDeleteResults.setText(r.getString("delete"));
		btnEditResults.setText(r.getString("edit"));
		btnResetResults.setText(r.getString("reset"));
		tabbedPane.setTitleAt(0,r.getString("home"));
		tabbedPane.setTitleAt(1,r.getString("results"));
		tabbedPane.setTitleAt(2,r.getString("standings"));
		tabbedPane.setTitleAt(3,r.getString("teams"));
		}
		
	}

	//#####################################################################################################################################################################
	//#########  METODA per popup #########################################################################################################################################
	//#####################################################################################################################################################################
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
