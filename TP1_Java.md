# Trabajo Práctico 1 – Programación Orientada a Objetos en Java

**Alumno:** Saires Andrés
**Temas:** encapsulamiento, relaciones entre clases, herencia, clases abstractas, polimorfismo

---

## Ejercicio 1: Encapsulamiento y relaciones entre clases

### Idea general

- Todos los atributos pasan a ser `private` y se acceden mediante getters y setters.
- Las validaciones viven en los setters, y los constructores usan esos setters para que no se puedan saltar.
- `Estudiante` tiene una lista de `Materia` (relación uno a muchos).
- `Carrera` y `Universidad` tienen una lista de `Estudiante`.

### Materia.java

```java
public class Materia {
    private String nombre;
    private String codigo;
    private int creditos;
    private double calificacion;

    public Materia(String nombre, String codigo, int creditos, double calificacion) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        setCalificacion(calificacion); // pasa por la validación
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    public double getCalificacion() { return calificacion; }
    public void setCalificacion(double calificacion) {
        if (calificacion < 0 || calificacion > 10) {
            throw new IllegalArgumentException("La calificación debe estar entre 0 y 10");
        }
        this.calificacion = calificacion;
    }
}
```

### Estudiante.java

```java
import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private String nombre;
    private String apellido;
    private int edad;
    private String carrera;
    private double promedio;
    private List<Materia> materias = new ArrayList<>();

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, int edad, String carrera, double promedio) {
        // Se usan los setters para que las validaciones también se apliquen acá
        setNombre(nombre);
        setApellido(apellido);
        setEdad(edad);
        this.carrera = carrera;
        setPromedio(promedio);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        this.apellido = apellido;
    }

    public int getEdad() { return edad; }
    public void setEdad(int edad) {
        if (edad <= 16) {
            throw new IllegalArgumentException("La edad debe ser mayor a 16 años");
        }
        this.edad = edad;
    }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public double getPromedio() { return promedio; }
    public void setPromedio(double promedio) {
        if (promedio < 0 || promedio > 10) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 10");
        }
        this.promedio = promedio;
    }

    public List<Materia> getMaterias() { return materias; }

    public void agregarMateria(Materia materia) {
        materias.add(materia);
    }

    public double calcularPromedio() {
        if (materias.isEmpty()) {
            return 0;
        }
        double suma = 0;
        for (Materia m : materias) {
            suma += m.getCalificacion();
        }
        return suma / materias.size();
    }
}
```

### Carrera.java

```java
import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String nombre;
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void listarEstudiantes() {
        for (Estudiante e : estudiantes) {
            System.out.println(e.getNombre() + " " + e.getApellido()
                    + " - Edad: " + e.getEdad());
        }
    }

    public Estudiante buscarEstudiante(String nombre) {
        for (Estudiante e : estudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null; // no encontrado
    }
}
```

### Universidad.java

```java
import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private String nombre;
    private String direccion;
    private List<Estudiante> estudiantes = new ArrayList<>();

    public Universidad(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Estudiante> getEstudiantes() { return estudiantes; }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }
}
```

### App.java

```java
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

        System.out.println("Promedios:");
        System.out.println(e1.getNombre() + ": " + e1.calcularPromedio());
        System.out.println(e2.getNombre() + ": " + e2.calcularPromedio());

        System.out.println("\nEstudiantes de " + carrera.getNombre() + ":");
        carrera.listarEstudiantes();

        Estudiante buscado = carrera.buscarEstudiante("martín");
        System.out.println("\nBúsqueda: "
                + (buscado != null ? buscado.getNombre() + " " + buscado.getApellido() : "no encontrado"));

        try {
            e1.setEdad(15);
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
```

### Salida

```
Promedios:
Lucía: 8.5
Martín: 6.5

Estudiantes de Ingeniería en Sistemas:
Lucía Gómez - Edad: 20
Martín Pérez - Edad: 22

Búsqueda: Martín Pérez
Error: La edad debe ser mayor a 16 años
```

### Conceptos aplicados

