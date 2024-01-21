
/** 
 * Programming Exercise: Implementing the Caesar Cipher
 * Assignment 1: Word Play
 * 
 * @author Kyle H.
 * @version 20 Apr 2023
 */

public class WordPlay {
    // This method returns true if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u'
    // or the uppercase versions) and false otherwise.
    public boolean isVowel(char ch) {
        if ("aeiouAEIOU".indexOf(ch) != -1) {
            return true;
        } else {
            return false;
        }
    }

    public void testIsVowel() {
        char ch = 'b';
        System.out.println(isVowel(ch));
    }
    
    // This method should return a String that is the string phrase with all the
    // vowels (uppercase or lowercase) replaced by ch.
    public String replaceVowels(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        String replacedPhrase = "";
        for (int k = 0; k < phrase.length(); k++) {
            if (isVowel(sb.charAt(k))) {
                sb.setCharAt(k, ch);
            }
        }
        replacedPhrase = sb.toString();
        return replacedPhrase;
    }

    public void testReplaceVowels() {
        String phrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        char ch = '*';
        System.out.println(replaceVowels(phrase, ch));
    }
    
    // This method returns a String that is the string phrase but with the Char ch 
    // (uppercase of lowercase) replaced by '*' or '+'
    public String emphasize(String phrase, char ch) {
        StringBuilder sb = new StringBuilder(phrase);
        String emphasizedPhrase = "";
        for (int k = 0; k < phrase.length(); k++) {
            if (sb.charAt(k) == Character.toUpperCase(ch) || sb.charAt(k) == Character.toLowerCase(ch)) {
                if (k % 2 == 0) {
                    sb.setCharAt(k, '*');
                } else {
                    sb.setCharAt(k, '+');
                }
            }
        }
        emphasizedPhrase = sb.toString();
        return emphasizedPhrase;
    }
    
    public void testEmphasize() {
        String phrase = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        char ch = 'a';
        System.out.println(emphasize(phrase, ch));
    }
}
    

