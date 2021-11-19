package Mechanics.items;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.event.SwingPropertyChangeSupport;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class Armor extends Item {

    public String name;
    double hardness;
    double softness;
    
    public int[] modifications;
    public String description;

    static JSONObject armorFile;
    JSONParser parser = new JSONParser();
    
    ItemType itemType = ItemType.ARMOR;
    

    public Armor (String name) {
        try {
        this.name = name;
        init(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Armor() {
        
    }
    
    private void init(String name) throws Exception{

        if (Armor.armorFile == null) {
            Armor.armorFile = (JSONObject) parser.parse(new FileReader("src\\Bin\\Json\\Items\\Armor.json"));
        }
        
        JSONObject armorObject = (JSONObject) Armor.armorFile.get(name);
        
        this.hardness = (double) armorObject.get("hardness");
        this.softness = (double) armorObject.get("softness");

        //Needs to fill modifications without type problems
        JSONArray mods = (JSONArray) armorObject.get("modifications");
        this.modifications = new int[mods.size()];
        for (int i = 0; i < mods.size(); i++) {
            Long val = (Long) mods.get(i);
            this.modifications[i] = val.intValue();
        }

        this.description = (String) armorObject.get("description");

    }


    @Override
    public String toString() {
        return this.name + "\n" + this.description + "\n" +
            "softness: " + this.softness + " hardness:  " + this.hardness + 
            " modifications: " + Arrays.toString(this.modifications);
    }
    

    public static void main(String[] args) {
        Armor testArmor = new Armor("Basic Armor");
        System.out.println(testArmor);
    }

}
