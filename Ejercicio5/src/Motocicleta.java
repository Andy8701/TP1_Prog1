public class Motocicleta extends Vehiculo {

    public Motocicleta(String marca, String modelo, double tarifaDiaria) {
        super(marca, modelo, tarifaDiaria);
    }

    @Override
    public double calcularAlquiler(int dias) {
        // 10% de descuento sobre la tarifa total
        return getTarifaDiaria() * dias * 0.9;
    }
}