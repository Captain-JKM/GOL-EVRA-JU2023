import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana extends JFrame implements ActionListener {
    private final Tablero tablero;

    public Ventana(int filas, int columnas) {
        super("Juego de la Vida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        tablero = new Tablero(filas, columnas);
        add(tablero, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

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

        // Menú desplegable para seleccionar patrón
        JComboBox<String> patronComboBox = new JComboBox<>();
        patronComboBox.addItem("Patrón de la Muerte");
        patronComboBox.setSelectedItem("Patrón de la Muerte"); // Seleccionar el patrón por defecto
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Iniciar")) {
            tablero.iniciarJuego();
        } else if (e.getActionCommand().equals("Pausar")) {
            tablero.pausarJuego();
        } else if (e.getActionCommand().equals("Limpiar")) {
            tablero.limpiarTablero();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana(100, 100);
            ventana.setVisible(true);
        });
    }
}