- **Encapsulamiento:** los atributos son privados y la validación está centralizada en los setters.
- **Constructor con setters:** si se asignara `this.edad = edad` directamente, se podría crear un estudiante de 10 años saltándose la validación.
- **`promedio` vs `calcularPromedio()`:** el atributo guarda un valor asignado a mano (con validación); el método lo calcula a partir de las materias.

---

## Ejercicio 2: Herencia y constructores

### Persona.java

```java
public class Persona {
    private String nombre;
    private String dni;

    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
}
```

### Alumno.java

```java
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
        System.out.println("Nombre: " + getNombre());
        System.out.println("DNI: " + getDni());
        System.out.println("Legajo: " + legajo);
        System.out.println("Promedio: " + promedio);
    }
}
```

### App.java

```java
public class App {
    public static void main(String[] args) {
        Alumno a1 = new Alumno("Lucía Gómez", "40123456", "L-1001", 8.5);
        Alumno a2 = new Alumno("Martín Pérez", "38987654", "L-1002", 7.2);

        a1.mostrarDatos();
        System.out.println();
        a2.mostrarDatos();
    }
}
```

### Salida

```
Nombre: Lucía Gómez
DNI: 40123456
Legajo: L-1001
Promedio: 8.5

Nombre: Martín Pérez
DNI: 38987654
Legajo: L-1002
Promedio: 7.2
```

### Conceptos aplicados

- **`extends`:** `Alumno` es una `Persona`; hereda sus atributos y métodos públicos y agrega `legajo` y `promedio`.
- **`super(...)`:** debe ser la primera línea del constructor de la subclase y llama al constructor de la clase padre.
- **Atributos privados y herencia:** `Alumno` no accede directamente a `nombre` ni a `dni`; usa `getNombre()` y `getDni()`.

---

## Ejercicio 3: Clases abstractas y sobrescritura

### Empleado.java

```java
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

    public abstract double calcularSueldo();
}
```

### EmpleadoPlanta.java

```java
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
```

### EmpleadoContratado.java

```java
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
```

### App.java

```java
public class App {
    public static void main(String[] args) {
        EmpleadoPlanta planta = new EmpleadoPlanta("Lucía Gómez", "40123456", 500000, 5);
        EmpleadoContratado contratado = new EmpleadoContratado("Martín Pérez", "38987654", 120, 3500);

        System.out.println(planta.getNombre() + " (planta): $" + planta.calcularSueldo());
        System.out.println(contratado.getNombre() + " (contratado): $" + contratado.calcularSueldo());

        // new Empleado("X", "1"); // ERROR de compilación: una clase abstracta no se puede instanciar
    }
}
```

### Salida

```
Lucía Gómez (planta): $550000.0
Martín Pérez (contratado): $420000.0
```

Cálculos: planta = 500000 + (500000 × 0.02 × 5) = 550000; contratado = 120 × 3500 = 420000.

### Conceptos aplicados

- **Clase abstracta:** no se puede instanciar; sirve como molde común para las subclases.
- **Método abstracto:** `calcularSueldo()` no tiene cuerpo en la base y toda subclase concreta debe implementarlo.
- **`@Override`:** indica sobrescritura; si la firma no coincide, el compilador avisa.

---

## Ejercicio 4: Polimorfismo

Se reutiliza la jerarquía del ejercicio 3 sin modificarla.

### App.java

```java
public class App {
    public static void main(String[] args) {
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

        // Análisis: clase real de cada objeto
        System.out.println("\nAnálisis de tipos:");
        for (Empleado e : empleados) {
            System.out.println(e.getNombre() + " -> " + e.getClass().getSimpleName());
        }
    }
}
```

### Salida

```
Lucía Gómez - Sueldo: $550000.0
Martín Pérez - Sueldo: $420000.0
Sofía Ramírez - Sueldo: $780000.0
Julián Díaz - Sueldo: $400000.0

Análisis de tipos:
Lucía Gómez -> EmpleadoPlanta
Martín Pérez -> EmpleadoContratado
Sofía Ramírez -> EmpleadoPlanta
Julián Díaz -> EmpleadoContratado
```

### Análisis: qué implementación ejecuta Java

Para verificarlo, se agrega temporalmente un `println` dentro de cada `calcularSueldo()`:

