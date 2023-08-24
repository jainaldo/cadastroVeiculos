import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Util {
    public static final Color COR_AZUL = new Color(37, 150, 190);
    public static final Color COR_VERDE = new Color(40, 190, 37);
    public static final Color COR_VERMELHO = new Color(194, 76, 76);
    public static ImageIcon ICON_AZUL = createColorIcon(COR_AZUL);
    public static ImageIcon ICON_VERDE = createColorIcon(COR_VERDE);
    public static ImageIcon ICON_VERMELHO = createColorIcon(COR_VERMELHO);
    private Util(){}

    private static ImageIcon createColorIcon(Color color) {
        BufferedImage img = new BufferedImage(25, 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, 25, 20);
        g.dispose();
        return new ImageIcon(img);
    }

}
