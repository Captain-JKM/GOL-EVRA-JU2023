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
                for (int i = 0; i < filas; i++) { //Recorres las filas del tablero
                    for (int j = 0; j < columnas; j++) { //Recorres las columnas del tablero
                        celdas[i][j].setEstado(false); //Asignas el valor false a cada celda
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
