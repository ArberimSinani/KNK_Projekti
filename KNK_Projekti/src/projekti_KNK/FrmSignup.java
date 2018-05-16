package projekti_KNK;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JTextField;
import java.sql.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class FrmSignup extends JFrame {

	private JPanel contentPane;
	private JTextField txtFullName;
	private JTextField txtUserName;
	private JTextField txtEmail;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					FrmSignup frame = new FrmSignup();
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
	public FrmSignup() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 456);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 668, 105);
		panel.setBackground(new Color(246, 144, 59));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmSignup.class.getResource("/images/basketball-user-small.png")));
		label.setBounds(10, 11, 88, 88);
		panel.add(label);
		
		JLabel lblSignUp = new JLabel("Sign up");
		lblSignUp.setForeground(Color.WHITE);
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Calibri", Font.PLAIN, 38));
		lblSignUp.setBounds(206, 32, 260, 47);
		panel.add(lblSignUp);
		
		txtFullName = new JTextField();
		txtFullName.setColumns(10);
		txtFullName.setBounds(56, 151, 252, 37);
		contentPane.add(txtFullName);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(355, 151, 252, 37);
		contentPane.add(txtUserName);
		
		JLabel lblName = new JLabel("Full name");
		lblName.setForeground(Color.DARK_GRAY);
		lblName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblName.setBounds(56, 127, 252, 24);
		contentPane.add(lblName);
		
		JLabel lblLastName = new JLabel("User name");
		lblLastName.setForeground(Color.DARK_GRAY);
		lblLastName.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblLastName.setBounds(355, 127, 252, 24);
		contentPane.add(lblLastName);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(56, 223, 252, 37);
		contentPane.add(txtEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.DARK_GRAY);
		lblEmail.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblEmail.setBounds(56, 199, 252, 24);
		contentPane.add(lblEmail);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(355, 223, 97, 37);
		contentPane.add(dateChooser);
		
		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setForeground(Color.DARK_GRAY);
		lblDateOfBirth.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(355, 199, 128, 24);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setForeground(Color.DARK_GRAY);
		lblGender.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblGender.setBounds(474, 199, 97, 24);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBackground(Color.WHITE);
		rdbtnMale.setBounds(474, 230, 59, 23);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBackground(Color.WHITE);
		rdbtnFemale.setBounds(535, 230, 71, 23);
		contentPane.add(rdbtnFemale);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setForeground(Color.DARK_GRAY);
		label_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_1.setBounds(56, 271, 218, 24);
		contentPane.add(label_1);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setBounds(56, 294, 252, 37);
		contentPane.add(pwdPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm password");
		lblConfirmPassword.setForeground(Color.DARK_GRAY);
		lblConfirmPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(355, 271, 218, 24);
		contentPane.add(lblConfirmPassword);
		
		pwdConfPassword = new JPasswordField();
		pwdConfPassword.setBounds(355, 294, 252, 37);
		contentPane.add(pwdConfPassword);
		
		JButton btnSignUp = new JButton("Sign up");
		
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnSignUp.setBackground(new Color(246, 144, 59));
		btnSignUp.setBounds(263, 356, 135, 37);
		contentPane.add(btnSignUp);
	}
}
