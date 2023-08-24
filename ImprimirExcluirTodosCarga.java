import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImprimirExcluirTodosCarga extends JFrame {
    private static ImprimirExcluirTodosCarga janela;
    private String[] colunas = {
            "Placa",
            "Marca",
            "Modelo",
            "Cor",
            "Qtd. Rodas",
            "Veloc. Máx",
            "Qtd. Pist",
            "Potênc.",
            "Tara",
            "Carga Máx"
    };
    private DefaultTableModel model = new DefaultTableModel(colunas, 0);
    private JTable tabela = new JTable(model);

    private ImprimirExcluirTodosCarga() {
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

    public static ImprimirExcluirTodosCarga getImprimirExcluirTodosCarga(){
        if (janela== null) {
            janela = new ImprimirExcluirTodosCarga();
        }
        return janela;
    }

    private void imprimir() {
        if (BDVeiculos.getDBVeiculos().getListaCarga().isEmpty()) {
            Util.notificar("Lista vazia", "Ainda não tem veículos cadastrados!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            limparTabela();
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            for (Carga carga: BDVeiculos.getDBVeiculos().getListaCarga()) {
                String[] dados = {
                        carga.getPlaca(),
                        carga.getMarca(),
                        carga.getModelo(),
                        carga.getCor(),
                        Integer.toString(carga.getQtdRodas()),
                        Float.toString(carga.getVelocMax()),
                        Integer.toString(carga.getMotor().getQtdPist()),
                        Integer.toString(carga.getMotor().getPotencia()),
                        Integer.toString(carga.getTara()),
                        Integer.toString(carga.getCargaMax())
                };
                modelo.addRow(dados);
            }

            tabela.setModel(modelo);
        }
    }

    private void excluir() {
        if (BDVeiculos.getDBVeiculos().getListaCarga().isEmpty()) {
            Util.notificar(
                    "Lista vazia",
                    "Ainda não tem veículos cadastrados!",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            int resp = Util.confirmar(
                    "Excluir todos",
                    "Tem certeza que deseja excluir todos os veículos de carga?"
            );
            if (resp == 0) {
                BDVeiculos.getDBVeiculos().excluirTodosCarga();
                Util.notificar(
                        "Sucesso",
                        "Todos os veículos de carga foram excluídos!",
                        JOptionPane.INFORMATION_MESSAGE
                );
                limparTabela();
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
