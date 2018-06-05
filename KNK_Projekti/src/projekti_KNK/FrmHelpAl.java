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

public class FrmHelpAl extends JFrame {

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
					FrmHelpAl frame = new FrmHelpAl();
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
	public FrmHelpAl() {
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
		
		JLabel label_1 = new JLabel("<html><b>Ky eshte nje applikacion per menagjimin e rezultateve te Superliges se Basketbollit</b> <br><br>"
									+ " Ne Home Page gjendet nje tabele me informata per ndeshjet e ardhshme.Te dhena per ekipe musafire,nikoqire,koha dhe data.<br>"
									+ " The search field is used to search for the teams you want to see. <br>"
									+ "<br> Faqja tjeter eshte Rezultatet. Mund te shtohen,fshiher,editohen apo te resetohen te dhena ne tabele.<br><br>"
									+ " Tabela Rangimet tregon ekiptet e renditura sipas pikeve. Mund te shohesh edhe sa ndeshje jane luajtur,<br>"
									+ " fitoret dhe humbjet,etj. Rezultatet perditesohen pasi nje ndeshje te mbaroje.<br><br>"
									+ " Ne faqen e fundit jane shfaqur te gjitha ekipet me ikonat e tyre, informata bazike sikur<br>"
									+ " Kontakti, ngjyrat e ekipit, numrat e telefonit dhe trajneret. Gjithashtu qdo ekip e ka tabelen e tyre me rezultate. <br><br>"
									+ " Nuk mund te qasesh nese nuk ke qasje administrative,dmth nuk mund te shtoni apo ndryshoni te dhena. <br><br> "
									+ "<i>Per me shume informata na kontaktoni ne : fiek-gr8@gmail.com </i> <br><br></html>");
		scrollPane.setViewportView(label_1);
		label_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmHelpAl.class.getResource("/images/help.png")));
		label.setBounds(661, 11, 87, 88);
		contentPane.add(label);
		
		JLabel lblHelp = new JLabel("SI MUND TE NDIHMOJME");
		lblHelp.setForeground(new Color(0, 102, 204));
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 30));
		lblHelp.setBounds(205, 11, 467, 88);
		contentPane.add(lblHelp);
		
		JLabel label_2 = new JLabel("");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setIcon(new ImageIcon(FrmHelpAl.class.getResource("/images/helpBackground.jpg")));
		label_2.setBounds(0, 0, 1084, 561);
		contentPane.add(label_2);
	}
}
