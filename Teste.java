import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void main(String[] args) {
        criaJanelaPrincipal();
    }

    private static void criaJanelaPrincipal() {
        int largura = 500;
        int altura = 250;

        JFrame telaPrincipal = criaJanela("Gestão de Veículos", largura, altura);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel linkPasseio = criaMenuItem(iconAzul, "Passeio", corAzul, criaJanelaMenuPasseio(telaPrincipal), telaPrincipal);
        JLabel linkCarga = criaMenuItem(iconVerde, "Carga", corVerde, criaJanelaMenuCarga(telaPrincipal), telaPrincipal);
        JPanel menu = criaMenu(Arrays.asList(linkPasseio, linkCarga));
        telaPrincipal.add(menu, BorderLayout.CENTER);
        telaPrincipal.setVisible(true);
    }

    private static JPanel criaMenu(List<JLabel> itemsMenu) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1, 10, 10));

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

    private static JFrame criaJanelaMenuCarga(JFrame janelaRetorno) {
        JFrame janela = criaJanela("Veículos de Carga", 500,300);
        JLabel linkCadastrar = criaMenuItem(iconVerde, "Cadastrar", corVerde, new JFrame(), new JFrame());
        JLabel linkConsultarExcluirPlaca = criaMenuItem(iconVerde, "Consultar/Excluir pela placa", corVerde, new JFrame(), new JFrame());
        JLabel linkImprimirExcluirTodos = criaMenuItem(iconVerde, "Imprimir/Excluir todos", corVerde, new JFrame(), new JFrame());
        JLabel linkSair = criaMenuItem(iconVermelho, "Sair", corVermelho, janelaRetorno, janela);
        JPanel menu = criaMenu(Arrays.asList(linkCadastrar, linkConsultarExcluirPlaca, linkImprimirExcluirTodos, linkSair));

        janela.add(menu, BorderLayout.CENTER);
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                janelaRetorno.setVisible(true);
            }
        });
        return janela;
    }

    private static JFrame criaJanelaMenuPasseio(JFrame janelaRetorno) {
        JFrame janela = criaJanela("Veículos de Passeio", 500,300);
        JLabel linkCadastrar = criaMenuItem(iconAzul, "Cadastrar", corAzul, cadastroPasseio(janela), janela);
        JLabel linkConsultarExcluirPlaca = criaMenuItem(iconAzul, "Consultar/Excluir pela placa", corAzul, new JFrame(), janela);
        JLabel linkImprimirExcluirTodos = criaMenuItem(iconAzul, "Imprimir/Excluir todos", corAzul, new JFrame(), janela);
        JLabel linkSair = criaMenuItem(iconVermelho, "Sair", corVermelho, janelaRetorno, janela);
        JPanel menu = criaMenu(Arrays.asList(linkCadastrar, linkConsultarExcluirPlaca, linkImprimirExcluirTodos, linkSair));
        janela.add(menu, BorderLayout.CENTER);
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                janelaRetorno.setVisible(true);
            }
        });
        return janela;
    }

    private static JFrame cadastroPasseio(JFrame janelaRetorno) {
        JFrame janela = criaJanela("Cadastro de Passeio", 500, 600);
        Map<String, JTextField> campos = new HashMap<>();

        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(200, 20));

        campos.put("Tara", text);


        JPanel panel = new JPanel();
        panel.setLayout(new CardLayout());


        for (String key: campos.keySet()) {
            JLabel label = new JLabel(key);
            campos.get(key).setHorizontalAlignment(2);
            panel.add(label);
            panel.add(campos.get(key));
        }
        janela.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                janelaRetorno.setVisible(true);
            }
        });

        janela.add(panel);
        return janela;


    }

    private static JLabel criaMenuItem(ImageIcon icon, String nome, Color hoverColor, JFrame entraJanela, JFrame sairJanela) {
        JLabel link = new JLabel();
        link.setIcon(icon);
        link.setText(nome);
        link.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        link.setIconTextGap(10);
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sairJanela.setVisible(false);
                entraJanela.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                link.setForeground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                link.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                link.setForeground(null);
            }
        });
        return link;
    }

    public static void iniciarSistema() {
        boolean continua = true;

        while (continua) {
            imprimeMenu();

            switch (opcao) {
                case 1:
                    adicionarPasseio();
                    break;
                case 2:
                    adicionarCarga();
                    break;
                case 3:
                    imprimeTodosPasseios();
                    break;
                case 4:
                    imprimeTodosCargas();
                    break;
                case 5:
                    imprimePasseioPelaPlaca();
                    break;
                case 6:
                    imprimeCargaPelaPlaca();
                    break;
                case 7:
                    excluirPasseioPelaPlaca();
                    break;
                case 8:
                    excluirCargaPelaPlaca();
                    break;
                case 9:
                    continua = false;
                    informe(TipoInforme.SUCESSO, "Você saiu do sistema com sucesso! ;)");
                    break;
                default:
                    informe(TipoInforme.ATENCAO, "O valor deve ser >= 1 e <= 9.");
                    leitura.entDados("\nPressione <ENTER> para continuar...");
                    break;
            }
        }
    }

    public static void imprimeMenu(){
        System.out.println("\n==============================================================");
        System.out.println("\n\tSistema de Gestão de Veículos - Menu Inicial");
        System.out.println("\n1. Cadastrar Veiculo de Passeio");
        System.out.println("2. Cadastrar Veiculo de Carga");
        System.out.println("3. Imprimir Todos os Veiculos de Passeio");
        System.out.println("4. Imprimir Todos os Veiculos de Carga");
        System.out.println("5. Imprimir Veiculo de Passeio pela Placa");
        System.out.println("6. Imprimir Veiculo de Carga pela Placa");
        System.out.println("7. Excluir Veiculo de Passeio pela Placa");
        System.out.println("8. Excluir Veiculo de Carga pela Placa");
        System.out.println("9. Sair do Sistema");

        try {
            opcao = Integer.parseInt(leitura.entDados("\nEscolha uma opção: "));
        } catch (NumberFormatException e) {
            informe(TipoInforme.ATENCAO, "ATENÇÃO: Deve informar o número da opção.");
            imprimeMenu();
        }
    }

    public static void adicionarPasseio() {
        System.out.println("\n\n============= CADASTRO DE VEICULO DE PASSEIO ===============");
        System.out.println("Insira os dados e valores solicitados abaixo:");
        System.out.println("============================================================");

        Passeio novoPasseio = new Passeio();
        try {
            novoPasseio.setPlaca(leitura.entDados("Placa:"));
            novoPasseio = bdVeiculos.existePlacaPasseio(novoPasseio);
        } catch (VeicExistException e) {
            return;
        }

        novoPasseio.setMarca(leitura.entDados("Marca:"));
        novoPasseio.setModelo(leitura.entDados("Modelo:"));
        novoPasseio.setCor(leitura.entDados("Cor:"));
        novoPasseio.setQtdRodas(Integer.parseInt(leitura.entDados("Quantidade de Rodas:")));
        novoPasseio.setQtdPassageiros(Integer.parseInt(leitura.entDados("Capacidade de Passageiros:")));

        try {
            novoPasseio.setVelocMax(Float.parseFloat(leitura.entDados("Velocidade Maxima:")));
        } catch (VelocException ve) {
            try {
                novoPasseio.setVelocMax(velocidadePadraoPasseio);
            } catch (VelocException e) {
                System.out.println("Erro no Sistema");
            }
        }

        novoPasseio.getMotor().setPotencia(Integer.parseInt(leitura.entDados("Potencia do Motor:")));
        novoPasseio.getMotor().setQtdPist(Integer.parseInt(leitura.entDados("Qtd Pistoes do Motor:")));

        bdVeiculos.adicionarPasseio(novoPasseio);

        informe(TipoInforme.SUCESSO, "Veiculo cadastrado com sucesso!");

        String resposta = leitura.entDados("\nDeseja adicionar mais veiculo de passeio? (S/N)");

        if (resposta.equalsIgnoreCase("s")) {
            adicionarPasseio();
        }
    }

    public static void adicionarCarga() {
        System.out.println("\n\n============= CADASTRO DE VEICULO DE CARGA ===============");
        System.out.println("Insira os dados e valores solicitados abaixo:");
        System.out.println("============================================================");

        Carga novaCarga = new Carga();
        try {
            novaCarga.setPlaca(leitura.entDados("Placa:"));
            novaCarga = bdVeiculos.existePlacaCarga(novaCarga);
        } catch (Exception e) {
            return;
        }

        novaCarga.setMarca(leitura.entDados("Marca:"));
        novaCarga.setModelo(leitura.entDados("Modelo:"));
        novaCarga.setCor(leitura.entDados("Cor:"));
        novaCarga.setCargaMax(Integer.parseInt(leitura.entDados("Carga Maxima:")));
        novaCarga.setTara(Integer.parseInt(leitura.entDados("Tara:")));
        novaCarga.setQtdRodas(Integer.parseInt(leitura.entDados("Quantidade de Rodas:")));

        try {
            novaCarga.setVelocMax(Float.parseFloat(leitura.entDados("Velocidade Maxima:")));
        } catch (VelocException e) {
            try {
                novaCarga.setVelocMax(velocidadePadraoCarga);
            } catch (VelocException e1) {
                System.out.println("Erro no Sistema");
            }
        }

        novaCarga.getMotor().setPotencia(Integer.parseInt(leitura.entDados("Potencia do Motor:")));
        novaCarga.getMotor().setQtdPist(Integer.parseInt(leitura.entDados("Qtd Pistoes do Motor:")));

        bdVeiculos.adicionarCarga(novaCarga);

        informe(TipoInforme.SUCESSO, "Veiculo cadastrado com sucesso!");

        String resposta = leitura.entDados("\nDeseja adicionar mais veiculo de carga? (S/N)");

        if (resposta.equalsIgnoreCase("s")) {
            adicionarCarga();
        }
    }

    public static void informe(TipoInforme tipo, String mensagem) {
        switch (tipo) {
            case ATENCAO:
                System.out.println("\n=========================== ATENÇÃO ===========================");
                System.out.println(mensagem);
                break;
            case SUCESSO:
                System.out.println("\n=========================== SUCESSO ===========================");
                System.out.println(mensagem);
                break;
            default:
                break;
        }
    }

    public static void imprimeTodosPasseios() {
        System.out.println("\n============= TODOS OS VEICULOS DE PASSEIO ===============");
        if(bdVeiculos.estaVazioPasseio()) {
            informe(TipoInforme.ATENCAO, "Ainda não tem veiculos de passeio cadastrado!");
            return;
        }

        bdVeiculos.imprimeTodosPasseios();
        informe(TipoInforme.SUCESSO, "A impressão de todos os veiculos de passeio foi concluida!");
    }

    public static void imprimeTodosCargas() {
        System.out.println("\n============= TODOS OS VEICULOS DE CARGA ===============");
        if(bdVeiculos.estaVazioCarga()) {
            informe(TipoInforme.ATENCAO, "Ainda não tem veiculos de carga cadastrado!");
            return;
        }

        bdVeiculos.imprimeTodosCargas();
        informe(TipoInforme.SUCESSO, "A impressão de todos os veiculos de carga foi concluida!");
    }

    public static void imprimePasseioPelaPlaca() {
        System.out.println("\n============== PESQUISAR VEICULO PASSEIO ===========");
        Passeio passeio = new Passeio();
        passeio.setPlaca(leitura.entDados("Informe a placa que deseja pesquisar:"));
        passeio = bdVeiculos.buscarPasseio(passeio);

        if (passeio == null) {
            informe(TipoInforme.ATENCAO, "Não foi encontrado veiculo de passeio com esta placa.");
            return;
        }

        bdVeiculos.imprimePasseio(passeio);
        informe(TipoInforme.SUCESSO, "A impressão do veiculo de passeio foi concluida!");
    }

    public static void imprimeCargaPelaPlaca() {
        System.out.println("\n============== PESQUISAR VEICULO CARGA ===========");
        Carga carga = new Carga();
        carga.setPlaca(leitura.entDados("Informe a placa que deseja pesquisar:"));
        carga = bdVeiculos.buscarCarga(carga);

        if (carga == null) {
            informe(TipoInforme.ATENCAO, "Não foi encontrado veiculo de carga com esta placa.");
            return;
        }

        bdVeiculos.imprimeCarga(carga);
        informe(TipoInforme.SUCESSO, "A impressão do veiculo de carga foi concluida!");
    }

    private static void excluirPasseioPelaPlaca() {
        System.out.println("\n============== EXCLUIR VEICULO PASSEIO ===========");
        Passeio passeio = new Passeio();
        passeio.setPlaca(leitura.entDados("Informe a placa que deseja excluir:"));
        passeio = bdVeiculos.buscarPasseio(passeio);

        if (passeio == null) {
            informe(TipoInforme.ATENCAO, "Não foi encontrado veiculo de passeio com esta placa.");
            return;
        }

        bdVeiculos.excluirPasseio(passeio);
        informe(TipoInforme.SUCESSO, "O veiculo de passeio foi excluido!");
    }

    private static void excluirCargaPelaPlaca() {
        System.out.println("\n============== EXCLUIR VEICULO CARGA ===========");
        Carga carga = new Carga();
        carga.setPlaca(leitura.entDados("Informe a placa que deseja excluir:"));
        carga = bdVeiculos.buscarCarga(carga);

        if (carga == null) {
            informe(TipoInforme.ATENCAO, "Não foi encontrado veiculo de carga com esta placa.");
            return;
        }

        bdVeiculos.excluirCarga(carga);
        informe(TipoInforme.SUCESSO, "O veiculo de carga foi excluido!");
    }
}
