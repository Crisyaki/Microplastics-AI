import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

class Imagen {
    private double imageHeight;
    private double imageWidth;
    private BufferedImage imgBuffer;
    private String name;
    
    public Imagen(double imageHeight, double imageWidth, BufferedImage imgBuffer) {
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.imgBuffer = imgBuffer;
    }
    
    public Imagen(File f) {
        try {
            this.imgBuffer = ImageIO.read(f);
            this.imageHeight=imgBuffer.getHeight();
            this.imageWidth=imgBuffer.getWidth();
            this.name = f.getName();
            
        } catch (IOException ex) {
            Logger.getLogger(Imagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    public String getName() {
        return name;
    }
    
    public double getHeigth(){
        return imageHeight;
    }
    public double getWidth(){
        return imageWidth;
    }
    BufferedImage getBufferedImage() {
        return this.imgBuffer;
    }
    
}
