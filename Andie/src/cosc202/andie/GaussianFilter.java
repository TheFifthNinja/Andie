package cosc202.andie;

import java.awt.Graphics;
import java.awt.image.*;
/**
 * <p>
 * ImageOperation to apply a Gaussian filter to an image
 * </p>
 * 
 * <p>
 * A Gaussian filter reduces noise around an image and subsequently blurs an image. This is done by
 * using a mathematical function, and can be implemented by a convoloution.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jayden Smith
 * @version 3.0
 */
public class GaussianFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Gaussian filter with the given radius.
     * </p>
     * 
     * <p>
     * The values of the sigma are determined using the 'radius' of the convolution kernel used.
     * A radius of 1 means sigma = 1/3, 2 is 2/3, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed GaussianFilter
     */
    GaussianFilter(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Construct a Gaussian filter with the default size.
     * </p
     * >
     * <p>
     * By default, a Gaussian filter has radius 1.
     * </p>
     * 
     * @see GaussianFilter(int)
     */
    GaussianFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Gaussian filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Gaussian filter is implemented via convolution.
     * The size of the convolution kernel is specified by the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Gaussian filter to.
     * @return The resulting (blurred)) image.
     */
    public BufferedImage apply(BufferedImage input) {
        int length = (2*radius+1);

        int width = input.getWidth();
        int height = input.getHeight();
        float [][] twoDArray = new float[length][length];

        // The values for the kernel   
        double sigma = radius/3.0;    
        int centre = radius;
        for(int i = 0; i<length; i++){
            for(int j = 0; j<length; j++){
                    float x = (float) centre - j;
                    if(x < 0.0){
                        x *= -1;
                    }
                    float y = (float) centre - i;
                    if(y < 0.0){
                        y *= -1;
                    }

                    twoDArray[i][j] = G(x,y,sigma);
                
            }
        }
        int size = (2*radius+1)*(2*radius+1);
        float[] oneDArray = new float[size];
        //Flatten 2D array to 1D array...
        int location = 0;
        for(int i = 0; i < length; i ++) 
            for(int j = 0; j < length; j ++){                           
                oneDArray[location] = twoDArray[i][j];
                location++;
            }  
        
        // check if the sum of all the elements is greater than 1
        float sum = 0.0f;
        for(float a: oneDArray){
            sum += a;
        }
        //if greater than 1 divide each element by the sum
        if(sum >= 1.0){
            for(int i = 0; i<oneDArray.length; i++){
                oneDArray[i] = oneDArray[i]/sum;
            }
        }
        
        // Make a 3x3 filter from the array
        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, oneDArray);
        // Apply this as a convolution - same code as in MeanFilter
        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage newImage = new BufferedImage(width + 2*radius, height+2*radius, input.getType());//create a new image that is double the size
        Graphics g = newImage.getGraphics();// make new image a graphic which is now twice as big
        g.drawImage(input, radius, radius, null); //draws the input image to the larger new Image area
        g.dispose();

        BufferedImage output = new BufferedImage(input.getColorModel(), 
                                input.copyData(null), 
                                input.isAlphaPremultiplied(), null);
        convOp.filter(newImage, output);
        return output;
    }

    /**
     * <p>
     * Find the needed result from the Gaussian Equation
     * </p>
     * 
     * <p>
     * To get the result of the gaussian equation we need to pass in some variables
     * This result is then used in the BufferedImage method
     * The parameters x and y are dependent on the location you are at within the array
     * and sigma is determined by using the {@link radius}.  
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param x the x axis position in the array
     * @param y the y axis position in the array
     * @param sigma the value of sigma which is determined by the radius
     * @return The answer to the equation.
     */
    public float G(double x, double y, double sigma){

        double powerOfE = -1 * ((Math.pow(x, 2) + Math.pow(y, 2))/(2 * Math.pow(sigma, 2)));
        double answer = ((1/(2 * Math.PI * Math.pow(sigma, 2))) * Math.pow(Math.E, powerOfE));

        return (float) answer;

    }

}