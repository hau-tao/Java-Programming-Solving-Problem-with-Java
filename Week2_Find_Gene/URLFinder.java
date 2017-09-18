
/**
 * Write a description of class URLFinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class URLFinder
{
    int count_https = 0;
    int countDotCom =0 ;
    int com = 0;
    int countDot = 0;
   
    public StorageResource findURLs(String url){
        URLResource page = new URLResource(url);
        String  source = page.asString();
        
        StorageResource store = new StorageResource ();

        int start = 0;
        
        while(true){
            int index = source.indexOf("href=", start);
            if (index == -1){
                break;
            }
            int firstQuote = index + 6;
            int endQuote = source.indexOf("\"", firstQuote);
            String sub = source.substring(firstQuote, endQuote);
            if(sub.startsWith("http") ){
                store.add(sub);
                //count https in url
                if(sub.startsWith("https")){
                ++count_https;
       
                }    
            }
            start = endQuote +1;
                
        }
         
        
        

        return store;        
    }
   
    
    public void testURL(){
        StorageResource s1 = findURLs("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        //StorageResource s2 = findURLs("http://www.doctorswithoutborders.org");
        
        
        for (String link: s1.data())
        {
            int start = 0;
             while(true)
        
             {
                 int n = link.indexOf('.', start);
                if (n == -1)
                {
                    break;
                }
                ++countDot;
                start = n+1;
            }
            System.out.println(link);
            if (link.indexOf("com", 0) != -1)
            ++com;
            if (link.endsWith("com") || link.endsWith("com/"))
            ++countDotCom;
            
     
        }
       
            
        
        System.out.println("size = "+ s1.size());
        System.out.println("the number of https link "+ count_https);
        System.out.println("the number of com in link "+ com);
        System.out.println("the number of .com "+ countDotCom ) ;
        System.out.println("the number of dot "+ countDot ) ;
        
        
        //System.out.println("size = "+ s2.size());
    }
}
        
    
    


