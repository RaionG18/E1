package Model;

import utils.TicketUtils;

/**
 * Representa un comprador en el sistema.
 */
public class Comprador {
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
