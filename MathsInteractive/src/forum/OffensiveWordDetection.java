//Author: Johnathan Evans
package forum;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * OffensiveWordDetection.java - A class that detects offensive words in a string
 * @author Johnathan Evans
 * @version 1.3
 */
public class OffensiveWordDetection {
    
    //private String offensiveWords = "fuck";
    private ArrayList<String> offensiveWords;
    
    public OffensiveWordDetection() {
        this.offensiveWords = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("swearWords.csv"))) {
            String importedWords;
            while((importedWords = reader.readLine()) != null) {
                String[] words = importedWords.split(",");
                for(String w : words) {
                    offensiveWords.add(w);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OffensiveWordDetection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean checkOffensive(String text) {
        for(String word : offensiveWords){
            if(text.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
