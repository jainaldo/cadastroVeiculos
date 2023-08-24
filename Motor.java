public class Motor {
    private int qtdPist;
    private int potencia;

    public Motor() {
        qtdPist = 0;
        potencia = 0;
    }

    public final void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public final void setQtdPist(int qtdPist) {
        this.qtdPist = qtdPist;
    }

    public final int getPotencia() {
        return potencia;
    }

    public final int getQtdPist() {
        return qtdPist;
    }
}
