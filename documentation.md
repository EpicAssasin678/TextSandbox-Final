### initConversation v1.0

    public void initConversation (String getDialogueID) throws Exception {

        //sets the reference dialogue key for the later reply methods
        conversationIdentifier = getDialogueID;
        //go from object to JSONObject
        Object dialogueIn = parser.parse(new FileReader("src\\Bin\\Conversations.json"));
        JSONObject jDialogue1 = (JSONObject) dialogueIn;

        //casted the jDialogue1 into a new type of input and prints dialogueOut
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
        //used for event options
        for (int i = 0; i < dialogueOptions.size(); i++) {

            JSONObject option = (JSONObject) dialogueOptions.get(i);

            //DEBUG System.out.println("ELEMENT IS: " + dialogueOptions.get(i));
            String optionStr = (String) option.get("option" + Integer.toString(i + 1));

            if (option.containsKey("option" + Integer.toString(i + 1))  ) {

                System.out.println("[" + (i + 1) + "] " + optionStr);

            } else {

                System.out.println("NULL ERROR IN OPTIONSTR IN LINE 70");
            }
            

            /** DEBUG 
            *  System.out.println("DEBUG " + "option" + Integer.toString(i + 1));
            *    System.out.println("Found " + optionStr);
            */
            
            //check last JSONObject in Array for the key "BREAKPOINT"
            if (i + 1 == dialogueOptions.size() && dialogueOptions.size() != 0) {
                System.out.println("\n");
            }

        }
        
        //reply section
        Scanner userPrompt = new Scanner(System.in);
        int userReply = userPrompt.nextInt();
        //set instanceVar replyInt to the response        
        replyInt = userReply;         
        //close userPrompt
        userPrompt.close();

        //getting replies array
        JSONArray dialogueReplies =  (JSONArray) jDialogue1.get(getDialogueID.concat("Replies"));
        System.out.println(dialogueReplies);

        //if a reply option exists, it will then print the reply
        if (dialogueReplies != null) {

            //break check
            if (replyInt == dialogueOptions.size()) {
                //set breakpoint trigger to true if chosen last option by default
                breakPointTriggered = true;
                System.out.println("Debug: Breakpoint event triggered.");
                
            }

            
            
            System.out.println("INITIATING REPLY");
            //index remember

            //making sure that this is a normal reply object
            JSONObject option = (JSONObject) dialogueReplies.get(replyInt - 1);
            if (option.size() == 1) {

                System.out.println(option);
                String replyString = (String) option.get("option" + Integer.toString(replyInt));
                System.out.println("\n" + replyString);

            //second for more replies
            } else if (option.size() > 1) {

                System.out.println(option);
                String replyString = (String) option.get("option" + Integer.toString(replyInt));
                System.out.println("\n" + replyString);

                //recursive element to make sure that this continues to work

                if (option.containsKey("option" + Integer.toString(replyInt))   ) {

                }
            }
            

            
            
            //reset the breakpoint 
            breakPointTriggered = false;
        } 


    }
