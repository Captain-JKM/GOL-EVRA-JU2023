import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Tablero extends JPanel implements ActionListener {
    private int filas;
    private int columnas;
    private Celda[][] celdas;
    private Timer timer;

    private Reglas reglas;


    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;

        setLayout(new GridLayout(filas, columnas));
        celdas = new Celda[filas][columnas];
        timer = new Timer(250, this);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
                add(celdas[i][j]);
            }
        }

        // Asigna el estado inicial "Patrón de la Muerte"
        celdas[1][0].setEstado(true);
        celdas[2][1].setEstado(true);
        celdas[2][2].setEstado(true);
        celdas[1][2].setEstado(true);
        celdas[0][2].setEstado(true);

        reglas = new Reglas();


    }

    public void iniciarTablero(String patron) {
        limpiarTablero(); // Limpia el tablero
        boolean[][] matriz = getPatron(patron); // Obtiene la matriz del patrón
        if (matriz != null) {
            int f = matriz.length; // Número de filas del patrón
            int c = matriz[0].length; // Número de columnas del patrón
            int x = 0; // Cambia el valor de x a 0 para iniciar desde la esquina superior izquierda
            int y = 0; // Cambia el valor de y a 0 para iniciar desde la esquina superior izquierda
            // Coloca el patrón en la esquina superior izquierda del tablero
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
        patrones.put("Goofy", new boolean[][] {
                {true, true, true, true, true, true, true, true},
                {true, false, true, false, true, true, false, false},
                {true, true, true,false,false,true,true,true}
        });
        patrones.put("Colmena", new boolean[][] {
                {false, false, false, false, false, false},
                {false, false, true, true, false, false},
                {false, true, false, false, true, false},
                {false, false, true, true, false, false},
                {false, false, false, false, false, false} });
        patrones.put("Glider", new boolean[][] {
                {false, false, false,true, false, false},
                {false, true, false, false, false,true},
                {true, false, false, false, false, false},
                {true, false, false, false,false ,true},
                {true ,true ,true ,true ,true,false} });
        // Agregar más patrones según sea necesario

        return patrones.get(patron);
    }

    public void iniciarJuego() {
        timer.start();
    }
    public void randomGame() {
        timer.stop();
        Random random = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j].setEstado(random.nextBoolean());
            }
        }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean[][] estadoCeldas = obtenerEstadoCeldas();
        boolean[][] siguienteEstado = reglas.aplicarReglas(estadoCeldas);

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j].setEstado(siguienteEstado[i][j]);
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
}