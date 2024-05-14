import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class Login {
	static Connection con;
	static ResultSet rs = null;

	static public void main(String[] rk) {

		new Login();
	}

	public Login() {

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STC", "root", "SYSTEM");
		} catch (Throwable ex) {
			System.out.println(ex);
		}

		JFrame f = new JFrame("Login");
		f.setSize(660, 400);
		f.setLayout(null);

		JLabel username = new JLabel();

		JLabel password = new JLabel();
		// Register
		JButton reg = new JButton("register");
		reg.setBounds(400, 240, 100, 30);

		f.add(reg);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.WHITE);
		JTextField t = new JTextField();
		JPasswordField p = new JPasswordField();
		JButton b = new JButton("SUBMIT");
		t.setBackground(Color.WHITE);
		p.setBackground(Color.WHITE);
		username.setText("Username");
		username.setFont(new Font("Arial", 1, 18));
		password.setText("Password");
		password.setFont(new Font("Arial", 1, 18));

		t.setFont(new Font("Arial", 1, 28));
		f.add(username);
		f.add(password);
		f.add(t);
		f.add(p);
		f.add(b);

		class MyListener implements ActionListener {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == b) {

					String user = t.getText();

					char[] pas = p.getPassword();
					String pwd = String.valueOf(pas);
					try {
						// Statement st = con.createStatement();
						PreparedStatement ps = con.prepareStatement("select * from logindetails where userid = ?");

						ps.setString(1, user);
						// ResultSet rs = rs.executeQuery(query);
						rs = ps.executeQuery();
						String msg = null;
						// System.out.println(rs);
						if (rs.next()) {
							rs.beforeFirst();
							while (rs.next()) {
								String id = rs.getString(1);
								if (user.equals(id)) {

									String pass = rs.getString(2);
									// String username = rs.getString(3);
									// String pass = rs.getString(2);
									if (pwd.equals(pass)) {
										// msg = ("Hello " + username);

										new mainMenu();
										t.setText("");
										p.setText("");
										f.setVisible(false);

									} else {
										msg = "Incorrect Password";
										JOptionPane.showMessageDialog(f, msg);

									}
								}

							}
						} else {
							msg = "User ID does not Exist";
							JOptionPane.showMessageDialog(f, msg);
						}
					} catch (Throwable exc) {
						System.out.println(exc);
					}

					// msg = "User ID does not Exist";
					// JOptionPane.showMessageDialog(f, msg);
				} else if (ae.getSource() == reg) {
					new Register();
					f.setVisible(false);
				}

			}
		}
		MyListener ml = new MyListener();
		b.addActionListener(ml);
		reg.addActionListener(ml);
		username.setBounds(225, 40, 200, 40);
		password.setBounds(225, 130, 200, 40);
		t.setBounds(225, 80, 200, 50);
		p.setBounds(225, 170, 200, 50);
		b.setBounds(240, 240, 100, 30);

		f.setVisible(true);
	}
}