// Clase Celda
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Celda extends JPanel {
    private boolean estado;
    private Color color;

    // Constructor de la celda
    public Celda() {
        estado = false;
        color = Color.WHITE;
        // Ajustamos el tamaño de nuestras celdas
        setPreferredSize(new Dimension(50, 50));
        agregarEventoRaton(); // Agregamos el evento de ratón en el constructor
    }

    // Método para obtener el estado de la celda
    public boolean getEstado() {
        return estado;
    }

    // Método para establecer el estado de la celda y repintarla
    public void setEstado(boolean nuevoEstado) {
        estado = nuevoEstado;
        repaint(); // Volvemos a dibujar la celda con el nuevo estado
    }

    // Método para establecer el color de la celda y repintarla
    public void setColor(Color nuevoColor) {
        color = nuevoColor;
        repaint(); // Volvemos a dibujar la celda con el nuevo color
    }

    // Configurar eventos de ratón para la interacción del usuario
    public void agregarEventoRaton() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Si se presiona el botón izquierdo del ratón
                    setEstado(!getEstado()); // Cambiamos el estado de la celda al opuesto
                    if (getEstado()) { // Si la celda está viva
                        setColor(Color.BLACK); // Cambiamos el color a negro
                    } else { // Si la celda está muerta
                        setColor(Color.WHITE); // Cambiamos el color a blanco
                    }
                    // Cambia el valor del atributo pintado en el tablero a verdadero
                    ((Tablero)getParent()).setPintado(true);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        // Dibujar celda del color correspondiente
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1); // Dibujamos el borde ajustado
    }
}
