import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class coldestDay
{
    

    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser)
        {
            if(!currentRow.get("TemperatureF").contains("-9999"))
            {
                coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
            }
            else{
                return coldestSoFar;
            }
   
        }
        return coldestSoFar;
    }
    public void testcoldestHourInFile (){
        FileResource fr = new FileResource("data/2014/weather-2014-05-01.csv");
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldest temperature on that day was "+ coldest.get("TemperatureF") +" at "
        + coldest.get("DateUTC"));
    }
    public String fileWithColdestTemperature ()
    {
        CSVRecord coldest = null;
        String nameOfFile = "";
        DirectoryResource dr = new DirectoryResource();

        for(File f: dr.selectedFiles())
        {
            nameOfFile = f.getName();
            FileResource fr = new FileResource(f);
            coldest = coldestHourInFile(fr.getCSVParser());
        }
        return  nameOfFile;
        
    }
    public void testFileWithColdestTemperature ()
    {  String s = fileWithColdestTemperature ();
       FileResource fr = new FileResource(s);
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldest = coldestHourInFile(parser);
        System.out.println("Coldest day was in file "+ s );
        System.out.println("coldest temperature on that day was "+ coldest.get("TemperatureF"));
       System.out.println("All the Temperature on the coldest day were: ");
        parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
        }
        
        
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser){
         CSVRecord lowestHumiditySoFar = null;
        for (CSVRecord currentRow : parser)
        {
            if(!currentRow.get("Humidity").contains("N/A")){
           
                lowestHumiditySoFar = getLowestOfTwo(currentRow, lowestHumiditySoFar);
            }
            else
            {
                return lowestHumiditySoFar;
            }
          
   
        }
        return lowestHumiditySoFar;
    
    }
    public void  testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVRecord lowestHumidity = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was "+ lowestHumidity.get("Humidity") +" at "
        +  lowestHumidity.get("DateUTC"));
        
    }
    public double averageTemperatureInFile(CSVParser parser)
    {
        int count = 0;
        double total = 0.0;
        double averageTemp =0.0;
        
        for(CSVRecord record: parser){
            String getTemp = record.get("TemperatureF");
            total += Double.parseDouble(getTemp);
            ++count;
            
        }
        averageTemp = total/count;
        return averageTemp;
    }
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file is "+ averageTemperatureInFile(fr.getCSVParser()));
    }
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser , int value){
        double totalTemp =0.0;
        int count = 0;
        for (CSVRecord record: parser)
        {
           
            
            if ( Double.parseDouble(record.get("Humidity")) >= value){
                totalTemp += Double.parseDouble(record.get("TemperatureF"));
                ++count;
                
            }
            
        }
        return totalTemp/count;
        
    }
    public void testAverageTemperatureWithHighHumidityInFile()
    {
        FileResource fr = new FileResource();
        if ( averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80) > 0.0){
        System.out.println("Average Temp when high Humidity is  "+ averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80));}
        else{
        System.out.println("No temperatures with that humidity ");}
    }
    
    
    // the codest temperature in a year
    public CSVRecord coldestInYear(){
        CSVRecord coldestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
            
        }
        return coldestSoFar;
        
    }
    // test  the codest temperature in a year
    public void testcoldestInYear (){
        
        CSVRecord coldest = coldestInYear();
        System.out.println("coldest temperature in a year was "+ coldest.get("TemperatureF") +" at "
        + coldest.get("DateUTC"));
    }
    
    
    // the lowest  huminity in a year
    public CSVRecord lowestHumidityInYear(){
        CSVRecord lowestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
            
        }
        return lowestSoFar;
        
    }
    // test  the codest temperature in a year
    public void testlowestHumidityInYear (){
        
        CSVRecord lowest = lowestHumidityInYear();
        System.out.println("lowest humidity in a year was "+ lowest.get("Humidity") +" at "
        + lowest.get("DateUTC"));
    }
    public CSVRecord getColdestOfTwo(CSVRecord currentRow, CSVRecord coldestSoFar)
    {
            if(coldestSoFar == null ){
                coldestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                
                if(currentTemp < coldestTemp)
                {
                   coldestSoFar= currentRow;
                }
            }
            return coldestSoFar;
    }
    public CSVRecord getLowestOfTwo(CSVRecord currentRow, CSVRecord lowestHumiditySoFar)
    {
            if(lowestHumiditySoFar == null ){
                lowestHumiditySoFar = currentRow;
            }
            else{
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                
                if(currentHumidity< lowestHumidity)
                {
                   lowestHumiditySoFar= currentRow;
                }
            }
            return lowestHumiditySoFar;
    }
    /*
    public void testHottestInManyDays (){
        
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was "+ largest.get("TemperatureF") +" at "
        + largest.get("DateUTC"));
    }*/
    
    


}
