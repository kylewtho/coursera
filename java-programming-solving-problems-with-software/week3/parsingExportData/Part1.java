
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public String countryInfo(CSVParser parser, String country) {
        String info = "NOT FOUND";
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                info = String.format("%s: %s: %s", country, record.get("Exports"), record.get("Value (dollars)"));
            }
        }
        return info;
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int CountryCounter = 0;
        for (CSVRecord record : parser) {
            if(record.get("Exports").contains(exportItem)) {
                CountryCounter++;
            }
        }
        return CountryCounter;
    }
    
    public void bigExporters(CSVParser parser, String amount) {
        int amountLength = amount.length();
        for (CSVRecord record : parser) {
            int valueLength = record.get("Value (dollars)").length();
            if (valueLength > amountLength) {
                System.out.println(valueLength + " >= " + amountLength);
                System.out.println(String.format(record.get("Country"), record.get("Value (dollars)")));
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        
        // Q3
        CSVParser parser = fr.getCSVParser();
        System.out.println("=====Q3=====");
        listExportersTwoProducts(parser, "fish", "nuts");
        
        // Q4
        parser = fr.getCSVParser();
        System.out.println("=====Q4=====");
        System.out.println(numberOfExporters(parser, "gold"));
        
        // Q5
        parser = fr.getCSVParser();
        System.out.println("=====Q5=====");
        System.out.println(countryInfo(parser, "Nauru"));
        
        // Q6
        parser = fr.getCSVParser();
        System.out.println("=====Q6=====");
        bigExporters(parser, "$999,999,999,999");
    }
}
