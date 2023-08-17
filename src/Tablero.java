import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tablero extends JPanel implements ActionListener {
    private int filas;
    private int columnas;
    private Celda[][] celdas;
    private Timer timer;
    private int generacion;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.generacion = 0;

        setLayout(new GridLayout(filas, columnas));
        celdas = new Celda[filas][columnas];
        timer = new Timer(500, this);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
                add(celdas[i][j]);
            }
        }
    }

    public void iniciarJuego() {
        timer.start();
    }

    public void pausarJuego() {
        timer.stop();
    }

    public void limpiarTablero() {
        timer.stop();
        // Limpia el estado de las celdas
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
