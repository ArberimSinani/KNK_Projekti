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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

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
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
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
			String sql="select team as 'Team', games as 'GP',wins as 'W', losses as 'L',"
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
		setTitle("Basketball");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DIGITRON\\Desktop\\Fakultet LINA\\KNK\\PROJEKTIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII I FUNDIT\\KNK_Projekti\\KNK_Projekti\\src\\images\\15983-200.png"));
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
		
		JPanel resultsPanel = new JPanel();
		tabbedPane.addTab("Results", null, resultsPanel, null);
		tabbedPane.setForegroundAt(1, Color.WHITE);
		tabbedPane.setBackgroundAt(1, Color.BLACK);
		
		
		JPanel teamsPanel = new JPanel();
		tabbedPane.addTab("Teams", null, teamsPanel, null);
		tabbedPane.setForegroundAt(2, Color.WHITE);
		tabbedPane.setBackgroundAt(2, Color.BLACK);
	
		
		objMethods.setupTabTraversalKeys(tabbedPane);//Thirrja per metoden qe mundeson nderrimin e tabave me TAB dhe shift+TAB
		
		JPanel standingsPanel = new JPanel();
		standingsPanel.setBackground(new Color(51, 102, 102));
		tabbedPane.addTab("Standings", null, standingsPanel, null);
		tabbedPane.setForegroundAt(3, Color.WHITE);
		tabbedPane.setBackgroundAt(3, Color.BLACK);
		standingsPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 84, 851, 155);
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
		tblStandings.setFont(new Font("Calibri", Font.PLAIN, 18));
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
		titlePanel.setBounds(218, 0, 861, 49);
		standingsPanel.add(titlePanel);
		titlePanel.setLayout(null);
		titlePanel.setBackground(new Color(0, 0, 0));
		
		JLabel lblStandingsTable = new JLabel("Standings ");
		lblStandingsTable.setFont(new Font("Calibri", Font.PLAIN, 23));
		lblStandingsTable.setHorizontalAlignment(SwingConstants.CENTER);
		lblStandingsTable.setForeground(Color.WHITE);
		lblStandingsTable.setBounds(294, 11, 223, 28);
		titlePanel.add(lblStandingsTable);
		
		txtTeam = new JTextField();
		txtTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtTeam.setBounds(11, 82, 188, 33);
		standingsPanel.add(txtTeam);
		txtTeam.setColumns(10);
		
		txtGamesplayed = new JTextField();
		txtGamesplayed.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtGamesplayed.setColumns(10);
		txtGamesplayed.setBounds(10, 142, 188, 33);
		standingsPanel.add(txtGamesplayed);
		
		txtWins = new JTextField();
		txtWins.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtWins.setColumns(10);
		txtWins.setBounds(10, 207, 188, 33);
		standingsPanel.add(txtWins);
		
		txtLosses = new JTextField();
		txtLosses.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtLosses.setColumns(10);
		txtLosses.setBounds(11, 272, 188, 33);
		standingsPanel.add(txtLosses);
		
		txtScored = new JTextField();
		txtScored.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtScored.setColumns(10);
		txtScored.setBounds(11, 334, 188, 33);
		standingsPanel.add(txtScored);
		
		txtTaken = new JTextField();
		txtTaken.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtTaken.setColumns(10);
		txtTaken.setBounds(11, 396, 188, 33);
		standingsPanel.add(txtTaken);
		
		txtPoints = new JTextField();
		txtPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
		txtPoints.setColumns(10);
		txtPoints.setBounds(11, 459, 188, 33);
		standingsPanel.add(txtPoints);
		
		JLabel lblTeam = new JLabel("Team");
		lblTeam.setForeground(Color.LIGHT_GRAY);
		lblTeam.setBounds(10, 61, 189, 20);
		standingsPanel.add(lblTeam);
		
		JLabel lblGamesPlayed = new JLabel("Games played");
		lblGamesPlayed.setForeground(Color.LIGHT_GRAY);
		lblGamesPlayed.setBounds(10, 121, 188, 20);
		standingsPanel.add(lblGamesPlayed);
		
		JLabel lblWins = new JLabel("Wins");
		lblWins.setForeground(Color.LIGHT_GRAY);
		lblWins.setBounds(12, 186, 188, 20);
		standingsPanel.add(lblWins);
		
		JLabel lblLosses = new JLabel("Losses");
		lblLosses.setForeground(Color.LIGHT_GRAY);
		lblLosses.setBounds(11, 251, 188, 20);
		standingsPanel.add(lblLosses);
		
		JLabel lblScored = new JLabel("Scored");
		lblScored.setForeground(Color.LIGHT_GRAY);
		lblScored.setBounds(11, 316, 188, 20);
		standingsPanel.add(lblScored);
		
		JLabel lblTaken = new JLabel("Taken");
		lblTaken.setForeground(Color.LIGHT_GRAY);
		lblTaken.setBounds(13, 378, 188, 20);
		standingsPanel.add(lblTaken);
		
		JLabel lblPoints = new JLabel("Points");
		lblPoints.setForeground(Color.LIGHT_GRAY);
		lblPoints.setBounds(11, 440, 185, 20);
		standingsPanel.add(lblPoints);
		
		//Butoni ADD ============================================================================================================
		JButton btnAdd = new JButton("Add");
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
		btnAdd.setBounds(339, 365, 111, 33);
		btnAdd.setBackground(Color.BLACK);
		standingsPanel.add(btnAdd);
		
		//Butoni Edit ===================================================================================
		JButton btnEdit = new JButton("Edit");
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
		btnEdit.setBounds(228, 365, 111, 33);
		btnEdit.setBackground(Color.BLACK);
		standingsPanel.add(btnEdit);
		
		//BUTONI DELETE ======================================================================================================
		JButton btnDelete = new JButton("Delete");
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
		btnDelete.setBounds(339, 334, 111, 33);
		btnDelete.setBackground(Color.BLACK);
		standingsPanel.add(btnDelete);
		
		//Butoni RESET ===================================================================================
		JButton btnReset = new JButton("Reset");
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
		btnReset.setBounds(228, 334, 111, 33);
		standingsPanel.add(btnReset);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 220, 512);
		standingsPanel.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBounds(64, 12, 84, 58);
		panel.add(label);
		label.setIcon(new ImageIcon(FrmMain.class.getResource("/images/basketball-logo-with-flames.png")));
		updateTable();
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
