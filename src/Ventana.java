import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Ventana extends JFrame implements ActionListener {
    private Tablero tablero;
    private JButton botonIniciar;
    private JButton botonPausar;
    private JButton botonLimpiar;
    private JButton botonAplicar;
    private JComboBox<String> menuPatrones;
    private String[] patrones = {"Muerte", "Bloque", "Colmena", "Barco", "Sapo", "Reloj", "Planeador", "Nave ligera", "Nave media", "Nave pesada"};
    private String patronSeleccionado; // Cambiado a String

    public Ventana() {
        super("Juego de la vida");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        // Aquí se debe crear el tablero con los argumentos necesarios
        tablero = new Tablero(100, 100);
        //mandamos los datos a nuestro constructor para ser pintado mas adelante
        this.add(tablero, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(Color.BLACK);

        botonIniciar = new JButton("Iniciar");
        botonIniciar.addActionListener(this);
        panelInferior.add(botonIniciar);

        botonPausar = new JButton("Pausar");
        botonPausar.addActionListener(this);
        panelInferior.add(botonPausar);

        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(this);
        panelInferior.add(botonLimpiar);

        menuPatrones = new JComboBox<>(patrones);
        panelInferior.add(menuPatrones);

        botonAplicar = new JButton("Aplicar");
        botonAplicar.addActionListener(this);
        panelInferior.add(botonAplicar);

        // Escuchador de acción para el menú desplegable
        menuPatrones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                patronSeleccionado = (String) menuPatrones.getSelectedItem(); // Actualizado a patronSeleccionado
            }
        });

        // Panel barra baja
        this.add(panelInferior, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciar) {
            tablero.iniciarJuego();
        } else if (e.getSource() == botonPausar) {
            tablero.pausarJuego();
        } else if (e.getSource() == botonLimpiar) {
            tablero.limpiarTablero();
        } else if (e.getSource() == botonAplicar) {
            tablero.cargarPatron(patronSeleccionado); // Llama al método cargarPatron del tablero con patronSeleccionado
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ventana ventana = new Ventana();
            ventana.setVisible(true);
        });
    }
}
