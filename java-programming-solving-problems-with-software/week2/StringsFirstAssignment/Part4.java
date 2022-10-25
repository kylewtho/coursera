
/**
 * Part4: Finding Web Links
 * 
 * @author Kyle H.
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Part4 {
    public void findWebLink() {
        // creats a file resource
        URLResource file = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        String link = "";
        
        // reads the file word by word
        for (String item : file.words()) {
            String itemLower = item.toLowerCase();
            int ytIndex = itemLower.indexOf("youtube.com");
            
        // checks if "youtube.com" is in the link; indetifies the URL
            if (ytIndex != -1) {
                int startIndex = itemLower.lastIndexOf("\"", ytIndex);
                int stopIndex = itemLower.indexOf("\"", ytIndex+1);
                
                link = itemLower.substring(startIndex+1, stopIndex);
                System.out.println(link);
            }
        }
    }
}

