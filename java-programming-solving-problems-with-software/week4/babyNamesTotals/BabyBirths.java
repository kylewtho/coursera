/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    /**
     * Modify the method totalBirths (shown in the video for this project) to also print the 
     * number of girls names , the number of boys names and the total names in the file.
     */
    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int numNames = 0;
        int numBoyNames = 0;
        int numGirlNames = 0;
        
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            numNames ++;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                numBoyNames ++;
            }
            else {
                totalGirls += numBorn;
                numGirlNames ++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("male boys = " + totalBoys);
        System.out.println("number of names = " + numNames);
        System.out.println("number of boy names = " + numBoyNames);
        System.out.println("number of girl names = " + numGirlNames);
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
    /**
     * Write the method named getRank that has three parameters: an integer named year, a 
     * string named name, and a string named gender (F for female and M for male). This method 
     * returns the rank of the name in the file for the given gender,  where rank 1 is the name 
     * with the largest number of births. If the name is not in the file, then -1 is returned.
     */
    public int getRank(int year, String name, String gender) {
        String filename = "data/yob" + year + "short.csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
       
        int rank = 0;
        boolean isExist = false;
        
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                rank ++;
                if (record.get(0).equals(name)) {
                    isExist = true;
                    break;
                }
            }
        }
        if (isExist == true) {
            return rank;
        } else {
            return -1;
        }
    }
    public void testGetRank() {
        int rank = getRank(2012, "Mason", "M");
        System.out.println(rank);
    }
    /**
     * Write the method named getName that has three parameters: an integer named year, an 
     * integer named rank, and a string named gender (F for female and M for male). This 
     * method returns the name of the person in the file at this rank, for the given gender, 
     * where rank 1 is the name with the largest number of births. If the rank does not exist 
     * in the file, then “NO NAME”  is returned.
     */
    public String getName(int year, int rank, String gender) {
        String name = "NO NAME";
        String filename = "data/yob" + year + "short.csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        int count = 0;
        
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                count ++;
                if (count == rank) {
                    name = record.get(0);
                }
            }
        }
        
        return name;
    }
    public void testGetName() {
        String name = getName(2012, 2, "F");
        System.out.println(name);
    }
    /**
     * Write the void method named whatIsNameInYear that has four parameters: a string name, 
     * an integer named year representing the year that name was born,  an integer named 
     * newYear and a string named gender (F for female and M for male). This method determines 
     * what name would have been named if they were born in a different year, based on the 
     * same popularity. 
     */
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        
        String result = name + " born in " + year + " would be " + newName + " if she was born in " + newYear;
        
        System.out.println(result);
    }
    public void testWhatIsNameInYear() {
        String name = "Isabella";
        int year = 2012;
        int newYear = 2014;
        String gender = "F";
        
        whatIsNameInYear(name, year, newYear, gender);
    }
    /**
     * Write the method yearOfHighestRank that has two parameters: a string name, and a 
     * string named gender (F for female and M for male). This method selects a range of 
     * files to process and returns an integer, the year with the highest rank for the 
     * name and gender. If the name and gender are not in any of the selected files, it 
     * should return -1. 
     */
    public int yearOfHighestRank(String name, String gender) {
        boolean isExist = false;
        int highestRank = 0;
                
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int rank = 0;
            String fname = "data/" + f.getName();
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record: parser) {
                if (record.get(1).equals(gender)) {
                    rank ++;
                    if (record.get(0).equals(name)) {
                        isExist = true;
                        if (highestRank == 0) highestRank = rank;
                        else if (rank < highestRank) highestRank = rank;
                        break;
                    }
                }
            }
        }
        if (isExist = false) return -1;
        else return highestRank;
    }
    public void testyearOfHighestRank() {
        int highestRank = yearOfHighestRank("Mason", "M");
        System.out.println("Highest rank is " + highestRank);
    }
    /**
     * Write the method getAverageRank that has two parameters: a string name, and a 
     * string named gender (F for female and M for male). This method selects a range of 
     * files to process and returns a double representing the average rank of the name and 
     * gender over the selected files. It should return -1.0 if the name is not ranked in 
     * any of the selected files. 
     */
    public double getAverageRank(String name, String gender) {
        boolean isExist = false;
        double avgRank = 0;
        double sumRank = 0;
        int countRank = 0;
                
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            int rank = 0;
            String fname = "data/" + f.getName();
            FileResource fr = new FileResource(fname);
            CSVParser parser = fr.getCSVParser(false);
            for (CSVRecord record: parser) {
                if (record.get(1).equals(gender)) {
                    rank ++;
                    if (record.get(0).equals(name)) {
                        isExist = true;
                        sumRank += rank;
                        countRank ++;
                        break;
                    }
                }
            }
        }
        if (isExist = false) return -1;
        else {
            avgRank = sumRank / countRank;
            return avgRank;
        }
    }
    public void testGetAverageRank() {
        double avgRank = getAverageRank("Jacob", "M");
        System.out.println("Average rank is " + avgRank);
    }
    
    /**
     * Write the method getTotalBirthsRankedHigher that has three parameters: an integer 
     * named year, a string named name, and a string named gender (F for female and M for 
     * male). This method returns an integer, the total number of births of those names 
     * with the same gender and same year who are ranked higher than name. 
     */
}
