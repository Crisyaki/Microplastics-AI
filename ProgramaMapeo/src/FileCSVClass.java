import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import org.apache.commons.csv.*;

public class FileCSVClass {
    String imgName;
    Segmentation segmet;
    String NAME_CSV_FILE;
    
    //Vamos a crear un nuevo csv para cada imagen ya en python en el programa
    //principal se realizará un join de todos los csv
   
    public FileCSVClass(String imgName, Segmentation segment){
        this.imgName = imgName.substring(0,imgName.length()-4);
        this.segmet = segment;
        this.NAME_CSV_FILE = "./CSV/DatosImg" + imgName.substring(0,imgName.length()-4) 
                               + "Size" + segmet.getWxh()
                               + "Seg" + segmet.getPixelesSeg() + ".csv";
    }
    //ejemplo de CSV: https://www.callicoder.com/java-read-write-csv-file-apache-commons-csv/
    
    public void writeCSV() throws IOException{
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(NAME_CSV_FILE));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Nombre Segmento","Plasticos"));
        //imgID1-R0C0
        int nSegmentos = segmet.getnSegmentos();
        for (int i = 0; i < nSegmentos; i++) { //Row
            for (int j = 0; j < nSegmentos; j++) { //Colums
                String s = "imgID" + imgName + "-R" + i + "C" + j;
                csvPrinter.printRecord(s,segmet.getValue(i, j));    
            }      
        }
        csvPrinter.flush();  
    }
    
    //Modificar por si el nombre del archivo está mal y los pixeles no coinciden
    public void loadCSV() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(NAME_CSV_FILE));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader() //detectamos el Header automático
                    .withIgnoreHeaderCase()
                    .withTrim());
        CSVtoSeg(segmet,csvParser);
        
    }
    private void CSVtoSeg(Segmentation seg, CSVParser csvParser){
        //Hay que hacer el recuento de Row y Colums manualmente
        int r = 0;
        int c = 0;
        for (CSVRecord csvRecord : csvParser) {
            int value = Integer.parseInt(csvRecord.get("Plasticos"));
            if (value == 0) seg.setZero(r, c);
            else seg.setOne(r, c);
            //-----
            if (c == seg.getnSegmentos()-1){
                r++;
                c = 0;
            } else c++;
        }
    }
    
    public void importCSV(File file) throws IOException{
        Object [] pixelesSegmento ={"64","32","16"}; 
        Object opcion = JOptionPane.showInputDialog(null,
                "Selecciona un tamaño de segmento correspondiente al import del CSV", 
                "Elegir",JOptionPane.QUESTION_MESSAGE,null,pixelesSegmento, pixelesSegmento[0]);
        if(opcion != null){//no se ha cancelado la importación
            int pSeleccionados = segmet.getPixelesSeg();
            int pImport = Integer.parseInt(opcion.toString());
            System.out.println("CSVPixels= " + pImport + "SelectPixels= " + pSeleccionados);
            //De momento la importación la hacemos de menor precisión a mayor precisión
            //Ej de 64px a 16px
            if(pSeleccionados > pImport) return; 
            
            //Añadimos la opción de ver el tamaño de la imagen
            Object [] tamaño ={"2240","2048"}; 
            Object opcion2 = JOptionPane.showInputDialog(null,
                "Selecciona un tamaño de segmento correspondiente al import del CSV", 
                "Elegir",JOptionPane.QUESTION_MESSAGE,null,tamaño, tamaño[0]);
            
            if(opcion2 != null){//no se ha cancelado la importación
                int pTamaño = Integer.parseInt(opcion2.toString());
                
                //Creamos un segmento nuevo de los n segmento correspondientes
                // con el nº de pixeles de segmentación del csv a importar
                Segmentation seg = new Segmentation(segmet.getWxh()/pImport,pTamaño);
                System.out.println("New SegmentationCSV.length= " + seg.getnSegmentos());
                System.out.println("Segmentation.length= " + segmet.getnSegmentos());
            
                Reader reader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                                .withFirstRecordAsHeader() //detectamos el Header automático
                                .withIgnoreHeaderCase()
                                .withTrim());
                CSVtoSeg(seg,csvParser);
                System.out.println("El CSVtoSeg acabó bien");
            
                //Para saber que pixel corresponde cada obtenemos el factor de conversión 
                int factorConv = (int) pImport/pSeleccionados;
                int correspX = 0;
                int correspY = 0;
                int value = 0;
                System.out.println("Factor de conversión = " +  factorConv);
                //Recorremos los segmentos pequeños (pSeleccionados)
                for (int i = 0; i < segmet.getnSegmentos(); i++) { //Row
                    //Divimos para saber a que segmento del import pertenece
                    correspX = (int) i/factorConv;
                    
                    for (int j = 0; j < segmet.getnSegmentos(); j++) { //Colums
                        correspY = (int) j/factorConv;
                        //Obtenemos el valor y se lo asignamos a todas las casillas 
                        //que se encuentren en la equivalencia
                        value = seg.getValue(correspX, correspY);
                        if (value == 0) segmet.setZero(i, j);
                        else segmet.setOne(i, j);
                    }      
                }
                System.out.println("correspX= " + correspX + "correspY= " + correspY);
            }

        }
    }
}
