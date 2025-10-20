## 👥 Integrantes del grupo
- **Luis David Morales** 
- **Lucas Karlsson** 
# 🚍 Proyecto: Sistema de Venta de Boletos – Taller P1 (Programación III)

## 📘 Descripción general
Este proyecto corresponde a la **corrección del Taller 1** de la asignatura **Programación III**, desarrollado en el lenguaje **Java** utilizando **Swing** como biblioteca para la creación de la interfaz gráfica.  
El sistema permite **gestionar la venta de boletos** de autobús para tres rutas disponibles, controlando la capacidad, validaciones por pasajero y el total recaudado.

Se implementaron estructuras de datos, validaciones y manejo de eventos para garantizar la correcta operación del sistema, cumpliendo con los requisitos planteados en el enunciado original.

---

## 🧩 Funcionalidades principales
- Selección de **ruta** mediante un `JComboBox`.
- Registro de **cédula**, **nombre del pasajero** y **cantidad de boletos**.
- Validación de:
  - Cantidad de boletos por transacción (1 a 5).
  - Máximo de 5 boletos acumulados por cédula.
  - Capacidad total de 20 asientos por ruta.
- Cálculo automático del **total recaudado** y de los boletos **vendidos/restantes**.
- Registro ordenado de las compras en una **cola FIFO (First In, First Out)**.
- Visualización de todas las compras realizadas en un `JTextArea`.

---

## 🧱 Estructura del proyecto

| **Clase** | **Responsabilidad** |
|------------|----------------------|
| `Compra` | Representa una compra con los datos del pasajero, ruta, cantidad de boletos y precio. Incluye métodos para calcular el total y mostrar la información. |
| `ColaCompras` | Administra una estructura de tipo `Queue<Compra>` que almacena las compras en orden FIFO. Permite agregar y listar las transacciones realizadas. |
| `Ventana` | Implementa la interfaz gráfica en **Swing**. Gestiona los eventos del botón **Comprar**, valida los datos y actualiza los totales de boletos y recaudación. |

---

## 🧠 Lógica de funcionamiento

1. **Ingreso de datos:** el usuario introduce su cédula, nombre, ruta y cantidad de boletos.  
2. **Validación:** el sistema verifica que los datos sean correctos y dentro del rango permitido.  
3. **Registro:** si la validación es exitosa, se crea un objeto `Compra` y se agrega a la cola.  
4. **Actualización:** los valores de boletos vendidos, disponibles y total recaudado se recalculan automáticamente.  
5. **Visualización:** se muestran todas las compras registradas en el área de texto.  

---

## 💻 Requisitos de ejecución

- **Lenguaje:** Java 17 o superior  
- **Entorno:** IntelliJ IDEA (recomendado)  
- **Librerías utilizadas:** Swing (por defecto en el JDK)  

---

## ⚙️ Instalación y ejecución

1. Clonar este repositorio:
   ```bash
   git clone https://github.com/LuisdavidM88/PROGRAMACION-III-Correci-n-Taller-P1.git
