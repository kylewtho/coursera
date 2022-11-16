
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
public class Part1 {
    // 1
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestSoFar = null; // record with the coldest temperture in the file
        double currTemp = 0.0;
        double coldestTemp = 0.0;
        
        for (CSVRecord record : parser) {
            if (coldestSoFar == null) {
                coldestSoFar = record;
            }
            coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            currTemp = Double.parseDouble(record.get("TemperatureF"));
            if (currTemp != -9999 && currTemp < coldestTemp) {
                coldestSoFar = record;
            }
        }
        return coldestSoFar;
    }
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("The coldest temperature on that day was " + record.get("TemperatureF"));
        System.out.println("Time: " + record.get("TimeEST"));
    }
    
    // 2
    public String fileWithColdestTemperature() {
        String coldestFilename = null;
        CSVRecord coldestSoFar = null;
        double currTemp = 0.0;
        double coldestTemp = 0.0;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord record = coldestHourInFile(fr.getCSVParser());
            if (coldestSoFar == null) {
                coldestSoFar = record;
                coldestFilename = f.getName();
            }
            currTemp = Double.parseDouble(record.get("TemperatureF"));
            coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
            if (currTemp != -9999 && currTemp < coldestTemp) {
                coldestSoFar = record;
                coldestFilename = f.getName();
            }
        }  
        return coldestFilename;
    }
    public void testFileWithColdestTemperature() {
        String coldestFilename = fileWithColdestTemperature();
        System.out.println("Coldest day was in file: " + coldestFilename);
        
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestSoFar = coldestHourInFile(parser);
        System.out.println("The coldest temperature: " + coldestSoFar.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were");
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.println(record.get("DateUTC") + " " + record.get("TemperatureF"));
        }    
    }
    
    // 3
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestSoFar = null;
        double lowestHumid = 0.0;
        double currHumid = 0.0;
        
        for (CSVRecord record : parser) {
            if (lowestSoFar == null) {
                lowestSoFar = record;
            }
            
            if (record.get("Humidity").equals("N/A")) {
                currHumid = 9999;
            } else {
                currHumid = Double.parseDouble(record.get("Humidity"));
            }
            if (lowestSoFar.get("Humidity").equals("N/A")) {
                lowestHumid = 9999;
            } else {
                lowestHumid = Double.parseDouble(lowestSoFar.get("Humidity"));
            }
            
            if (currHumid != 9999 && currHumid < lowestHumid) {
                lowestSoFar = record;
            }
        }
        return lowestSoFar;
    }
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    // 4
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestSoFar = null;
        double currHumid = 0.0;
        double lowestHumid = 0.0;
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord record = lowestHumidityInFile(fr.getCSVParser());
            if (lowestSoFar == null) {
                lowestSoFar = record; 
                lowestHumid = Double.parseDouble(record.get("Humidity"));
            }
            currHumid = Double.parseDouble(record.get("Humidity"));
            lowestHumid = Double.parseDouble(lowestSoFar.get("Humidity"));
            if (currHumid != -9999 && currHumid < lowestHumid) {
                lowestSoFar = record;
            }
        }  
        
        return lowestSoFar;        
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + record.get("Humidity") + " at " + record.get("DateUTC"));
    }
    
    // 5
    public double averageTemperatureInFile(CSVParser parser) {
        double avgTemp = 0.0;
        int count = 0;
        double sum = 0.0;
        
        for (CSVRecord record : parser) {
            sum += Double.parseDouble(record.get("TemperatureF"));
            count ++;
        }
        avgTemp = sum / count;
        return avgTemp;
    }
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureInFile(parser);
        System.out.println("Average temperature in file is " + avgTemp);
    }
    
    // 6
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double avgTemp = 0.0;
        int count = 0;
        double sum = 0.0;
        
        for (CSVRecord record : parser) {
            if (Double.parseDouble(record.get("Humidity")) >= value) {
                sum += Double.parseDouble(record.get("TemperatureF"));
                count ++;
            }
        }
        avgTemp = sum / count;
        return avgTemp;
    }
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average Temp when high Humidity is " + avgTemp);
    }
    
    // test
    public void quiz3() {

    }
}
