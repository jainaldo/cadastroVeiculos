public abstract class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private float velocMax;
    private int qtdRodas;
    private Motor motor;

    public Veiculo() {
        placa = " ";
        marca = " ";
        modelo = " ";
        cor = " ";
        velocMax = 0;
        qtdRodas = 0;
        motor = new Motor();
    }

    public final void setCor(String cor) {
        this.cor = cor;
    }

    public final void setMarca(String marca) {
        this.marca = marca;
    }

    public final void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public final void setMotor(Motor motor) {
        this.motor = motor;
    }

    public final void setPlaca(String placa) {
        this.placa = placa;
    }

    public final void setQtdRodas(int qtdRodas) {
        this.qtdRodas = qtdRodas;
    }

    public final void setVelocMax(float velocMax) throws VelocException{
        if (velocMax < 80 || velocMax > 110) {
            throw new VelocException(velocMax);
        }
        this.velocMax = velocMax;
    }

    public String getCor() {
        return cor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Motor getMotor() {
        return motor;
    }

    public String getPlaca() {
        return placa;
    }

    public int getQtdRodas() {
        return qtdRodas;
    }

    public float getVelocMax() {
        return velocMax;
    }

    public abstract float calcVel(float velocMax);
}
