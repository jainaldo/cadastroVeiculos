public final class Passeio extends Veiculo implements Calcular {
    private int qtdPassageiros;

    public Passeio() {
        qtdPassageiros = 0;
    }

    public final int getQtdPassageiros() {
        return qtdPassageiros;
    }

    public final void setQtdPassageiros(int qtdPassageiros) {
        this.qtdPassageiros = qtdPassageiros;
    }

    @Override
    public final float calcVel(float velocMax) {
        return velocMax * 1000;
    }

    @Override
    public final int calcular() {
        int soma = 0;

        soma += getPlaca().length();
        soma += getMarca().length();
        soma += getModelo().length();
        soma += getCor().length();

        return soma;
    }
}
