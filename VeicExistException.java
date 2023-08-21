
public class VeicExistException extends Exception {

    VeicExistException(String tipoVeiculo, String placa) {
        super(String.format("Já existe um veículo (%s) com esta placa (%s)", tipoVeiculo, placa));
    }
}