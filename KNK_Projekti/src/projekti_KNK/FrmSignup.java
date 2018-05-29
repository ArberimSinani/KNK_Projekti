package projekti_KNK;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class FrmSignup extends JFrame {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		Connection conn=null;
		ResultSet res=null;
		PreparedStatement pst=null;

	private JPanel contentPane;
	private JTextField txtFullName;
	private JTextField txtUserName;
	private JTextField txtEmail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfPassword;
	Cursor handCursor =  new Cursor(Cursor.HAND_CURSOR); //Create a hand cursor object
	
	JLabel lblName = new JLabel("Full name");
	JLabel lblUsername = new JLabel("User name");
	JLabel lblEmail = new JLabel("Email");
	JLabel lblDateOfBirth = new JLabel("Date of birth");
	JLabel lblPassword = new JLabel("Password");
	JLabel lblGender = new JLabel("Gender");
	JLabel lblConfirmPassword = new JLabel("Confirm password");
	JButton btnSignUp = new JButton("Sign up");
	JLabel lblAlreadyAUser = new JLabel("Already a user? Log in");
	JRadioButton rdbtnMale = new JRadioButton("Male");
	JRadioButton rdbtnFemale = new JRadioButton("Female");
	JLabel lblSignUp = new JLabel("Sign up");
	
	String language;
	//Login object
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmSignup frame = new FrmSignup(null);
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
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmSignup(String lang) {
		this.setUndecorated(true); // Remove titlebar
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DIGITRON\\Desktop\\Fakultet LINA\\KNK\\PROJEKTIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII I FUNDIT\\KNK_Projekti\\KNK_Projekti\\src\\images\\15983-200.png"));
		conn=SQLConn.connectDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		language = lang;
		
		
		lblDateOfBirth.setForeground(Color.BLACK);
		lblDateOfBirth.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(355, 199, 128, 24);
		contentPane.add(lblDateOfBirth);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 686, 105);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmSignup.class.getResource("/images/basketball-user-small.png")));
		label.setBounds(10, 11, 88, 88);
		panel.add(label);
		
		
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Calibri", Font.PLAIN, 38));
		lblSignUp.setBounds(206, 32, 260, 47);
		panel.add(lblSignUp);
		
		JLabel lblExit = new JLabel("");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
			
		});
		lblExit.setIcon(new ImageIcon(FrmSignup.class.getResource("/images/close_essential_set_x_icon_white.png")));
		lblExit.setToolTipText("Close");
		lblExit.setBounds(648, 11, 20, 20);
		lblExit.setCursor(handCursor);
		panel.add(lblExit);
		
		txtFullName = new JTextField();
		txtFullName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		txtFullName.setBackground(Color.WHITE);
		txtFullName.setColumns(10);
		txtFullName.setBounds(56, 151, 252, 37);
		contentPane.add(txtFullName);
		
		txtUserName = new JTextField();
		txtUserName.setBackground(Color.WHITE);
		txtUserName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		txtUserName.setColumns(10);
		txtUserName.setBounds(355, 151, 252, 37);
		contentPane.add(txtUserName);
		
		
		lblName.setBackground(Color.BLACK);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblName.setBounds(56, 127, 252, 24);
		contentPane.add(lblName);
		
		lblUsername.setBackground(Color.BLACK);
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUsername.setBounds(355, 127, 252, 24);
		contentPane.add(lblUsername);
		
		txtEmail = new JTextField();
		txtEmail.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setColumns(10);
		txtEmail.setBounds(56, 223, 252, 37);
		contentPane.add(txtEmail);
		
	
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEmail.setBounds(56, 199, 252, 24);
		contentPane.add(lblEmail);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBorder(null);
		dateChooser.setForeground(new Color(0, 0, 0));
		dateChooser.setBackground(new Color(51, 102, 102));
		dateChooser.setBounds(355, 223, 143, 37);
		contentPane.add(dateChooser);
		
		
		lblGender.setForeground(Color.BLACK);
		lblGender.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblGender.setBounds(510, 195, 97, 24);
		contentPane.add(lblGender);
		
		
		rdbtnMale.setFont(new Font("Calibri", Font.PLAIN, 12));
		rdbtnMale.setForeground(new Color(0, 0, 0));
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBackground(new Color(51, 102, 102));
		rdbtnMale.setBounds(510, 219, 97, 23);
		contentPane.add(rdbtnMale);
		
		
		rdbtnFemale.setForeground(new Color(0, 0, 0));
		rdbtnFemale.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBackground(new Color(51, 102, 102));
		rdbtnFemale.setBounds(510, 237, 97, 23);
		contentPane.add(rdbtnFemale);
		
		
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPassword.setBounds(56, 271, 218, 24);
		contentPane.add(lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pwdPassword.setBackground(Color.WHITE);
		pwdPassword.setBounds(56, 294, 252, 37);
		contentPane.add(pwdPassword);
		

		lblConfirmPassword.setForeground(Color.BLACK);
		lblConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(355, 271, 218, 24);
		contentPane.add(lblConfirmPassword);
		
		pwdConfPassword = new JPasswordField();
		pwdConfPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				btnSignUp.getRootPane().setDefaultButton(btnSignUp);
			}
		});
		pwdConfPassword.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pwdConfPassword.setBackground(Color.WHITE);
		pwdConfPassword.setBounds(355, 294, 252, 37);
		contentPane.add(pwdConfPassword);
		
		
		//Signup with ENTER key:
		btnSignUp.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				btnSignUp.getRootPane().setDefaultButton(btnSignUp);	
			}
		});
		btnSignUp.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				char gender = 0;
				if(rdbtnMale.isSelected()) {
					gender = 'M';
				}
				else if(rdbtnFemale.isSelected()) {
					gender = 'F';
				}
				Date date = dateChooser.getDate();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String userdate = format.format(date);
				
				
				try {
					if(pwdPassword.getText().equals(pwdConfPassword.getText())) {
						if(passwordVal(pwdPassword.getText())) {
							if(emailVal(txtEmail.getText())) {
								String sql="insert into tblusers (fullname,username,useremail,userbdate,usergender,userpassword) values "
										+ "('"+txtFullName.getText()+"','"+txtUserName.getText()+"','"+txtEmail.getText()+"','"+userdate+"','"+gender+"','"+pwdPassword.getText()+"');";
								pst=conn.prepareStatement(sql);
								pst.execute();
								pst.close();
								FrmLogin loginFrame = new FrmLogin(language);
								dispose();
								loginFrame.setVisible(true);
								loginFrame.setLocationRelativeTo(null);
							}
							else {
								JOptionPane.showMessageDialog(null,"Email is not valid");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Password must contain one number,one upper case letter, and at least 8 characters long!");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"Password doesn't match");
					}
					
				} 
				catch (Exception e2){
					JOptionPane.showMessageDialog(null, "Email or username already taken");
				}
				
			}
		});
		
		btnSignUp.setForeground(Color.LIGHT_GRAY);
		
		btnSignUp.setFont(new Font("Calibri", Font.BOLD, 22));
		btnSignUp.setBackground(Color.BLACK);
		btnSignUp.setBounds(263, 356, 135, 37);
		btnSignUp.setCursor(handCursor);
		contentPane.add(btnSignUp);
		
		
		lblAlreadyAUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FrmLogin loginFrame = new FrmLogin(language);
				dispose();
				loginFrame.setUndecorated(true);
				loginFrame.setVisible(true);
				loginFrame.setLocationRelativeTo(null);
			}
		});
		lblAlreadyAUser.setForeground(new Color(0, 0, 0));
		lblAlreadyAUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAlreadyAUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlreadyAUser.setCursor(handCursor);
		lblAlreadyAUser.setBounds(263, 392, 135, 14);
		contentPane.add(lblAlreadyAUser);
		setLanguage(language);
		
		
	}
	
	public void setLanguage(String lang) {
		if(lang == "EN") {
			Locale.setDefault(new Locale("en"));
		}
		else if(lang == "AL") {
			Locale.setDefault(new Locale("al"));
		}
		if(lang != null) {
		ResourceBundle r = ResourceBundle.getBundle("log");
		lblName.setText(r.getString("fullname"));
		lblUsername.setText(r.getString("username"));
		lblDateOfBirth.setText(r.getString("dateofbirth"));
		lblPassword.setText(r.getString("password"));
		lblGender.setText(r.getString("gender"));
		lblConfirmPassword.setText(r.getString("confpwd"));
		lblSignUp.setText(r.getString("signup"));
		btnSignUp.setText(r.getString("signup"));
		lblAlreadyAUser.setText(r.getString("alrUser"));
		rdbtnMale.setText(r.getString("male"));
		rdbtnFemale.setText(r.getString("female"));
		}
		
	}
	//Password validation method
	public static boolean passwordVal(String pasword) {
		boolean correctPass = false;
		String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(passwordPattern);
		Matcher matcher = pattern.matcher(pasword);
		correctPass = matcher.matches();
		return correctPass ;
	}
	//Email validation method
	public static boolean emailVal(String email) {
		boolean correctEmail = false;
		String passwordPattern = "^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$";
		Pattern pattern = Pattern.compile(passwordPattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		correctEmail = matcher.matches();
		return correctEmail;	
	}
}
