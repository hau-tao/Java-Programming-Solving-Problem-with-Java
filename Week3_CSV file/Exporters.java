
import edu.duke.*;
import org.apache.commons.csv.*;


public class Exporters
{
    public String countryInfo(CSVParser parser, String country)
    {
        String printOut = " ";
        for(CSVRecord record: parser){
            //Look at the "Export" column
            
            String export = record.get("Exports");
            String foundCountry = record.get("Country");
            String value = record.get("Value (dollars)");
            
            //Check if it contains exportOfInterest
            if(foundCountry.contains(country)){
                // If so, write down the "Country" from that row
                printOut =  foundCountry +": " + export +": "+ value;
                break;
            }
            else
            {
                printOut = " NOT FOUND";
            }
        }
        return printOut;
        
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for(CSVRecord record: parser)
        {
            String export = record.get("Exports");
            String foundCountry = record.get("Country");
            if (export.contains(exportItem1) && export.contains(exportItem2))
            {
                System.out.println("countries export these items: " + foundCountry);
            }
            
        }
    }
    public void bigExporters(CSVParser parser, String amount)
    {
        System.out.println("These countries export greater than amount: ");
        for(CSVRecord record: parser)
        {
            String value = record.get("Value (dollars)");
            String foundCountry = record.get("Country");
            if (amount.length() < value.length())
            {
                System.out.println(foundCountry +" " + value);
            }
            
        }
        
        
    }
    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int count = 0;
        for(CSVRecord record: parser)
        {
            String export = record.get("Exports");
            String foundCountry = record.get("Country");
            if (export.contains(exportItem))
            {
                ++count;
            }
            
        }
        return count;
        
    }
    public void tester(){
        FileResource fr= new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        System.out.println("the number of countries export: "+ numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999”");
        
    }
    
 
}
