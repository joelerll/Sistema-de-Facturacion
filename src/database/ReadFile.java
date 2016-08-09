/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author joelerll
 */
public class ReadFile {
    private String clave;
    private String nombreBaseDatos;

    public ReadFile() {
    }
    
    public static ReadFile leer(){
        ReadFile f = new ReadFile();
        String fileName="datos.txt";
        int count = 1;
        try{
          FileReader inputFile = new FileReader(fileName);
          BufferedReader bufferReader = new BufferedReader(inputFile);
          String line;
          while ((line = bufferReader.readLine()) != null)   {
              if (count == 1)
              {
                  f.setClave(line);
              }else{
                  f.setNombreBaseDatos(line);
              }
            count ++;
          }
          bufferReader.close();
        }catch(Exception e){
          System.out.println("No se pudo leer el archivo" + e.getMessage());                      
        }
        return f;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    public void setNombreBaseDatos(String nombreBaseDatos) {
        this.nombreBaseDatos = nombreBaseDatos;
    }
    
    
}
