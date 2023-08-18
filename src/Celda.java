import javax.swing.*;
import java.awt.*;

public class Celda extends JPanel {
    private boolean estado;
    private Color color;

    public Celda() {
        estado = false;
        color = Color.WHITE;
        //Ajustamos el tamaño de nuestras celdas
        setPreferredSize(new Dimension(5, 5));
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean nuevoEstado) {
        estado = nuevoEstado;
        if (estado) {
            color = Color.BLACK;
        } else {
            color = Color.WHITE;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void cambiarColor(int opcion) {
        switch (opcion) {
            case 1:
                color = Color.BLACK;
                break;
            case 2:
                color = Color.RED;
                break;
            case 3:
                color = Color.GREEN;
                break;
            // ... Agregar más colores según la necesidad
            default:
                color = Color.BLACK;
        }
        repaint();
    }
}

