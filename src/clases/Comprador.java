package clases;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Graciela
 */
public class Comprador extends Thread {

    private String nomComprador;
    private Inventario inventario;
    private JTable table;
    private int avanceActual = 0;

    public Comprador(String nomComprador, Inventario inventario) {
        this.nomComprador = nomComprador;
        this.inventario = inventario;
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

            if (pedidoActual.getListaDetalles().isEmpty()) {
                pedidoActual.agregarDetalle(new DetallePedido(catalogo.get(0), 1));

            }
            pedidoActual.setEstado("Procesando");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {

            }

            boolean aceptado = inventario.procesoDescuentoPedido(pedidoActual);

            if (aceptado) {
                pedidoActual.setEstado("Aceptado");
            } else {
                pedidoActual.setEstado("Rechazado");
            }

            avanceActual = i;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Comprador: " + nomComprador + " | Intento " + avanceActual + " de 3 | Estado: " + pedidoActual.getEstado());

                }

            });

        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hilo de " + nomComprador + " ha finalizado.");
            }
        });
    }

    public String getNomComprador() {
        return nomComprador;
    }

    public int getAvanceActual() {
        return avanceActual;
    }
    
    

}


