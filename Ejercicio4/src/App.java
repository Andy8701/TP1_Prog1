public class App {
    public static void main(String[] args) {
        // Arreglo de tipo Empleado con objetos de distintas subclases
        Empleado[] empleados = {
            new EmpleadoPlanta("Lucía Gómez", "40123456", 500000, 5),
            new EmpleadoContratado("Martín Pérez", "38987654", 120, 3500),
            new EmpleadoPlanta("Sofía Ramírez", "35111222", 650000, 10),
            new EmpleadoContratado("Julián Díaz", "42333444", 80, 5000)
        };

        // Recorrido polimórfico: sin if ni switch
        for (Empleado e : empleados) {
            System.out.println(e.getNombre() + " - Sueldo: $" + e.calcularSueldo());
        }

        // Análisis: qué clase real tiene cada objeto
        System.out.println("\nAnálisis de tipos:");
        for (Empleado e : empleados) {
            System.out.println(e.getNombre() + " -> " + e.getClass().getSimpleName());
        }
    }
}