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

    /**
     * Calcula o consumo total de energia de todos os eletrodomésticos da casa.
     *
     * @return O consumo total de energia de todos os eletrodomésticos, em kWh.
     */
    public double calcularConsumoTotal() {
        double consumoTotal = 0;
        for (Eletrodomestico e : eletrodomesticos) {
            consumoTotal += e.calcularConsumo();
        }
        return consumoTotal;
    }

    /**
     * Calcula o custo total de energia com base no consumo total e na tarifa.
     *
     * @return O custo total de energia, em reais.
     */
    public double calcularCustoTotal() {
        return calcularConsumoTotal() * tarifaKwh;
    }

    //Getters and Setters

    public double getTarifaKwh() {
        return tarifaKwh;
    }

    public void setTarifaKwh(double tarifaKwh) {
        this.tarifaKwh = tarifaKwh;
    }

    public List<Eletrodomestico> getEletrodomesticos() {
        return eletrodomesticos;
    }

    public void setEletrodomesticos(List<Eletrodomestico> eletrodomesticos) {
        this.eletrodomesticos = eletrodomesticos;
    }
}
