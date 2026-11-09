package clases;

import java.util.ArrayList;

/**
 * Clase para administrar los productos disponibles y controlar que los 4 compradores 
 * no descuenten las mismas existencias al mismo tiempo.
 *
 *
 * @author Graciela
 */
public class Inventario {

    private ArrayList<Producto> listaProductos;

    public Inventario() {
        listaProductos = new ArrayList<>();
        inicializarCatalogo();
    }

    private void inicializarCatalogo() {

        listaProductos.add(new Producto("Café Latte", 1500, 10));
        listaProductos.add(new Producto("Té frío", 1000, 8));
        listaProductos.add(new Producto("Empanada", 1500, 12));
        listaProductos.add(new Producto("Sándwich", 2500, 8));
        listaProductos.add(new Producto("Jugo natural", 1800, 6));
        listaProductos.add(new Producto("Galleta", 800, 15));
    }

    public ArrayList<Producto> getListaProductos() {

        return listaProductos;
    }

    /**
     * Método Sincronizado. Evita que varios hilos modifiquen las existncias al mismo tiempo.
     * @param pedido cuyos productos se van a descontar.
     * 
     * @return true si el pedido puede procesarse, false si no hay existencia suficiente.
     */
    
    
    public synchronized boolean procesoDescuentoPedido(Pedido pedido) {

        for (DetallePedido detalle : pedido.getListaDetalles()) {
            Producto prodCatalogo = detalle.getProducto();

            if (prodCatalogo.getExistenciaActual() < detalle.getCantidad()) {
                return false; // Faltó Stock, se rechaza
            }

        }
        
        //Descontar las existencias

        for (DetallePedido detalle : pedido.getListaDetalles()) {
            Producto prodCatalogo = detalle.getProducto();
            prodCatalogo.descontarStock(detalle.getCantidad());

        }

        return true;

    }
    
    public ArrayList<Producto> getProductos(){
        return listaProductos;
    }

    
                
    }
    

