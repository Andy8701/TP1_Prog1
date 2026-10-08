import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Vehiculo[] vehiculos = {
            new Auto("Toyota", "Corolla", 40000),
            new Motocicleta("Honda", "CB 250", 20000),
            new AutoElectrico("Tesla", "Model 3", 60000, 15000),
            new Camioneta("Toyota", "Hilux", 55000, 5000)
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese la cantidad de días de alquiler: ");
        int dias = sc.nextInt();

        System.out.println("\nCosto del alquiler por " + dias + " días:");
        for (Vehiculo v : vehiculos) {
            System.out.println(v.getMarca() + " " + v.getModelo() + ": $" + v.calcularAlquiler(dias));
        }

        sc.close();
    }
}