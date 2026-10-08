public class EmpleadoContratado extends Empleado {
    private int horasTrabajadas;
    private double valorHora;

    public EmpleadoContratado(String nombre, String dni, int horasTrabajadas, double valorHora) {
        super(nombre, dni);
        this.horasTrabajadas = horasTrabajadas;
        this.valorHora = valorHora;
    }

    public int getHorasTrabajadas() { return horasTrabajadas; }
    public void setHorasTrabajadas(int horasTrabajadas) { this.horasTrabajadas = horasTrabajadas; }

    public double getValorHora() { return valorHora; }
    public void setValorHora(double valorHora) { this.valorHora = valorHora; }

    @Override
    public double calcularSueldo() {
        return horasTrabajadas * valorHora;
    }
}