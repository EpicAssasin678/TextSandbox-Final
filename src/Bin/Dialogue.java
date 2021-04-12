package Bin;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.util.Scanner;


public class Dialogue {

    //instance variables
    static JSONParser parser = new JSONParser();
    public boolean hasReply = false;
    public boolean breakPointTriggered = false;
    public String breakPointStr;
    public int breakPointIndex;
    public String conversationIdentifier;
    public int replyInt;
    public boolean breaknow = false;
    
    //empty constructor
    public Dialogue() {

    }
    
    // instance for TICK level
    public int TICKER;

    /**
     * 
     * @param ticker
     */
    public void setTICKER (int ticker) {

        this.TICKER = ticker;
    
    }

    public Integer getTICKER () {
        return this.TICKER;
    }

    public int LAYER;
    //for realoding backwards
    public int prevLAYER;

    public void setLAYER (int layer) {

        this.LAYER = layer;

    }

    public Integer getLAYER () {

        return this.LAYER;

    }

    public void saveLAYER (int saveLAYER) {

        prevLAYER = saveLAYER;
        //reset the layer
        // debug System.out.println("DEBUG: LAYER SAVED TO prevLAYER, SETTING CURRENT TO 0")
        LAYER = 0;

    } 

    public void switchHasReply (boolean setto) {
        hasReply = setto;
    }

    public void setBreakPointTriggered (boolean setto) {
        breakPointTriggered = setto;
    }

    public Boolean checkBreakPoint () {
        return breakPointTriggered;
    }
    
    public void storeBreakPointString (String key) {
        breakPointStr = key;
    }

    /**
     * Prints objects from array that have the desired keyFilter (String) + the index + 1.
     * Keyfilter is a String type
     * @apiNote the for loop, iterating over the length of 'arr', Reply is set to the JSONObject at the index of int i
     * @apiNote is set to Reply at a key equal to keyFilter concatenated with int i
     * @param arr
     * @param Reply
     * @param keyFilter
     */
    public void printKeyFilteredList (JSONArray arr, JSONObject Reply, String keyFilter) throws Exception {

        //set instance variables equal to a value
        //System.out.println("\nprintKeyFilteredList function called, method was called as: " + "\ngetFeedback( " + arr + ", " + Reply + ", " + keyFilter + ") \n" );

        //System.out.println(arr);
        
        for (int i = 0; i < arr.size(); i++) {
            
            Reply = (JSONObject) arr.get(i);
            String replyStr = (String) Reply.get(keyFilter + Integer.toString(i + 1));

            //debug
            //System.out.println(Reply);
            //System.out.println(replyStr);
            //System.out.println(i);

            if (replyStr != null) {

                //standard to print the options
                //debug System.out.println( arr.get(i) );
                System.out.println("[" + (i + 1) + "] " + replyStr);

            } else if (Reply.get("LAYER") != null) {

                // since JSON.simple stores values as long in this case at least, obj will cast to long
                long foundLayer = (long) Reply.get("LAYER");
                // takes the long foundlayer and printlns toSting()
                System.out.println("DEBUG: LAYER HAS ADVANCED, SETTING LAYER TO " + Long.toString(foundLayer));
                break;

            } else if (Reply.get("breakpoint") != null) {

                //sets replyStr to a breakpoint 
                replyStr = (String) Reply.get("breakpoint");
                storeBreakPointString(replyStr);
                //System.out.println("breakpoint key encountered, storing " + replyStr);
                //checking for breakpoint would be if replyInt 

            } else {

                //System.out.println("NULL FOUND BREAKING");
                break;

            }
        }
    }

    /**
     * 
     * @category print
     * @param arr
     * @param Reply
     * @param keyFilter
     * @throws Exception
     */
    public void printCertainObject (JSONArray arr, JSONObject Reply, String keyFilter, int val) throws Exception {

        //System.out.println("\nprintCertainObject function called, method was called as: " + "\ngetFeedback(" + arr + ", " + Reply + ", " + keyFilter + ", " + Integer.toString(val) + ") \n");

        
        //set instance variables equal to a value
        Reply = (JSONObject) arr.get(val - 1);
        String replyStr = (String) Reply.get(keyFilter.concat(Integer.toString(val)));

        //System.out.println("Looking for obj: " + replyStr);
        System.out.println(replyStr);
        
    }

    
    Scanner userPrompt;
    
    /**
     * gerates Scanner and saves inout to replyInt
     * @apiNote sitched hasReply to true
     * might make an if state
     */
    public void generateUserPrompt () {

        //System.out.println("USER PROMPT GENERATE");

        System.out.println("\nYour Response: ");
        userPrompt = new Scanner(System.in);
        int userReply = userPrompt.nextInt();

        //set instanceVar replyInt to the response        
        replyInt = userReply;   
            
        //close userPrompt
        switchHasReply(true);

    }

    public void checkUserPrompt() {

        
    }
    
    /**
     * 
     * @param arr
     * @param Feedback
     * @param ReplyInt
     * @throws Exception
     */
    public void getFeedback(JSONArray arr, JSONObject Feedback, int ReplyInt) throws Exception{
        
        //debug
        //System.out.println("\ngetFeedback function called, method was called as: " + "\ngetFeedback(" + arr + ", " + Feedback + ", " + Integer.toString(ReplyInt) + ") \n");

        //int FeedbackIndex = arr.indexOf(Feedback);
        //! Have not made a solution to correlate with the LAYER it was chosen from 
        //System.out.println("Key value looked for is: feedback" + Integer.toString(ReplyInt));
        printCertainObject(arr, Feedback, "feedback", ReplyInt);

    }

