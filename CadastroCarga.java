import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCarga extends JFrame {
    private static CadastroCarga janela;
    private JLabel lTara = new JLabel("Tara:");
    private JLabel lCargaMax = new JLabel("Carga Máx:");
    private JLabel lPlaca = new JLabel("Placa:");
    private JLabel lMarca = new JLabel("Marca:");
    private JLabel lModelo = new JLabel("Modelo:");
    private JLabel lCor = new JLabel("Cor:");
    private JLabel lQtdRodas = new JLabel("Qtd. Rodas:");
    private JLabel lVelocidadeMax = new JLabel("Velocidade Max:");
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

    private final float VELOC_PADRAO_CARGA = 90;

    private CadastroCarga() {
        iniciar();
    }

    private void iniciar(){
        setTitle("Cadastro de Carga");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(10,2, 4, 4));
        panelCampos.add(lTara);
        panelCampos.add(textTara);
        panelCampos.add(lCargaMax);
        panelCampos.add(textCargaMax);
        panelCampos.add(lPlaca);
        panelCampos.add(textPlaca);
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

        JButton bCadastrar = new JButton("Cadastrar");
        bCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cadastrarCarga();
            }
        });

        JButton bLimpar = new JButton("Limpar");
        bLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                limparCampos();
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
        panelButoes.add(bCadastrar);
        panelButoes.add(bLimpar);
        panelButoes.add(bSair);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(20,20));
        panel.setBorder(new EmptyBorder(20, 40, 20, 40 ));
        panel.add(panelCampos, BorderLayout.CENTER);
        panel.add(panelButoes, BorderLayout.SOUTH);
        add(panel);
        pack();
    }

    public static CadastroCarga getCadastroCarga(){
        if (janela== null) {
            janela = new CadastroCarga();
        }
        return janela;
    }

    private void cadastrarCarga() {
        if (textTara.getText().isBlank() || !Util.numeroValido(textTara.getText())) {
            Util.notificar(textTara, "Deve informar um número para Tara.");
        } else if (textCargaMax.getText().isBlank() || !Util.numeroValido(textCargaMax.getText())) {
            Util.notificar(textCargaMax, "Deve informar um número para Carga Max.");
        } else if (textPlaca.getText().isBlank()) {
            Util.notificar(textPlaca, "Deve informar a Placa.");
        } else if (textMarca.getText().isBlank()) {
            Util.notificar(textMarca, "Deve informar a Marca.");
        } else if (textModelo.getText().isBlank()) {
            Util.notificar(textModelo, "Deve informar o Modelo.");
        } else if (textCor.getText().isBlank()) {
            Util.notificar(textCor, "Deve informar a Cor.");
        } else if (textQtdRodas.getText().isBlank() || !Util.numeroValido(textQtdRodas.getText())) {
            Util.notificar(textQtdRodas, "Deve informar um número para Qtd. Rodas.");
        } else if (textVelocidadeMax.getText().isBlank() || !Util.floatValido(textVelocidadeMax.getText())) {
            Util.notificar(textVelocidadeMax, "Deve informar um número para Velocidade Max.");
        } else if (textQtdPistoes.getText().isBlank() || !Util.numeroValido(textQtdPistoes.getText())) {
            Util.notificar(textQtdPistoes, "Deve informar um número para Qtd. Pistões.");
        } else if (textPotencia.getText().isBlank() || !Util.numeroValido(textPotencia.getText())) {
            Util.notificar(textPotencia, "Deve informar um número para Potência.");
        } else {
            try {
                Carga novoCarga = new Carga();
                novoCarga.setTara(Integer.parseInt(textTara.getText()));
                novoCarga.setCargaMax(Integer.parseInt(textCargaMax.getText()));
                novoCarga.setPlaca(textPlaca.getText());
                novoCarga.setMarca(textMarca.getText());
                novoCarga.setModelo(textModelo.getText());
                novoCarga.setCor(textCor.getText());
                novoCarga.setQtdRodas(Integer.parseInt(textQtdRodas.getText()));
                novoCarga.getMotor().setPotencia(Integer.parseInt(textPotencia.getText()));
                novoCarga.getMotor().setQtdPist(Integer.parseInt(textQtdPistoes.getText()));

                try {
                    novoCarga.setVelocMax(Float.parseFloat(textVelocidadeMax.getText()));
                } catch (VelocException ve) {
                    try {
                        novoCarga.setVelocMax(VELOC_PADRAO_CARGA);
                    } catch (VelocException e) {
                        System.out.println("Erro no Sistema");
                    }
                    Util.notificar("Atenção", ve.getMessage(), JOptionPane.WARNING_MESSAGE);
                }

                BDVeiculos.getDBVeiculos().adicionarCarga(novoCarga);
                Util.notificar("Sucesso", "Veiculo cadastrado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } catch (VeicExistException ve) {
                Util.notificar("Placa duplicada", ve.getMessage(), JOptionPane.ERROR_MESSAGE);
                textPlaca.setText("");
                textPlaca.requestFocus();
            }
        }
    }

    private void limparCampos() {
        textTara.setText("");
        textCargaMax.setText("");
        textPlaca.setText("");
        textMarca.setText("");
        textModelo.setText("");
        textCor.setText("");
        textQtdRodas.setText("");
        textVelocidadeMax.setText("");
        textQtdPistoes.setText("");
        textPotencia.setText("");
        textTara.requestFocus();
    }

    private void sair() {
        int resp = Util.confirmar("Saida","Deseja realmente sair?");
        if(resp == 0){
            limparCampos();
            dispose();
        }
    }
}
