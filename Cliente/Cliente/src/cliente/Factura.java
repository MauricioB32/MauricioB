import java.util.ArrayList;
import java.util.List;

public class Factura {
    // Variable estática para el ID autoincremental de la factura
    private static int sigid = 12345;
    private static final double IVA = 0.18; // 18% de IVA

    private int numeroFactura;
    private String fecha;
    private Cliente cliente;
    private List<LineaFactura> lineas;

    /**
     * Constructor que inicializa la factura, asigna un nuevo ID y
     * establece el cliente y la fecha.
     */
    public Factura(Cliente cliente, String fecha) {
        this.numeroFactura = getSigid(); // Llama al método para obtener y actualizar el ID
        this.cliente = cliente;
        this.fecha = fecha;
        this.lineas = new ArrayList<>();
    }

    /**
     * Devuelve el valor actual de sigid y luego lo incrementa para la siguiente factura.
     * Es estático para que el contador sea compartido por todas las instancias de Factura.
     * @return El número de factura único.
     */
    public static int getSigid() {
        return sigid++;
    }

    /**
     * Añade un nuevo producto a la factura.
     * @param producto El producto a añadir.
     * @param cantidad La cantidad de ese producto.
     */
    public void anadirProducto(Producto producto, int cantidad) {
        int numeroDeLinea = this.lineas.size() + 1;
        LineaFactura nuevaLinea = new LineaFactura(numeroDeLinea, producto, cantidad);
        this.lineas.add(nuevaLinea);
    }

    /**
     * Imprime la factura completa con el formato especificado.
     */
    public void imprimir() {
        System.out.println("Factura nº: " + this.numeroFactura);
        System.out.println("Fecha: " + this.fecha);
        System.out.println("\nDatos del cliente");
        System.out.println("----------------------");
        System.out.println("Nombre: " + this.cliente.getNombre());
        System.out.println("Dirección: " + this.cliente.getDireccion());
        System.out.println("Teléfono: " + this.cliente.getTelefono());
        System.out.println("\nDetalle de la factura");
        System.out.println("---------------------");
        System.out.println("Línea;Producto;Cantidad;Precio ud.;Precio total");
        System.out.println("--");

        double subtotal = 0;
        for (LineaFactura linea : this.lineas) {
            linea.imprimirLinea();
            subtotal += linea.calcularPrecioTotal();
        }

        double montoIva = subtotal * IVA;
        double total = subtotal + montoIva;

        System.out.println("\n");
        System.out.printf("Subtotal: %.2f €\n", subtotal);
        System.out.printf("IVA (%.0f%%): %.2f €\n", IVA * 100, montoIva);
        System.out.printf("TOTAL: %.4f €\n", total);
    }
}