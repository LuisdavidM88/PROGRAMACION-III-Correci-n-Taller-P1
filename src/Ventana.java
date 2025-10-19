import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Ventana {
    private JPanel principal;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtCliente;  // campo para ingresar cantidad de boletos
    private JButton btnComprar;
    private JTextArea txtCompras;
    private JComboBox cboRuta;
    private JTextField txtVendidosQG;
    private JTextField txtVendidosQC;
    private JTextField txtVendidosQL;
    private JTextField txtRestantesQG;
    private JTextField txtRestantesQC;
    private JTextField txtRestantesQL;
    private JTextField txtTotalRecaudado;

    // estructura tipo cola donde se almacenan las compras realizadas
    private ColaCompras cola = new ColaCompras();

    // capacidad máxima por ruta
    private final int CAPACIDAD = 20;

    // variables que almacenan la cantidad de boletos vendidos por cada ruta
    private int vendidosQG = 0; // QUITO - GUAYAQUIL
    private int vendidosQC = 0; // QUITO - CUENCA
    private int vendidosQL = 0; // QUITO - LOJA
    private double recaudado = 0.0;

    // estructura que guarda la cantidad de boletos comprados por cada cédula
    private Map<String, Integer> boletosPorCedula = new HashMap<>();

    public Ventana() {

        // accion que se ejecuta cuando el usuario presiona el botón comprar
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // lectura de los datos ingresados por el usuario
                String ruta = cboRuta.getSelectedItem() != null ? cboRuta.getSelectedItem().toString() : "";
                String cedula = txtCedula.getText().trim();
                String nombre = txtNombre.getText().trim();
                String cantidadStr = txtCliente.getText().trim();

                // verificación de que los campos no estén vacíos
                if (ruta.isEmpty() || ruta.equalsIgnoreCase("Seleccione")) {
                    JOptionPane.showMessageDialog(null, "Seleccione una ruta.");
                    return;
                }
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese la cédula.");
                    return;
                }
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ingrese el nombre del pasajero.");
                    return;
                }

                int cantidad;
                try {
                    cantidad = Integer.parseInt(cantidadStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Cantidad inválida. Ingrese un número entero.");
                    return;
                }

                // validación del rango de boletos permitidos por compra
                if (cantidad < 1 || cantidad > 5) {
                    JOptionPane.showMessageDialog(null, "La cantidad debe estar entre 1 y 5 boletos.");
                    return;
                }

                // comprobación del límite total de boletos permitidos por cédula
                int yaTiene = boletosPorCedula.getOrDefault(cedula, 0);
                if (yaTiene + cantidad > 5) {
                    JOptionPane.showMessageDialog(null, "Máximo 5 boletos por cédula en total.");
                    return;
                }

                // verificación de que haya asientos disponibles en la ruta seleccionada
                int vendidosRuta = getVendidosRuta(ruta);
                if (vendidosRuta + cantidad > CAPACIDAD) {
                    JOptionPane.showMessageDialog(null, "No hay suficientes asientos en esta ruta.");
                    return;
                }

                // creación del objeto Compra y almacenamiento en la cola
                double precio = precioPorRuta(ruta);
                Compra compra = new Compra(ruta, cedula, nombre, cantidad, precio);
                cola.encolar(compra);

                // actualización de contadores y registro de boletos por cédula
                setVendidosRuta(ruta, vendidosRuta + cantidad);
                recaudado += compra.getTotal();
                boletosPorCedula.put(cedula, yaTiene + cantidad);

                // actualización de la interfaz gráfica con los nuevos datos
                txtCompras.setText(cola.toString());
                actualizarMetricaUI();
                JOptionPane.showMessageDialog(null, "Compra registrada con éxito.");

                // limpieza de los campos para ingresar una nueva compra
                limpiarCampos();
            }
        });

        // inicialización de métricas al cargar la interfaz
        actualizarMetricaUI();
    }

    // metodo que devuelve el precio del boleto según la ruta seleccionada
    private double precioPorRuta(String ruta) {
        if (ruta.equals("QUITO - GUAYAQUIL")) return 10.50;
        if (ruta.equals("QUITO - CUENCA")) return 12.75;
        if (ruta.equals("QUITO - LOJA")) return 15.00;
        return 0.0;
    }

    // metodo que obtiene la cantidad de boletos vendidos por ruta
    private int getVendidosRuta(String ruta) {
        if (ruta.equals("QUITO - GUAYAQUIL")) return vendidosQG;
        if (ruta.equals("QUITO - CUENCA")) return vendidosQC;
        if (ruta.equals("QUITO - LOJA")) return vendidosQL;
        return 0;
    }

    // metodo que actualiza la cantidad de boletos vendidos por ruta
    private void setVendidosRuta(String ruta, int valor) {
        if (ruta.equals("QUITO - GUAYAQUIL")) vendidosQG = valor;
        if (ruta.equals("QUITO - CUENCA")) vendidosQC = valor;
        if (ruta.equals("QUITO - LOJA")) vendidosQL = valor;
    }

    // metodo que actualiza los valores mostrados en la interfaz gráfica
    private void actualizarMetricaUI() {
        txtVendidosQG.setText(String.valueOf(vendidosQG));
        txtVendidosQC.setText(String.valueOf(vendidosQC));
        txtVendidosQL.setText(String.valueOf(vendidosQL));

        txtRestantesQG.setText(String.valueOf(CAPACIDAD - vendidosQG));
        txtRestantesQC.setText(String.valueOf(CAPACIDAD - vendidosQC));
        txtRestantesQL.setText(String.valueOf(CAPACIDAD - vendidosQL));

        txtTotalRecaudado.setText(String.format("$%.2f", recaudado));
    }

    // metodo que limpia los campos de entrada después de realizar una compra
    private void limpiarCampos() {
        if (cboRuta.getItemCount() > 0) cboRuta.setSelectedIndex(0);
        txtCedula.setText("");
        txtNombre.setText("");
        txtCliente.setText("");
    }

    // metodo principal que inicializa y muestra la ventana
    public static void main(String[] args) {
        JFrame frame = new JFrame("Venta de Boletos");
        frame.setContentPane(new Ventana().principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
