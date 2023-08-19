package Model;

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
