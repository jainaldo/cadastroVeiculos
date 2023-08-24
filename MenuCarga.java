import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuCarga extends JFrame {
    private static MenuCarga janela;
    private MenuCarga() {
        iniciar();
    }

    private void iniciar() {
        int largura = 500;
        int altura = 260;
        setTitle("Veículos de Carga");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(largura, altura);

        JLabel linkCadastrar = new JLabel();
        linkCadastrar.setIcon(Util.ICON_VERDE);
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
                Util.mudaCorLink(linkCadastrar, Util.COR_VERDE, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Util.mudaCorLink(linkCadastrar, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JLabel linkConsultarExcluir = new JLabel();
        linkConsultarExcluir.setIcon(Util.ICON_VERDE);
        linkConsultarExcluir.setText("Consultar / Excluir pela placa");
        linkConsultarExcluir.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        linkConsultarExcluir.setIconTextGap(10);
        linkConsultarExcluir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                consultarExcluir();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Util.mudaCorLink(linkConsultarExcluir, Util.COR_VERDE, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Util.mudaCorLink(linkConsultarExcluir, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JLabel linkImprimirExcluirTodos = new JLabel();
        linkImprimirExcluirTodos.setIcon(Util.ICON_VERDE);
        linkImprimirExcluirTodos.setText("Imprimir/Excluir todos");
        linkImprimirExcluirTodos.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        linkImprimirExcluirTodos.setIconTextGap(10);
        linkImprimirExcluirTodos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                imprimirExcluirTodos();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Util.mudaCorLink(linkImprimirExcluirTodos, Util.COR_VERDE, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Util.mudaCorLink(linkImprimirExcluirTodos, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JLabel linkSair = new JLabel();
        linkSair.setIcon(Util.ICON_VERMELHO);
        linkSair.setText("Sair");
        linkSair.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        linkSair.setIconTextGap(10);
        linkSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sair();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Util.mudaCorLink(linkSair, Util.COR_VERMELHO, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Util.mudaCorLink(linkSair, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40 ));
        panel.add(linkCadastrar);
        panel.add(linkConsultarExcluir);
        panel.add(linkImprimirExcluirTodos);
        panel.add(linkSair);
        add(panel);
    }

    public static MenuCarga getMenuCarga() {
        if (janela == null) {
            janela = new MenuCarga();
        }
        return janela;
    }

    private void cadastrar() {
        CadastroCarga.getCadastroCarga().setVisible(true);
    }

    private void consultarExcluir() {
        ConsultarExcluirCarga.getConsultarExcluirCarga().setVisible(true);
    }

    private void imprimirExcluirTodos() {
        ImprimirExcluirTodosCarga.getImprimirExcluirTodosCarga().setVisible(true);
    }

    private void sair(){
        int resp = Util.confirmar("Saida","Deseja realmente sair?");
        if(resp == 0){
            dispose();
        }
    }
}