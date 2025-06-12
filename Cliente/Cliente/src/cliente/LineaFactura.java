public class LineaFactura {
    private int cantidad;
    private Producto producto;
    private int numeroLinea;

    public LineaFactura(int numeroLinea, Producto producto, int cantidad) {
        this.numeroLinea = numeroLinea;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularPrecioTotal() {
        return this.cantidad * this.producto.getPrecioUnitario();
    }
    
    public void imprimirLinea() {
        System.out.printf("%d;%s;%d;%.2f;%.2f\n",
                this.numeroLinea,
                this.producto.getDescripcion(),
                this.cantidad,
                this.producto.getPrecioUnitario(),
                this.calcularPrecioTotal());
    }
}