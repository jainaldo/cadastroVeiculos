
public class VeicExistException extends Exception {

    VeicExistException(String tipoVeiculo, String placa) {
        super(String.format("Já existe um veículo de %s com a placa: %s", tipoVeiculo, placa));
    }
}