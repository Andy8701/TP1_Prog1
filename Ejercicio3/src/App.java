public class App {
    public static void main(String[] args) {
        EmpleadoPlanta planta = new EmpleadoPlanta("Lucía Gómez", "40123456", 500000, 5);
        EmpleadoContratado contratado = new EmpleadoContratado("Martín Pérez", "38987654", 120, 3500);

        System.out.println(planta.getNombre() + " (planta): $" + planta.calcularSueldo());
        System.out.println(contratado.getNombre() + " (contratado): $" + contratado.calcularSueldo());

        // new Empleado("X", "1"); // ERROR de compilación: una clase abstracta no se puede instanciar
    }
}