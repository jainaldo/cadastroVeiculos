public class VelocException extends Exception {
    VelocException(float velocidade) {
        System.out.println("\n=========================== ATENÇÃO ===========================");
        String kmFormat = String.format("%.3f km/h", velocidade).replace(".", ",");
        System.out.println(String.format("A velocidade máxima (%s) esta fora dos limites brasileiros.\n", kmFormat));
    }
}
