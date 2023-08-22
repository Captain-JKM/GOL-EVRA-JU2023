import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Ventana extends JFrame implements ActionListener {
    private final Tablero tablero;
    private JLabel generacionesLabel;

    public Ventana(int filas, int columnas, int velocidad, int generacion) {
        super("Juego de la Vida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        tablero = new Tablero(filas, columnas, velocidad, generacion, this);
        add(tablero, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        generacionesLabel = new JLabel("Generación: 0");
        add(generacionesLabel, BorderLayout.NORTH);

        // Botón para iniciar el juego
        JButton botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(this);
        panelBotones.add(botonIniciar);

        // Botón para pausar el juego
        JButton botonPausar = new JButton("Pausar");
        botonPausar.addActionListener(this);
        panelBotones.add(botonPausar);

        // Botón para limpiar el tablero
        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(this);
        panelBotones.add(botonLimpiar);

        // Botón para un patrón random
        JButton botonRandom = new JButton("Random");
        botonRandom.addActionListener(this);
        panelBotones.add(botonRandom);

        // Menú desplegable para seleccionar patrón
        JComboBox<String> patronComboBox = new JComboBox<>();
        patronComboBox.addItem("Goofy");
        patronComboBox.addItem("Colmena");
        patronComboBox.addItem("Glider");
        patronComboBox.addItem("Eje1");
        patronComboBox.setSelectedItem("Goofy"); // Seleccionar el patrón por defecto
        // Agregar más patrones al menú
        panelBotones.add(patronComboBox);

        // Botón para aplicar el patrón seleccionado
        JButton botonAplicarPatron = new JButton("Aplicar Patrón");
        botonAplicarPatron.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String patronSeleccionado = (String) patronComboBox.getSelectedItem();
                if (patronSeleccionado != null) {
                    tablero.iniciarTablero(patronSeleccionado);
                }
            }
        });
        panelBotones.add(botonAplicarPatron);

        add(panelBotones, BorderLayout.SOUTH);
    }

    public void actualizarGeneracionesLabel(int generacionActual) {
        generacionesLabel.setText("Generación: " + generacionActual);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Iniciar")) {
            tablero.iniciarJuego();
        } else if (e.getActionCommand().equals("Pausar")) {
            tablero.pausarJuego();
        } else if (e.getActionCommand().equals("Limpiar")) {
            tablero.limpiarTablero();
        } else if (e.getActionCommand().equals("Random")) {
            tablero.randomGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Scanner sc = new Scanner(System.in);
            System.out.print("Dame Filas: ");
            int filas = sc.nextInt();
            System.out.print("Dame Columnas: ");
            int columnas = sc.nextInt();
            System.out.print("Velocidad de juego: ");
            int velocidad = sc.nextInt();
            System.out.print("Generaciones: ");
            int generacion = sc.nextInt();
            Ventana ventana = new Ventana(filas, columnas, velocidad, generacion);
            ventana.setVisible(true);
        });
    }
}
