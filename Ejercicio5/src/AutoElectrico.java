public class AutoElectrico extends Vehiculo {
    private double cargoFijoCarga;

    public AutoElectrico(String marca, String modelo, double tarifaDiaria, double cargoFijoCarga) {
        super(marca, modelo, tarifaDiaria);
        this.cargoFijoCarga = cargoFijoCarga;
    }

    public double getCargoFijoCarga() { return cargoFijoCarga; }
    public void setCargoFijoCarga(double cargoFijoCarga) { this.cargoFijoCarga = cargoFijoCarga; }

    @Override
    public double calcularAlquiler(int dias) {
        // Tarifa por días más un cargo fijo por el servicio de carga
        return getTarifaDiaria() * dias + cargoFijoCarga;
    }
}