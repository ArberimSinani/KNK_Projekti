package projekti_KNK;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

public class FrmHelpEn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmHelpEn frame = new FrmHelpEn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmHelpEn() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 879, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(100, 149, 237), 5, true));
		scrollPane.setBounds(10, 110, 857, 273);
		contentPane.add(scrollPane);
		
		JLabel label_1 = new JLabel("<html><b>This is an application for managing the results of the Basketball Superleague.</b> <br><br>"
									+ " At home page there is a table with upcoming games information. Such as home and away team names, time and date.<br>"
									+ " The search field is used to search for the teams you want to see. <br>"
									+ "<br> Next page is Results. You can add, delete, edit and reset information into the table.<br> <br>"
									+ " Standings table shows the teams ranked by the highest points. You can also see how many games have been played,<br>"
									+ " wins and losses, etc. The results are updated immediately after a specific game is finished.<br><br>"
									+ " The last page is where all the Superleague Teams are presented with their icon, basic information as their<br>"
									+ " contact, team colors, phone number and coach. Also each team has their own table with the latest results. <br><br>"
									+ " You can not login without admins permission,which means you can not add,delete or edit any data. <br><br> "
									+ "<i>For more information contact us : fiek-gr8@gmail.com </i> <br><br></html>");
		scrollPane.setViewportView(label_1);
		label_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmHelpEn.class.getResource("/images/help.png")));
		label.setBounds(661, 11, 87, 88);
		contentPane.add(label);
		
		JLabel lblHelp = new JLabel("HOW CAN WE HELP");
		lblHelp.setForeground(new Color(0, 102, 204));
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 30));
		lblHelp.setBounds(205, 11, 467, 88);
		contentPane.add(lblHelp);
		
		JLabel label_2 = new JLabel("");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setIcon(new ImageIcon(FrmHelpEn.class.getResource("/images/helpBackground.jpg")));
		label_2.setBounds(0, 0, 1084, 561);
		contentPane.add(label_2);
	}
}
