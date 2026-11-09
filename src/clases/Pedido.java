package clases;

import java.util.ArrayList;

/**
 * Esta clase representa un pedido completo. Guarda los productos solicitados,
 * calcula el total mediante recursividad Mantiene el estado de los pedidos.
 *
 * @author Graciela
 */
public class Pedido {

    private String nomComprador;
    private String estado;

    private ArrayList<DetallePedido> listaDetalles;

    public Pedido(String nomComprador) {
        this.nomComprador = nomComprador;
        this.estado = "En espera";
        this.listaDetalles = new ArrayList<>();
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<DetallePedido> getListaDetalles() {
        return listaDetalles;
    }



    /**
     * Calcula el total del pedido utilizando recursividad.
     * @param indice posicion del detalle que se va a calcular.
     * @return total acumulado de los detalles del pedido.
     */    
    
    
    public double calcularTotalRec(int indice) {

        if (indice >= listaDetalles.size()) {
            return 0.0;
        }

        return listaDetalles.get(indice).getSubtotal() + calcularTotalRec(indice + 1);
    }

}
