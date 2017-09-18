import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class AverageRank
{
    public int getRank (int year, String name, String gender, File f){
        //return the rank of the nanme in filw with given gender
        // return -1, if the name is not in the file
        int rankedBoys = 0;
        int rankedGirls = 0;
        int ranked =0;
               FileResource fr = new FileResource(f);
               for (CSVRecord rec : fr.getCSVParser(false)) {
               if ( rec.get(1).equals(gender)){
                if(rec.get(1).equals("F")){
                    ++rankedGirls;
                    if(rec.get(0).equals(name)){
                        ranked = rankedGirls;
                    }
                    
    
                }
                else{
                    ++rankedBoys;
                    if(rec.get(0).equals(name)){
                        ranked = rankedBoys;
                    }
                    
                   
                }
            }
    
        }

   return ranked;

}
   public double getAverageRank( String name, String gender){
        int highestRankAtYear = 2012;
        int total = 0;
        int count = 0;
        double averageRank = 0.0;
        double temp = -1;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            // check if name and gnder in file
            FileResource fr = new FileResource(f);
            //convert file Name  to integer of year
            String fileName = f.getName();
            String nameYear = fileName.substring(3,7);
            int year = Integer.parseInt(nameYear);
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(0).equals(name) == false|| rec.get(1).equals(gender))
                { 
                    temp  = -1;
                }
                if(rec.get(0).equals(name)&& rec.get(1).equals(gender)){
                     int currentRank = getRank(year, name, gender,f);
                     total += currentRank;
                     ++count;
                    
                }
            }
            
           
            
            
        
    }
    averageRank = (double)(total)/count;
    System.out.println("The average Rank was: "+ averageRank);
    if ( temp == -1)
    return -1;
    return averageRank;
    }
    // test getaverage rank
    public void testGetAverageRank()
    {
       // getAverageRank("Susan", "F");
       // getAverageRank("Robert", "M");
       getAverageRank("Mason", "M");
       getAverageRank("Jacob", "M");
       
    }
}
