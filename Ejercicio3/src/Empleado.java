public abstract class Empleado {
    private String nombre;
    private String dni;

    public Empleado(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    // Cada subclase está obligada a implementarlo
    public abstract double calcularSueldo();
}