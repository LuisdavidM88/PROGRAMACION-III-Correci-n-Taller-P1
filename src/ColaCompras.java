import java.util.LinkedList;
import java.util.Queue;

public class ColaCompras {
    // estructura tipo cola donde se guardan las compras
    private Queue<Compra> cola;

    // constructor que inicializa la cola
    public ColaCompras() {
        cola = new LinkedList<>();
    }

    // agrega una compra al final de la cola
    public void encolar(Compra compra) {
        cola.add(compra);
    }

    // verifica si la cola está vacía
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    // devuelve todas las compras en formato de texto
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Compra c : cola) {
            sb.append(c.toString());
        }
        return sb.toString();
    }
}
