package excercise;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MaquinaExpendedora maquina = new MaquinaExpendedora();
        //Carga inicial de productos y stock
        maquina.agregarProducto("A1", new Chocolate("Carlos V", 15.50), 5);
        maquina.agregarProducto("A2", new Gomita("Panditas", 12.00), 2);
        maquina.agregarProducto("B1", new Chocolate("Snickers", 20.00), 0); 

        //Productos añadidos
        maquina.agregarProducto("C1", new Caramelo("SourCaramels", 17.00), 4);

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        System.out.println("¡Bienvenido a la Máquina de Dulces Virtual!");

        while (!salir) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Ver dulces");
            System.out.println("2. Insertar dinero");
            System.out.println("3. Comprar dulce");
            System.out.println("4. Panel de Administrador");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");
            
            String opcion = scanner.nextLine();

            try {
                switch (opcion) {
                    case "1":
                        maquina.mostrarCatalogo();
                        break;
                    case "2":
                        System.out.print("Ingresa el monto (moneda/billete): $");
                        double monto = Double.parseDouble(scanner.nextLine());
                        maquina.insertarDinero(monto);
                        break;
                    case "3":
                        System.out.print("Ingresa el código del dulce (Ej. A1): ");
                        String codigo = scanner.nextLine();
                        maquina.comprarProducto(codigo);
                        break;
                    case "4":
                        // Solicitar credenciales antes de invocar la lógica
                        System.out.print("Ingrese el usuario Administrador: ");
                        String user = scanner.nextLine();
                        System.out.print("Ingrese la contraseña: ");
                        String pass = scanner.nextLine();
                        
                        // Pasamos las variables capturadas al método
                        maquina.reporteAdministrador(user, pass);
                        break;
                    case "5":
                        salir = true;
                        System.out.println("¡Gracias por comprar!");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingresa un número válido.");
            } catch (SinStockException | FondosInsuficientesException | AccesoDenegadoException | IllegalArgumentException e) {
                // Se agregó AccesoDenegadoException para capturar el error de validación aquí de forma centralizada
                System.out.println("OPERACIÓN CANCELADA: " + e.getMessage());
            }
        }
        scanner.close();
    }
}