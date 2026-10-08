public class Alumno extends Persona {
    private String legajo;
    private double promedio;

    public Alumno(String nombre, String dni, String legajo, double promedio) {
        super(nombre, dni); // inicializa los atributos heredados
        this.legajo = legajo;
        this.promedio = promedio;
    }

    public String getLegajo() { return legajo; }
    public void setLegajo(String legajo) { this.legajo = legajo; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) { this.promedio = promedio; }

    public void mostrarDatos() {
        // nombre y dni son privados en Persona: se acceden con los getters heredados
        System.out.println("Nombre: " + getNombre());
        System.out.println("DNI: " + getDni());
        System.out.println("Legajo: " + legajo);
        System.out.println("Promedio: " + promedio);
    }
}