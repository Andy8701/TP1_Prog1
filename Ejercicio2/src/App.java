public class App {
    public static void main(String[] args) {
        Alumno a1 = new Alumno("Lucía Gómez", "40123456", "L-1001", 8.5);
        Alumno a2 = new Alumno("Martín Pérez", "38987654", "L-1002", 7.2);

        a1.mostrarDatos();
        System.out.println();
        a2.mostrarDatos();
    }
}