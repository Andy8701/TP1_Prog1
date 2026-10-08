public class EmpleadoPlanta extends Empleado {
    private double sueldoBase;
    private int antiguedadAnios;

    public EmpleadoPlanta(String nombre, String dni, double sueldoBase, int antiguedadAnios) {
        super(nombre, dni);
        this.sueldoBase = sueldoBase;
        this.antiguedadAnios = antiguedadAnios;
    }

    public double getSueldoBase() { return sueldoBase; }
    public void setSueldoBase(double sueldoBase) { this.sueldoBase = sueldoBase; }

    public int getAntiguedadAnios() { return antiguedadAnios; }
    public void setAntiguedadAnios(int antiguedadAnios) { this.antiguedadAnios = antiguedadAnios; }

    @Override
    public double calcularSueldo() {
        return sueldoBase + (sueldoBase * 0.02 * antiguedadAnios);
    }
}