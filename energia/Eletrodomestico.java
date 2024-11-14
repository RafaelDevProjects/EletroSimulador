package energia;
public class Eletrodomestico {
    private String nome;
    private double potencia;
    private double horasPorDia;

    public Eletrodomestico(String nome, double potencia, double horasPorDia){
        this.nome = nome;
        this.potencia = potencia;
        this.horasPorDia = horasPorDia;
    }

    public double calcularConsumo() {
        return (potencia * horasPorDia * 30) / 1000;
    }

    public double calcularConsumo(double dias) {
        return (potencia * horasPorDia * dias) / 1000;
    }

    public String getNome() {
        return nome;
    }

    public double getPotencia() {
        return potencia;
    }

    public double getHorasPorDia() {
        return horasPorDia;
    }
}
