public class Motor {
    private int qtdPist;
    private int potencia;

    public Motor() {
        qtdPist = 0;
        potencia = 0;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setQtdPist(int qtdPist) {
        this.qtdPist = qtdPist;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getQtdPist() {
        return qtdPist;
    }
}
