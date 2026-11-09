package clases;

/**
 * Clase que representa un producto disponible en la cafetería.
 * Almacena el nombre, precio y las existencias iniciales y actuales.
 * 
 * @author Graciela
 */


public class Producto {

    private String nombre;
    private int precio;
    private int existenciaInicial;
    private int existenciaActual;
    

    public Producto(String nombre, int precio, int existenciaInicial) {
        this.nombre = nombre;
        this.precio = precio;
        this.existenciaInicial = existenciaInicial;
        this.existenciaActual = existenciaInicial;
     
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

    public boolean descontarStock(int cantidad) {

        if (this.existenciaActual >= cantidad) {
            this.existenciaActual -= cantidad;
            return true;

        }

        return false;

    }

}
