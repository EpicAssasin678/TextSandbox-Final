package Tools;

public class CodeTools  {

    public int debugMode;

    //print message with less words
    public static void println(String message) {
        System.out.println(message);
    }


    //prints message if debug on
    public void debugMsg(String message) {
        if (debugMode == 0) {
            println(message);
        }
    }

    public void printlnTitle(String titleMessage) {
        println("\n==============");
        println(titleMessage);
        println("==============");
    }

    
}