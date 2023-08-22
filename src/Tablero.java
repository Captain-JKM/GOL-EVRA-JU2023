import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Tablero extends JPanel implements ActionListener {
    private int filas;
    private int columnas;
    private int velocidad;
    private int generaciones;
    private Celda[][] celdas;
    private Timer timer;
    private Reglas reglas;
    private int generacionActual;
    private int generacionesObjetivo;

    public Tablero(int filas, int columnas, int velocidad, int generaciones) {
        this.filas = filas;
        this.columnas = columnas;
        this.velocidad = velocidad;
        this.generaciones = generaciones;

        setLayout(new GridLayout(filas, columnas));
        celdas = new Celda[filas][columnas];
        timer = new Timer(velocidad, this);


        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = new Celda();
                add(celdas[i][j]);
            }
        }

        generacionActual = 0;
        generacionesObjetivo = generaciones;

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
        patrones.put("Eje1", new boolean[][] {
                {false, false, false, false, false},
                {false, false,false, false, false},
                {true, false, true, false, true},
                {true, false, false, false, false},
                {false, true, false, false, false}
        });

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
        if (generacionActual < generacionesObjetivo || generacionesObjetivo == 0) {
            boolean[][] estadoCeldas = obtenerEstadoCeldas();
            boolean[][] siguienteEstado = reglas.aplicarReglas(estadoCeldas);

            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    celdas[i][j].setEstado(siguienteEstado[i][j]);
                }
            }
            generacionActual++;
            repaint();
        } else {
            timer.stop();
        }
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