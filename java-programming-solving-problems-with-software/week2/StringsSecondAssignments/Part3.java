
/**
 * Part 3
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int dnaLength = dna.length();
        int currIndex = dna.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                // returns the index of the first occurrence of stopCodon
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex+1);
            }
        }
        return dnaLength;
    }
    public void testFindStopCodon(){
        String dna = "ATGaaaaaaaaTAGaaaaTAG";
        int result = findStopCodon(dna, 0, "TAG");
        System.out.println(dna.substring(0, result+3));
    }
    public String findGene(String dna, int n) {
        int startIndex = dna.indexOf("ATG", n);
        int stopIndexClosest = dna.length();
        // if there is no start codon, returns an empty string
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        stopIndexClosest = Math.min(stopIndexClosest, taaIndex);
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        stopIndexClosest = Math.min(stopIndexClosest, tagIndex);
        int atgIndex = findStopCodon(dna, startIndex, "ATG");
        stopIndexClosest = Math.min(stopIndexClosest, atgIndex);
        if (stopIndexClosest == dna.length()) {
            return "";
        } else {
            return dna.substring(startIndex, stopIndexClosest+3);
        }
    }
    public void testFindGene() {
        String dna= "AGDEGAASZZATAAAAA";
        System.out.println("The dna string is :" + dna);
        String gene = findGene(dna, 0);
        System.out.println("Gene: " + gene);
        
        dna= "aaaaaaATGaaaaaaaaaTAGaaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene: " + gene);
        
        dna= "aaaaaaATGaaaaaaaaaTAGTTATGAaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene: " + gene);
        
        dna= "aaaaaaATGaaaaaaaaaAAAAaaa";
        System.out.println("The dna string is :" + dna);
        gene = findGene(dna, 0);
        System.out.println("Gene: " + gene);
    }
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    public int countGenes(String dna) {
        int startIndex = 0;
        int count = 0;
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            count++;
            startIndex = dna.indexOf(dna, startIndex) + gene.length();
        }
        return count;
    }
    public void testCountGenes() {
        String dna = "ATGTAAGATGCCCTAGT";
        String result = String.format("%s, gene count: %d", dna, countGenes(dna));
        System.out.println(result);
        printAllGenes(dna);
    }
}
