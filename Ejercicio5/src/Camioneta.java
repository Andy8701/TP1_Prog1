public class Camioneta extends Vehiculo {
    private double seguroDiario;

    public Camioneta(String marca, String modelo, double tarifaDiaria, double seguroDiario) {
        super(marca, modelo, tarifaDiaria);
        this.seguroDiario = seguroDiario;
    }

    public double getSeguroDiario() { return seguroDiario; }
    public void setSeguroDiario(double seguroDiario) { this.seguroDiario = seguroDiario; }

    @Override
    public double calcularAlquiler(int dias) {
        // Tarifa más un seguro adicional por cada día
        return (getTarifaDiaria() + seguroDiario) * dias;
    }
}