
/**
 * Part 2: Finding a Gene - Using the Simplified Algorithm Reorganized
 * 
 * @author Kyle H.
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String result = "";
        startCodon = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();        

        // finds the index position of the start codon
        int startIndex = dna.indexOf(startCodon);        
        // if there is no start codon, return the empty string
        if (startIndex == -1) {
            return "";
        }
        
        // finds the idex position of the first stop codon apprearing
        // after the stop codonthat was found
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        
        // if there is no such stop codon, return the empty string
        if (currIndex == -1) {
            return "";
        }
        
        // if the length of the substring between the codons is a 
        // multuple of 3, return the substring
        if ((currIndex - startIndex) % 3 == 0) {
            return dna.substring(startIndex, currIndex+3);
        } else {
        // if not, updeate currIndex to the index of the next stop codon
            currIndex = dna.indexOf(stopCodon, currIndex+1);
        }
        return "";
    }
    
    public void testSimpleGene() {
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
    //Test 1: DNA with no "ATG"
    String dna = "ASTGSGTAATTAATCG";
        System.out.println("DNA with no ATG " + dna);
        String gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
        
    //Test 2: DNA with no "TAA"
        dna = "AATGSGTAGTTASTCG";
        System.out.println("DNA with no TAA " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);
    
    //Test 3: DNA with no "ATG" or "TAA"
        dna = "AATTSGTAGTTASTCG";
        System.out.println("DNA with no ATG or TAA " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);    
    
    //Test 4: DNA with ATG&TAA and the substring between them is a multiple of 3
        dna = "AATGSGGTAATCGTTAATCG";
        System.out.println("DNA with ATG and TAA correct mod3 " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene);    
    
    //Test 5: DNA with ATG&TAA and the substring between them is not a multiple of 3
    dna = "AATGSGTAATCGTTAGTCG";     
    //dna = "AATGSGTAATCGTCTAATCG";
        System.out.println("DNA with ATG and TAA incorrect mod3 " + dna);
        gene = findSimpleGene(dna, startCodon, stopCodon);
        System.out.println("Gene is " + gene); 
    }
}
