import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Characters.Character;

public class App {

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

    
    //  GLOBAL PLAYER CHARACTER
    static Character PLAYER;
    

    public static void main(String[] args) throws Exception {
        
        Character test = new Character("Zachery", 4, 20.0, 100);

        //enemy for test combat test purposes
        Character enemy = new Character("Enemy", 23, 8.0);
        System.out.println("Character Stats:");

        test.setDebug(1);
        test.displayCharacterStats();
        test.setExpToNextLevel(40.0);
        test.generateExpBar();
        test.displayXpStats();
        
        //randomDamage test
        enemy.attack(test, 90);

        Scanner prompt1 = new Scanner(System.in);
        System.out.println("Enter in: ");
        String playername = prompt1.nextLine();
        
        
        //fix this so that we can implement into character class
        createNewCharacter(playername);
        prompt1.close();

        enemy.attack(PLAYER, 20);
        System.out.println(PLAYER);

        
    }


    //NEEDS FIX
    public static void createNewCharacter(String name) {
        System.out.println("\nCharacter Creation: ");
        PLAYER = new Character(name, 1, Character.CHARACTER_DEFAULT_EXP, Character.CHARACTER_DEFAULT_HEALTH);
        PLAYER.displayCharacterStats();
    }

    



    //constructor for new App object
    //game screen options basically, but hosts field for JFrame object "window"
    
    
    
    
    public App () {

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
