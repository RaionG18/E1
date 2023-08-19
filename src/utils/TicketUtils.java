package utils;

import java.util.Random;

/**
 * Clase de utilidades relacionadas con la generación y validación de tickets.
 */
public class TicketUtils {
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