    int TARGET = 4;
    
    //String DialogueID to grab name from key selection
    JSONObject option;
    JSONObject feedback;
    int loopCount = 0;
    

    /**
     * ? big issues with recursion as I see no way of making another initConversation
     * @param DialogueID
     * @throws Exception
     */
    public void initConversation (String DialogueID) throws Exception {

        
        //sets the reference dialogue key for the later reply methods
        if (loopCount <= 0) {
            conversationIdentifier = DialogueID;
            setLAYER(0);

        }
        //go from object to JSONObject
        //just prints out the begginging quote
    
        Object dialogueIn = parser.parse(new FileReader("src\\Bin\\Json\\Conversations.json"));
        JSONObject jDialogue1 = (JSONObject) dialogueIn;
        String dialogueOut = (String) jDialogue1.get(DialogueID);

        //made sure to print on on first time of loop or on the first menu
        if (loopCount <= 0 || getLAYER() == 0) {
            System.out.println(dialogueOut);
        }
        
        loopCount++;
        //System.out.println("LOOPING\n" + "LOOPED " + loopCount + " TIMES\n" + "LAYER = " + LAYER);
        
        //**Past this point is where initConversation() might end, should only parse the JSON file and that is it */
        //instantiates a JSONArray obj from jDialogue obj
        JSONArray dialogueOptions = (JSONArray) jDialogue1.get(DialogueID.concat("Options"));
        

        //? could also set equal to dialogueOptions.get(dialogueOptions.indexOf(this.TICKER))
        //? which in turn, could be used for retrieval of other layers through number manipulation
        //depricated JSONArray dialogueOptionsLayer = (JSONArray) dialogueOptions.get(dialogueOptions.indexOf(0));
        JSONObject dialogueOptionsLayer = (JSONObject) dialogueOptions.get(getLAYER());
        JSONArray optionsLayerOut = (JSONArray) dialogueOptionsLayer.get("LAYER".concat(Integer.toString(getLAYER())));

        //System.out.println("\nDEBUG: LAYER" + getLAYER() + " INFORMATION IS "  + optionsLayerOut + "\n");

        /** DEBUG
        System.out.println("ARRAY NAME: " + DialogueID.concat("Options"));
        System.out.println("ARRAY PAIRED: " + dialogueOptions);
        */
        printKeyFilteredList(optionsLayerOut, option, "option");

        generateUserPrompt();

        //checks if the breakpoint has been captured
        //? how to go backwards in conversation though?
        if (breakPointStr.equals("option".concat(Integer.toString(replyInt) ))) {
            
            //System.out.println("Entered in breakpoint");
            //if so, then decrements the layer;
            setBreakPointTriggered(true);

        }

        //breakpoint check system
        //System.out.println("BREAKPOINT TRIGGERED: " + breakPointTriggered);

        if (breakPointTriggered == true ) {

            if (LAYER == 0) {

                LAYER--;
                //System.out.println("\nNegativeLayer set.");
                breaknow = true;

            } else {
                
                LAYER--;
            }

            
            //System.out.println("breakPoint was triggered, layer is subtracted by 1. \nLayer is now " + getLAYER());
        }
        
        if (breaknow != true) {
            System.out.println("\nREPLY INTEGER IS SET TO: " + replyInt);
            //reply section, moves through JSON data to print feedback arrays
            
            JSONArray dialogueFeedback = (JSONArray) jDialogue1.get(DialogueID.concat("Feedback"));
            JSONObject dialogueFeedbackLayer = (JSONObject) dialogueFeedback.get(getLAYER());
            JSONArray feedbackLayerOut = (JSONArray) dialogueFeedbackLayer.get("LAYER".concat(Integer.toString(getLAYER())));
            
            //String feedbackID = (String) optionsLayerOut.get(replyInt - 1).toString();
            //System.out.println("DEBUG: Feedback layer has been resolved to: " + feedbackID + "\n");
            //System.out.println("DEBUG: Feedback array found to be: " + dialogueFeedback );
            //System.out.println("DEBUG: Feedback LAYER extracted to be: " + dialogueFeedbackLayer);
            //System.out.println("DEBUG: LAYER Array extracted to be: " + feedbackLayerOut + "\n");

            if (breakPointTriggered == false ) {
                
                //System.out.println("\nBreakpoint not triggered."); 
                
                LAYER++;
                //System.out.println("\nLayer is now " + LAYER);
                getFeedback(feedbackLayerOut, feedback, replyInt);

            }
        }
        
        //recursion control
        if (loopCount <= TARGET && breaknow == false) {

            //resets breakpoint
            breakPointTriggered = false;
            initConversation(DialogueID);


        } else {

            userPrompt.close();  

        }
        //      debug
        //debug System.out.println(option.size());
        //debug System.out.println(dialogueOptions.get(userReply - 1));
        //debug System.out.println(option.get("reply1"));
        //debug System.out.println("Looking for the object " + "reply" +  dialogueOptions.indexOf(replyInt) );
        //debug System.out.println(option.containsKey("reply" + dialogueOptions.indexOf(replyInt)) );
        
        //getting replies array

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
        //rightNow.initDialogue("dialogueEvent01");
        rightNow.initConversation("dialogueEvent01");

    }

    
}