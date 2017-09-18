
/**
 * Write a description of class GrayScaleConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class GrayScaleConverter
{
    // I started with the image I wanted (inImage)
    public ImageResource makeGray(ImageResource inImage){
        // I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
              
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()){
            
            // look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            // compute inPixel's Red + inPixel's Green + inPixel's Bule
            // divide that sum by 3 (call it average)
            int average = (inPixel.getRed() + inPixel.getBlue() +inPixel.getGreen())/3;
            // set Pixel's Red to average
            pixel.setRed (average);
            // set Pixel's Green to average
            pixel.setGreen(average);
            // set Pixel's Blue to average
            pixel.setBlue(average);
            
            
        }
        // outImage is your answer
        return outImage;
    }
    public void selectAndConvert(){
    DirectoryResource dr = new DirectoryResource();
    for (File f: dr.selectedFiles())
    {
        ImageResource inImage = new ImageResource(f);
        ImageResource gray = makeGray(inImage);
        gray.draw();
        
    }
    
    
}

}

