import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class rankedHisghestInYear
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
   // yearOfHighestRank
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int currentFile ;
        int highestRankFile = 0;
        int highestRankInYear = 0;
        int temp =0;
        for(File f: dr.selectedFiles()){
            // check if name and gnder in file
            FileResource fr = new FileResource(f);
            //convert file Name  to integer of year
            String fileName = f.getName();
            String nameYear = fileName.substring(3,7);
            int year = Integer.parseInt(nameYear);
            
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(0).equals(name) == false || rec.get(1).equals(gender)== false)
                {
                    temp = -1;
                }
                if(rec.get(0).equals(name)&& rec.get(1).equals(gender))
                {
                    currentFile = getRank(year, name, gender, f);
                    if (highestRankFile == 0)
                    {
                        highestRankFile = currentFile;
                        highestRankInYear = year;
                    }
                    else if ( currentFile < highestRankFile)
                    {
                        highestRankFile = currentFile;
                        highestRankInYear = year;
                    }
   
                    break;
                    
                }
                
            }
            
        
            
        }
        if (gender.equals("F"))
                System.out.print("Her highest ranking was in "+ highestRankInYear);
            else
                System.out.print("His highest ranking was in "+ highestRankInYear);
        if (temp == -1)
        {
            return -1;
        }
        return highestRankFile;
        
       
    }

    //test HighestRank
    public void testYearOfHighestRank(){
         yearOfHighestRank("Genevieve", "F");
         yearOfHighestRank("Mich", "M");
   
    }
    
}