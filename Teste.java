import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Teste {

    private static int opcao = 0;
    private static Leitura leitura = new Leitura();
    private static BDVeiculos bdVeiculos = new BDVeiculos();

    private static float velocidadePadraoPasseio = 100;
    private static float velocidadePadraoCarga = 90;

    private static Color corVerde = new Color(40, 190, 37);
    private static Color corAzul = new Color(37, 150, 190);
    private static Color corVermelho = new Color(194, 76, 76);

    private static ImageIcon iconVerde = createColorIcon(corVerde);
    private static ImageIcon iconAzul = createColorIcon(corAzul);
    private static ImageIcon iconVermelho = createColorIcon(corVermelho);

    private static JFrame telaPrincipal;
    private static JFrame telaMenuPasseio;
    private static JFrame telaCadastrarPasseio;
    private static JFrame telaConsultarExcluirPasseio;

    private static JLabel lQtdPassageiros = new JLabel("Qtd. Passageiros:");
    private static JLabel lPlaca = new JLabel("Placa:");
    private static JLabel lMarca = new JLabel("Marca:");
    private static JLabel lModelo = new JLabel("Modelo:");
    private static JLabel lCor = new JLabel("Cor:");
    private static JLabel lQtdRodas = new JLabel("Qtd. Rodas:");
    private static JLabel lVelocidadeMax = new JLabel("Velocidade Max:");
    private static JLabel lQtdPistoes = new JLabel("Qtd. Pistões:");
    private static JLabel lPotencia = new JLabel("Potência:");
    private static JLabel lInformePlaca = new JLabel("Informe a Placa:");

    private static JTextField textQtdPassageiros = new JTextField();
    private static JTextField textPlaca = new JTextField();
    private static JTextField textMarca = new JTextField();
    private static JTextField textModelo = new JTextField();
    private static JTextField textCor = new JTextField();
    private static JTextField textQtdRodas = new JTextField();
    private static JTextField textVelocidadeMax = new JTextField();
    private static JTextField textQtdPistoes = new JTextField();
    private static JTextField textPotencia = new JTextField();
    private static JTextField textInformePlaca = new JTextField();

    private static JButton bLimparCamposPasseio = new JButton("Limpar");
    private static JPanel panelCampos = new JPanel();
    private static JPanel panelButoes = new JPanel();



    public static void main(String[] args) {
        getTelaPrincipal().setVisible(true);
    }

    private static JFrame getTelaPrincipal() {
        int largura = 500;
        int altura = 250;
        telaPrincipal = criaJanela("Gestão de Veículos", largura, altura);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel linkPasseio = criaMenuItem(iconAzul, "Passeio");
        linkPasseio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navegar(telaPrincipal, getTelaMenuPasseio());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mudaCorLink(linkPasseio, corAzul, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mudaCorLink(linkPasseio, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JPanel menu = criaMenu(Arrays.asList(linkPasseio));
        telaPrincipal.add(menu, BorderLayout.CENTER);
        return telaPrincipal;
    }

    private static JFrame getTelaMenuPasseio() {
        int largura = 500;
        int altura = 300;
        telaMenuPasseio = criaJanela("Veículos de Passeio", largura, altura);
        telaMenuPasseio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                getTelaPrincipal().setVisible(true);
            }
        });

        JLabel linkCadastrar = criaMenuItem(iconAzul, "Cadastrar");
        linkCadastrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navegar(telaMenuPasseio, getTelaCadastrarPasseio());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mudaCorLink(linkCadastrar, corAzul, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mudaCorLink(linkCadastrar, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JLabel linkConsultarExcluir = criaMenuItem(iconAzul, "Consultar/Excluir pela Placa");
        linkConsultarExcluir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navegar(telaMenuPasseio, getTelaConsultarExcluirPasseio());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mudaCorLink(linkConsultarExcluir, corAzul, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mudaCorLink(linkConsultarExcluir, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JLabel linkSair = criaMenuItem(iconVermelho, "Sair");
        linkSair.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                navegar(telaMenuPasseio, getTelaPrincipal());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mudaCorLink(linkSair, corVermelho, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mudaCorLink(linkSair, null, Cursor.DEFAULT_CURSOR);
            }
        });

        JPanel menu = criaMenu(Arrays.asList(linkCadastrar, linkConsultarExcluir, linkSair));
        telaMenuPasseio.add(menu, BorderLayout.CENTER);


        return telaMenuPasseio;
    }

    private static JFrame getTelaCadastrarPasseio() {

        int largura = 500;
        int altura = 100;
        telaCadastrarPasseio = criaJanela("Cadastro de Passeio", largura, altura);
        telaCadastrarPasseio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                getTelaMenuPasseio().setVisible(true);
            }
        });


        panelCampos.removeAll();
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

        panelButoes.removeAll();
        panelButoes.setLayout(new FlowLayout());

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
                textQtdPassageiros.requestFocus();
            }
        });
        JButton bNovo = new JButton("Novo");
        JButton bSair = new JButton("Sair");
        bSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                limparCampos();
                navegar(telaCadastrarPasseio, getTelaMenuPasseio());
            }
        });

        panelButoes.add(bCadastrar);
        panelButoes.add(bLimpar);
        panelButoes.add(bNovo);
        panelButoes.add(bSair);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(panelCampos, BorderLayout.CENTER);
        panel.add(panelButoes, BorderLayout.SOUTH);

        telaCadastrarPasseio.add(panel);
        telaCadastrarPasseio.pack();
        return telaCadastrarPasseio;
    }

    private static JFrame getTelaConsultarExcluirPasseio() {
        int largura = 500;
        int altura = 100;

        telaConsultarExcluirPasseio = criaJanela("Consultar/Excluir pela placa", largura, altura);
        telaConsultarExcluirPasseio.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                getTelaMenuPasseio().setVisible(true);
            }
        });

        panelCampos.removeAll();
        panelCampos.setLayout(new GridLayout(9,2, 4, 4));

        lInformePlaca.setForeground(corVermelho);
        panelCampos.add(lInformePlaca);
        panelCampos.add(textInformePlaca);
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

        panelButoes.removeAll();
        panelButoes.setLayout(new FlowLayout());

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
                limparCampos();
                navegar(telaConsultarExcluirPasseio, getTelaMenuPasseio());
            }
        });

        panelButoes.add(bConsultar);
        panelButoes.add(bExcluir);
        panelButoes.add(bSair);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(panelCampos, BorderLayout.CENTER);
        panel.add(panelButoes, BorderLayout.SOUTH);

        telaConsultarExcluirPasseio.add(panel);
        telaConsultarExcluirPasseio.pack();
        return telaConsultarExcluirPasseio;
    }

    public static void limparCampos() {
        textQtdPassageiros.setText("");
        textPlaca.setText("");
        textMarca.setText("");
        textModelo.setText("");
        textCor.setText("");
        textQtdRodas.setText("");
        textVelocidadeMax.setText("");
        textQtdPistoes.setText("");
        textPotencia.setText("");
        textInformePlaca.setText("");
    }

    private static ActionListener criaLimparCamposActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                limparCampos();
                if (actionEvent.getSource().equals(bLimparCamposPasseio)) {
                    textQtdPassageiros.requestFocus();
                }
            }
        };
    }

    public static void campoInvalido(JTextField campo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Campo inválido", JOptionPane.ERROR_MESSAGE);
        campo.requestFocus();
    }

    public static ActionListener cadastrarPasseiroActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cadastrarPasseio();
            }
        };
    }

    private static ActionListener criaSairActionListener(JFrame origem, JFrame destino) {

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                limparCampos();
//                panelCampos.removeAll();
//                navegar(destino);
            }
        };
    }

    private static JPanel criaMenu(List<JLabel> itemsMenu) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(itemsMenu.size(),1, 10, 10));

        for (JLabel item: itemsMenu) {
            panel.add(item);
        }

        return panel;
    }

    private static ImageIcon createColorIcon(Color color) {
        BufferedImage img = new BufferedImage(25, 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, 25, 20);
        g.dispose();
        return new ImageIcon(img);
    }

    private static JFrame criaJanela(String titulo, int largura, int altura){
        JFrame janela = new JFrame();
        janela.setTitle(titulo);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setLayout(new FlowLayout(FlowLayout.LEADING, 50, 40));
        janela.setLocationRelativeTo(null);
        janela.setSize(largura, altura);

        return janela;
    }

    private static WindowAdapter criarWindowListener(JFrame destino){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                destino.setVisible(true);
            }
        };
    }

    public static void navegar(JFrame origem,JFrame destino) {
        origem.dispose();
        destino.setVisible(true);
    }

    public static void mudaCorLink(JLabel link, Color cor, int tipoCursor) {
        link.setCursor(Cursor.getPredefinedCursor(tipoCursor));
        link.setForeground(cor);
    }

    private static MouseListener criaMouseListener(JLabel link, Color cor, JFrame destino) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                navegar(destino);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mudaCorLink(link, cor, Cursor.HAND_CURSOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mudaCorLink(link, null, Cursor.DEFAULT_CURSOR);
            }
        };
    }

    private static JLabel criaMenuItem(ImageIcon icon, String nome) {
        JLabel link = new JLabel();
        link.setIcon(icon);
        link.setText(nome);
        link.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        link.setIconTextGap(10);

        return link;
    }

