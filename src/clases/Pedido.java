package clases;

import java.util.ArrayList;

/**
 * Esta clase representa un pedido completo.
 * Guarda los productos solicitados, calcula el total mediante recursividad
 * Mantiene el estado de los pedidos.
 * 
 * @author Graciela
 */
public class Pedido {

    private String nomComprador;
    private int precio;
    String estado;

    ArrayList<DetallePedido> listaDetalles;

    public Pedido(String nomComprador) {
        this.nomComprador = nomComprador;
        this.estado = "En espera";
        this.listaDetalles = listaDetalles = new ArrayList<>();
    }

    public void agregarDetalle(DetallePedido detalle) {
        this.listaDetalles.add(detalle);
    }

    public String getNomComprador() {
        return nomComprador;
    }

    public void setNomComprador(String nomComprador) {
        this.nomComprador = nomComprador;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<DetallePedido> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(ArrayList<DetallePedido> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }

    //Funcion Recursiva
    public double calcularTotalRec(int indice) {

        if (indice >= listaDetalles.size()) {
            return 0.0;
        }

        return listaDetalles.get(indice).getSubtotal() + calcularTotalRec(indice + 1);
    }

}
