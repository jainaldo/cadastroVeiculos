import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Leitura {
    public String entDados(String rotulo) {
        System.out.println(rotulo);

        InputStreamReader teclado = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(teclado);

        String resultado = "";

        try {
            resultado = buffer.readLine();
        } catch (IOException e) {
            System.out.println("\n Erro de sistema.");
        }

        return resultado;
    }
}
