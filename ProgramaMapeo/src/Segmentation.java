
import java.awt.Point;


public class Segmentation {
    int[][] matriz;
    int nSegmentos;
    int wxh; //= 2240;
    
    public Segmentation(int nSegmentos, int wxh){
        this.matriz = new int[nSegmentos][nSegmentos];
        this.nSegmentos = nSegmentos;
        this.wxh = wxh;
    }
    
    public void resetMatriz(){
        this.matriz = new int[nSegmentos][nSegmentos];
    }
    
    public void allPlastics(){
        for (int i = 0; i < matriz.length; i++) 
            for (int j = 0; j < matriz.length; j++) 
                matriz[i][j] = 1;                      
    }
    
    public void setZero(int x, int y){
        matriz[x][y] = 0;
    }
    
    public void setZero(Point p){
        matriz[p.x][p.y] = 0;
    }
    
    public void setOne(int x, int y){
        matriz[x][y] = 1;
    }
    
    public void setOne(Point p){
        matriz[p.x][p.y] = 1;
    }
    
    public int[][] getMatriz(){
        return matriz;
    }
    
    public int getValue(int x, int y){
        return matriz[x][y];
    }

    public int getnSegmentos() {
        return nSegmentos;
    }
    
    public int getPixelesSeg(){
        return wxh/nSegmentos;
    }

    public int getWxh() {
        return wxh;
    }
    
    
}
