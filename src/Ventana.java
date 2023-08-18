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
        JButton botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(this);
        panelBotones.add(botonIniciar);

        JButton botonPausar = new JButton("Pausar");
        botonPausar.addActionListener(this);
        panelBotones.add(botonPausar);

        JButton botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(this);
        panelBotones.add(botonLimpiar);

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
            Ventana ventana = new Ventana(30, 30);
            ventana.setVisible(true);
        });
    }
}
