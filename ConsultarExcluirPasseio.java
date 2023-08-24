import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsultarExcluirPasseio extends JFrame {
    private static ConsultarExcluirPasseio janela;
    private JLabel lQtdPassageiros = new JLabel("Qtd. Passageiros:");
    private JLabel lPlaca = new JLabel("Informe a Placa:");
    private JLabel lMarca = new JLabel("Marca:");
    private JLabel lModelo = new JLabel("Modelo:");
    private JLabel lCor = new JLabel("Cor:");
    private JLabel lQtdRodas = new JLabel("Qtd. Rodas:");
    private JLabel lVelocidadeMax = new JLabel("Velocidade Max:");
    private JLabel lQtdPistoes = new JLabel("Qtd. Pistões:");
    private JLabel lPotencia = new JLabel("Potência:");

    private JTextField textQtdPassageiros = new JTextField();
    private JTextField textPlaca = new JTextField();
    private JTextField textMarca = new JTextField();
    private JTextField textModelo = new JTextField();
    private JTextField textCor = new JTextField();
    private JTextField textQtdRodas = new JTextField();
    private JTextField textVelocidadeMax = new JTextField();
    private JTextField textQtdPistoes = new JTextField();
    private JTextField textPotencia = new JTextField();

    private ConsultarExcluirPasseio() {
        iniciar();
    }

    private void iniciar(){
        setTitle("Consultar / Excluir pela placa");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(9,2, 4, 4));
        lPlaca.setForeground(Util.COR_VERMELHO);
        textQtdPassageiros.setEditable(false);
        textMarca.setEditable(false);
        textModelo.setEditable(false);
        textCor.setEditable(false);
        textQtdRodas.setEditable(false);
        textVelocidadeMax.setEditable(false);
        textQtdPistoes.setEditable(false);
        textPotencia.setEditable(false);

        panelCampos.add(lPlaca);
        panelCampos.add(textPlaca);
        panelCampos.add(lQtdPassageiros);
        panelCampos.add(textQtdPassageiros);
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
                consultarPasseio();
            }
        });

        JButton bExcluir = new JButton("Excluir");
        bExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                excluirPasseio();
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

    public static ConsultarExcluirPasseio getConsultarExcluirPasseio(){
        if (janela== null) {
            janela = new ConsultarExcluirPasseio();
        }
        return janela;
    }

    private void consultarPasseio() {
        if (textPlaca.getText().isBlank()) {
            Util.notificar(textPlaca, "Deve informar a placa");
        } else {
            Passeio passeio = new Passeio();
            passeio.setPlaca(textPlaca.getText());
            passeio = BDVeiculos.getDBVeiculos().buscarPasseio(passeio);

            if (passeio == null) {
                Util.notificar(
                        "Veiculo não encontrado",
                        String.format("Não foi encontrado veiculo de passeio com esta placa: %s.", textPlaca.getText()),
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                textPlaca.setText(passeio.getPlaca());
                textQtdPassageiros.setText(Integer.toString(passeio.getQtdPassageiros()));
                textMarca.setText(passeio.getMarca());
                textModelo.setText(passeio.getModelo());
                textCor.setText(passeio.getCor());
                textQtdRodas.setText(Integer.toString(passeio.getQtdRodas()));
                textVelocidadeMax.setText(Float.toString(passeio.getVelocMax()));
                textQtdPistoes.setText(Integer.toString(passeio.getMotor().getQtdPist()));
                textPotencia.setText(Integer.toString(passeio.getMotor().getPotencia()));
            }
        }
    }

    private void excluirPasseio() {
        if (textPlaca.getText().isBlank()) {
            Util.notificar(textPlaca, "Deve informar a placa");
        } else {
            Passeio passeio = new Passeio();
            passeio.setPlaca(textPlaca.getText());
            passeio = BDVeiculos.getDBVeiculos().buscarPasseio(passeio);

            if (passeio == null) {
                Util.notificar(
                        "Veiculo não encontrado",
                        String.format("Não foi encontrado veiculo de passeio com esta placa: %s.", textPlaca.getText()),
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                int resp = Util.confirmar(
                        "Excluir",
                        String.format("Tem certeza que deseja excluir o veiculo de passeio de placa: %s ? ", textPlaca.getText())
                );
                if (resp == 0) {
                    BDVeiculos.getDBVeiculos().excluirPasseio(passeio);
                    limparCampos();
                    Util.notificar("Sucesso", "Veiculo excluído com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }

    private void limparCampos() {
        textQtdPassageiros.setText("");
        textPlaca.setText("");
        textMarca.setText("");
        textModelo.setText("");
        textCor.setText("");
        textQtdRodas.setText("");
        textVelocidadeMax.setText("");
        textQtdPistoes.setText("");
        textPotencia.setText("");
        textQtdPassageiros.requestFocus();
    }

    private void sair() {
        int resp = Util.confirmar("Saida","Deseja realmente sair?");
        if(resp == 0){
            limparCampos();
            dispose();
        }
    }
}
