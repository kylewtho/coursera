
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class Part1 {
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
            if (findGene(dna, startIndex) == "") {
                break;
            }
            System.out.println("Gene: " + gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
    }
    public void testPrintAllGenes() {
        String dna = "ATGaaaTAGaaaATGaaaTAA";
        printAllGenes(dna);
    }
    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource genes = new StorageResource();
        while (true) {
            String gene = findGene(dna, startIndex);
            if (gene.isEmpty()) {
                break;
            }
            genes.add(gene);
            //System.out.println("Test: " + gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }
        return genes;
    }
    public void testGetAllGenes() {
        String dna = "ATGaaaTAGaaaATGaaaTAA";
        StorageResource genes = getAllGenes(dna);
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
    }
    // part 2
        public float cgRatio(String dna) {
        String dnaUpper = dna.toUpperCase();
        int count = 0;
        int dnaLength = dna.length();
        float ratio = 0;
        for (int i=0; i<dnaLength; i++) {
            if (dnaUpper.charAt(i) == 'C' || dnaUpper.charAt(i) == 'G') {
                count++;
            }
        }
        ratio = (float)count/(float)dnaLength;
        //System.out.println("count: " + count);
        //System.out.println("length: " + dnaLength);
        //System.out.println("ratio: " + ratio);
        return ratio;
    }
    public void testCgRatio() {
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    public int countCTG(String dna) {
        int count = 0;
        // TBD
        return count;
    }
    // part 3
    public void processGenes(StorageResource sr) {
        int countLonger = 0;
        int countCGRatioHigher = 0;
        int lengthLongest = 0;
        
        // prints all the Strings in sr that are longer than 9 characters
        System.out.println("=====Strings longer than 9 characters=====");
        for (String str : sr.data()) {
            if (str.length() > 9) {
                System.out.println(str);
                countLonger++;
            }
        }
        
        // prints the number of Strings in sr that are longer than 9 characters
        System.out.println("=====Number of Strings longer than 9 characters=====");
        System.out.println(countLonger);
        
        // prints the Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("=====Strings: C-G-ratio is higher than 0.35=====");
        for (String str : sr.data()) {
            if (cgRatio(str) > 0.35) {
                System.out.println(str);
                countCGRatioHigher++;
            }
        }        
        
        // prints the number of Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("=====Number of Strings: C-G-ratio is higher than 0.35=====");
        System.out.println(countCGRatioHigher);
        
        // prints the length of the longest gene in sr
        System.out.println("=====Length of the longest gene=====");
        for (String str : sr.data()) {
            if (str.length() > lengthLongest) {
                lengthLongest = str.length();
            }
        }
        System.out.println(lengthLongest);
    }
    
    public void testProcessGenes() {
        String dna
    }
}
