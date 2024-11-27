import java.util.ArrayList;
public class ej2 {
    public static void main(String[] args) {

        TarjetaCredito tarjetaCredito = new TarjetaCredito("Juan Pérez", "1234567890123456", "12/26", "123");
        PayPal paypal = new PayPal("Maria García", "PP123456", "maria.garcia@example.com");

        Pagos sistemaPagos = new Pagos();

        sistemaPagos.agregarMetodoPago(tarjetaCredito);
        sistemaPagos.agregarMetodoPago(paypal);

        System.out.println("Información de los métodos de pago:");
        sistemaPagos.mostrarInformacionMetodosPago();

        System.out.println("\nRealizando pagos:");
        sistemaPagos.realizarPagos();

        System.out.println("\nCancelando pagos:");
        sistemaPagos.cancelarPagos();
    }
}

public abstract class MetodoPago {
    protected String titular;
    protected String numero;

    public MetodoPago(String titular, String numero) {
        this.titular = titular;
        this.numero = numero;
    }

    public abstract void realizarPago();

    public String getTitular() {
        return titular;
    }

    public String getNumero() {
        return numero;
    }
}

public class TarjetaCredito extends MetodoPago implements Cancelable {
    private String fechaExpiracion;
    private String codigoSeguridad;

    public TarjetaCredito(String titular, String numero, String fechaExpiracion, String codigoSeguridad) {
        super(titular, numero);
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    @Override
    public void realizarPago() {
        System.out.println("Procesando pago con tarjeta de crédito:");
        System.out.println("Titular: " + titular);
        System.out.println("Número de tarjeta: " + numero);
        System.out.println("Fecha de expiración: " + fechaExpiracion);
        System.out.println("Pago realizado con éxito.");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con tarjeta de crédito cancelado.");
    }


    public String getFechaExpiracion() {
        return fechaExpiracion;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }
}

public class PayPal extends MetodoPago implements Cancelable {
    private String correoElectronico;

    public PayPal(String titular, String numero, String correoElectronico) {
        super(titular, numero);
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void realizarPago() {
        System.out.println("Procesando pago con PayPal:");
        System.out.println("Titular: " + titular);
        System.out.println("Correo electrónico asociado: " + correoElectronico);
        System.out.println("Pago realizado con éxito.");
    }

    @Override
    public void cancelarPago() {
        System.out.println("Pago con PayPal cancelado.");
    }


    public String getCorreoElectronico() {
        return correoElectronico;
    }
}

public interface Cancelable {
    void cancelarPago();
}



public class Pagos {
    private ArrayList<MetodoPago> metodosPago;

    public Pagos() {
        metodosPago = new ArrayList<>();
    }

    public void agregarMetodoPago(MetodoPago metodoPago) {
        metodosPago.add(metodoPago);
    }

    public void realizarPagos() {
        for (MetodoPago metodoPago : metodosPago) {
            metodoPago.realizarPago();
        }
    }

    public void cancelarPagos() {
        for (MetodoPago metodoPago : metodosPago) {
            if (metodoPago instanceof Cancelable) {
                ((Cancelable) metodoPago).cancelarPago();
            }
        }
    }

    public void mostrarInformacionMetodosPago() {
        for (MetodoPago metodoPago : metodosPago) {
            System.out.println("Método de pago: " + metodoPago.getClass().getSimpleName());
            System.out.println("Titular: " + metodoPago.getTitular());
            System.out.println("Número: " + metodoPago.getNumero());
            System.out.println();
        }
    }
}

