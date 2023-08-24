import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarExcluirCarga extends JFrame {
    private static ConsultarExcluirCarga janela;
    private JLabel lTara = new JLabel("Tara:");
    private JLabel lCargaMax = new JLabel("Carga Máx:");
    private JLabel lPlaca = new JLabel("Informe a Placa:");
    private JLabel lMarca = new JLabel("Marca:");
    private JLabel lModelo = new JLabel("Modelo:");
    private JLabel lCor = new JLabel("Cor:");
    private JLabel lQtdRodas = new JLabel("Qtd. Rodas:");
    private JLabel lVelocidadeMax = new JLabel("Velocidade Máx:");
    private JLabel lQtdPistoes = new JLabel("Qtd. Pistões:");
    private JLabel lPotencia = new JLabel("Potência:");

    private JTextField textTara = new JTextField();
    private JTextField textCargaMax = new JTextField();
    private JTextField textPlaca = new JTextField();
    private JTextField textMarca = new JTextField();
    private JTextField textModelo = new JTextField();
    private JTextField textCor = new JTextField();
    private JTextField textQtdRodas = new JTextField();
    private JTextField textVelocidadeMax = new JTextField();
    private JTextField textQtdPistoes = new JTextField();
    private JTextField textPotencia = new JTextField();

    private ConsultarExcluirCarga() {
        iniciar();
    }

    private void iniciar(){
        setTitle("Consultar / Excluir pela placa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(10,2, 4, 4));
        lPlaca.setForeground(Util.COR_VERMELHO);
        textTara.setEditable(false);
        textCargaMax.setEditable(false);
        textMarca.setEditable(false);
        textModelo.setEditable(false);
        textCor.setEditable(false);
        textQtdRodas.setEditable(false);
        textVelocidadeMax.setEditable(false);
        textQtdPistoes.setEditable(false);
        textPotencia.setEditable(false);

        panelCampos.add(lPlaca);
        panelCampos.add(textPlaca);
        panelCampos.add(lTara);
        panelCampos.add(textTara);
        panelCampos.add(lCargaMax);
        panelCampos.add(textCargaMax);
        panelCampos.add(lMarca);
        panelCampos.add(textMarca);
        panelCampos.add(lModelo);
        panelCampos.add(textModelo);
        panelCampos.add(lCor);
        panelCampos.add(textCor);
        panelCampos.add(lQtdRodas);
        panelCampos.add(textQtdRodas);
        panelCampos.add(lVelocidadeMax);
        panelCampos.add(textVelocidadeMax);
        panelCampos.add(lQtdPistoes);
        panelCampos.add(textQtdPistoes);
        panelCampos.add(lPotencia);
        panelCampos.add(textPotencia);

        JButton bConsultar = new JButton("Consultar");
        bConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                consultar();
            }
        });

        JButton bExcluir = new JButton("Excluir");
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
        panelButoes.add(bConsultar);
        panelButoes.add(bExcluir);
        panelButoes.add(bSair);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20,20));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40 ));
        panel.add(panelCampos, BorderLayout.CENTER);
        panel.add(panelButoes, BorderLayout.SOUTH);
        add(panel);
        pack();
    }

    public static ConsultarExcluirCarga getConsultarExcluirCarga(){
        if (janela== null) {
            janela = new ConsultarExcluirCarga();
        }
        return janela;
    }

    private void consultar() {
        if (textPlaca.getText().isBlank()) {
            Util.notificar(textPlaca, "Deve informar a placa.");
        } else {
            Carga carga = new Carga();
            carga.setPlaca(textPlaca.getText());
            carga = BDVeiculos.getDBVeiculos().buscarCarga(carga);

            if (carga == null) {
                Util.notificar(
                        "Veículo não encontrado",
                        String.format("Não foi encontrado veículo de carga com esta placa: %s.", textPlaca.getText()),
                        JOptionPane.INFORMATION_MESSAGE
                );
                limparCampos();
            } else {
                textPlaca.setText(carga.getPlaca());
                textTara.setText(Integer.toString(carga.getTara()));
                textCargaMax.setText(Integer.toString(carga.getCargaMax()));
                textMarca.setText(carga.getMarca());
                textModelo.setText(carga.getModelo());
                textCor.setText(carga.getCor());
                textQtdRodas.setText(Integer.toString(carga.getQtdRodas()));
                textVelocidadeMax.setText(Float.toString(carga.getVelocMax()));
                textQtdPistoes.setText(Integer.toString(carga.getMotor().getQtdPist()));
                textPotencia.setText(Integer.toString(carga.getMotor().getPotencia()));
            }
        }
    }

    private void excluir() {
        if (textPlaca.getText().isBlank()) {
            Util.notificar(textPlaca, "Deve informar a placa.");
        } else {
            Carga carga = new Carga();
            carga.setPlaca(textPlaca.getText());
            carga = BDVeiculos.getDBVeiculos().buscarCarga(carga);

            if (carga == null) {
                Util.notificar(
                        "Veiculo não encontrado",
                        String.format("Não foi encontrado veículo de carga com esta placa: %s.", textPlaca.getText()),
                        JOptionPane.INFORMATION_MESSAGE
                );
                limparCampos();
            } else {
                int resp = Util.confirmar(
                        "Excluir",
                        String.format("Tem certeza que deseja excluir o veículo de carga de placa: %s ?", textPlaca.getText())
                );
                if (resp == 0) {
                    BDVeiculos.getDBVeiculos().excluirCarga(carga);
                    Util.notificar("Sucesso", "Veiculo excluído com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
            }
        }

    }

    private void limparCampos() {
        textPlaca.setText("");
        textTara.setText("");
        textCargaMax.setText("");
        textMarca.setText("");
        textModelo.setText("");
        textCor.setText("");
        textQtdRodas.setText("");
        textVelocidadeMax.setText("");
        textQtdPistoes.setText("");
        textPotencia.setText("");
        textPlaca.requestFocus();
    }

    private void sair() {
        int resp = Util.confirmar("Saida","Deseja realmente sair?");
        if(resp == 0){
            limparCampos();
            dispose();
        }
    }
}
