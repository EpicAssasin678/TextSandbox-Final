# Potential Functions
## The generate experience bar tool:
    

    public void generateExperienceBar() {
        for (int i = 0; i < 10; i++) {

            //add one ahead because of index val
            this.expBar.charAt(i + 1) = "*";
            
        }
    }
	
## Ideas For Inspiration:
https://www.youtube.com/watch?v=G5yr4sekAI0

https://www.youtube.com/watch?v=-6l7LK8kaUk

-made a frame for ui

## Example of Editable JFrame:
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Editpad {

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                // the GUI as seen by the user (without frame)
                JPanel gui = new JPanel(new BorderLayout());
                gui.setBorder(new EmptyBorder(2,3,2,3));

                // adjust numbers for a bigger default area
                JTextArea editArea = new JTextArea(5,40);
                // adjust the font to a monospaced font.
                Font font = new Font(
                        Font.MONOSPACED, 
                        Font.PLAIN, 
                        editArea.getFont().getSize());
                editArea.setFont(font);
                gui.add(new JScrollPane(editArea,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

                JFrame f = new JFrame("Editpad");
                f.add(gui);
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See http://stackoverflow.com/a/7143398/418556 for demo.
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // should be done last, to avoid flickering, moving,
                // resizing artifacts.
                f.setVisible(true);
            }
        };
        // Swing GUIs should be created and updated on the EDT
        // http://docs.oracle.com/javase/tutorial/uiswing/concurrency
        SwingUtilities.invokeLater(r);
    }
}

# Notes for Fixes 
TODO: fix the creat Character methods.=
TODO: make an interface for a game session

TODO: have the dialogue script pair a name with the character and have quotes around the thing

# Tutorials
How to [READ] JSON: https://www.youtube.com/watchv=cFCgFlqF5kw
How to WRITE JSON: https://www.youtube.com/watch?v=iMcOhnGA6Ao
JSON Simple: https://www.youtube.com/watch?v=U-5VHRvOFpA




## JSON Dialogue Map

    \Dialogue : "Dialogue"
    have the dialogue be numerical and map it correspondingly with it's response options
        \dialogueEvent01 : "Hello would you like to buys something here?"
        then the dialogueEvent will have options in an array which we then pass through a foreach loop
        \dialogueEvent01Options : {
            option1 : "Yes.",
            option2 : "No."
        },
        have these be the responses to these questions, with close on command options
        \dialogueEvent01Replies : [

        ]



### Structure

    Dialogue events will be ID'd by their number ex: dialogueEvent01
    Numerically the element options will be the ID + "Options"
        The array of the element options will have keys named "option" + whatever numerical order 
            EX: option1, option2, ...
        The reply can be an array, but ususlaly will not be.
        The dialogueEventReplies will follow the same structure

    To find the key for the loop it follows, it is index[key +1] 
    Each reply has a secondary array with a corresponding val, for example:

    dialogueEvent01Replies: [
        {"option1" [
            {"reply" : "Blah blah"},
            {"trigger" : 1}
        ]
        },
    ]

BREAKPOINTS 
    points at which the while() breaks
    for example the "no" option 
    the key would actually be "Break event"

### Looping Conversation Function
*METHOD 1* RECURSION

    When a conversation will loop like for example, when you talk to someone or ask a question and you don't want to end it, so you have an intermediary phase of dialogue. For example:

    [Dialogue1] "Hello, what do you want to ask me about?"
    [1] "I want to ask you what you know about the jam"
    [2] "I do not know."
    [3] "Bye"

    ENTERED IN: 1

    "The jam is homecrafted and the finest in the valley."

    [1] "Ok, thank you"
    [2] "Alright but what else?"

    If 2 would be chosen then:

    [Dialogue1] "Well, it is made with Africanized bee honey."

    [1] Cool.

    Which would go back to:

    [Dialogue1] "Hello, what do you want to ask me about?"
    [1] "I want to ask you what you know about the jam"
    [2] "I do not know."
    [3] "Bye"

### TICKER Idea
    The ticker integer can count the length of the options by incrementing per interval. To navigate
    mathematically, the layer integer will be able to count the layer made.

# Prompt System
## Prompt 


## Dialogue Depricated

package Bin;
import java.io.FileReader;
import java.lang.reflect.Type;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.List;
import java.util.ArrayList;

public class Dialogue {

    static JSONParser parser = new JSONParser();
    public boolean hasReply = false;
    public String conversationIdentifier;

    //empty constructor
    public Dialogue() {

    }
    
    public static String conversationOutput (String dialogue_id) {
        return dialogue_id;
    }
    
    //String getDialogueID to grab name from key selection
    public void initConversation (String getDialogueID) throws Exception {
        //sets the reference dialogue key for the later reply methods
        conversationIdentifier = getDialogueID;
        //go from object to JSONObject
        Object dialogueIn = parser.parse(new FileReader("src\\Bin\\Conversations.json"));
        JSONObject jDialogue1 = (JSONObject) dialogueIn;

        //casted the jDialogue1 into a new type of input 
        String dialogueOut = (String) jDialogue1.get(getDialogueID);
        System.out.println(dialogueOut);

        //instantiates a JSONArray obj from jDialogue obj
        JSONArray dialogueOptions = (JSONArray) jDialogue1.get(getDialogueID.concat("Options"));

        /** DEBUGS
        System.out.println("ARRAY NAME: " + getDialogueID.concat("Options"));
        System.out.println("ARRAY PAIRED: " + dialogueOptions);
        */
        
        System.out.println("\nYour Response: ");

        //for loop iterates over the elements in the array
        for (int i = 0; i < dialogueOptions.size(); i++) {

            JSONObject option = (JSONObject) dialogueOptions.get(i);

            //DEBUG System.out.println("ELEMENT IS: " + dialogueOptions.get(i));

            String optionStr = (String) option.get("option" + Integer.toString(i + 1));

            /** DEBUG 
            *  System.out.println("DEBUG " + "option" + Integer.toString(i + 1));
            *    System.out.println("Found " + optionStr);
            */

            
            System.out.println("[" + i + "] " + optionStr);

            if (i == dialogueOptions.size() && dialogueOptions.size() != 0) {
                System.out.println("\n");
            }
        }
        

    }

    public static void userReply () {

    }
    /**
     * new initConversation method
     * @param args
     * @throws Exception
     * 
     *  will pass in a dialogue id that is an identifier for the key value of the dialogue option
     *  the String value of the arguemnt can get concatenated and depending on structure a for each loop can iterate the next key's response
     * 
     */
    
    public static void main(String[] args) throws Exception {
        
        Dialogue rightNow = new Dialogue();
        rightNow.initConversation("dialogueEvent01");
        

        
    }

}