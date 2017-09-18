
/**
 * Write a description of class FindGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.lang.*;
import java.io.*;
import edu.duke.*;
public class FindGene
{
    public void printAllStarts (String dna)
    {
       // FileResource file = new FileResource("GRch38dnapart.fa");
       //for (String line: file.lines())
        //{
           // System.out.println(line);
       // }
        int start = 0 ;
        int count = 0;
        int count_60 =0;
        int longest_length = 0;
        
        
        double ratio =0.0;
        int count_ratio= 0;
        int counter  =0;
        int count_CTG = 0;
        int index_CTG = 0;
         // count CTG in strand of DNA
        int start_1 =  0;
        while(true)
        {  
            int pos  = dna.indexOf("CTG", start_1);
            
            if (pos == -1 )
            break;
            count_CTG += 1;
            start_1 = pos + 3;
            
        }
        
        while(true)
        {
            int count_C = 0;
            int count_G = 0;
            int loc = dna.indexOf("ATG", start);
            //int nextLoc = dna.indexOf("ATG", loc +3);
            int temp;
            temp = findStopIndex(dna, loc +3);
           
            while ( temp == -1 )
            {
               
                start = loc + 3;
        
               loc = dna.indexOf("ATG", start);
                
               temp = findStopIndex(dna, loc +3);
            }
            if ( loc == -1 || temp + 3 >= dna.length())
            {
                break;
            }
            
            //CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA    
            String gene = dna.substring(loc, temp+3);

            System.out.println(gene);
            
           

            
           
            // count ratio of C and G
            
            for (int i = 0; i < gene.length(); i++){
                char c = gene.charAt(i); 
                if ( c == 'C')
                {++ count_C;
               }
                else if ( c == 'G')
                {++count_G;
                   }
                }
                ratio = (double)(count_C + count_G)/gene.length();
                
                if (ratio > 0.35)
                { 
                    ++ count_ratio;
                    
                }
                //Process char
            
           
            
            // count greathe than 60 nuc
            int size = gene.length();
            // count  the length of the longest of genne
            if (size > longest_length)
            {
                longest_length = size;
            }
            
            if ( size > 60)
            {
                ++count_60;
            }
            // count number of gene
            ++count;
            
 
            start = temp + 3;

        }
        System.out.println("The number of gene in DNA: " + count);
        System.out.println("The number of gene is greater than 60 nucleis: " + count_60);
        System.out.println("The number of gene has CG ratio is greater than 0.35: "+ count_ratio);
        System.out.println("The number of CTG in strand of DNA: " + count_CTG);
        System.out.println("The length of the longest gene: " + longest_length);
        
    }
    
    public int findStopIndex(String dna, int index){
        int stop1 =dna.indexOf("TGA", index);
        if (stop1 == -1 || (stop1-index) %3 != 0){
             stop1 = dna.length();
        }
        int stop2 =dna.indexOf("TAA", index);
        if (stop2 == -1 || (stop2-index) %3 != 0){
             stop2 = dna.length();
        }
        int stop3 = dna.indexOf("TAG", index);
        if (stop3 == -1 || (stop3-index) %3 != 0){
             stop3 = dna.length();
        }
        if (stop1 == dna.length() && stop2 == dna.length() && stop3 == dna.length())
        return -1;
        return Math.min(stop1, Math.min(stop2, stop3));
    }
    
    public void testFinder()
    {
        FileResource file = new FileResource("GRch38dnapart.fa");
        for (String a : file.lines()){
        printAllStarts (a);
        } 
    }
    
    
    
}

/*for reference looking for C
 * int countcg = 0;
int start = 0;
while (true) {
    int pos = dna.indexOf("c", start);
    if (pos == -1) {
        break;
    }
    countcg += 1;
}*/

