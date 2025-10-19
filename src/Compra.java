public class Compra {
    private String ruta;
    private String cedula;
    private String nombre;
    private int cantidad;
    private double precioUnitario;

    public Compra(String ruta, String cedula, String nombre, int cantidad, double precioUnitario) {
        this.ruta = ruta;
        this.cedula = cedula;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // devuelve la ruta seleccionada
    public String getRuta() {
        return ruta;
    }

    // devuelve la cédula del cliente
    public String getCedula() {
        return cedula;
    }

    // devuelve el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    // devuelve la cantidad de boletos
    public int getCantidad() {
        return cantidad;
    }

    // devuelve el precio de un boleto
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    // calcula el valor total de la compra
    public double getTotal() {
        return cantidad * precioUnitario;
    }

    // muestra los datos de la compra en formato de texto
    @Override
    public String toString() {
        return "RUTA: " + ruta +
                ", CANT: " + cantidad +
                ", PASAJERO: " + nombre +
                ", TOTAL: $" + String.format("%.2f", getTotal()) + "\n";
    }
}
