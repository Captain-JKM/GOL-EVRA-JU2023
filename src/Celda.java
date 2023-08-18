import javax.swing.*;
import java.awt.*;

public class Celda extends JPanel {
    private boolean estado;
    private Color color;

    public Celda() {
        estado = false;
        color = Color.WHITE;
        // Ajustamos el tamaño de nuestras celdas
        setPreferredSize(new Dimension(10, 10));
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean nuevoEstado) {
        estado = nuevoEstado;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        // Dibujar celda negra si el estado es verdadero
        if (estado) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width, height);
        }
    }
}