```java
// En EmpleadoPlanta
System.out.println("Ejecutando calcularSueldo() de EmpleadoPlanta");

// En EmpleadoContratado
System.out.println("Ejecutando calcularSueldo() de EmpleadoContratado");
```

Aunque la variable `e` es de tipo `Empleado`, Java ejecuta en cada caso la versión de la clase real del objeto.

### Conceptos aplicados

- **Polimorfismo:** una referencia de tipo `Empleado` puede apuntar a cualquier subclase.
- **Enlace dinámico:** el tipo de la variable decide qué métodos se pueden llamar; el tipo del objeto real decide cuál implementación se ejecuta, en tiempo de ejecución.
- **Extensibilidad:** agregar un nuevo tipo de empleado no obliga a modificar el bucle.
- **Límite de la referencia:** con una variable `Empleado` no se puede llamar a métodos propios de una subclase, como `getValorHora()`.

---

## Ejercicio 5: Integración de conceptos – Alquiler de vehículos

Las tarifas y reglas de cálculo son definidas por mí, ya que el enunciado no las fija.

### Vehiculo.java

```java
public abstract class Vehiculo {
    private String marca;
    private String modelo;
    private double tarifaDiaria;

    public Vehiculo(String marca, String modelo, double tarifaDiaria) {
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
    }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public double getTarifaDiaria() { return tarifaDiaria; }
    public void setTarifaDiaria(double tarifaDiaria) { this.tarifaDiaria = tarifaDiaria; }

    public abstract double calcularAlquiler(int dias);
}
```

### Auto.java

```java
public class Auto extends Vehiculo {

    public Auto(String marca, String modelo, double tarifaDiaria) {
        super(marca, modelo, tarifaDiaria);
    }

    @Override
    public double calcularAlquiler(int dias) {
        return getTarifaDiaria() * dias;
    }
}
```

### Motocicleta.java

```java
public class Motocicleta extends Vehiculo {

    public Motocicleta(String marca, String modelo, double tarifaDiaria) {
        super(marca, modelo, tarifaDiaria);
    }

    @Override
    public double calcularAlquiler(int dias) {
        // 10% de descuento sobre la tarifa total
        return getTarifaDiaria() * dias * 0.9;
    }
}
```

### AutoElectrico.java

```java
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
```

### Camioneta.java (ampliación)

```java
public class Camioneta extends Vehiculo {
    private double seguroDiario;

    public Camioneta(String marca, String modelo, double tarifaDiaria, double seguroDiario) {
        super(marca, modelo, tarifaDiaria);
        this.seguroDiario = seguroDiario;
    }

    public double getSeguroDiario() { return seguroDiario; }
    public void setSeguroDiario(double seguroDiario) { this.seguroDiario = seguroDiario; }

    @Override
    public double calcularAlquiler(int dias) {
        // Tarifa más un seguro adicional por cada día
        return (getTarifaDiaria() + seguroDiario) * dias;
    }
}
```

### Main.java

```java
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
```

### Salida (ingresando 3 días)

```
Ingrese la cantidad de días de alquiler: 3

Costo del alquiler por 3 días:
Toyota Corolla: $120000.0
Honda CB 250: $54000.0
Tesla Model 3: $195000.0
Toyota Hilux: $180000.0
```

Cálculos: Auto = 40000 × 3 = 120000; Moto = 20000 × 3 × 0.9 = 54000; Eléctrico = 60000 × 3 + 15000 = 195000; Camioneta = (55000 + 5000) × 3 = 180000.

### Conceptos aplicados

- **Encapsulamiento:** atributos privados con getters y setters; las subclases usan `getTarifaDiaria()`.
- **Herencia y `super(...)`:** los atributos comunes están una sola vez en `Vehiculo`.
- **Método abstracto:** `calcularAlquiler(int dias)` obliga a cada tipo de vehículo a definir su cálculo.
- **Polimorfismo sin `if` ni `switch`:** el bucle llama a `v.calcularAlquiler(dias)` y Java elige la implementación según la clase real.
- **Ampliación:** agregar `Camioneta` solo requirió crear la clase y sumarla al arreglo, sin modificar el bucle.
