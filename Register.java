import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Register {
    public static void main(String[] args) {
        new Register();
    }

    public Register() {
        JFrame f = new JFrame("Registration");
        f.setSize(800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Serif", 1, 20);

        // Rigister
        JLabel reg = new JLabel("Register");
        reg.setBounds(330, 25, 150, 50);
        reg.setFont(new Font("Serif", 1, 30));
        f.add(reg);

        // name
        JLabel n = new JLabel("Name :");
        n.setFont(font);
        n.setBounds(200, 150, 100, 50);
        JTextField name = new JTextField();
        name.setBounds(320, 150, 200, 50);
        f.add(n);
        f.add(name);

        // username
        JLabel u = new JLabel();
        u.setText("Username :");
        u.setFont(font);
        u.setBounds(200, 210, 100, 50);
        JTextField user = new JTextField();
        user.setBounds(320, 210, 200, 50);
        f.add(u);
        f.add(user);

        // password
        JLabel p = new JLabel();
        p.setText("Password :");
        p.setFont(font);
        p.setBounds(200, 270, 100, 50);
        JPasswordField pass = new JPasswordField();
        pass.setBounds(320, 270, 200, 50);
        f.add(p);
        f.add(pass);

        // Mobile
        JLabel m = new JLabel();
        m.setText("Mobile No. :");
        m.setFont(font);
        m.setBounds(200, 330, 110, 50);
        JTextField mob = new JTextField();
        mob.setBounds(320, 330, 200, 50);
        f.add(m);
        f.add(mob);

        // Submit
        JButton sub = new JButton("Submit");
        sub.setFont(font);
        sub.setBounds(300, 450, 100, 50);
        f.add(sub);
        class MyListener implements ActionListener {
            public void actionPerformed(ActionEvent ae) {
                    char[] pasword=pass.getPassword();
                    String passw=String.valueOf(pasword);
                    if (name.getText().equals("") || user.getText().equals("") || passw.equals("") || mob.getText().equals("")) {
                        JOptionPane.showMessageDialog(f, "You must fill in all the fields.");
                    } else {
                        String na = name.getText();
                        String id = user.getText();
                        char [] pas=pass.getPassword();
                        String pa = String.valueOf(pas);
                        String mobi = mob.getText();

                        // Save the Question in Database

                        try {
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/STC", "root","SYSTEM");
                            PreparedStatement pst = con.prepareStatement("insert into logindetails values (?, ?, ?, ?)");
                            PreparedStatement ps2 = con.prepareStatement("select * from logindetails where userid=?");
                            ps2.setString(1, id);
                            ResultSet rs = ps2.executeQuery();
                            if (rs.next()==true) {
                                
                                
                                rs.beforeFirst();
                                while (rs.next()) {
                                    String eid = rs.getString(1);
                                    if (eid.equalsIgnoreCase(id)) {
                                        JOptionPane.showMessageDialog(f,id + "already exists please choose unique user id");
                                        break;
                                    }
                                    //  else {
                                    //     System.out.println("i am in else");
                                    //     pst.setString(1, id);
                                    //     pst.setString(2, pa);
                                    //     pst.setString(3, na);
                                    //     pst.setString(4, mobi);

                                    //     pst.executeUpdate();
                                    //     JOptionPane.showMessageDialog(f, "Thank you for Registering " + na);
                                    //     name.setText("");
                                    //     pass.setText("");
                                    //     user.setText("");
                                    //     mob.setText("");
                                    //     // new Login();
                                    //     f.setVisible(false);
                                    //     break;
                                    // }

                                }

                            }
                            else {

                            pst.setString(1, id);
                            pst.setString(2, pa);
                            pst.setString(3, na);
                            pst.setString(4, mobi);

                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(f, "Thank you for Registering " + na);
                            name.setText("");
                            pass.setText("");
                            user.setText("");
                            mob.setText("");
                            new Login();
                            f.setVisible(false);
                            }

                        } catch (Throwable ex) {
                            System.out.println(ex);
                        }
                    }

            }

        }
        MyListener ml = new MyListener();
        sub.addActionListener(ml);

        f.setVisible(true);
    }
}
