import java.util.ArrayList;
import java.util.List;

public class BDVeiculos {
    private List<Passeio> listaPasseio;
    private List<Carga> listaCarga;

    private static BDVeiculos geraDBVeiculosUnico;
    private BDVeiculos() {
        listaPasseio = new ArrayList<Passeio>();
        listaCarga = new ArrayList<Carga>();
    }

    public static BDVeiculos getDBVeiculos() {
        if (geraDBVeiculosUnico == null) {
            geraDBVeiculosUnico = new BDVeiculos();
        }
        return geraDBVeiculosUnico;
    }

    public Passeio buscarPasseio(Passeio passeioProcurado) {
        for (Passeio passeio : listaPasseio) {
            if (passeio.getPlaca().equalsIgnoreCase(passeioProcurado.getPlaca())) {
                return passeio;
            }
        }
        return null;
    }

    public Passeio adicionarPasseio(Passeio novoPasseio) throws VeicExistException {
        Passeio passeio = buscarPasseio(novoPasseio);
        if (passeio != null){
            throw new VeicExistException("passeio", novoPasseio.getPlaca());
        }
        listaPasseio.add(novoPasseio);
        return novoPasseio;
    }

    public Carga buscarCarga(Carga cargaProcurada) {
        for (Carga carga : listaCarga) {
            if (carga.getPlaca().equalsIgnoreCase(cargaProcurada.getPlaca())) {
                return carga;
            }
        }
        return null;
    }

    public Carga adicionarCarga(Carga novaCarga) throws VeicExistException {
        Carga carga = buscarCarga(novaCarga);
        if (carga != null){
            throw new VeicExistException("carga", novaCarga.getPlaca());
        }
        listaCarga.add(novaCarga);
        return novaCarga;
    }

    public void excluirPasseio(Passeio passeio) {
        listaPasseio.remove(passeio);
    }

    public void excluirCarga(Carga carga) {
        listaCarga.remove(carga);
    }

    public List<Passeio> getListaPasseio() {
        return listaPasseio;
    }

    public void excluirTodosPasseio() {
        listaPasseio = new ArrayList<Passeio>();
    }

    public List<Carga> getListaCarga() {
        return listaCarga;
    }

    public void excluirTodosCarga() {
        listaCarga = new ArrayList<Carga>();
    }
}
