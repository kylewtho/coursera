
/**
 * Part 1: Finding a Gene - Using the Simplified Algorithm
 * 
 * @author: Kyle H.
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        String result = "";

        // finds the index position of the start codon "ATG"
        int startIndex = dna.indexOf("ATG");        
        // if there is no "ATG", return the empty string
        if (startIndex == -1) {
            return "";
        }
        
        // finds the idex position of the first stop codon "TAA" apprearing
        // after the "ATG" that was found
        int currIndex = dna.indexOf("TAA", startIndex+3);
        
        // if there is no such "TAA", return the empty string
        if (currIndex == -1) {
            return "";
        }
        
        // if the length of the substring between the "ATG" and "TAA" is a 
        // multuple of 3, return the substring that starts with that "ATG"
        // and ends with that "TAA"
        if ((currIndex - startIndex) % 3 == 0) {
            return dna.substring(startIndex, currIndex+3);
        } else {
        // if not, updeate currIndex to the index of the next TAA, starting
        // from (currIndex+1)
            currIndex = dna.indexOf("TAA", currIndex+1);
        }
        return "";
    }
    
    public void testSimpleGene() {
    //Test 1: DNA with no "ATG"
    String dna = "ASTGSGTAATTAATCG";
        System.out.println("DNA with no ATG " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
        
    //Test 2: DNA with no "TAA"
        dna = "AATGSGTAGTTASTCG";
        System.out.println("DNA with no TAA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);
    
    //Test 3: DNA with no "ATG" or "TAA"
        dna = "AATTSGTAGTTASTCG";
        System.out.println("DNA with no ATG or TAA " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);    
    
    //Test 4: DNA with ATG&TAA and the substring between them is a multiple of 3
        dna = "AATGSGGTAATCGTTAATCG";
        System.out.println("DNA with ATG and TAA correct mod3 " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene);    
    
    //Test 5: DNA with ATG&TAA and the substring between them is not a multiple of 3
    dna = "AATGSGTAATCGTTAGTCG";     
    //dna = "AATGSGTAATCGTCTAATCG";
        System.out.println("DNA with ATG and TAA incorrect mod3 " + dna);
        gene = findSimpleGene(dna);
        System.out.println("Gene is " + gene); 
    }
}
