public class VelocException extends Exception {
    VelocException(float velocidade) {
        super(String.format("A velocidade máxima (%s) esta fora dos limites brasileiros.\n", String.format("%.3f km/h", velocidade).replace(".", ",")));
    }
}
