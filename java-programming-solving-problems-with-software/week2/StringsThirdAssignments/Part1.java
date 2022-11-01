
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
        int atgIndex = findStopCodon(dna, startIndex, "TGA");
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
        //String dna = "ATGaaaTAGaaaATGaaaTAA";
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        String dnaUpper = dna.toUpperCase();
        //System.out.println(dna);
        
        StorageResource genes = getAllGenes(dnaUpper);
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
        int start = 0;
        
        while(true) {
            int startIndex = dna.indexOf("CTG", start);
            if (startIndex == -1) {
                break;
            }
            count++;
            start = startIndex + 2;
        }
        return count;
    }
    // part 3
    public void processGenes(StorageResource sr) {
        int countLonger = 0;
        int countCGRatioHigher = 0;
        int lengthLongest = 0;
        
        // prints all the Strings in sr that are longer than 9 characters
        System.out.println("Strings longer than 9 characters:");
        for (String str : sr.data()) {
            if (str.length() > 9) {
                System.out.println(str);
                countLonger++;
            }
        }
        
        // prints the number of Strings in sr that are longer than 9 characters
        System.out.println("Number of Strings longer than 9 characters: " + countLonger);
        
        // prints the Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("Strings: C-G-ratio is higher than 0.35:");
        for (String str : sr.data()) {
            if (cgRatio(str) > 0.35) {
                System.out.println(str);
                countCGRatioHigher++;
            }
        }        
        
        // prints the number of Strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("Number of Strings with C-G-ratio higher than 0.35: " + countCGRatioHigher);
        
        // prints the length of the longest gene in sr
        for (String str : sr.data()) {
            if (str.length() > lengthLongest) {
                lengthLongest = str.length();
            }
        }
        System.out.println("Length of the longest gene: " + lengthLongest);
    }
    
    public void testProcessGenes() {
        StorageResource sr = new StorageResource();
        // test cases
        //sr.add("ATGCCCCGGTAA");
        //sr.add("ATGCCCGGGTTTTTTTTTTTTTTTTAA");
        //sr.add("ATGTTTTTTTTTTTTTTTTAA");
        
        // test cases from file
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        String dnaUpper = dna.toUpperCase();
        
        sr = getAllGenes(dnaUpper);
        //for (String gene : sr.data()) {
        //    System.out.println(gene);
        //}
        processGenes(sr);
    }
    
    public void quizProcessGenes() {
        StorageResource sr = new StorageResource();
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();    
        String dnaUpper = dna.toUpperCase();
        
        sr = getAllGenes(dnaUpper);
        
        int countLonger = 0;
        int countCGRatioHigher = 0;
        int lengthLongest = 0;
        int numOfGenes = 0;
        
        for (String str : sr.data()) {
            numOfGenes++;
            if (str.length() > 60) { // longer that 60
                countLonger++;
            }
            System.out.println(str);
        }
        
        // Q1: How many genes are there in the file brca1line.fa?
        System.out.println("Total number of genes: " + numOfGenes);
        // Q2: How many genes are there in the file brca1line.fa that are longer than 60?
        System.out.println("Number of Strings longer than 60 characters: " + countLonger);

        for (String str : sr.data()) {
            if (cgRatio(str) > 0.35) {
                countCGRatioHigher++;
            }
        }        
        
        // Q3: How many genes are there in the file brca1line.fa that have a C-G-ratio greater than 0.35?
        System.out.println("Number of Strings with C-G-ratio higher than 0.35: " + countCGRatioHigher);
    }
    
    public void quizProcessGenes2() {
        StorageResource sr = new StorageResource();
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();    
        String dnaUpper = dna.toUpperCase();
        
        sr = getAllGenes(dnaUpper);
        
        int countLonger = 0;
        int countCGRatioHigher = 0;
        int lengthLongest = 0;
        int numOfGenes = 0;
        int countCTG = 0;
        
        for (String str : sr.data()) {
            numOfGenes++;
            if (str.length() > 60) { // longer than 60
                countLonger++;
            }
            if (cgRatio(str) > 0.35) {
                countCGRatioHigher++;
            }
            countCTG = countCTG(dnaUpper);
            if (str.length() > lengthLongest) {
                lengthLongest = str.length();
            }
            //System.out.println(str);
        }
        
        // Q1: How many genes are there?
        System.out.println("Total number of genes: " + numOfGenes);
        // Q2: How many genes are there that are longer than 60?
        System.out.println("Number of Strings longer than 60 characters: " + countLonger);
        // Q3: How many genes are there that have a C-G-ratio greater than 0.35?
        System.out.println("Number of Strings with C-G-ratio higher than 0.35: " + countCGRatioHigher);
        // Q4: Numbers of codon "CTG"
        System.out.println("Occurence of codon CTG: " + countCTG);
        // Q5: Prints the length of the longest gene
        System.out.println("Length of the longest gene: " + lengthLongest);
    }
}
