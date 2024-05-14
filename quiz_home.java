import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class quiz_home {
	private static ResultSet rk = null;
	private static int Res = 0;
	static int[] drawNum = new int[10];
	public static int count = 0;

	public static void main(String[] rk) {
		new quiz_home();
	}

	// to generate random numbers without repetition
	public static void getqid() {

		int MAX = 107;

		for (int i = 0; i < MAX; i++) {
			drawNum[0] = (int) (Math.random() * MAX) + 0;

			while (drawNum[1] == drawNum[0]) {
				drawNum[1] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[2] == drawNum[0]) || (drawNum[2] == drawNum[1])) {
				drawNum[2] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[3] == drawNum[0]) || (drawNum[3] == drawNum[1]) || (drawNum[3] == drawNum[2])) {
				drawNum[3] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[4] == drawNum[0]) || (drawNum[4] == drawNum[1]) || (drawNum[4] == drawNum[2])
					|| (drawNum[4] == drawNum[3])) {
				drawNum[4] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[5] == drawNum[0]) || (drawNum[5] == drawNum[1]) || (drawNum[5] == drawNum[2]) ||
					(drawNum[5] == drawNum[3]) || (drawNum[5] == drawNum[4])) {
				drawNum[5] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[6] == drawNum[0]) || (drawNum[6] == drawNum[1]) || (drawNum[6] == drawNum[2]) ||
					(drawNum[6] == drawNum[3]) || (drawNum[6] == drawNum[4]) || (drawNum[6] == drawNum[5])) {
				drawNum[6] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[7] == drawNum[0]) || (drawNum[7] == drawNum[1]) || (drawNum[7] == drawNum[2])
					|| (drawNum[7] == drawNum[3]) ||
					(drawNum[7] == drawNum[4]) || (drawNum[7] == drawNum[5]) || (drawNum[7] == drawNum[6])) {
				drawNum[7] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[8] == drawNum[0]) || (drawNum[8] == drawNum[1]) || (drawNum[8] == drawNum[2]) ||
					(drawNum[8] == drawNum[3]) || (drawNum[8] == drawNum[4]) || (drawNum[8] == drawNum[5]) ||
					(drawNum[8] == drawNum[6]) || (drawNum[8] == drawNum[7])) {
				drawNum[8] = (int) (Math.random() * MAX) + 0;
			}
			while ((drawNum[9] == drawNum[0]) || (drawNum[9] == drawNum[1]) || (drawNum[9] == drawNum[2])
					|| (drawNum[9] == drawNum[3]) ||
					(drawNum[9] == drawNum[4]) || (drawNum[9] == drawNum[5]) || (drawNum[9] == drawNum[6])
					|| (drawNum[9] == drawNum[7]) ||
					(drawNum[9] == drawNum[8])) {
				drawNum[9] = (int) (Math.random() * MAX) + 0;
			}

		}
	}

	// to fetch question from database

	public static ResultSet getques(String s) {
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STC", "root", "SYSTEM");
			PreparedStatement ps = con.prepareStatement("select * from questionbank where qid = ?");

			ps.setString(1, s);

			rs = ps.executeQuery();
			return rs;
		} catch (Throwable ex) {
			System.out.println(ex);
		}
		return rs;
	}

	quiz_home() {

		getqid();
		String s = String.valueOf(drawNum[count]);
		try {
			rk = getques(s);
			rk.next();
		} catch (Throwable ex) {
			System.out.println(ex);
		}

		JFrame f = new JFrame("Attempt quiz");
		f.setSize(1000, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.getContentPane().setBackground(Color.white);
		f.setLayout(null);

		Font plain = new Font("Serif", 0, 30);
		Font bold1 = new Font("Serif", 1, 30);
		Font bold = new Font("Serif", 1, 20);

		// question count
		int number = count + 1;
		JLabel qcount = new JLabel();
		qcount.setText((number) + "/10");
		qcount.setBounds(600, 50, 100, 50);
		qcount.setFont(new Font("Serif", 1, 40));
		f.add(qcount);

		JLabel question = new JLabel("Question statement goes here...");
		question.setFont(bold);
		JRadioButton opa = new JRadioButton("Option a");
		opa.setFont(plain);
		JRadioButton opb = new JRadioButton("Option b");
		opb.setFont(plain);
		JRadioButton opc = new JRadioButton("Option c");
		opc.setFont(plain);
		JRadioButton opd = new JRadioButton("Option d");
		opd.setFont(plain);

		JButton next = new JButton("NEXT");
		next.setFont(bold);
		JButton restart = new JButton("RESTART");
		restart.setFont(bold);
		JButton clear = new JButton("CLEAR");
		clear.setFont(bold);
		JButton end = new JButton("END TEST");
		end.setFont(bold);

		// Submit button
		JButton submit = new JButton("SUBMIT");
		submit.setFont(bold);
		submit.setBounds(450, 700, 150, 40);
		submit.setVisible(false);
		f.add(submit);

		end.setBounds(40, 700, 150, 40);
		restart.setBounds(250, 700, 150, 40);
		next.setBounds(450, 700, 150, 40);
		clear.setBounds(630, 700, 150, 40);
		f.add(next);
		f.add(clear);
		f.add(end);
		f.add(restart);

		f.add(opa);
		opa.setBounds(180, 300, 500, 50);
		f.add(opb);
		opb.setBounds(180, 370, 500, 50);
		f.add(opc);
		opc.setBounds(180, 440, 500, 50);
		f.add(opd);
		opd.setBounds(180, 510, 500, 50);
		opa.setBackground(Color.white);
		opa.setForeground(Color.BLACK);
		opa.setFont(plain);
		opb.setFont(plain);
		opc.setFont(plain);
		opd.setFont(plain);
		opb.setBackground(Color.WHITE);
		opb.setForeground(Color.BLACK);
		opc.setBackground(Color.WHITE);
		opc.setForeground(Color.BLACK);
		opd.setBackground(Color.white);
		opd.setForeground(Color.BLACK);

		ButtonGroup bg = new ButtonGroup();
		bg.add(opa);
		bg.add(opb);
		bg.add(opc);
		bg.add(opd);

		f.add(question);
		question.setBounds(100, 100, 800, 200);
		question.setFont(bold1);
		question.setForeground(Color.black);
		try {

			String q = rk.getString(1);
			String a = rk.getString(2);
			String b = rk.getString(3);
			String c = rk.getString(4);
			String d = rk.getString(5);
			// String ans = rs.getString(6);
			question.setText(q);
			opa.setText(a);
			opb.setText(b);
			opc.setText(c);
			opd.setText(d);
			opa.setActionCommand(a);
			opb.setActionCommand(b);
			opc.setActionCommand(c);
			opd.setActionCommand(d);
		} catch (Throwable exc) {
			System.out.println(exc);
		}

		// Event Handling

		class MyListener implements ActionListener, ItemListener {
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() == clear) {
					bg.clearSelection();
				} else if (ae.getSource() == end) {
					JOptionPane.showMessageDialog(f, "Your Score is:  " + Res + "/10");
					// count = 0;
					// Res=0;
					new quizMain();
					f.setVisible(false);

					// display the result page
				} else if (ae.getSource() == next) {
					try {

						String answer;
						String ans = rk.getString(6);
						if (bg.getSelection() == null) {
							JOptionPane.showMessageDialog(f, "Please, Chose options from next Question");
						} else {
							answer = bg.getSelection().getActionCommand();
							if (answer.equals(ans)) {
								Res += 1;
								JOptionPane.showMessageDialog(f, "Correct Answer");
							} else {
								JOptionPane.showMessageDialog(f,
										"              Wrong Answer!" + "\n Correct Answer was: " + ans);
							}
						}
						bg.clearSelection();
						count++;
						if (count == 9) {
							next.setVisible(false);
							submit.setVisible(true);
						}
						int number = count + 1;
						qcount.setText(number + "/10");
						String s = String.valueOf(drawNum[count]);
						rk = getques(s);
						rk.next();
						String q = rk.getString(1);
						String a = rk.getString(2);
						String b = rk.getString(3);
						String c = rk.getString(4);
						String d = rk.getString(5);
						question.setText(q);

						opa.setText(a);
						opb.setText(b);
						opc.setText(c);
						opd.setText(d);
						opa.setActionCommand(a);
						opb.setActionCommand(b);
						opc.setActionCommand(c);
						opd.setActionCommand(d);

					} catch (Throwable exc) {
						System.out.println(exc);
					}
				} else if (ae.getSource() == restart) {
					JOptionPane.showMessageDialog(f, "Your Score is:  " + Res + "/10");
					// Res = 0;
					// count = 0;
					new quiz_home();
					f.setVisible(false);

				} else if (ae.getSource() == submit) {
					try {
						String answer;
						String ans = rk.getString(6);
						if (bg.getSelection() == null) {
							JOptionPane.showMessageDialog(f, "You have not chosen the Answer!");
						} else {
							answer = bg.getSelection().getActionCommand();
							if (answer.equals(ans)) {
								Res += 1;
								JOptionPane.showMessageDialog(f, "Correct Answer");
							} else {
								JOptionPane.showMessageDialog(f,
										"              Wrong Answer!" + "\n Correct Answer was: " + ans);
							}
						}
						bg.clearSelection();
					} catch (Throwable exc) {
						System.out.println(exc);
					}
					JOptionPane.showMessageDialog(f, "Your Score is:  " + Res + "/10");
					Res = 0;
					count = 0;
					new quizMain();
					f.setVisible(false);
				}
			}

			public void itemStateChanged(ItemEvent e) {

			}
		}

		MyListener ml = new MyListener();
		end.addActionListener(ml);
		clear.addActionListener(ml);
		restart.addActionListener(ml);
		next.addActionListener(ml);
		submit.addActionListener(ml);

		opa.addItemListener(ml);
		opb.addItemListener(ml);
		opc.addItemListener(ml);
		opd.addItemListener(ml);

		f.setVisible(true);
	}
}