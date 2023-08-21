import java.util.ArrayList;
import java.util.List;

public class BDVeiculos {
    private List<Passeio> listaPasseio = new ArrayList<Passeio>();
    private List<Carga> listaCarga = new ArrayList<Carga>();

    public Passeio criarPasseio(String placa) throws VeicExistException {
        Passeio passeio = buscarPasseio(placa);
        if (passeio != null) {
            throw new VeicExistException("passeio", placa);
        }
        passeio = new Passeio();
        passeio.setPlaca(placa);
        return passeio;
    }

    public Passeio buscarPasseio(String placa) {
        for (Passeio passeio : listaPasseio) {
            if (passeio.getPlaca().equalsIgnoreCase(placa)) {
                return passeio;
            }
        }
        return null;
    }

    public void adicionarPasseio(Passeio novoPasseio){
        listaPasseio.add(novoPasseio);
    }

    public Carga existePlacaCarga(Carga novaCarga) throws VeicExistException {
        Carga cargaExiste = buscarCarga(novaCarga);
        if (cargaExiste != null) {
            throw new VeicExistException("carga", novaCarga.getPlaca());
        }
        return novaCarga;
    }

    public Carga buscarCarga(Carga cargaProcurada) {
        for (Carga carga : listaCarga) {
            if (carga.getPlaca().equalsIgnoreCase(cargaProcurada.getPlaca())) {
                return carga;
            }
        }
        return null;
    }

    public void adicionarCarga(Carga novaCarga){
        listaCarga.add(novaCarga);
    }

    public void imprimeTodosPasseios() {
        for (Passeio passeio : listaPasseio) {
            imprimePasseio(passeio);
        }
    }

    public void imprimeTodosCargas() {
        for (Carga carga : listaCarga) {
            imprimeCarga(carga);
        }
    }

    public boolean estaVazioPasseio() {
        return listaPasseio.isEmpty();
    }

    public boolean estaVazioCarga() {
        return listaCarga.isEmpty();
    }

    public void imprimePasseio(Passeio veiculo) {
        System.out.println(String.format("\n============ VEICULO PASSEIO PLACA (%s): ============\n", veiculo.getPlaca()));
        System.out.println("Placa...............:= " + veiculo.getPlaca());
        System.out.println("Marca...............:= " + veiculo.getMarca());
        System.out.println("Modelo..............:= " + veiculo.getModelo());
        System.out.println("Cor.................:= " + veiculo.getCor());
        System.out.println("Qtd rodas...........:= " + veiculo.getQtdRodas());
        System.out.println("Qtd Passageiros.....:= " + veiculo.getQtdPassageiros());
        System.out.println("Potencia do Motor...:= " + veiculo.getMotor().getPotencia());
        System.out.println("Qtd Pistoes Motor...:= " + veiculo.getMotor().getQtdPist());
        System.out.println("Velocidade Maxima (km/h)...:= " + String.format("%.3f km/h", veiculo.getVelocMax()).replace(".", ","));
        System.out.println("Velocidade Maxima (m/h)....:= " + String.format("%.3f m/h", veiculo.calcVel(veiculo.getVelocMax())).replace(".", ","));
        System.out.println("Qtd total de todos os atributos do tipo String...:= " + veiculo.calcular());
    }

    public void imprimeCarga(Carga veiculo) {
        System.out.println(String.format("\n============ VEICULO CARGA PLACA (%s): ============\n", veiculo.getPlaca()));
        System.out.println("Placa...............:= " + veiculo.getPlaca());
        System.out.println("Marca...............:= " + veiculo.getMarca());
        System.out.println("Modelo..............:= " + veiculo.getModelo());
        System.out.println("Cor.................:= " + veiculo.getCor());
        System.out.println("Qtd rodas...........:= " + veiculo.getQtdRodas());
        System.out.println("Tara................:= " + veiculo.getTara());
        System.out.println("Carga Maxima........:= " + veiculo.getTara());
        System.out.println("Potencia do Motor...:= " + veiculo.getMotor().getPotencia());
        System.out.println("Qtd Pistoes Motor...:= " + veiculo.getMotor().getQtdPist());
        System.out.println("Velocidade Maxima (km/h)...:= " + String.format("%.3f km/h", veiculo.getVelocMax()).replace(".", ","));
        System.out.println("Velocidade Maxima (cm/h)...:= " + String.format("%.3f cm/h", veiculo.calcVel(veiculo.getVelocMax())).replace(".", ","));
        System.out.println("Qtd total de todos os atributos do tipo numericos...:= " + veiculo.calcular());
    }

    public void excluirPasseio(Passeio passeio) {
        listaPasseio.remove(passeio);
    }

    public void excluirCarga(Carga carga) {
        listaCarga.remove(carga);
    }
}
