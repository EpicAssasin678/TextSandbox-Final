import java.util.Scanner;

/**

 */
public class Adventure {
    // instance variables - replace the example below with your own
    static Scanner charCreate = new Scanner(System.in);

    /**
     * Constructor for objects of class Adventure
     */
    public Adventure() {
        // initialise instance variables

    }

    
    public int debugMode;

    public void setDebug(int debugMode) {
        this.debugMode = debugMode;
        if (debugMode == 1) {
            System.out.println("debug on");
        }
    }

    // tester
    public static void main(String[] args) {


        
    }
    
}
