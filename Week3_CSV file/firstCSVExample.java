
/**
 * Write a description of class firstCSVExample here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class firstCSVExample
{
    public void readFood(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record: parser){
            System.out.println(record.get("Name") + " ");
            //System.out.print(record.get("Favorite Color") + " ");
           // System.out.print(record.get("Favorite Food") + " ");
        }
    }
    
}
