public class Main {

    public static void main(String[] args) {
        // 1. Crear un cliente
        Cliente cliente1 = new Cliente("Agapito Piedralisa", "c/ Río Seco, 2", "123456789");

        // 2. Crear una nueva factura para el cliente
        Factura factura1 = new Factura(cliente1, "18/4/2011");

        // 3. Crear los productos
        Producto p1 = new Producto("Ratón USB", 8.43);
        Producto p2 = new Producto("Memoria RAM 2GB", 21.15);
        Producto p3 = new Producto("Altavoces", 12.66);

        // 4. Añadir los productos y sus cantidades a la factura
        factura1.anadirProducto(p1, 1);
        factura1.anadirProducto(p2, 2);
        factura1.anadirProducto(p3, 1);

        // 5. Imprimir la factura completa
        factura1.imprimir();

        // Ejemplo: Crear una segunda factura para demostrar que el ID se incrementa
        System.out.println("\n================================================");
        System.out.println("Creando una segunda factura para demostración...");
        System.out.println("================================================\n");
        
        Cliente cliente2 = new Cliente("Benito Camelas", "Av. Siempreviva, 742", "987654321");
        Factura factura2 = new Factura(cliente2, "19/4/2011");
        factura2.anadirProducto(p1, 5); // 5 ratones
        factura2.imprimir();
    }
}