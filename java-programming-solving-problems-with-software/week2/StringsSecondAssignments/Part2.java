
/**
 * Part2: HowMany - Finding Multiple Occurrences
 * 
 * @author Kyle H.
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
    int count = 0;
    int aIndex = stringb.indexOf(stringa);
    // while stringa found
    while (aIndex != -1) {
        count += 1;
        aIndex = stringb.indexOf(stringa, aIndex+stringa.length());
    }
    return count;
    }
    public void testHowMany() {
        String stringa = "GAA";
        String stringb = "ATGAACGAATTGAATC";
        String result = String.format("Checked %s in %s, occurence: %d", stringa, stringb, howMany(stringa, stringb));
        System.out.println(result);
        
        stringa = "AA";
        stringb = "ATAAAA";
        result = String.format("Checked %s in %s, occurence: %d", stringa, stringb, howMany(stringa, stringb));
        System.out.println(result);
        
        stringa = "AA";
        stringb = "ATABABAB";
        result = String.format("Checked %s in %s, occurence: %d", stringa, stringb, howMany(stringa, stringb));
        System.out.println(result);
        
        stringa = "ACAB";
        stringb = "AAAAACABACABAAAACABACABAA";
        result = String.format("Checked %s in %s, occurence: %d", stringa, stringb, howMany(stringa, stringb));
        System.out.println(result);
    }
}
