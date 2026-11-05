
package clases;

/**
 *
 * @author Graciela
 */
public class Producto {
    
    private String nombre;
    private int precio;
    private int existenciaInicial;
    private int existenciaActual;

    public Producto(String nombre, int precio, int existenciaInicial, int existenciaActual) {
        this.nombre = nombre;
        this.precio = precio;
        this.existenciaInicial = existenciaInicial;
        this.existenciaActual = existenciaActual;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getExistenciaInicial() {
        return existenciaInicial;
    }

    public int getExistenciaActual() {
        return existenciaActual;
    }
    
    
    
    
    
    
}
