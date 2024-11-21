import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TapeteDeSierpinski extends JPanel {

    private final int ladoInicial = 400;
    private final int margen = 20;

    public TapeteDeSierpinski() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLACK);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int xInicial = (getWidth() - ladoInicial) / 2;
        int yInicial = (getHeight() - ladoInicial) / 2;

        dibujarTapete(g2d, xInicial, yInicial, ladoInicial, 0);
    }

    private void dibujarTapete(Graphics2D g, int x, int y, int lado, int nivel) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, lado, lado);

        g.setColor(interpolarColor(new Color(255, 0, 0), new Color(255, 0, 0), nivel / 6.0f));
        g.fillRect(x + 2, y + 2, lado - 4, lado - 4);

        if (lado > 10) {
            int nuevoLado = lado / 3;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1 && j == 1) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x + nuevoLado, y + nuevoLado, nuevoLado, nuevoLado);
                    } else {
                        dibujarTapete(g, x + i * nuevoLado, y + j * nuevoLado, nuevoLado, nivel + 1);
                    }
                }
            }
        }
    }

    private Color interpolarColor(Color c1, Color c2, float ratio) {
        int rojo = (int) (c1.getRed() * (1 - ratio) + c2.getRed() * ratio);
        int verde = (int) (c1.getGreen() * (1 - ratio) + c2.getGreen() * ratio);
        int azul = (int) (c1.getBlue() * (1 - ratio) + c2.getBlue() * ratio);
        return new Color(rojo, verde, azul);
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Tapete de Sierpinski Recursivo");
        TapeteDeSierpinski panel = new TapeteDeSierpinski();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(panel);
        ventana.setSize(500, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
