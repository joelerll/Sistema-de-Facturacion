/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductoOpciones;

import Clases.ProductoVO;
import java.util.List;

/**
 *
 * @author joelerll
 */
public class ListProductos {
    
     private List<ProductoVO> productosOB;

    public ListProductos(List<ProductoVO> productosOB) {
        this.productosOB = productosOB;
    }
     
     
    public static List<ProductoVO> Productos(List <ProductoVO> productos)
    {
        return productos;
    }
}
