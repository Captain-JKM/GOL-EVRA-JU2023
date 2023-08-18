import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Ventana extends JFrame {

    private Tablero tablero; // Tablero del juego
    private JButton botonIniciar; // Botón para iniciar el juego
    private JButton botonPausar; // Botón para pausar el juego
    private JButton botonLimpiar; // Botón para limpiar el tablero
    private JButton botonAplicar; // Botón para aplicar el patrón seleccionado
    private JComboBox<String> menuPatrones; // Menú para seleccionar el patrón
    private String[] patrones = {"Muerte", "Bloque", "Colmena", "Barco", "Sapo", "Reloj", "Planeador", "Nave ligera", "Nave media", "Nave pesada"}; // Arreglo de nombres de patrones

    public Ventana() {
        super("Juego de la vida");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        tablero = new Tablero();
        this.add(tablero, BorderLayout.CENTER);
        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(Color.BLACK);
        botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablero.iniciarJuego();
            }
        });
        panelInferior.add(botonIniciar);
        botonPausar = new JButton("Pausar");
        botonPausar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablero.pausarJuego();
            }
        });
        panelInferior.add(botonPausar);
        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablero.limpiarTablero();
            }
        });
        panelInferior.add(botonLimpiar);
        menuPatrones = new JComboBox<>(patrones);
        panelInferior.add(menuPatrones);
        botonAplicar = new JButton("Aplicar");
        botonAplicar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patron = (String) menuPatrones.getSelectedItem();
                tablero.iniciarTablero(patron);
            }
        });
        panelInferior.add(botonAplicar);
        this.add(panelInferior, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        });
    }
}