//    public static void iniciarSistema() {
//        boolean continua = true;
//
//        while (continua) {
//            imprimeMenu();
//
//            switch (opcao) {
//                case 1:
//                    adicionarPasseio();
//                    break;
//                case 2:
//                    adicionarCarga();
//                    break;
//                case 3:
//                    imprimeTodosPasseios();
//                    break;
//                case 4:
//                    imprimeTodosCargas();
//                    break;
//                case 5:
//                    imprimePasseioPelaPlaca();
//                    break;
//                case 6:
//                    imprimeCargaPelaPlaca();
//                    break;
//                case 7:
//                    excluirPasseioPelaPlaca();
//                    break;
//                case 8:
//                    excluirCargaPelaPlaca();
//                    break;
//                case 9:
//                    continua = false;
//                    informe(TipoInforme.SUCESSO, "Você saiu do sistema com sucesso! ;)");
//                    break;
//                default:
//                    informe(TipoInforme.ATENCAO, "O valor deve ser >= 1 e <= 9.");
//                    leitura.entDados("\nPressione <ENTER> para continuar...");
//                    break;
//            }
//        }
//    }
//
//    public static void imprimeMenu(){
//        System.out.println("\n==============================================================");
//        System.out.println("\n\tSistema de Gestão de Veículos - Menu Inicial");
//        System.out.println("\n1. Cadastrar Veiculo de Passeio");
//        System.out.println("2. Cadastrar Veiculo de Carga");
//        System.out.println("3. Imprimir Todos os Veiculos de Passeio");
//        System.out.println("4. Imprimir Todos os Veiculos de Carga");
//        System.out.println("5. Imprimir Veiculo de Passeio pela Placa");
//        System.out.println("6. Imprimir Veiculo de Carga pela Placa");
//        System.out.println("7. Excluir Veiculo de Passeio pela Placa");
//        System.out.println("8. Excluir Veiculo de Carga pela Placa");
//        System.out.println("9. Sair do Sistema");
//
//        try {
//            opcao = Integer.parseInt(leitura.entDados("\nEscolha uma opção: "));
//        } catch (NumberFormatException e) {
//            informe(TipoInforme.ATENCAO, "ATENÇÃO: Deve informar o número da opção.");
//            imprimeMenu();
//        }
//    }
//
    public static void cadastrarPasseio() {
        if (textQtdPassageiros.getText().isBlank()) {
            campoInvalido(textQtdPassageiros, "Deve informar a Qtd. Passageiros.");
        } else if (textPlaca.getText().isBlank()) {
            campoInvalido(textPlaca, "Deve informar a Placa.");
        } else if (textMarca.getText().isBlank()) {
            campoInvalido(textMarca, "Deve informar a Marca.");
        } else if (textModelo.getText().isBlank()) {
            campoInvalido(textModelo, "Deve informar o Modelo.");
        } else if (textCor.getText().isBlank()) {
            campoInvalido(textCor, "Deve informar a Cor.");
        } else if (textQtdRodas.getText().isBlank()) {
            campoInvalido(textQtdRodas, "Deve informar a Qtd. Rodas.");
        } else if (textVelocidadeMax.getText().isBlank()) {
            campoInvalido(textVelocidadeMax, "Deve informar a Velocidade Max.");
        } else if (textQtdPistoes.getText().isBlank()) {
            campoInvalido(textQtdPistoes, "Deve informar a Qtd. Pistões.");
        } else if (textPotencia.getText().isBlank()) {
            campoInvalido(textPotencia, "Deve informar a Potência.");
        } else if (validaNumero(textQtdPassageiros.getText())) {
            campoInvalido(textQtdPassageiros, "Deve informar um número para Qtd. Passageiros");
        } else if (validaNumero(textQtdRodas.getText())){
            campoInvalido(textQtdRodas, "Deve informar um número para Qtd. Rodas.");
        } else if (!floatValido(textVelocidadeMax.getText())) {
            campoInvalido(textVelocidadeMax, "Deve informar um número para Velocidade Max.");
        } else if (validaNumero(textQtdPistoes.getText())) {
            campoInvalido(textQtdPistoes, "Deve informar um número para Qtd. Pistões.");
        } else if (validaNumero(textPotencia.getText())) {
            campoInvalido(textPotencia, "Deve informar um número para Potência");
        } else {
            try {
                Passeio novoPasseio = bdVeiculos.criarPasseio(textPlaca.getText());
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
                        novoPasseio.setVelocMax(velocidadePadraoPasseio);
                    } catch (VelocException e) {
                        System.out.println("Erro no Sistema");
                    }
                    JOptionPane.showMessageDialog(null, ve.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
                }

                bdVeiculos.adicionarPasseio(novoPasseio);

                JOptionPane.showMessageDialog(null, "Veiculo cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            } catch (VeicExistException ve) {
                campoInvalido(textPlaca, ve.getMessage());
            }
        }
    }

    public static void consultarPasseio() {
        if (textInformePlaca.getText().isBlank()) {
            campoInvalido(textInformePlaca, "Deve informar a placa");
        } else {
            Passeio passeio = bdVeiculos.buscarPasseio(textInformePlaca.getText());

            if (passeio == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Não foi encontrado veiculo de passeio com esta placa.",
                        "Veiculo não encontrado",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
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

    private static void excluirPasseio() {
        if (textInformePlaca.getText().isBlank()) {
            campoInvalido(textInformePlaca, "Deve informar a placa");
        } else {
            Passeio passeio = bdVeiculos.buscarPasseio(textInformePlaca.getText());

            if (passeio == null) {
                JOptionPane.showMessageDialog(
                        null,
                        String.format("Não foi encontrado veiculo de passeio com esta placa: %s.", textInformePlaca.getText()),
                        "Veiculo não encontrado",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                int opcao = JOptionPane.showInternalConfirmDialog(
                        null,
                        String.format("Tem certeza que deseja excluir o veiculo de passeio de placa: %s ? ", textInformePlaca.getText()),
                        "Confirmação da exclusão",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (opcao == 0) {
                    bdVeiculos.excluirPasseio(passeio);
                    JOptionPane.showMessageDialog(null, "Veiculo excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }

    private static boolean validaNumero(String texto) {
        try {
            Integer.parseInt(texto);
            return false;
        }catch (NumberFormatException e){
            return true;
        }
    }

    private static boolean floatValido(String texto) {
        try {
            Float.parseFloat(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

//    public static void adicionarCarga() {
//        System.out.println("\n\n============= CADASTRO DE VEICULO DE CARGA ===============");
//        System.out.println("Insira os dados e valores solicitados abaixo:");
//        System.out.println("============================================================");
//
//        Carga novaCarga = new Carga();
//        try {
//            novaCarga.setPlaca(leitura.entDados("Placa:"));
//            novaCarga = bdVeiculos.existePlacaCarga(novaCarga);
//        } catch (Exception e) {
//            return;
//        }
//
//        novaCarga.setMarca(leitura.entDados("Marca:"));
//        novaCarga.setModelo(leitura.entDados("Modelo:"));
//        novaCarga.setCor(leitura.entDados("Cor:"));
//        novaCarga.setCargaMax(Integer.parseInt(leitura.entDados("Carga Maxima:")));
//        novaCarga.setTara(Integer.parseInt(leitura.entDados("Tara:")));
//        novaCarga.setQtdRodas(Integer.parseInt(leitura.entDados("Quantidade de Rodas:")));
//
//        try {
//            novaCarga.setVelocMax(Float.parseFloat(leitura.entDados("Velocidade Maxima:")));
//        } catch (VelocException e) {
//            try {
//                novaCarga.setVelocMax(velocidadePadraoCarga);
//            } catch (VelocException e1) {
//                System.out.println("Erro no Sistema");
//            }
//        }
//
//        novaCarga.getMotor().setPotencia(Integer.parseInt(leitura.entDados("Potencia do Motor:")));
//        novaCarga.getMotor().setQtdPist(Integer.parseInt(leitura.entDados("Qtd Pistoes do Motor:")));
//
//        bdVeiculos.adicionarCarga(novaCarga);
//
//        informe(TipoInforme.SUCESSO, "Veiculo cadastrado com sucesso!");
//
//        String resposta = leitura.entDados("\nDeseja adicionar mais veiculo de carga? (S/N)");
//
//        if (resposta.equalsIgnoreCase("s")) {
//            adicionarCarga();
//        }
//    }
//
//    public static void informe(TipoInforme tipo, String mensagem) {
//        switch (tipo) {
//            case ATENCAO:
//                System.out.println("\n=========================== ATENÇÃO ===========================");
//                System.out.println(mensagem);
//                break;
//            case SUCESSO:
//                System.out.println("\n=========================== SUCESSO ===========================");
//                System.out.println(mensagem);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public static void imprimeTodosPasseios() {
//        System.out.println("\n============= TODOS OS VEICULOS DE PASSEIO ===============");
//        if(bdVeiculos.estaVazioPasseio()) {
//            informe(TipoInforme.ATENCAO, "Ainda não tem veiculos de passeio cadastrado!");
//            return;
//        }
//
//        bdVeiculos.imprimeTodosPasseios();
//        informe(TipoInforme.SUCESSO, "A impressão de todos os veiculos de passeio foi concluida!");
//    }
//
//    public static void imprimeTodosCargas() {
//        System.out.println("\n============= TODOS OS VEICULOS DE CARGA ===============");
//        if(bdVeiculos.estaVazioCarga()) {
//            informe(TipoInforme.ATENCAO, "Ainda não tem veiculos de carga cadastrado!");
//            return;
//        }
//
//        bdVeiculos.imprimeTodosCargas();
//        informe(TipoInforme.SUCESSO, "A impressão de todos os veiculos de carga foi concluida!");
//    }
//
//
//
//    public static void imprimeCargaPelaPlaca() {
//        System.out.println("\n============== PESQUISAR VEICULO CARGA ===========");
//        Carga carga = new Carga();
//        carga.setPlaca(leitura.entDados("Informe a placa que deseja pesquisar:"));
//        carga = bdVeiculos.buscarCarga(carga);
//
//        if (carga == null) {
//            informe(TipoInforme.ATENCAO, "Não foi encontrado veiculo de carga com esta placa.");
//            return;
//        }
//
//        bdVeiculos.imprimeCarga(carga);
//        informe(TipoInforme.SUCESSO, "A impressão do veiculo de carga foi concluida!");
//    }
//
//
//
//    private static void excluirCargaPelaPlaca() {
//        System.out.println("\n============== EXCLUIR VEICULO CARGA ===========");
//        Carga carga = new Carga();
//        carga.setPlaca(leitura.entDados("Informe a placa que deseja excluir:"));
//        carga = bdVeiculos.buscarCarga(carga);
//
//        if (carga == null) {
//            informe(TipoInforme.ATENCAO, "Não foi encontrado veiculo de carga com esta placa.");
//            return;
//        }
//
//        bdVeiculos.excluirCarga(carga);
//        informe(TipoInforme.SUCESSO, "O veiculo de carga foi excluido!");
//    }
}
