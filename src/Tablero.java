import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Tablero extends JPanel implements ActionListener {
    private int filas;
    private int columnas;
    private Celda[][] celdas;
    private Timer timer;
    private int generacion;

    //Aqui declaramos el tablero, para nosotros que sera llamado en ventana
    //donde podemos configurar su tamaño
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.generacion = 0;

        setLayout(new GridLayout(filas, columnas));
        celdas = new Celda[filas][columnas];
        timer = new Timer(50, this);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
                add(celdas[i][j]);
            }
        }
    }
    //sacamos de ventana el patron y lo llamamos aca para hacer la seleccion
    public void cargarPatron(String patron) {
        limpiarTablero(); // Limpia el tablero antes de cargar un nuevo patrón

        switch (patron) {
            case "Muerte":
                // Código para cargar el patrón "Muerte"
                break;
            case "Bloque":
                // Código para cargar el patrón "Bloque"
                break;
            case "Colmena":
                // Código para cargar el patrón "Colmena"
                break;
            // Añade más casos para los demás patrones
        }

    }

    public void iniciarTablero(String patron) {
        limpiarTablero(); // Limpia el tablero
        boolean[][] matriz = getPatron(patron); // Obtiene la matriz del patrón
        if (matriz != null) {
            int f = matriz.length; // Número de filas del patrón
            int c = matriz[0].length; // Número de columnas del patrón
            int x = (filas - f) / 2; // Posición x del centro del tablero
            int y = (columnas - c) / 2; // Posición y del centro del tablero
            // Coloca el patrón en el centro del tablero
            for (int i = 0; i < f; i++) {
                for (int j = 0; j < c; j++) {
                    celdas[x + i][y + j].setEstado(matriz[i][j]);
                }
            }
            repaint();
        }
    }
    private boolean[][] getPatron(String patron) {
        // Define patrones predefinidos
        HashMap<String, boolean[][]> patrones = new HashMap<>();
        patrones.put("Patrón de la Muerte", new boolean[][] {
                {false, true, false},
                {true, false, true},
                {false, true, false}
        });
        // Agregar más patrones según sea necesario

        return patrones.get(patron);
    }

    public void iniciarJuego() {
        timer.start();
    }

    public void pausarJuego() {
        timer.stop();
    }

    public void limpiarTablero() {
        timer.stop();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j].setEstado(false);
            }
        }
        repaint();
    }

    private boolean[][] obtenerEstadoCeldas() {
        boolean[][] estadoCeldas = new boolean[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                estadoCeldas[i][j] = celdas[i][j].getEstado();
            }
        }
        return estadoCeldas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean[][] estadoCeldas = obtenerEstadoCeldas();
        boolean[][] siguienteEstado = Reglas.aplicarReglas(estadoCeldas, celdas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j].setEstado(siguienteEstado[i][j]);
            }
        }

        generacion++;
        repaint();
    }
}
