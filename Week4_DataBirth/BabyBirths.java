/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int UniqueBoys = 0;
        int UniqueGirls = 0;
        int temp = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            //totalBirths += numBorn;
            if (rec.get(1).equals("M")&& numBorn != temp) {
                temp = numBorn;
                ++UniqueBoys ;
            }
            else if(rec.get(1).equals("F")&& numBorn !=  temp){
                temp = numBorn;
                ++UniqueGirls ;
            }
        }
       
        System.out.println("female girls = " + (UniqueGirls - 1));
        System.out.println("male boys = " + (UniqueBoys - 1));
              
        
    }


    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank (int year, String name, String gender){
        //return the rank of the nanme in filw with given gender
        // return -1, if the name is not in the file
        int rankedBoys = 0;
        int rankedGirls = 0;
        int ranked =0;
      
           
         
               FileResource fr = new FileResource();
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
               
               
      
       
    
        //System.out.println("The rank is: " + ranked);
        /*
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if ((rec.get(0).equals(name))&& !(rec.get(1).equals(gender)))
            return -1;
        }*/
   return ranked;

}
       
       
  
     // test getrank
     public void testGetRank(){
         //System.out.println("The rank is: " + getRank(1960, "Emily", "F"));
         //System.out.println("The rank is: " + getRank(1971, "Frank", "M"));
       }
       // getNamem return name at given rank, if not availble rank, return NO NAME
       public String getName(int year, int rank, String gender){
        int rankedBoys = 0;
        int rankedGirls = 0;
        String NAME =" ";
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if ( rec.get(1).equals(gender)){
                if(rec.get(1).equals("F")){
                    ++rankedGirls;
                    if(rankedGirls == rank)
                    {
                        NAME = rec.get(0);
                        break;
                    }
                    else
                    {
                        NAME = "NO NAME";
                    }
                }
                else{
                    ++rankedBoys;
                    if(rankedBoys == rank)
                    {
                        NAME =  rec.get(0);
                        break;
                    }
                    else
                    {
                        NAME = "NO NAME";
                    }
                }
            }
            
        }
        return NAME;
    }
    // test getName
    public void testGetName()
    {
        System.out.println("The name is: " + getName(1980, 350, "F"));
        System.out.println("The name is: " + getName(1982, 450, "M"));
        
    }
    // whatIsNameInYear
    public void whatIsNameInYear( String name, int year, int newYear, String gender){
       
        String newName = "" ;
        int rank = 0;
            // return rank in a year in a file
           //rank = getRank(year,name,gender);

            // define name in a new year with same rank as in a current year
            
            newName = getName(newYear, rank, gender);
            System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+ newYear);
                  
    }
    // test WhatIsNameInYear
    public void testWhatIsNameInYear(){
        whatIsNameInYear("Susan", 1972, 2014, "F");
        whatIsNameInYear("Owen", 1974, 2014, "M");
        
        
    }
    // yearOfHighestRank
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int highestRankAtYear = 2012;
        for(File f: dr.selectedFiles()){
            // check if name and gnder in file
            FileResource fr = new FileResource(f);
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(!rec.get(0).equals(name) || !rec.get(1).equals(gender))
                highestRankAtYear = -1;
            }
            //convert file Name  to integer of year
            String fileName = f.getName();
            String nameYear = fileName.substring(3,6);
            int year = Integer.parseInt(nameYear);
            int currentRank = getRank(year, name, gender);
            if (highestRank == -1)
            {
                highestRank = currentRank;
            }
            if( currentRank < highestRank)
            {
                highestRank = currentRank;
                highestRankAtYear = year;
            }
       
        }
        if(highestRankAtYear == -1){
        System.out.println("NOT FOUND");
        return highestRankAtYear;
        }
      
        if (gender.equals("F"))
        System.out.print("Her highest ranking was in "+ highestRankAtYear);
        else
        System.out.print("His highest ranking was in "+ highestRankAtYear);
        
        return highestRankAtYear;
    }

    //test HighestRank
    public void testYearOfHighestRank(){
         yearOfHighestRank("Genevieve", "F");
         //yearOfHighestRank("Mich", "M");
   
    }
   
    // get average rank return double average rank of the name and gender
    public double getAverageRank( String name, String gender){
        int highestRankAtYear = 2012;
        int total = 0;
        int count = 0;
        double averageRank = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            // check if name and gnder in file
            FileResource fr = new FileResource(f);
            for(CSVRecord rec : fr.getCSVParser(false)){
                if(rec.get(0).equals(name) == false|| !rec.get(1).equals(gender))
                 averageRank = -1;
            }
            //convert file Name  to integer of year
            String fileName = f.getName();
            String nameYear = fileName.substring(3,6);
            int year = Integer.parseInt(nameYear);
            int currentRank = getRank(year, name, gender);
            total += currentRank;
            ++count;
        
    }
    averageRank = (double)(total)/count;
    System.out.println("The average Rank was: "+ averageRank);
    return averageRank;
    }
    
    // test getaverage rank
    public void testGetAverageRank()
    {
        getAverageRank("Susan", "F");
        getAverageRank("Rober", "M");
    }
    
    // get total births ranked higher return toal birth of those having higher rank compared with given rank
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalBirth = 0;
        int startRank = 1;
        int currentRank = getRank(year, name, gender);
        FileResource fr = new FileResource();
        for(CSVRecord rec: fr.getCSVParser(false)){
            
            if(rec.get(1).equals(gender))
            {
                int numBorn = Integer.parseInt(rec.get(2));
                if(startRank == currentRank)
                break;
                else{
                    ++startRank;
                totalBirth += numBorn;
                
            }
                
            }
    
        }
        return totalBirth;
        
    }
    // test getTotalBirthsRankedHigher
    public void testGetTotalBirthsRankedHigher()
    {
        int value = getTotalBirthsRankedHigher(1990,"Emily","F");
        System.out.println("Total births ranked higher: "+ value);
        
    }
    
}
