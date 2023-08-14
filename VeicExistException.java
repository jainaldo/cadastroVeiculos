
public class VeicExistException extends Exception {

    VeicExistException(String tipoVeiculo, String placa) {
        System.out.println("\n=========================== ATENÇÃO ===========================");
        System.out.println(String.format("Já existe um veículo (%s) com esta placa (%s)", tipoVeiculo, placa));
    }
}