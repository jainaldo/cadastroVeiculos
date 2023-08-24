import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Teste {
    private static float velocidadePadraoPasseio = 100;
    private static float velocidadePadraoCarga = 90;

    public static void main(String[] args) {
        JanelaPrincipal.getJanelaPrincipal().setVisible(true);
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
