public class App {
    public static void main(String[] args) {
        Carrera carrera = new Carrera("Ingeniería en Sistemas");

        Estudiante e1 = new Estudiante("Lucía", "Gómez", 20, "Ingeniería en Sistemas", 0);
        Estudiante e2 = new Estudiante("Martín", "Pérez", 22, "Ingeniería en Sistemas", 0);

        e1.agregarMateria(new Materia("Programación I", "PRG101", 6, 9));
        e1.agregarMateria(new Materia("Matemática", "MAT101", 4, 8));
        e2.agregarMateria(new Materia("Programación I", "PRG101", 6, 7));
        e2.agregarMateria(new Materia("Matemática", "MAT101", 4, 6));

        carrera.agregarEstudiante(e1);
        carrera.agregarEstudiante(e2);

        // Promedio de cada estudiante
        System.out.println("Promedios:");
        System.out.println(e1.getNombre() + ": " + e1.calcularPromedio());
        System.out.println(e2.getNombre() + ": " + e2.calcularPromedio());

        // Listado de la carrera
        System.out.println("\nEstudiantes de " + carrera.getNombre() + ":");
        carrera.listarEstudiantes();

        // Búsqueda
        Estudiante buscado = carrera.buscarEstudiante("martín");
        System.out.println("\nBúsqueda: "
                + (buscado != null ? buscado.getNombre() + " " + buscado.getApellido() : "no encontrado"));

        // Prueba de validación
        try {
            e1.setEdad(15);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}