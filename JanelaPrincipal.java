import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class JanelaPrincipal extends JFrame {

    private static JanelaPrincipal janela;
    private JanelaPrincipal() {
        iniciar();
    }

    private void iniciar() {
        int largura = 420;
        int altura = 200;
        setTitle("Gestão de Veículos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(largura, altura);
        JLabel linkPasseio = new JLabel();
        linkPasseio.setIcon(Util.ICON_AZUL);
        linkPasseio.setText("Passeio");
        linkPasseio.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        linkPasseio.setIconTextGap(10);
        linkPasseio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuPasseio();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                linkPasseio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                linkPasseio.setForeground(Util.COR_AZUL);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                linkPasseio.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                linkPasseio.setForeground(null);
            }
        });

        JLabel linkCarga = new JLabel();
        linkCarga.setIcon(Util.ICON_VERDE);
        linkCarga.setText("Carga");
        linkCarga.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        linkCarga.setIconTextGap(10);
        linkCarga.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                menuCarga();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                linkCarga.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                linkCarga.setForeground(Util.COR_VERDE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                linkCarga.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                linkCarga.setForeground(null);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40 ));
        panel.add(linkPasseio);
        panel.add(linkCarga);
        add(panel);
    }

    public static JanelaPrincipal getJanelaPrincipal() {
        if (janela == null) {
            janela = new JanelaPrincipal();
        }
        return janela;
    }

    private void menuPasseio(){
        MenuPasseio.getMenuPasseio().setVisible(true);
    }

    private void menuCarga(){
//        JanelaMenuPasseio.getJanelaMenuPasseio().setVisible(true);
    }
}
