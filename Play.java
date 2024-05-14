import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Play extends JFrame implements ActionListener {
    public static void main(String[] args) throws Exception {
        new Play();
    }

    private JButton button1, button2, button3, button4;
    private JTextField text1;
    private JLabel label1;
    public String word;
    public String wordlist[] = new String[255];
    public int numwords = 0;

    public Play() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("Word.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                wordlist[numwords++] = str;
            }
            in.close();
        } catch (IOException e) {
        }
        this.setSize(800, 800);
        this.setTitle("play");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new GridLayout(9, 3));
        button1 = new JButton("Enter");
        button2 = new JButton("Main Menu");
        button3 = new JButton("Exit");
        button4 = new JButton("Get the answer!");
        label1 = new JLabel("");
        label1.setFont(new Font("Arial", Font.ITALIC, 36));
        text1 = new JTextField(40);
        JLabel j1 = new JLabel("Word to be scrambled:");
        j1.setFont(new Font("Arial", Font.ITALIC, 20));
        JLabel j2 = new JLabel("Your Guess :");
        j2.setFont(new Font("Arial", Font.ITALIC, 36));

        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel("                          Word Scramble"));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(j1);
        panel.add(label1);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(j2);
        panel.add(text1);
        panel.add(button1);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(button2);
        panel.add(button4);
        panel.add(button3);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        getnewword();
        this.add(panel);
        this.setVisible(true);
        text1.requestFocus();
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button1) {
            String text = text1.getText();
            if (text.equals("")) {
                JOptionPane.showMessageDialog(this, "Come on, guess something");
            } else if (text.equalsIgnoreCase(word)) {
                JOptionPane.showMessageDialog(this, "Correct!");
                getnewword();
            } else {
                JOptionPane.showMessageDialog(this, "Wrong");
            }
        } else if (event.getSource() == button2) {
            this.setVisible(false);
            new mainMenu();
        } else if (event.getSource() == button3) {
            new Login();
            this.setVisible(false);
        } else if (event.getSource() == button4) {
            JOptionPane.showMessageDialog(this, "Correct word : " + word);
            getnewword();
        }
        text1.setText("");
        text1.requestFocus();
    }
    //  to generate random number
    public void getnewword() {
        int rndword;
        Random randGen2 = new Random();
        rndword = randGen2.nextInt(numwords);
        word = wordlist[rndword].replace(" ", "");
        label1.setText(scramble(word.toLowerCase()));
    }

    public static String scramble(String wordtoscramble) {
        String newword = "";
        int rndnum;
        Random randGen = new Random();
        boolean letter[] = new boolean[wordtoscramble.length()];
        do {
            rndnum = randGen.nextInt(wordtoscramble.length());
            if (letter[rndnum] == false) {
                newword = newword + wordtoscramble.charAt(rndnum);
                letter[rndnum] = true;
            }
        } while (newword.length() < wordtoscramble.length());
        return newword;
    }
}