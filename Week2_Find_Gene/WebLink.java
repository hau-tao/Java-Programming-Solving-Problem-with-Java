
/**
 * Write a description of class WebLink here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import edu.duke.*;
public class WebLink
{
   public void youTubeLink(){
       URLResource link = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");;
       for ( String word : link.words())
       {
           System.out.println(word);
       }
    }
    
}
