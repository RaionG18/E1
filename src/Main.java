/*
* POO - EJERCICIO 1
*
* EMILIO GORDILLO - 18062
*
 */

import java.util.Random;
import java.util.Scanner;

/**
 * Representa un comprador en el sistema.
 */
class Comprador {
    private String nombre;
    private String dpi;
    private int cantidadBoletos;
    private double presupuesto;
    private int ticket;

    /**
     * Constructor de un comprador.
     *
     * @param nombre Nombre del comprador.
     * @param dpi Documento de identificación del comprador. Debe tener 13 dígitos y solo números.
     * @param cantidadBoletos Cantidad de boletos que desea comprar.
     * @param presupuesto Presupuesto del comprador.
     */
    public Comprador(String nombre, String dpi, int cantidadBoletos, double presupuesto) {
        if (!esDPIValido(dpi)) {
            throw new IllegalArgumentException("DPI no válido. Debe contener 13 dígitos y solo números.");
        }
        this.nombre = nombre;
        this.dpi = dpi;
        this.cantidadBoletos = cantidadBoletos;
        this.presupuesto = presupuesto;
        this.ticket = TicketUtils.generarTicket();
    }

    private boolean esDPIValido(String dpi) {
        return dpi.matches("\\d{13}");
    }

    /**
     * Obtiene la cantidad de boletos que el comprador desea comprar.
     *
     * @return Cantidad de boletos.
     */
    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    /**
     * Obtiene el presupuesto del comprador.
     *
     * @return Presupuesto del comprador.
     */
    public double getPresupuesto() {
        return presupuesto;
    }

    /**
     * Verifica si el ticket del comprador es válido.
     *
     * @return true si es válido, false en caso contrario.
     */
    public boolean tieneTicketValido() {
        return TicketUtils.validarTicket(ticket);
    }
}

/**
 * Clase de utilidades relacionadas con la generación y validación de tickets.
 */
class TicketUtils {
    private static final Random rand = new Random();

    /**
     * Genera un ticket aleatorio.
     *
     * @return Número de ticket.
     */
    public static int generarTicket() {
        return rand.nextInt(28000) + 1;
    }

    /**
     * Valida un ticket.
     *
     * @param ticket Ticket a validar.
     * @return true si es válido, false en caso contrario.
     */
    public static boolean validarTicket(int ticket) {
        int a = rand.nextInt(15000) + 1;
        int b = rand.nextInt(15000) + 1;
        return (ticket + a + b) % 2 != 0;
    }
}

/**
 * Representa una localidad con un tipo, precio y cantidad de boletos disponibles.
 */
class Localidad {
    public static final int TIPO_1 = 1;
    public static final int TIPO_5 = 5;
    public static final int TIPO_10 = 10;

    private static final double PRECIO_TIPO_1 = 300;
    private static final double PRECIO_TIPO_5 = 565;
    private static final double PRECIO_TIPO_10 = 1495;

    private int tipo;
    private double precio;
    private int boletosDisponibles = 20;

    /**
     * Constructor de una localidad.
     *
     * @param tipo Tipo de localidad (TIPO_1, TIPO_5, TIPO_10).
     */
    public Localidad(int tipo) {
        this.tipo = tipo;
        switch (tipo) {
            case TIPO_1:
                this.precio = PRECIO_TIPO_1;
                break;
            case TIPO_5:
                this.precio = PRECIO_TIPO_5;
                break;
            case TIPO_10:
                this.precio = PRECIO_TIPO_10;
                break;
        }
    }

    /**
     * Verifica si hay boletos disponibles en la localidad.
     *
     * @return true si hay espacio, false en caso contrario.
     */
    public boolean validarEspacio() {
        return boletosDisponibles > 0;
    }

    /**
     * Verifica si la cantidad de boletos solicitada está disponible.
     *
     * @param cantidadBoletos Cantidad de boletos solicitados.
     * @return true si hay suficientes boletos, false en caso contrario.
     */
    public boolean validarDisponibilidadBoletos(int cantidadBoletos) {
        return boletosDisponibles >= cantidadBoletos;
    }

