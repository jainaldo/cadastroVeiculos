import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPasseio extends JFrame {
    private static MenuPasseio janela;
    private MenuPasseio() {
        iniciar();
    }

    private void iniciar() {
        int largura = 500;
        int altura = 300;
        setTitle("Veículos de Passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(largura, altura);

        JLabel linkCadastrar = new JLabel();
        linkCadastrar.setIcon(Util.ICON_AZUL);
        linkCadastrar.setText("Cadastrar");
        linkCadastrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        linkCadastrar.setIconTextGap(10);
        linkCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cadastrar();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                linkCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                linkCadastrar.setForeground(Util.COR_AZUL);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                linkCadastrar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                linkCadastrar.setForeground(null);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40 ));
        panel.add(linkCadastrar);
        add(panel);
    }

    public static MenuPasseio getMenuPasseio() {
        if (janela == null) {
            janela = new MenuPasseio();
        }
        return janela;
    }

    private void cadastrar() {
        CadastroPasseio.getCadastroPasseio().setVisible(true);
    }
}
