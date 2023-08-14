public final class Carga extends Veiculo implements Calcular {
    private int cargaMax;
    private int tara;

    public Carga() {
        cargaMax = 0;
        tara = 0;
    }

    public final int getCargaMax() {
        return cargaMax;
    }

    public final void setCargaMax(int cargaMax) {
        this.cargaMax = cargaMax;
    }

    public final int getTara() {
        return tara;
    }

    public final void setTara(int tara) {
        this.tara = tara;
    }

    @Override
    public final float calcVel(float velocMax) {
        return velocMax * 100000;
    }

    @Override
    public final int calcular() {
        int soma = 0;

        soma += getCargaMax();
        soma += getQtdRodas();
        soma += getTara();
        soma += getVelocMax();
        soma += getMotor().getPotencia();
        soma += getMotor().getQtdPist();

        return soma;
    }
}
