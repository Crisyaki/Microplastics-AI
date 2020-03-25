import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;


public class PanelImagen extends JPanel{
   
    private double panelHeight; 
    private double panelWidth;
    private Imagen img;  
    private Segmentation segment;
    private boolean red = true;
    
    private int nSegm = 35;
    private int pixeles = 64;
    private BasicStroke stroke = new BasicStroke(3);

    public void setImagen(Imagen i){
        this.img=i; 
    }
    
    public void setRed(boolean red) {
        this.red = red;
    }
    
    public void setSegmentation (Segmentation s){
        this.segment = s;
        nSegm = segment.getnSegmentos();
        pixeles = segment.getPixelesSeg();
        System.out.println("pixeles en PanelImagen: "+ pixeles);
        if(nSegm >= 140) stroke = new BasicStroke(1);
    }

    @Override
    public void paintComponent(Graphics g){
        panelHeight = this.getSize().getHeight();
        panelWidth = this.getSize().getWidth();  
        try{
            //g.drawImage(img.getBufferedImage(), 0, 0, null);
            g.drawImage(img.getBufferedImage(), 0, 0, (int)img.getWidth(),(int) img.getHeigth(), null);
            paintCuadrados(g);
            if (red) paintRejilla(g);
        }catch (NullPointerException ex) {}   
    }
    
    private void paintRejilla(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.red);
        for(int i = 0; i<nSegm;i++){
            g2.drawLine(i*pixeles, 0, i*pixeles, (int)img.getHeigth());//lineas verticales
            g2.drawLine(0, i*pixeles,(int)img.getWidth(), i*pixeles);//lineas horizontales
        }
    }
    
    private void paintCuadrados(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Composite compOriginal = g2.getComposite();
        
        g2.setComposite( AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 0.4)); //Añadimos trasparencia
        g2.setColor(Color.red);
        
        int[][] m = segment.getMatriz();
        for (int i = 0; i < m.length; i++) { 
            for (int j = 0; j < m.length; j++) { //es una matriz cuadrada
                if (m[i][j] == 1){
                    //System.out.println("Hay un 1 en: " + i + " " + j);
                    Rectangle r = new Rectangle(i*pixeles, j*pixeles, pixeles, pixeles);
                    g2.fill(r);
                }                
            } 
        }
        g2.setComposite( compOriginal ); //Dejamos la trasparencia normal
    }
}

  

