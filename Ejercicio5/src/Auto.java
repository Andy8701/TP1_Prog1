public class Auto extends Vehiculo {

    public Auto(String marca, String modelo, double tarifaDiaria) {
        super(marca, modelo, tarifaDiaria);
    }

    @Override
    public double calcularAlquiler(int dias) {
        return getTarifaDiaria() * dias;
    }
}