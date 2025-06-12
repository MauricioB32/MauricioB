public class Producto {
    private String descripcion;
    private double precioUnitario;

    public Producto(String descripcion, double precioUnitario) {
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }
}