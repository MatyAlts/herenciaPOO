import java.util.ArrayList;
public class ej3 {
    public static void main(String[] args) {
        CorreoElectronico correo = new CorreoElectronico("Juan Pérez", "Hola, tienes una nueva notificación", "juan.perez@email.com");
        MensajeTexto mensaje = new MensajeTexto("Ana López", "Tienes un mensaje nuevo en tu aplicación", "+123456789");

        Notificaciones sistemaNotificaciones = new Notificaciones();

        sistemaNotificaciones.agregarCanalNotificacion(correo);
        sistemaNotificaciones.agregarCanalNotificacion(mensaje);

        System.out.println("Información de los canales de notificación:");
        sistemaNotificaciones.mostrarInformacionCanales();

        System.out.println("\nEnviando notificaciones:");
        sistemaNotificaciones.enviarNotificaciones();

        System.out.println("\nPersonalizando mensajes:");
        sistemaNotificaciones.personalizarMensajes("¡Nuevo mensaje para todos los usuarios!");

        System.out.println("\nEnviando notificaciones después de la personalización:");
        sistemaNotificaciones.enviarNotificaciones();
    }
}

public abstract class CanalNotificacion {
    protected String usuario;
    protected String mensaje;

    public CanalNotificacion(String usuario, String mensaje) {
        this.usuario = usuario;
        this.mensaje = mensaje;
    }

    public abstract void enviarNotificacion();


    public String getUsuario() {
        return usuario;
    }

    public String getMensaje() {
        return mensaje;
    }
}

public class CorreoElectronico extends CanalNotificacion implements Personalizable {
    private String direccionCorreo;

    public CorreoElectronico(String usuario, String mensaje, String direccionCorreo) {
        super(usuario, mensaje);
        this.direccionCorreo = direccionCorreo;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando correo electrónico:");
        System.out.println("A: " + direccionCorreo);
        System.out.println("Usuario: " + usuario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Correo enviado con éxito.");
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
        System.out.println("Mensaje personalizado para el correo electrónico: " + mensaje);
    }


    public String getDireccionCorreo() {
        return direccionCorreo;
    }
}

public class MensajeTexto extends CanalNotificacion implements Personalizable {
    private String numeroTelefono;

    public MensajeTexto(String usuario, String mensaje, String numeroTelefono) {
        super(usuario, mensaje);
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("Enviando mensaje de texto:");
        System.out.println("A: " + numeroTelefono);
        System.out.println("Usuario: " + usuario);
        System.out.println("Mensaje: " + mensaje);
        System.out.println("Mensaje enviado con éxito.");
    }

    @Override
    public void personalizarMensaje(String nuevoMensaje) {
        this.mensaje = nuevoMensaje;
        System.out.println("Mensaje personalizado para el mensaje de texto: " + mensaje);
    }


    public String getNumeroTelefono() {
        return numeroTelefono;
    }
}

public interface Personalizable {
    void personalizarMensaje(String nuevoMensaje);
}


public class Notificaciones {
    private ArrayList<CanalNotificacion> canalesNotificacion;

    public Notificaciones() {
        canalesNotificacion = new ArrayList<>();
    }

    public void agregarCanalNotificacion(CanalNotificacion canal) {
        canalesNotificacion.add(canal);
    }

    public void enviarNotificaciones() {
        for (CanalNotificacion canal : canalesNotificacion) {
            canal.enviarNotificacion();
        }
    }

    public void personalizarMensajes(String nuevoMensaje) {
        for (CanalNotificacion canal : canalesNotificacion) {
            if (canal instanceof Personalizable) {
                ((Personalizable) canal).personalizarMensaje(nuevoMensaje);
            }
        }
    }

    public void mostrarInformacionCanales() {
        for (CanalNotificacion canal : canalesNotificacion) {
            System.out.println("Canal: " + canal.getClass().getSimpleName());
            System.out.println("Usuario: " + canal.getUsuario());
            System.out.println("Mensaje: " + canal.getMensaje());
            System.out.println();
        }
    }
}
