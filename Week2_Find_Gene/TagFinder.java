
/**
 * Write a description of class TagFinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.*;
import edu.duke.*;
import java.io.*;
public class TagFinder
{
    public String findProtein(String dna){
        int start = dna.indexOf("ATG");
        int stop = dna.indexOf("TAG", start + 3);
        int stop_TAA = dna.indexOf("TAA", start +3);
        int stop_TGA = dna.indexOf("TGA", start +3);
        if ((stop -start)% 3 ==0 && stop != -1)
        {
            return dna.substring(start, stop + 3);
        }
        else {
            if ((stop_TGA - start) % 3 == 0 && stop_TGA != -1)
            {
                return dna.substring(start, stop_TGA +3);
                
            }
            else if ((stop_TAA - start) %3 == 0 && stop_TAA != -1)
            {
                 return dna.substring(start, stop_TAA+3);
            }
            else{
                return "";
                
            }
            
            
        }
    }
    public void testing()
    {
        String a= "acatgataacctaag";
        String result = findProtein(a.toUpperCase());
        System.out.println(result);
    }
    
 


}
