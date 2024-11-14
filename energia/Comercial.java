package energia;

public class Comercial extends Casa {

    public Comercial(double tarifaKwh) {
        super(tarifaKwh);
    }

    @Override
    public double calcularCustoTotal() {
        return super.calcularConsumoTotal() * tarifaKwh * 1.15;
    }
}
