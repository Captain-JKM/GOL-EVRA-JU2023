import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tablero extends JPanel implements ActionListener {
    private int filas;
    private int columnas;
    private Celda[][] celdas;
    private Timer timer;
    private int generacion;
    private boolean pintado; // Nuevo atributo
    //Variables auxiliares para pintar
    private int velocidad; //Velocidad de actualización en milisegundos
    private int[][] patronGuardado; //Matriz que guarda el patrón dibujado manualmente

    // Constructor del tablero
    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
        timer = new Timer(1000, this);
        velocidad = 1000;
        pintado = false;
        patronGuardado = null;

        for (int i = 0; i < filas; i++) { //Recorres las filas
            for (int j = 0; j < columnas; j++) { //Recorres las columnas
                celdas[i][j] = new Celda(this); //Creas una nueva celda y le pasas una referencia al tablero
                celdas[i][j].setBounds(j * 10, i * 10, 10, 10); //Estableces la posición y el tamaño de la celda
                this.add(celdas[i][j]); //Añades la celda al panel
            }
        }
    }

    // Método para establecer el atributo pintado
    public void setPintado(boolean valor) {
        pintado = valor;
    }

    // Método para cargar un patrón en el tablero
    public void cargarPatron(String patron) {
        limpiarTablero(); // Limpia el tablero antes de cargar un nuevo patrón

        switch (patron) {
            case "Muerte":
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        celdas[i][j].setEstado(false);
                    }
                }
                break;
            case "Bloque":
                // Código para cargar el patrón "Bloque"
                break;
            case "Colmena":
                // Código para cargar el patrón "Colmena"
                break;
            // Añade más casos para los demás patrones
        }

        pintado = true; // Establece el atributo pintado como verdadero después de cargar un patrón
        dibujarTablero(); // Dibuja el tablero con los patrones seleccionados
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

        if (pintado) { // Solo dibuja patrones si el tablero ha sido pintado manualmente
            // Aquí puedes llamar a los métodos de dibujo de patrones si los tienes implementados
        }

        generacion++;
        repaint();
    }
}
