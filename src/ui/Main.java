/*
* POO - EJERCICIO 1
*
* EMILIO GORDILLO - 18062
* EDUARDO RIVERA - 23281
 */

package ui;

import Model.Comprador;
import Model.Sistema;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = new Sistema();

        while (true) {
            displayMainMenu();
            int opcion = getIntegerInput(scanner);

            switch (opcion) {
                case 1:
                    Comprador comprador = getCompradorInfo(scanner);
                    sistema.nuevoComprador(comprador);
                    break;
                case 2:
                    sistema.consultarDisponibilidadTotal();
                    break;
                case 3:
                    System.out.print("Ingrese el tipo de localidad (1, 5 o 10): ");
                    int tipo = getIntegerInput(scanner);
                    sistema.consultarDisponibilidadIndividual(tipo);
                    break;
                case 4:
                    sistema.reporteCaja();
                    break;
                case 5:
                    System.out.println("¡Hasta pronto!");
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n----- MENU PRINCIPAL -----");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Nuevo comprador");
        System.out.println("2. Consultar disponibilidad total");
        System.out.println("3. Consultar disponibilidad individual");
        System.out.println("4. Reporte de caja");
        System.out.println("5. Salir");
        System.out.print("Opción: ");
    }

    private static int getIntegerInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static Comprador getCompradorInfo(Scanner scanner) {
        System.out.println("\n--- Nuevo Comprador ---");
        System.out.print("Ingrese nombre: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        String dpi;
        do {
            System.out.print("Ingrese DPI (13 dígitos): ");
            dpi = scanner.nextLine();
        } while (!dpi.matches("\\d{13}"));
        System.out.print("Ingrese cantidad de boletos: ");
        int cantidadBoletos = getIntegerInput(scanner);
        System.out.print("Ingrese presupuesto: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor, ingrese un monto válido.");
            scanner.next();
        }
        double presupuesto = scanner.nextDouble();
        return new Comprador(nombre, dpi, cantidadBoletos, presupuesto);
    }
}
