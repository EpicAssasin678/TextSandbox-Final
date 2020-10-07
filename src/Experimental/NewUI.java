package Experimental;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NewUI {

    //game window instance variables
    JFrame window;
    Container con;

    //title instance variables
    JPanel titleNamePanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Consolas", Font.PLAIN, 28);

    //Welcome instance variables
    JLabel welcomeNameLabel;

    //below title
    JPanel mainMenuPanel;
    //JLabel peterImage;
    
    public static void main(String[] args) {
        new NewUI();
    }


    public NewUI() {
        window = new JFrame();
        window.setSize(1920, 1080);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);
        window.setVisible(true);
        con = window.getContentPane();
        
        //title panel settings
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(600, 100, 300, 150);
        titleNamePanel.setBackground(Color.BLUE);

        //panel text settings
        titleNameLabel = new JLabel("Zach's SandBox");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);

        con.add(titleNamePanel);

        //welcome text 
        //have below title
        welcomeNameLabel = new JLabel("Welcome");
        welcomeNameLabel.setForeground(Color.WHITE);
        welcomeNameLabel.setFont(titleFont);
        titleNamePanel.add(welcomeNameLabel);

        //mainpanel 
        mainMenuPanel = new JPanel();
        mainMenuPanel.setBounds(600, (1920/2), 500, 900);
        



        //failure hurts me 
        /**
         *          peterImage = new JLabel("", img, JLabel.CENTER);
                    peterImage.setBounds(-400, -300, 200, 300);
                    window.add(peterImage);
                    peterImage.setVisible(true);
                    ImageIcon img = new ImageIcon("peterlicense.png");
         */

        
        

    }
}
