package Model;

import java.util.Random;

/**
 * Sistema principal que gestiona las compras y consultas de boletos.
 */
public class Sistema {
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
    public void nuevoComprador(Model.Comprador comprador) {
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
