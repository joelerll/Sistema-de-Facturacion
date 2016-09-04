/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Clases.ClienteVO;
import factura.ProductosCanasta;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

/**
 *
 * @author joelerll
 */
public class PDF {
    public static void print(String nFactura, Date fecha, String empleado, ClienteVO cliente, List<ProductosCanasta> productos, BigDecimal subtotal) throws IOException{
        DateFormat dateAnio = new SimpleDateFormat("yyyy");
        DateFormat dateMes = new SimpleDateFormat("MM");
        DateFormat dateDia = new SimpleDateFormat("dd");
        String direccion = "facturas"+"/"+dateAnio.format(fecha)+ "/"+ dateMes.format(fecha)+"/"+dateDia.format(fecha);
        File dir = new File(direccion);
        if(dir.exists()){
            System.out.println("Ya exitiste la carpeta");
        }else{
            dir.mkdirs();
        }
        DateFormat dateF = new SimpleDateFormat("kk-mm-ss_dd-MM-yyyy");
        String fileName = direccion + "/"+ dateF.format(fecha) + ".pdf";
	String imagem = "bill-512.png";
	System.out.println("Se creo su factura");
	PDDocument doc = new PDDocument();
	PDPage page = new PDPage(PDRectangle.A4);
	PDPageContentStream content = new PDPageContentStream(doc, page);
	doc.addPage(page);

	PDImageXObject pdImage = null;
	try {
	    pdImage = PDImageXObject.createFromFile(imagem, doc);
        } catch (IOException e1) {
            System.out.println("ERROR no cargo logo factura");
	}
	float scale = 0.2f;
	
	// Titulo factura     
	content.beginText();
	content.setFont(PDType1Font.HELVETICA, 26);
        content.setNonStrokingColor(Color.BLUE);
        content.newLineAtOffset(250, 785);
        content.showText("Factura Bazar A&J");
	content.endText();
        
        // Logo
        content.drawImage(pdImage, 30, 725, pdImage.getWidth()*scale, pdImage.getHeight()*scale);
        
        //Cuadro info empresa
        content.setNonStrokingColor(Color.BLACK);
        content.addRect(25, 700, 280, 1);
        content.setNonStrokingColor(Color.BLACK);
        content.addRect(304, 700, 1, -65);
        content.setNonStrokingColor(Color.BLACK);
        content.addRect(25, 634, 280, 1);
        content.setNonStrokingColor(Color.BLACK);
        content.addRect(25, 700, 1, -65);
        content.fill();
        
        // Texto info empresa
        content.beginText();
        content.setLeading(15); // da el salto de pagina
        content.setFont(PDType1Font.COURIER, 10);
        content.newLineAtOffset(30, 685);
        content.showText("Direccion: " + "Coop. Universitaria Mz 258 Solar 9");
        content.newLine();
        content.showText("Ciudad   : " + "Guayaquil, Ecuador");
        content.newLine();
        content.showText("Telefono : " + "042937914");
        content.newLine();
        content.showText("RUC      : " + "1710034065");
        content.endText();
        
         //Cuadro datos factura
        content.setNonStrokingColor(Color.BLACK); //arriba
        content.addRect(380, 700, 200, 1);
        content.setNonStrokingColor(Color.BLACK);  // derecha
        content.addRect(579, 700, 1, -65);
        content.setNonStrokingColor(Color.BLACK); // abajo
        content.addRect(380, 634, 200, 1);
        content.setNonStrokingColor(Color.BLACK); // izquierda
        content.addRect(380, 700, 1, -65);
        content.fill();
        
        DateFormat dateFormat = new SimpleDateFormat("dd - MM - yyyy");
        DateFormat dateFormath = new SimpleDateFormat("h:mm a");
        // Texto datos factura
        content.beginText();
        content.setLeading(15); // da el salto de pagina
        content.setFont(PDType1Font.COURIER, 10);
        content.newLineAtOffset(390, 685);
        content.showText("Factura N: " + nFactura);
        content.newLine();
        content.showText("Fecha    : " + dateFormat.format(fecha));
        content.newLine();
        content.showText("Hora     : " + dateFormath.format(fecha));
        content.newLine();
        content.showText("Empleado : " + empleado);
        content.endText();
        
        // Linea Divisoria
        content.setNonStrokingColor(Color.BLUE); // abajo
        content.addRect(0, 620, 595, 1);
        content.fill();
        
        // Datos Cliente
        content.beginText();
        content.setLeading(18); // da el salto de pagina
        content.setNonStrokingColor(Color.BLACK);
        content.setFont(PDType1Font.COURIER, 13);
        content.newLineAtOffset(30, 600);
        content.showText("Nombre   : " + cliente.getNombre_C() + cliente.getApellido_C());
        content.newLine();
        content.showText("Direcion : " + cliente.getDireccion_C());
        content.newLine();
        content.showText("Telefono : " + cliente.getConvencional_C());
        content.endText();
        
        content.setNonStrokingColor(Color.BLUE); // abajo
        content.addRect(0, 550, 595, 1);
        content.fill();
        
        content.setNonStrokingColor(Color.BLACK); // numero
        content.addRect(30, 510, 50, 15);
        content.fill(); 
        content.beginText();
        content.setLeading(0);
        content.setNonStrokingColor(Color.WHITE);
        content.setFont(PDType1Font.COURIER_BOLD, 13);
        content.newLineAtOffset(45, 515);
        content.showText("N." );
        content.endText();
        
        content.setNonStrokingColor(Color.BLACK); // cantidad
        content.addRect(90, 510, 40, 15);
        content.fill();
        content.beginText();
        content.setLeading(18); // da el salto de pagina
        content.setNonStrokingColor(Color.WHITE);
        content.setFont(PDType1Font.COURIER_BOLD, 13);
        content.newLineAtOffset(94, 515);
        content.showText("Cant" );
        content.endText();
        
        content.setNonStrokingColor(Color.BLACK); // Descripcion
        content.addRect(140, 510, 270, 15);
        content.fill();
        content.beginText();
        content.setLeading(18); // da el salto de pagina
        content.setNonStrokingColor(Color.WHITE);
        content.setFont(PDType1Font.COURIER_BOLD, 13);
        content.newLineAtOffset(230, 515);
        content.showText("Descripcion" );
        content.endText();
        
        content.setNonStrokingColor(Color.BLACK); // cantidad
        content.addRect(430, 510, 70, 15);
        content.fill();
        content.beginText();
        content.setLeading(18); // da el salto de pagina
        content.setNonStrokingColor(Color.WHITE);
        content.setFont(PDType1Font.COURIER_BOLD, 13);
        content.newLineAtOffset(440, 515);
        content.showText("P.Unit" );
        content.endText();
        
        content.setNonStrokingColor(Color.BLACK); // importe
        content.addRect(510, 510, 70, 15);
        content.fill();
        content.beginText();
        content.setLeading(18); // da el salto de pagina
        content.setNonStrokingColor(Color.WHITE);
        content.setFont(PDType1Font.COURIER_BOLD, 13);
        content.newLineAtOffset(515, 515);
        content.showText("Importe" );
        content.endText();

        int cont = 1;
        int vertical = 490;
        // Productos
        for (ProductosCanasta p : productos){
            content.beginText();
            content.setNonStrokingColor(Color.BLACK);
            content.setFont(PDType1Font.COURIER, 13);
            content.newLineAtOffset(45, vertical);
            content.showText("" +  cont);
            content.endText();
            
            content.beginText();
            content.setNonStrokingColor(Color.BLACK);
            content.setFont(PDType1Font.COURIER, 13);
            content.newLineAtOffset(105, vertical);
            content.showText("" +  p.getCantidad());
            content.endText();
            
            content.beginText();
            content.setNonStrokingColor(Color.BLACK);
            content.setFont(PDType1Font.COURIER, 11);
            content.newLineAtOffset(145, vertical);
            content.showText("" +  p.getNombre());
            content.endText();
            
            content.beginText();
            content.setNonStrokingColor(Color.BLACK);
            content.setFont(PDType1Font.COURIER, 13);
            content.newLineAtOffset(440, vertical);
            content.showText("" +  p.getPrecio_venta());
            content.endText();
            
            content.beginText();
            content.setNonStrokingColor(Color.BLACK);
            content.setFont(PDType1Font.COURIER, 13);
            content.newLineAtOffset(520, vertical);
            content.showText("" +  p.getPrecio_venta().multiply(new BigDecimal(p.getCantidad())));
            content.endText();
            
            cont ++;
            vertical -=20;
        }
        
        // max x pagina 595 x 841.8898
        content.setNonStrokingColor(Color.BLUE); // total
        content.addRect(0, 60, 595, 1);
        content.fill();
        
        /// TOTAL
        content.beginText();
        content.setNonStrokingColor(Color.BLACK);
        content.setFont(PDType1Font.COURIER_BOLD, 20);
        content.newLineAtOffset(500,35);
        content.showText(subtotal.multiply(new BigDecimal(0.14)).add(subtotal).setScale(2,3).toString());
        content.endText();
        
        content.beginText();
        content.newLineAtOffset(510,20);
        content.setFont(PDType1Font.COURIER_BOLD, 15);
        content.showText("TOTAL");
        content.endText();
        
        // IVA
        content.beginText();
        content.setNonStrokingColor(Color.BLACK);
        content.setFont(PDType1Font.COURIER_BOLD, 20);
        content.newLineAtOffset(430,35);
        content.showText("" + 14 + "%");
        content.endText();
        
        content.beginText();
        content.newLineAtOffset(430,20);
        content.setFont(PDType1Font.COURIER_BOLD, 15);
        content.showText("IVA");
        content.endText();
        
        // Subtotal
        content.beginText();
        content.setNonStrokingColor(Color.BLACK);
        content.setFont(PDType1Font.COURIER_BOLD, 20);
        content.newLineAtOffset(300,35);
        content.showText(subtotal.toString());
        content.endText();
        
        content.beginText();
        content.newLineAtOffset(300,20);
        content.setFont(PDType1Font.COURIER_BOLD, 15);
        content.showText("SUBTOTAL");
        content.endText();
        
	content.close();
	doc.save(fileName);
	doc.close();
        Process p = Runtime.getRuntime().exec(new String[]{"xpdf",fileName});
        System.out.println("your file created in : "+ System.getProperty("user.dir"));
    }
}
