package energia;

public class Residencial extends Casa {

    public  Residencial(double tarifaKwh) {
        super(tarifaKwh);
    }

    @Override
    public double calcularCustoTotal() {
        return super.calcularCustoTotal() * tarifaKwh;
    }

    public double calcularCustoTotal(int meses) {
        return super.calcularConsumoTotal() * tarifaKwh * meses;
    }
}
