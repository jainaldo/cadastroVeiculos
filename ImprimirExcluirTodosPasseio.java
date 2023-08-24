import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImprimirExcluirTodosPasseio extends JFrame {
    private static ImprimirExcluirTodosPasseio janela;
    private String[] colunas = {
            "Placa",
            "Marca",
            "Modelo",
            "Cor",
            "Qtd. Rodas",
            "Veloc. Máx",
            "Qtd. Pist",
            "Potênc.",
            "Qtd. Passag"
    };
    private DefaultTableModel model = new DefaultTableModel(colunas, 0);
    private JTable tabela = new JTable(model);

    private ImprimirExcluirTodosPasseio() {
        iniciar();
    }

    private void iniciar(){
        setTitle("Imprimir / Excluir todos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(750, 250));

        JButton bImprimir = new JButton("Imprimir Todos");
        bImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                imprimir();
            }
        });

        JButton bExcluir = new JButton("Excluir Todos");
        bExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                excluir();
            }
        });

        JButton bSair = new JButton("Sair");
        bSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sair();
            }
        });

        JPanel panelButoes = new JPanel();
        panelButoes.setLayout(new FlowLayout());
        panelButoes.add(bImprimir);
        panelButoes.add(bExcluir);
        panelButoes.add(bSair);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20,20));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40 ));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(panelButoes, BorderLayout.SOUTH);
        add(panel);
        pack();
    }

    public static ImprimirExcluirTodosPasseio getImprimirExcluirTodosPasseio(){
        if (janela== null) {
            janela = new ImprimirExcluirTodosPasseio();
        }
        return janela;
    }

    private void imprimir() {
        if (BDVeiculos.getDBVeiculos().getListaPasseio().isEmpty()) {
            Util.notificar("Lista vazia", "Ainda não tem veículos cadastrados!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            limparTabela();
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            for (Passeio passeio: BDVeiculos.getDBVeiculos().getListaPasseio()) {
                String[] dados = {
                        passeio.getPlaca(),
                        passeio.getMarca(),
                        passeio.getModelo(),
                        passeio.getCor(),
                        Integer.toString(passeio.getQtdRodas()),
                        Float.toString(passeio.getVelocMax()),
                        Integer.toString(passeio.getMotor().getQtdPist()),
                        Integer.toString(passeio.getMotor().getPotencia()),
                        Integer.toString(passeio.getQtdPassageiros())
                };
                modelo.addRow(dados);
            }

            tabela.setModel(modelo);
        }
    }

    private void excluir() {
        if (BDVeiculos.getDBVeiculos().getListaPasseio().isEmpty()) {
            Util.notificar(
                    "Lista vazia",
                    "Ainda não tem veículos cadastrados!",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            int resp = Util.confirmar(
                    "Excluir todos",
                    "Tem certeza que deseja excluir todos os veículos de passeio?"
            );
            if (resp == 0) {
                BDVeiculos.getDBVeiculos().excluirTodosPasseio();
                limparTabela();
                Util.notificar(
                        "Sucesso",
                        "Todos os veículos de passeio foram excluídos!",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }

    private void sair() {
        int resp = Util.confirmar("Saida","Deseja realmente sair?");
        if(resp == 0){
            limparTabela();
            dispose();
        }
    }

    private void limparTabela() {
        DefaultTableModel modelo = new DefaultTableModel(colunas,0);
        tabela.setModel(modelo);
    }
}
