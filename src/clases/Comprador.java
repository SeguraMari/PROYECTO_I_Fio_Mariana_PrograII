package clases;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import vista.FrmPedidoEnProceso;



/**
 * Representa el comprador que realiza pedidos de manera independiente
 * utilizando un hilo.
 * 
 * @author Graciela
 */


public class Comprador extends Thread {

    private String nomComprador;
    private Inventario inventario;
    private FrmPedidoEnProceso proceso;
    private int avanceActual = 0;
    private int numFila;

    public Comprador(String nomComprador, Inventario inventario, FrmPedidoEnProceso proceso, int numFila) {
        this.nomComprador = nomComprador;
        this.inventario = inventario;
        this.proceso = proceso;
        this.numFila = numFila;

    }

    @Override
    public void run() {

        Random random = new Random();
        ArrayList<Producto> catalogo = inventario.getListaProductos();

        for (int i = 1; i <= 3; i++) {

            try {
                Thread.sleep(random.nextInt(1000) + 500);
            } catch (InterruptedException e) {

            }

            //Crear el pedido para este intento 
            Pedido pedidoActual = new Pedido(nomComprador);

            //Selecciona al azar entre 1 y 3 productos distintos
            int cantProdEnPedido = random.nextInt(3) + 1;

            for (int j = 0; j < cantProdEnPedido; j++) {

                Producto prodAleatorio = catalogo.get(random.nextInt(catalogo.size()));
                int cantUnidad = random.nextInt(3) + 1;

                //Evitar duplicar el mismo producto en el mismo pedido
                boolean yaExiste = false;
                for (DetallePedido det : pedidoActual.getListaDetalles()) {
                    if (det.getProducto().getNombre().equals(prodAleatorio.getNombre())) {
                        yaExiste = true;
                        break;
                    }

                }

                if (!yaExiste) {
                    pedidoActual.agregarDetalle(new DetallePedido(prodAleatorio, cantUnidad));
                }

            }
            
            //Evitar productos vacíos

            if (pedidoActual.getListaDetalles().isEmpty()) {
                pedidoActual.agregarDetalle(new DetallePedido(catalogo.get(0), 1));

            }
            pedidoActual.setEstado("Procesando");
            avanceActual = i;

            proceso.actualizarFila(numFila, avanceActual + " de 3 ", "Procesando");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");

            }

            //Evaluamos descuento en el inventario
            boolean aceptado = inventario.procesoDescuentoPedido(pedidoActual);

            if (aceptado) {
                pedidoActual.setEstado("Aceptado");
            } else {
                pedidoActual.setEstado("Rechazado");
            }
            
            proceso.agregarPedido(pedidoActual, i);

            proceso.actualizarFila(numFila, avanceActual + " de 3", pedidoActual.getEstado());

            proceso.actualizarInventario(inventario);

        }

        proceso.actualizarFila(numFila, "3 de 3", "Finalizado");

    }

    public String getNomComprador() {
        return nomComprador;
    }

    public int getAvanceActual() {
        return avanceActual;
    }

}
