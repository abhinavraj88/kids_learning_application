import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class mainMenu extends JFrame implements ActionListener
{
    public static void main(String[] asd)
    {
        new mainMenu();
    }
    private JButton button1,button2,button3,button4;
    private JLabel label1;
    //private String[] words;

    public mainMenu()
    {
        this.setSize(800,800);
        this.setTitle("Main Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        

        this.setLayout(null);
        label1 = new JLabel("Main Menu");
        label1.setFont(new Font("Arial", 1, 28));
        button1 = new JButton("Jumbled word");
        button2 = new JButton("help?");
        button3 = new JButton("Exit");
        button4 = new JButton("Quiz");
        label1.setBounds(315, 50, 150, 50);
        button1.setBounds(90, 200, 200,200);
        button3.setBounds(660, 10, 100, 50);
        button2.setBounds(650, 650, 100, 50);
        button4.setBounds(500, 200, 200, 200);
        

        this.add(label1); 

        this.add(button1); 

        this.add(button2); 
        this.add(button3); 
        this.add(button4);
        
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == button3)
        {
            
            new Login();
            this.setVisible(false);
            
        }
        else if(event.getSource() == button2)
        {
            new Help();
            this.setVisible(false);
        }
        else if(event.getSource() == button1)
        {
            new Level();
            this.setVisible(false);
        }else if(event.getSource()==button4)
        {
            new quizMain();
            this.setVisible(false);
        }
    }
}

class Help extends JFrame implements ActionListener
{
    public static void main(String[] asd) throws Exception
    {
        new Help();
    }
    private JButton button;
    
    public Help()
    {
        this.setSize(500,300);
        this.setTitle("Help");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        button = new JButton("   OK   ");
        panel.add(new JLabel("                                       Help                                       "));
        panel.add(new JLabel("You will get a word, present in the Oxford Dictionary, whose letters are dearranged."));
        panel.add(new JLabel("You have to arrange those letters to form a word which is in the Oxford Dictionary."));
        panel.add(new JLabel("You can guess the word three times before the answer is displayed."));
        panel.add(new JLabel("The first alphabet of the word is given in Capital."));
        panel.add(new JLabel(" There are two levels in the game - EASY and HARD."));
        panel.add(new JLabel(" EASY  :  In this level you will get small words."));
        panel.add(new JLabel("                                    HARD  :  In this level you will get long words.                    "));
        panel.add(button);
        this.add(panel);
        button.addActionListener(this);
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == button)
        {
            this.setVisible(false);
            new mainMenu();
        }
    }
}