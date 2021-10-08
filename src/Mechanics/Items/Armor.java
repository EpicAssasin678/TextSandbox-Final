package Mechanics.items;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Armor extends Item {

    public String name;
    
    JSONParser parser = new JSONParser();
    JSONObject armorFile;
    ItemType itemType = ItemType.ARMOR;
    


    public Armor (String name) {
        this.name = name;
        
    }

    public Armor () {

    }
    
    public void init () {

    }
    
    public static void main(String[] args) {
        
    }
}
