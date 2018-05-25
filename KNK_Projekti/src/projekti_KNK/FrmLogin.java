package projekti_KNK;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrmLogin extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//objekti per lidhje
	Connection conn=null;
	//objekti per vendosje te rezultatit
	ResultSet res=null;
	//objekti per query
	PreparedStatement pst=null;
	Cursor handCursor =  new Cursor(Cursor.HAND_CURSOR); //Create a hand cursor object
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	// main frame object
	FrmMain mainFrame = new FrmMain();
	
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
					FrmLogin frame = new FrmLogin();
					frame.setUndecorated(true);//Removes title bar
					frame.setVisible(true);
					//Set the frame in the middle of the window
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
	public FrmLogin() {
		setTitle("Basketball ");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\DIGITRON\\Desktop\\Fakultet LINA\\KNK\\PROJEKTIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII I FUNDIT\\KNK_Projekti\\KNK_Projekti\\src\\images\\15983-200.png"));
		conn=SQLConn.connectDB();
		//this.setExtendedState(this.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 102, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Calibri", Font.BOLD, 29));
		lblLogin.setForeground(new Color(0, 0, 0));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(418, 64, 151, 47);
		contentPane.add(lblLogin);
		
		txtUsername = new JTextField();
		txtUsername.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //border e textfield
		txtUsername.setBackground(Color.LIGHT_GRAY);
		txtUsername.setToolTipText("");
		txtUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		txtUsername.setBounds(386, 147, 237, 35);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setBounds(386, 122, 237, 24);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblPassword.setBounds(386, 208, 237, 24);
		contentPane.add(lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				pwdPassword.selectAll();
			}
		});
		pwdPassword.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pwdPassword.setBackground(Color.LIGHT_GRAY);
		pwdPassword.setBounds(386, 233, 237, 35);
		contentPane.add(pwdPassword);
		
		JButton btnLogin = new JButton("Log In");
		
	//Login with Enter key
		btnLogin.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent arg0) {
				btnLogin.getRootPane().setDefaultButton(btnLogin);	
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
						@SuppressWarnings("deprecation")
						String sql="select * from tblusers where username = '"+txtUsername.getText()+"' and userPassword = '"+pwdPassword.getText()+"';";
						pst=conn.prepareStatement(sql);
						res = pst.executeQuery();
						if(res.next()) {
							@SuppressWarnings("deprecation")
							String sql1="select * from tblusers where username = '"+txtUsername.getText()+"' and userPassword = '"+pwdPassword.getText()+"' and userAdmin = 1;";
							pst=conn.prepareStatement(sql1);
							pst.execute();
							res = pst.executeQuery();
							if(res.next()) {
								dispose();
								mainFrame.setVisible(true);
								mainFrame.setLocationRelativeTo(null);
							}
							else {
								JOptionPane.showMessageDialog(null, "You don't have administrator access!");
							}
						
						}
						else {
							JOptionPane.showMessageDialog(null, "Username or password incorrect!");
						}
						
					pst.close();
				} 
				catch (Exception e2){
					JOptionPane.showMessageDialog(null, "Error: "+e2.getMessage());
				}
			}
		});
		btnLogin.setForeground(Color.LIGHT_GRAY);
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 16));
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setBounds(443, 299, 121, 35);
		//btnLogin.setOpaque(true);
		btnLogin.setCursor(handCursor); //Change cursor when u hover over this Button
		
		contentPane.add(btnLogin);
		
		JLabel lblSignup = new JLabel("Sign up");
		lblSignup.setForeground(Color.BLACK);
		lblSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
				FrmSignup signupFrame =  new FrmSignup(); 
				signupFrame.setUndecorated(true);
				signupFrame.setVisible(true);
				signupFrame.setLocationRelativeTo(null);
			}
		});
		lblSignup.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignup.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSignup.setBounds(476, 346, 55, 16);
		lblSignup.setCursor(handCursor); 
		contentPane.add(lblSignup);
		
		JLabel lblExit = new JLabel("");
		lblExit.setToolTipText("Close");
		lblExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		lblExit.setIcon(new ImageIcon(FrmLogin.class.getResource("/images/close_essential_set_x_icon_128.png")));
		lblExit.setBounds(648, 11, 20, 20);
		lblExit.setCursor(handCursor);
		contentPane.add(lblExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 339, 458);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrmLogin.class.getResource("/images/basketball-user.png")));
		label.setBounds(59, 61, 243, 260);
		panel.add(label);
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show password");
		chckbxShowPassword.setFont(new Font("Calibri", Font.PLAIN, 12));
		chckbxShowPassword.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(chckbxShowPassword.isSelected()) {
					pwdPassword.setEchoChar((char) 0);
				}
				else {
					pwdPassword.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxShowPassword.setBackground(new Color(51, 102, 102));
		chckbxShowPassword.setForeground(Color.BLACK);
		chckbxShowPassword.setBounds(386, 269, 237, 23);
		contentPane.add(chckbxShowPassword);
		
	}
}