    /**
     * Verifica si el presupuesto cubre el costo total de los boletos solicitados.
     *
     * @param totalPresupuesto Presupuesto total.
     * @param cantidadBoletos Cantidad de boletos solicitados.
     * @return true si el presupuesto es suficiente, false en caso contrario.
     */
    public boolean validarPrecio(double totalPresupuesto, int cantidadBoletos) {
        return (precio * cantidadBoletos) <= totalPresupuesto;
    }

    /**
     * Vende una cantidad específica de boletos, reduciendo los boletos disponibles.
     *
     * @param cantidad Cantidad de boletos a vender.
     */
    public void venderBoleto(int cantidad) {
        boletosDisponibles -= cantidad;
    }

    /**
     * Obtiene la cantidad de boletos disponibles en la localidad.
     *
     * @return Cantidad de boletos disponibles.
     */
    public int getBoletosDisponibles() {
        return boletosDisponibles;
    }

    /**
     * Obtiene el precio de un boleto en la localidad.
     *
     * @return Precio del boleto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Obtiene el tipo de la localidad.
     *
     * @return Tipo de localidad.
     */
    public int getTipo() {
        return tipo;
    }
}

/**
 * Sistema principal que gestiona las compras y consultas de boletos.
 */
class Sistema {
    private Localidad[] localidades = {
            new Localidad(Localidad.TIPO_1),
            new Localidad(Localidad.TIPO_5),
            new Localidad(Localidad.TIPO_10)
    };
    private double totalVendido = 0;

    /**
     * Procesa la compra de boletos para un comprador.
     *
     * @param comprador Comprador que desea adquirir boletos.
     */
    public void nuevoComprador(Comprador comprador) {
        if (comprador.tieneTicketValido()) {
            Random rand = new Random();
            Localidad localidadSeleccionada = localidades[rand.nextInt(3)];

            if (localidadSeleccionada.validarEspacio() &&
                    localidadSeleccionada.validarDisponibilidadBoletos(comprador.getCantidadBoletos()) &&
                    localidadSeleccionada.validarPrecio(comprador.getPresupuesto(), comprador.getCantidadBoletos())) {

                localidadSeleccionada.venderBoleto(comprador.getCantidadBoletos());
                double totalCompra = localidadSeleccionada.getPrecio() * comprador.getCantidadBoletos();
                totalVendido += totalCompra;
                System.out.println("¡Boletos vendidos exitosamente en localidad " + localidadSeleccionada.getTipo() + "!");
                System.out.println("Cantidad de boletos: " + comprador.getCantidadBoletos());
                System.out.println("Total: $" + totalCompra);
            } else {
                System.out.println("No se pudo completar la venta. Verifique disponibilidad o presupuesto.");
            }
        } else {
            System.out.println("Ticket no válido.");
        }
    }

    /**
     * Consulta y muestra la disponibilidad de boletos en todas las localidades.
     */
    public void consultarDisponibilidadTotal() {
        for (Localidad localidad : localidades) {
            System.out.println("Localidad " + localidad.getPrecio() + ": " + localidad.getBoletosDisponibles() + " boletos disponibles.");
        }
    }

    /**
     * Consulta y muestra la disponibilidad de boletos en una localidad específica.
     *
     * @param tipo Tipo de localidad a consultar.
     */
    public void consultarDisponibilidadIndividual(int tipo) {
        for (Localidad localidad : localidades) {
            if (localidad.getTipo() == tipo) {
                System.out.println("Localidad " + localidad.getPrecio() + ": " + localidad.getBoletosDisponibles() + " boletos disponibles.");
                return;
            }
        }
        System.out.println("Tipo de localidad incorrecto.");
    }

    /**
     * Muestra el total de dinero recaudado por la venta de boletos.
     */
    public void reporteCaja() {
        System.out.println("Total vendido: $" + totalVendido);
    }
}

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
