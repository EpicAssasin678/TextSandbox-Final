
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import Characters.Character;
import Mechanics.items.*;
import Bin.*;


public class App {

 
    
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
        

        //!THIS WORKS BUT NO COLOR
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p1 = runtime.exec("C:\\Users\\epicd\\OneDrive\\Documents\\Java Projects\\TextSandbox\\src\\coloroutput");
            InputStream is = p1.getInputStream();
            int i = 0;
            while( (i = is.read() ) != -1) {
                System.out.print((char)i);
            }
        } catch(IOException ioException) {
            System.out.println(ioException.getMessage() );
        }



    }


    //NEEDS FIX
    //make it static but return type character 
    public static void createNewCharacter(String name) {
        System.out.println("\nCharacter Creation: ");
        PLAYER = new Character(name, 1, Character.CHARACTER_DEFAULT_EXP, Character.CHARACTER_DEFAULT_HEALTH);
        PLAYER.displayCharacterStats();
    }

    
    
    //constructor for new App object
    //game screen options basically, but hosts field for JFrame object "window"
    
}





// RANDOM TESTS



        //randomDamage test
        //enemy.attack(test, 90);

        // Scanner prompt1 = new Scanner(System.in);
        //System.out.println("Enter in: ");
        // String playername = prompt1.nextLine();
        
        
        //fix this so that we can implement into character class
        //createNewCharacter(playername);
        //prompt1.close();

        //enemy.attack(PLAYER, 20);
        //System.out.println(PLAYER);

        //Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start C:\\Users\\epicd\\OneDrive\\Documents\\Java Projects\\TextSandbox\\src\\coloroutput.bat", " -s HEllo" });
        //src\coloroutput.bat
        /**
        String[] cmdstr = {"cmd.exe", "/C" , "coloroutput.bat"};
        Runtime.getRuntime().exec(cmdstr, null, new File("C:\\Users\\epicd\\OneDrive\\Documents\\Java Projects\\TextSandbox\\src\\"));
         */
        //String path = "cmd /c start C:\\Users\\epicd\\OneDrive\\Documents\\Java Projects\\TextSandbox\\src\\coloroutput";
        //Runtime currentRuntime = Runtime.getRuntime();
        //Process runproc = currentRuntime.exec(path);
