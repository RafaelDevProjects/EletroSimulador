package energia;

import java.util.ArrayList;
import java.util.List;

public class Casa {
    protected double tarifaKwh;
    protected List<Eletrodomestico> eletrodomesticos;

    public Casa(double tarifaKwh) {
        this.tarifaKwh = tarifaKwh;
        this.eletrodomesticos = new ArrayList<>();
    }

    public void adicionarEletrodomestico(Eletrodomestico e) {
        eletrodomesticos.add(e);
    }

    public double calcularConsumoTotal() {
        double consumoTotal = 0;
        for (Eletrodomestico e : eletrodomesticos) {
            consumoTotal += e.calcularConsumo();
        }
        return consumoTotal;
    }

    public double calcularCustoTotal() {
        return calcularConsumoTotal() * tarifaKwh;
    }
}
