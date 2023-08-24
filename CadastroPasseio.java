import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPasseio extends JFrame {
    private static CadastroPasseio janela;
    private JLabel lQtdPassageiros = new JLabel("Qtd. Passageiros:");
    private JLabel lPlaca = new JLabel("Placa:");
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

    private final float VELOC_PADRAO_PASSEIO = 100;

    private CadastroPasseio() {
        iniciar();
    }

    private void iniciar(){
        setTitle("Cadastro de Passeio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout(new GridLayout(9,2, 4, 4));
        panelCampos.add(lQtdPassageiros);
        panelCampos.add(textQtdPassageiros);
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
                cadastrarPasseio();
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

    public static CadastroPasseio getCadastroPasseio(){
        if (janela== null) {
            janela = new CadastroPasseio();
        }
        return janela;
    }

    private void cadastrarPasseio() {
        if (textQtdPassageiros.getText().isBlank() || !numeroValido(textQtdPassageiros.getText())) {
            notificar(textQtdPassageiros, "Deve informar um número para Qtd. Passageiros");
        } else if (textPlaca.getText().isBlank()) {
            notificar(textPlaca, "Deve informar a Placa.");
        } else if (textMarca.getText().isBlank()) {
            notificar(textMarca, "Deve informar a Marca.");
        } else if (textModelo.getText().isBlank()) {
            notificar(textModelo, "Deve informar o Modelo.");
        } else if (textCor.getText().isBlank()) {
            notificar(textCor, "Deve informar a Cor.");
        } else if (textQtdRodas.getText().isBlank() || !numeroValido(textQtdRodas.getText())) {
            notificar(textQtdRodas, "Deve informar um número para Qtd. Rodas.");
        } else if (textVelocidadeMax.getText().isBlank() || !floatValido(textVelocidadeMax.getText())) {
            notificar(textVelocidadeMax, "Deve informar um número para Velocidade Max.");
        } else if (textQtdPistoes.getText().isBlank() || !numeroValido(textQtdPistoes.getText())) {
            notificar(textQtdPistoes, "Deve informar um número para Qtd. Pistões.");
        } else if (textPotencia.getText().isBlank() || !numeroValido(textPotencia.getText())) {
            notificar(textPotencia, "Deve informar um número para Potência");
        } else {
            try {
                Passeio novoPasseio = new Passeio();
                novoPasseio.setPlaca(textPlaca.getText());
                novoPasseio.setMarca(textMarca.getText());
                novoPasseio.setModelo(textModelo.getText());
                novoPasseio.setCor(textCor.getText());
                novoPasseio.setQtdRodas(Integer.parseInt(textQtdRodas.getText()));
                novoPasseio.setQtdPassageiros(Integer.parseInt(textQtdPassageiros.getText()));
                novoPasseio.getMotor().setPotencia(Integer.parseInt(textPotencia.getText()));
                novoPasseio.getMotor().setQtdPist(Integer.parseInt(textQtdPistoes.getText()));

                try {
                    novoPasseio.setVelocMax(Float.parseFloat(textVelocidadeMax.getText()));
                } catch (VelocException ve) {
                    try {
                        novoPasseio.setVelocMax(VELOC_PADRAO_PASSEIO);
                    } catch (VelocException e) {
                        System.out.println("Erro no Sistema");
                    }
                    notificar("Atenção", ve.getMessage(), JOptionPane.WARNING_MESSAGE);
                }

                BDVeiculos.getDBVeiculos().adicionarPasseio(novoPasseio);
                notificar("Sucesso", "Veiculo cadastrado com sucesso!", JOptionPane.INFORMATION_MESSAGE);
                limparCampos();
            } catch (VeicExistException ve) {
                notificar("Placa duplicada", ve.getMessage(), JOptionPane.ERROR_MESSAGE);
                textPlaca.setText("");
                textPlaca.requestFocus();
            }
        }
    }

    private void notificar(JTextField campo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Campo inválido", JOptionPane.ERROR_MESSAGE);
        campo.requestFocus();
    }

    private void notificar(String titulo, String mensagem, int tipoMensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipoMensagem);
    }

    private boolean numeroValido(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    private boolean floatValido(String texto) {
        try {
            Float.parseFloat(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
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
        int resp = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente sair?",
                "Saida",
                JOptionPane.YES_NO_OPTION
        );

        if(resp == 0){
            limparCampos();
            dispose();
        }
    }
}
