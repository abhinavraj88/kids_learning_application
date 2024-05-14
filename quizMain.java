import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class quizMain {
    public static void main(String[] args) {
        new quizMain();
    }
    quizMain()
    {
        JFrame f=new JFrame("Quiz");
        f.setSize(800,800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
		f.setLayout(null);
		f.getContentPane().setBackground(Color.WHITE);
        Font font = new Font("Serif", 1, 20);

        //play
        JButton play=new JButton("Play");
        play.setFont(font);
        play.setBounds(330, 200, 150, 80);
        f.add(play);

        //Main menu
        JButton main=new JButton("Main Menu");
        main.setFont(font);
        main.setBounds(330, 300, 150, 80);
        f.add(main);

        //exit
        JButton exit=new JButton("Exit");
        exit.setFont(font);
        exit.setBounds(330, 400, 150, 80);
        f.add(exit);
        
        class MyListener implements ActionListener
        {
            public void actionPerformed(ActionEvent ae)
            {
                if(ae.getSource() == play)
                {
                // System.exit(0);
                    new quiz_home();
                    f.setVisible(false);
                    
                }
                else if(ae.getSource() == main)
                {
                    new mainMenu();
                    f.setVisible(false);
                    
                }
                else if(ae.getSource() == exit)
                {
                    System.exit(0);
                }
            }
            
        }
        MyListener ml = new MyListener();
        play.addActionListener(ml);
        main.addActionListener(ml);
        exit.addActionListener(ml);
        f.setVisible(true);
    }
}
